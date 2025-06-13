package com.modular.repository;

import com.modular.dto.LedgerResponseDto;
import com.modular.entity.Ledger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LedgerRepo extends JpaRepository<Ledger, Long> {
    Optional<Ledger> findByStudentName(String name);
}
