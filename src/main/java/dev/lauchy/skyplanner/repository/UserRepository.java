package dev.lauchy.skyplanner.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.lauchy.skyplanner.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
