package com.LeverX.FinalProject.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @JsonProperty
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "first_name cannot be empty")
    @JsonProperty
    private String first_name;

    @NotBlank(message = "last_name cannot be empty")
    @JsonProperty
    private String last_name;

    @NotBlank(message = "Password cannot be empty")
    @JsonProperty
    private String password;

    @Transient
    @NotBlank(message = "Password confirmation cannot be empty")
    private String password2;

    @JsonProperty
    @Email(message = "Email is not correct")
    private String email;

    @JsonProperty
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate created_at;

    @JsonProperty
    private boolean active;

    @JsonProperty
    private String activationCode;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @JsonProperty
    private Set<Role> roles;

/*    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonProperty
    private Set<Comment> commentSet;*/

    @JsonProperty
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rating_id")
    private Rating rating;

    public User(String first_name, String last_name, String password, String email, LocalDate created_at, boolean active, Set<Role> roles, Rating rating) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.password = password;
        this.email = email;
        this.created_at = created_at;
        this.active = active;
        this.roles = roles;
        this.rating = rating;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getUsername() {
        return getEmail();
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
        return isActive();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return active == user.active &&
                Objects.equals(id, user.id) &&
                Objects.equals(first_name, user.first_name) &&
                Objects.equals(last_name, user.last_name) &&
                Objects.equals(password, user.password) &&
                Objects.equals(email, user.email) &&
                Objects.equals(created_at, user.created_at) &&
                Objects.equals(roles, user.roles);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, first_name, last_name, password, email, created_at, active, roles);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", created_at=" + created_at +
                ", active=" + active +
                ", roles=" + roles +
                '}';
    }
}
