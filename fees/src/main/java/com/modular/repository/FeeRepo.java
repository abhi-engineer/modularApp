package com.modular.repository;

import com.modular.entity.FeeTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeeRepo extends JpaRepository<FeeTransaction, Long> {
    List<FeeTransaction> findByLedgerStudentNameIgnoreCase(String name);
}
