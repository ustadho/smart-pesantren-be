package id.smartpesantren.security;

import id.smartpesantren.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MyUserDetails implements UserDetails {

    private String username;
    private String foundationId;
    private String personId;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public MyUserDetails(String username, String password,
                         Collection<? extends GrantedAuthority> authorities,
                         String foundationId,
                         String personId) {
        System.out.println("MyUserDetails: "+ username);
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.foundationId = foundationId;
        this.personId = personId;
    }

    public static MyUserDetails build(User user) {
        List<GrantedAuthority> authorities = user.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                .collect(Collectors.toList());

        return new MyUserDetails(
                user.getLogin(),
                user.getLastName(),
                authorities,
                user.getFoundation().getId(),
                user.getPerson() == null? null: user.getPerson().getId()
                );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public String getFoundationId() {
        return foundationId;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public void setFoundationId(String foundationId) {
        this.foundationId = foundationId;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        MyUserDetails user = (MyUserDetails) o;

        return Objects.equals(username, user.username);
    }
}