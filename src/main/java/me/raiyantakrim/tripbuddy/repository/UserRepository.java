package me.raiyantakrim.tripbuddy.repository;

import me.raiyantakrim.tripbuddy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
