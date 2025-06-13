package com.modular.service;

import com.modular.dto.FeeDto;
import com.modular.dto.FeeResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FeeService {
    FeeResponseDto punchFee(FeeDto feeDto, String name);
    String getFeeByUpi();
    List<FeeResponseDto> getStudentAllTransactions(String name);

}
