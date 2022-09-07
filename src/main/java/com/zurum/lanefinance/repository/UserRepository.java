package com.zurum.lanefinance.repository;

import com.zurum.lanefinance.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser, String> {
    Optional<AppUser> getUserByEmail(String email);
}
