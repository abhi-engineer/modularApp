package com.modular.controller;

import com.modular.dto.LedgerResponseDto;
import com.modular.service.LedgerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ledger")
@CrossOrigin("*")
public class LedgerController {

    @Autowired
    private LedgerService ledgerService;

    @GetMapping("/student-ledger/{name}")
    public ResponseEntity<LedgerResponseDto> getStudentLedger(@PathVariable("name") String name){
        LedgerResponseDto ledgerResponseDto = ledgerService.getStudentLedger(name);
        return new ResponseEntity<>(ledgerResponseDto, HttpStatus.OK);
    }
}
