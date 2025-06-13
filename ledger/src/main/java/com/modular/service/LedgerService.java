package com.modular.service;

import com.modular.dto.LedgerResponseDto;

import java.util.List;

public interface LedgerService {

    LedgerResponseDto getStudentLedger(String name);
}
