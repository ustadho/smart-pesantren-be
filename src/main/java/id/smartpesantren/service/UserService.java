package id.smartpesantren.service;


import id.smartpesantren.config.Constants;
import id.smartpesantren.entity.*;
import id.smartpesantren.repository.FoundationRepository;
import id.smartpesantren.repository.AuthorityRepository;
import id.smartpesantren.repository.InstitutionRepository;
import id.smartpesantren.repository.UserRepository;
import id.smartpesantren.security.AuthoritiesConstants;
import id.smartpesantren.security.SecurityUtils;
import id.smartpesantren.service.dto.UserDTO;
import id.smartpesantren.service.util.RandomUtil;

import id.smartpesantren.web.rest.errors.EmailAlreadyUsedException;
import id.smartpesantren.web.rest.errors.InvalidPasswordException;
import id.smartpesantren.web.rest.errors.LoginAlreadyUsedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service class for managing users.
 */
@Service
@Transactional
public class UserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthorityRepository authorityRepository;

    private final FoundationRepository foundationRepository;
    private final InstitutionRepository institutionRepository;

    private final CacheManager cacheManager;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthorityRepository authorityRepository,
                       FoundationRepository foundationRepository, InstitutionRepository institutionRepository, CacheManager cacheManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityRepository = authorityRepository;
        this.foundationRepository = foundationRepository;
        this.institutionRepository = institutionRepository;
        this.cacheManager = cacheManager;
    }

    public Optional<User> activateRegistration(String key) {
        log.debug("Activating user for activation key {}", key);
        return userRepository.findOneByActivationKey(key)
                .map(user -> {
                    // activate given user for the registration key.
                    user.setActivated(true);
                    user.setActivationKey(null);
                    this.clearUserCaches(user);
                    log.debug("Activated user: {}", user);
                    return user;
                });
    }

    public Optional<User> completePasswordReset(String newPassword, String key) {
        log.debug("Reset user password for reset key {}", key);
        return userRepository.findOneByResetKey(key)
                .filter(user -> user.getResetDate().isAfter(Instant.now().minusSeconds(86400)))
                .map(user -> {
                    user.setPassword(passwordEncoder.encode(newPassword));
                    user.setResetKey(null);
                    user.setResetDate(null);
                    this.clearUserCaches(user);
                    return user;
                });
    }

    public Optional<User> requestPasswordReset(String mail) {
        return userRepository.findOneByEmailIgnoreCase(mail)
                .filter(User::getActivated)
                .map(user -> {
                    user.setResetKey(RandomUtil.generateResetKey());
                    user.setResetDate(Instant.now());
                    this.clearUserCaches(user);
                    return user;
                });
    }

    public User registerUser(UserDTO userDTO, String password) {
        userRepository.findOneByLogin(userDTO.getLogin().toLowerCase()).ifPresent(existingUser -> {
            boolean removed = removeNonActivatedUser(existingUser);
            if (!removed) {
                throw new LoginAlreadyUsedException();
            }
        });
        userRepository.findOneByEmailIgnoreCase(userDTO.getEmail()).ifPresent(existingUser -> {
            boolean removed = removeNonActivatedUser(existingUser);
            if (!removed) {
                throw new EmailAlreadyUsedException();
            }
        });
        User newUser = new User();
        String encryptedPassword = passwordEncoder.encode(password);
        newUser.setLogin(userDTO.getLogin().toLowerCase());
        // new user gets initially a generated password
        newUser.setPassword(encryptedPassword);
        newUser.setFirstName(userDTO.getFirstName());
        newUser.setLastName(userDTO.getLastName());
        newUser.setEmail(userDTO.getEmail().toLowerCase());
        newUser.setImageUrl(userDTO.getImageUrl());
        newUser.setLangKey(userDTO.getLangKey());
        // new user is not active
        newUser.setActivated(false);
        // new user gets registration key
        newUser.setActivationKey(RandomUtil.generateActivationKey());
        Set<Authority> authorities = new HashSet<>();
        authorityRepository.findById(AuthoritiesConstants.SUPERADMIN).ifPresent(authorities::add);
        newUser.setAuthorities(authorities);
        userRepository.save(newUser);
        this.clearUserCaches(newUser);
        log.debug("Created Information for User: {}", newUser);
        return newUser;
    }

    private boolean removeNonActivatedUser(User existingUser){
        if (existingUser.getActivated()) {
            return false;
        }
        userRepository.delete(existingUser);
        userRepository.flush();
        this.clearUserCaches(existingUser);
        return true;
    }

    public User createUser(UserDTO userDTO) {
        String cid = SecurityUtils.getFoundationId().get();
        User user = new User();
        user.setProfile(new UserProfile(userDTO.getProfile()));
        user.setFoundation(new Foundation(SecurityUtils.getFoundationId().get()));
        user.setLogin(userDTO.getLogin().toLowerCase());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail().toLowerCase());
        user.setImageUrl(userDTO.getImageUrl());
        if (userDTO.getLangKey() == null) {
            user.setLangKey(Constants.DEFAULT_LANGUAGE); // default language
        } else {
            user.setLangKey(userDTO.getLangKey());
        }
//        String encryptedPassword = passwordEncoder.encode(RandomUtil.generatePassword());
        String encryptedPassword = passwordEncoder.encode("12345678");
        user.setPassword(encryptedPassword);
        user.setResetKey(RandomUtil.generateResetKey());
        user.setResetDate(Instant.now());
        user.setActivated(true);
        Set<Authority> managedAuthorities = user.getAuthorities();
        userDTO.getAuthorities().stream().forEach(s -> {
            managedAuthorities.add(new Authority(s));
        });
        user.setAuthorities(managedAuthorities);

        Set<Institution> managedInstitutions = user.getInstitutions();
        userDTO.getInstitutions().stream().forEach(i -> {
            managedInstitutions.add(new Institution(i));
        });
        user.setInstitutions(managedInstitutions);

        user.setPerson(userDTO.getPersonId() == null? null: new PersonData(userDTO.getPersonId()));
        userRepository.save(user);
        this.clearUserCaches(user);
        log.debug("Created Information for User: {}", user);
        return user;
    }

    /**
     * Update basic information (first name, last name, email, language) for the current user.
     *
     * @param firstName first name of user
     * @param lastName last name of user
     * @param email email id of user
     * @param langKey language key
     * @param imageUrl image URL of user
     */
    public void updateUser(String firstName, String lastName, String email, String langKey, String imageUrl) {
        SecurityUtils.getCurrentUserLogin()
                .flatMap(userRepository::findOneByLogin)
                .ifPresent(user -> {
                    user.setFirstName(firstName);
                    user.setLastName(lastName);
                    user.setEmail(email.toLowerCase());
                    user.setLangKey(langKey);
                    user.setImageUrl(imageUrl);
                    this.clearUserCaches(user);
                    log.debug("Changed Information for User: {}", user);
                });
    }

    /**
     * Update all information for a specific user, and return the modified user.
     *
     * @param userDTO user to update
     * @return updated user
     */
    public Optional<UserDTO> updateUser(UserDTO userDTO) {
        return Optional.of(userRepository
                .findById(userDTO.getId()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(user -> {
                    this.clearUserCaches(user);
                    user.setProfile(new UserProfile(userDTO.getProfile()));
                    user.setLogin(userDTO.getLogin().toLowerCase());
                    user.setFirstName(userDTO.getFirstName());
                    user.setLastName(userDTO.getLastName());
                    user.setEmail(userDTO.getEmail().toLowerCase());
                    user.setImageUrl(userDTO.getImageUrl());
                    user.setActivated(userDTO.isActivated());
                    user.setLangKey(userDTO.getLangKey());
                    user.setProfile(user.getProfile());
                    user.setPerson(userDTO.getPersonId() == null? null: new PersonData(userDTO.getPersonId()));
                    Set<Authority> managedAuthorities = user.getAuthorities();
                    managedAuthorities.clear();
                    userDTO.getAuthorities().stream()
                            .map(authorityRepository::findById)
                            .filter(Optional::isPresent)
                            .map(Optional::get)
                            .forEach(managedAuthorities::add);

                    Set<Institution> managedInstitutions = user.getInstitutions();
                    managedInstitutions.clear();
                    userDTO.getInstitutions().stream()
                            .map(institutionRepository::findById)
                            .filter(Optional::isPresent)
                            .map(Optional::get)
                            .forEach(managedInstitutions::add);

                    this.clearUserCaches(user);
                    log.debug("Changed Information for User: {}", user);
                    return user;
                })
                .map(UserDTO::new);
    }

    public void deleteUser(String login) {
        userRepository.findOneByLogin(login).ifPresent(user -> {
            userRepository.delete(user);
            this.clearUserCaches(user);
            log.debug("Deleted User: {}", user);
        });
    }

    public void changePassword(String currentClearTextPassword, String newPassword) {
        SecurityUtils.getCurrentUserLogin()
                .flatMap(userRepository::findOneByLogin)
                .ifPresent(user -> {
                    String currentEncryptedPassword = user.getPassword();
                    if (!passwordEncoder.matches(currentClearTextPassword, currentEncryptedPassword)) {
                        throw new InvalidPasswordException();
                    }
                    String encryptedPassword = passwordEncoder.encode(newPassword);
                    user.setPassword(encryptedPassword);
                    this.clearUserCaches(user);
                    log.debug("Changed password for User: {}", user);
                });
    }

    public void resetDefaultPassword(String id) {
        userRepository.findById(id).ifPresent(user -> {
            String encryptedPassword = passwordEncoder.encode("12345678");
            user.setPassword(encryptedPassword);
            this.clearUserCaches(user);
            log.debug("Reset default password User: {}", user);
        });
    }

    @Transactional(readOnly = true)
    public Page<UserDTO> getAllManagedUsers(Pageable pageable, Integer profile, String q) {
        return userRepository.findAllByLoginNot(pageable, Constants.ANONYMOUS_USER, profile,"%"+q+"%").map(UserDTO::new);
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthoritiesByLogin(String login) {
        return userRepository.findOneWithAuthoritiesAndWithInstitutionsByLogin(login)
                .map(user -> {
                    user.getAuthorities().stream().filter(authority -> {
//                        if(c.getCompanyType()==Constants.COMPANY_TYPE_POS) {
//                            return authority.getEnableInPos() == true;
//                        } else if(c.getCompanyType()==Constants.COMPANY_TYPE_ACADEMIC) {
//                            return authority.getEnableInAcademic() == true;
//                        } else if(c.getCompanyType()==Constants.COMPANY_TYPE_PRINTING) {
//                            return authority.getEnableInPrinting() == true;
//                        }
                        return true;
                    });

//                    user.getInstitutions().stream().map(i -> {
//                        System.out.println("Institusi: "+ i.getName());
//                        return true;
//                    });
                    return user;
                });
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthorities(Long id) {
        return userRepository.findOneWithAuthoritiesById(id);
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthorities() {
        Optional<User> user = userRepository.findOneWithAuthoritiesAndWithInstitutionsByLogin(SecurityUtils.getCurrentUserLogin().get());
        return user;
    }

    /**
     * Not activated users should be automatically deleted after 3 days.
     * <p>
     * This is scheduled to get fired everyday, at 01:00 (am).
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void removeNotActivatedUsers() {
        userRepository
                .findAllByActivatedIsFalseAndCreatedDateBefore(Instant.now().minus(3, ChronoUnit.DAYS))
                .forEach(user -> {
                    log.debug("Deleting not activated user {}", user.getLogin());
                    userRepository.delete(user);
                    this.clearUserCaches(user);
                });
    }

    /**
     * @return a list of all the authorities
     */
    public List<String> getAuthorities() {
        Optional<Foundation> c = foundationRepository.findCurrentFoundation();
        if(c.isPresent()) {
            return authorityRepository.findAll().stream()
                    .filter(a -> {
//                        if(c.get().getCompanyType()==Constants.COMPANY_TYPE_POS) {
//                            return a.getEnableInPos() == true;
//                        } else if(c.get().getCompanyType()==Constants.COMPANY_TYPE_ACADEMIC) {
//                            return a.getEnableInAcademic() == true;
//                        } else if(c.get().getCompanyType()==Constants.COMPANY_TYPE_PRINTING) {
//                            return a.getEnableInPrinting() == true;
//                        }
                        return true;
                    })
                    .map(Authority::getName).collect(Collectors.toList());
        }
        return null;
    }

    private void clearUserCaches(User user) {
        Objects.requireNonNull(cacheManager.getCache(UserRepository.USERS_BY_LOGIN_CACHE)).evict(user.getLogin());
        Objects.requireNonNull(cacheManager.getCache(UserRepository.USERS_BY_EMAIL_CACHE)).evict(user.getEmail());
    }
}
