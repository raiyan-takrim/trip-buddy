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
    @Column(name = "user_id")
    private UUID userId;
    private String name;
    private String email;
    @Column(name = "password_hash")
    private String passwordHash;
    private Role role;
}
