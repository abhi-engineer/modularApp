package com.modular.controller;

import com.modular.dto.StudentCreateDto;
import com.modular.dto.StudentResponseDto;
import com.modular.exceptions.ResourceNotFoundException;
import com.modular.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RelationServiceNotRegisteredException;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/add")
    public ResponseEntity<StudentResponseDto> addStudent(@RequestBody StudentCreateDto studentDto){
        StudentResponseDto student = studentService.addStudent(studentDto);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<StudentResponseDto>> getAllStudents() throws ResourceNotFoundException {
        List<StudentResponseDto> studentList = studentService.getAllStudents();
        return new ResponseEntity<>(studentList,HttpStatus.OK);
    }



}
