package com.modular.service.impl;

import com.modular.dto.FeeDto;
import com.modular.dto.FeeResponseDto;
import com.modular.dto.LedgerResponseDto;
import com.modular.entity.FeeOptions;
import com.modular.entity.FeeTransaction;
import com.modular.entity.Ledger;
import com.modular.entity.Student;
import com.modular.exceptions.BadRequestException;
import com.modular.exceptions.ResourceNotFoundException;
import com.modular.repository.FeeRepo;
import com.modular.repository.LedgerRepo;
import com.modular.repository.StudentRepo;
import com.modular.service.FeeService;
import com.modular.service.LedgerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class FeeServiceImpl implements FeeService {

    @Autowired
    private FeeRepo feeRepo;
    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private LedgerRepo ledgerRepo;

    @Override
    public FeeResponseDto punchFee(FeeDto feeDto, String name) {

        Student student = studentRepo.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("No student found with this name"));

        // Get or Create Ledger for student
        Ledger ledger = ledgerRepo.findByStudentName(name).orElse(null);
        if (ledger == null) {
            ledger = new Ledger();
            ledger.setStudent(student);
            ledger.setTotalFee(0f);
            ledger.setPaidAmount(0f);
            ledger.setDueAmount(0f);
            ledger = ledgerRepo.save(ledger);
        }

        try {
            // Create FeeTransaction entity
            FeeTransaction feeTransaction = new FeeTransaction();
            feeTransaction.setAmount(feeDto.getAmount());
            feeTransaction.setFeeOptions(feeDto.getFeeOptions());
            feeTransaction.setTransactionId(feeDto.getTransactionId());
            feeTransaction.setPaymentReceivedAt(feeDto.getPaymentReceivedAt());
            feeTransaction.setPaymentSlipUrl(feeDto.getPaymentSlipUrl());
            feeTransaction.setLedger(ledger);

            // Save FeeTransaction
            feeTransaction = feeRepo.save(feeTransaction);

            float updatedPaid = ledger.getPaidAmount() + feeDto.getAmount();
            ledger.setPaidAmount(updatedPaid);
            ledgerRepo.save(ledger);

            return feeTransaction.toDto();

        } catch (Exception e) {
            throw new BadRequestException("Unable to pay fee, Try Again...");
        }
    }


    @Override
    public List<FeeResponseDto> getStudentAllTransactions(String name) {
        List<Student> students = studentRepo.findAllByName(name);
        if (students.isEmpty()) {
            throw new ResourceNotFoundException("No student found with name: " + name);
        }
        if (students.size() > 1) {
            throw new BadRequestException("Multiple students found with name: " + name);
        }
        Student student = students.get(0);

        List<FeeTransaction> feeList = feeRepo.findByLedgerStudentNameIgnoreCase(student.getName());
        if(feeList.isEmpty())
            throw new ResourceNotFoundException("No student present with given name");
        return feeList.stream().map(FeeTransaction::toDto).collect(Collectors.toList());
    }

    @Override
    public String getFeeByUpi() {
        Random random = new Random();
        long randomNumber = Math.abs(random.nextLong()) % 1_000_000_000L;
        return String.valueOf(randomNumber);
    }
}
