package com.modular.service.impl;

import com.modular.dto.LedgerResponseDto;
import com.modular.entity.Ledger;
import com.modular.exceptions.ResourceNotFoundException;
import com.modular.repository.LedgerRepo;
import com.modular.service.LedgerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LedgerServiceImpl implements LedgerService {

    @Autowired
    private LedgerRepo ledgerRepo;

    @Override
    public LedgerResponseDto getStudentLedger(String name) {
        Ledger ledger = ledgerRepo.findByStudentName(name).orElseThrow(() -> new ResourceNotFoundException("No data available for this name"));
        return ledger.toDto();
    }
}
