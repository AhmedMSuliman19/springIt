package com.example.springIt.repository;

import com.example.springIt.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByEmail(String email);
    Optional<Users> findByEmailAndActivationCode(String email, String activationCode);
}
