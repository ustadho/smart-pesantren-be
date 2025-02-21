/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.smartpesantren.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import id.smartpesantren.config.Constants;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Email;

/**
 *
 * @author ustadho
 */
@Entity
@Table(name = "c_security_user")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class User extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(length = 36)
    private String id;

    @ManyToOne
    @JoinColumn(name = "foundation_id", nullable = false)
    Foundation foundation;

    @ManyToOne
    @JoinColumn(name = "profile_id", nullable = false, columnDefinition = "integer default 1")
    UserProfile profile;

    @Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(min = 1)
    @Column(length = 50, unique = true, nullable = false)
    private String login;

    @JsonIgnore
    @Column(name = "password_hash",length = 60, nullable = false)
    private String password;

    @Column(name = "first_name", length = 50)
    private String firstName;

    @Column(name = "last_name", length = 50)
    private String lastName;

    @Email
    @Column(length = 100, unique = true)
    private String email;

    @Column(nullable = false)
    private boolean activated = false;

    @Size(min = 2)
    @Column(name = "lang_key", length = 5)
    private String langKey;

    @Column(name = "image_url", length = 256)
    private String imageUrl;

    @Column(name = "activation_key", length = 20)
    @JsonIgnore
    private String activationKey;

    @Size(max = 20)
    @Column(name = "reset_key")
    private String resetKey;

    @Column(name = "reset_date")
    private Instant resetDate = null;
    
    @JsonIgnore
    @ManyToMany
    @JoinTable(
        name = "c_security_user_authority",
        joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "name")})
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @BatchSize(size = 20)
    private Set<Authority> authorities = new HashSet<>();

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "c_security_user_institution",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "institution_id", referencedColumnName = "id")})
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @BatchSize(size = 20)
    private Set<Institution> institutions = new HashSet<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "user")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<PersistentToken> persistentTokens = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "person_id", unique = true)
    private PersonData person;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Foundation getFoundation() {
        return foundation;
    }

    public void setFoundation(Foundation foundation) {
        this.foundation = foundation;
    }

    public String getLogin() {
        return login;
    }

    //Lowercase the login before saving it in database
    public void setLogin(String login) {
        this.login = login.toLowerCase(Locale.ENGLISH);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean getActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public String getActivationKey() {
        return activationKey;
    }

    public void setActivationKey(String activationKey) {
        this.activationKey = activationKey;
    }

    public String getResetKey() {
        return resetKey;
    }

    public void setResetKey(String resetKey) {
        this.resetKey = resetKey;
    }

    public Instant getResetDate() {
       return resetDate;
    }

    public void setResetDate(Instant resetDate) {
       this.resetDate = resetDate;
    }

    public String getLangKey() {
        return langKey;
    }

    public void setLangKey(String langKey) {
        this.langKey = langKey;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public Set<Institution> getInstitutions() {
        return institutions;
    }

    public void setInstitutions(Set<Institution> institutions) {
        this.institutions = institutions;
    }

    public Set<PersistentToken> getPersistentTokens() {
        return persistentTokens;
    }

    public void setPersistentTokens(Set<PersistentToken> persistentTokens) {
        this.persistentTokens = persistentTokens;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public boolean isActivated() {
        return activated;
    }

    public PersonData getPerson() {
        return person;
    }

    public void setPerson(PersonData person) {
        this.person = person;
    }

    public UserProfile getProfile() {
        return profile;
    }

    public void setProfile(UserProfile profile) {
        this.profile = profile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        if (!login.equals(user.login)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return login.hashCode();
    }

    @Override
    public String toString() {
        return "User{" +
            "login='" + login + '\'' +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", email='" + email + '\'' +
            ", imageUrl='" + imageUrl + '\'' +
            ", activated='" + activated + '\'' +
            ", langKey='" + langKey + '\'' +
            ", activationKey='" + activationKey + '\'' +
            "}";
    }

    /**
     * @return the role
     */
}
