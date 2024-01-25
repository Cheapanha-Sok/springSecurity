package com.example.authenticationBackend.repository;

import com.example.authenticationBackend.models.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<ApplicationUser , Integer> {
    Optional<ApplicationUser> findByEmail(String email);
}
