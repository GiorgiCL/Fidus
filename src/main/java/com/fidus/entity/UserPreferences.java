package com.fidus.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_preferences")
@NoArgsConstructor
@Data
public class UserPreferences {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String theme = "dark";

    private String locale = "en";

    @Column(name = "notification_enabled")
    private boolean notificationEnabled = true;
}
