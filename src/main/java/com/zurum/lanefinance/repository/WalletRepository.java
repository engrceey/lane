package com.zurum.lanefinance.repository;

import com.zurum.lanefinance.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, String> {
}
