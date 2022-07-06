package com.zurum.lanefinance.repository;

import com.zurum.lanefinance.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
