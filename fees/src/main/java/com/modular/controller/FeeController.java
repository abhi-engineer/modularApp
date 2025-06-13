package com.modular.controller;

import com.modular.dto.FeeDto;
import com.modular.dto.FeeResponseDto;
import com.modular.service.FeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fee")
@CrossOrigin("*")
public class FeeController {

    @Autowired
    private FeeService feeService;

    @PostMapping("/pay/{name}")
    public ResponseEntity<FeeResponseDto> punchFee(@RequestBody FeeDto feeDto, @PathVariable("name")
 String name){
        FeeResponseDto feeResponseDto = feeService.punchFee(feeDto, name);
        return new ResponseEntity<>(feeResponseDto, HttpStatus.OK);
    }

    @GetMapping("/student-all-transaction/{name}")
    public ResponseEntity<List<FeeResponseDto>> getStudentAllTransactions(@PathVariable("name") String name){
        List<FeeResponseDto> list = feeService.getStudentAllTransactions(name);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
