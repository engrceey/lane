package com.zurum.lanefinance.repository;

import com.zurum.lanefinance.entity.TransactionLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionLog, String> {

}
