package me.raiyantakrim.tripbuddy.entity;

import jakarta.persistence.*;
import lombok.Data;
import me.raiyantakrim.tripbuddy.utility.Role;

import java.util.UUID;
@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false,  unique = true)
    private String email;

    @Column(name = "password_hash",  nullable = false)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
}
