package com.fidus.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true,nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String passwordHash;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "timezone")
    private String timezone;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        if(timezone == null){
            timezone = "UTC";
        }
        if(displayName == null && email != null){
            displayName = email.split("@")[0];
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }
    @Override
    public String getPassword() {
        return passwordHash;
    }
    @Override
    public String getUsername() {
        return email;
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
}
