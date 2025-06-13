package com.modular.service;

import com.modular.dto.StudentCreateDto;
import com.modular.dto.StudentResponseDto;
import com.modular.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.management.relation.RelationServiceNotRegisteredException;
import java.util.List;

@Service
public interface StudentService {
    StudentResponseDto addStudent(StudentCreateDto studentDto);
    List<StudentResponseDto> getAllStudents() throws ResourceNotFoundException;
}
