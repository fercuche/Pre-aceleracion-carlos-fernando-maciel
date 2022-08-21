package com.alkemy.challenge.auth.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
@NoArgsConstructor
@Getter
@Setter
@SQLDelete(sql = "UPDATE users SET enabled = true WHERE user_id = ?")
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Email
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    private boolean enabled;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public boolean isAccountNonExpired() {
        return false;
    }

    public boolean isAccountNonLocked() {
        return false;
    }

    public boolean isCredentialsNonExpired() {
        return false;
    }
}
