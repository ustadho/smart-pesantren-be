package id.smartpesantren.service.dto;

import id.smartpesantren.config.Constants;
import id.smartpesantren.dto.PersonSimpleDTO;
import id.smartpesantren.entity.Authority;
import id.smartpesantren.entity.Institution;
import id.smartpesantren.entity.User;
import id.smartpesantren.entity.UserProfile;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.*;
import java.time.Instant;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A DTO representing a user, with his authorities.
 */
public class UserDTO {

    private String id;

    @Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(min = 1, max = 50)
    private String login;

    @Size(max = 50)
    private String firstName;

    @Size(max = 50)
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Pattern(regexp = Constants.EMAIL_REGEX, message = "Invalid email format")
    @Size(min = 5, max = 100)
    private String email;

    @Size(max = 256)
    private String imageUrl;

    private boolean activated = false;

    @Size(min = 2, max = 5)
    private String langKey;

    private String createdBy;

    private Instant createdDate;

    private String lastModifiedBy;

    private Instant lastModifiedDate;

    @NotNull
    private Integer profile;

    private Set<String> authorities;
    private Set<String> institutions;

    private String personId;
    private PersonDTO personData;

    public UserDTO() {
        // Empty constructor needed for MapStruct.
    }

    public UserDTO(User user) {
        this(user.getId(), user.getLogin(), user.getFirstName(), user.getLastName(),
                user.getEmail(), user.getActivated(), user.getImageUrl(), user.getLangKey(),
                user.getCreatedBy(), user.getCreatedDate(), user.getLastModifiedBy(), user.getLastModifiedDate(),
                user.getAuthorities().stream().map(Authority::getName).collect(Collectors.toSet()),
                user.getInstitutions().stream().map(Institution::getId).collect(Collectors.toSet()),
                user.getPerson()==null? null: user.getPerson().getId(), user.getProfile());
        if(user.getPerson() != null) {
            PersonDTO dto = new PersonDTO();
            dto.setId(user.getPerson().getId());
            dto.setName(user.getPerson().getName());
            dto.setPersonType(user.getPerson().getPersonType());
            dto.setPhone(user.getPerson().getPhone());
            dto.setEmail(user.getPerson().getEmail());
            dto.setPhoto(user.getPerson().getPhoto());
            setPersonData(dto);
        }
    }

    public UserDTO(String id, String login, String firstName, String lastName,
                   String email, boolean activated, String imageUrl, String langKey,
                   String createdBy, Instant createdDate, String lastModifiedBy, Instant lastModifiedDate,
                   Set<String> authorities, Set<String> institutions, String personId, UserProfile profile) {

        this.id = id;
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.activated = activated;
        this.imageUrl = imageUrl;
        this.langKey = langKey;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.lastModifiedBy = lastModifiedBy;
        this.lastModifiedDate = lastModifiedDate;
        this.authorities = authorities;
        this.institutions = institutions;
        this.personId = personId;
        this.profile = profile == null? null: profile.getId();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public boolean isActivated() {
        return activated;
    }

    public String getLangKey() {
        return langKey;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public Integer getProfile() {
        return profile;
    }

    public void setProfile(Integer profile) {
        this.profile = profile;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public void setLangKey(String langKey) {
        this.langKey = langKey;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public void setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
    }

    public Set<String> getInstitutions() {
        return institutions;
    }

    public void setInstitutions(Set<String> institutions) {
        this.institutions = institutions;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public PersonDTO getPersonData() {
        return personData;
    }

    public void setPersonData(PersonDTO personData) {
        this.personData = personData;
    }

    @Override
    public String toString() {
        return "UserDTO{"
                + "login='" + login + '\''
                + ", firstName='" + firstName + '\''
                + ", lastName='" + lastName + '\''
                + ", email='" + email + '\''
                + ", imageUrl='" + imageUrl + '\''
                + ", activated=" + activated
                + ", langKey='" + langKey + '\''
                + ", createdBy=" + createdBy
                + ", createdDate=" + createdDate
                + ", lastModifiedBy='" + lastModifiedBy + '\''
                + ", lastModifiedDate=" + lastModifiedDate
                + ", authorities=" + (authorities==null? "": authorities.toString())
                + "}";
    }

}
