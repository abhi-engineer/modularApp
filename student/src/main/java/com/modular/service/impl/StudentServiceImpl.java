package com.modular.service.impl;

import com.modular.dto.StudentCreateDto;
import com.modular.dto.StudentResponseDto;
import com.modular.entity.Student;
import com.modular.exceptions.BadRequestException;
import com.modular.exceptions.ResourceNotFoundException;
import com.modular.repository.StudentRepo;
import com.modular.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepo studentRepo;


    @Override
    public StudentResponseDto addStudent(StudentCreateDto studentDto) {
        Student student = new Student();
        try{
            student.setName(studentDto.getName());
            student.setStandard(studentDto.getStandard());
            student = studentRepo.save(student);
        }catch (BadRequestException e){
            throw new BadRequestException("Failed to add student: ");
        }
        return student.toDto();
    }

    @Override
    public List<StudentResponseDto> getAllStudents() throws ResourceNotFoundException {
        List<Student> studentList = studentRepo.findAll();
        if(studentList.isEmpty())
            throw new ResourceNotFoundException("NO Record Found");
        return studentList.stream().map(Student::toDto).collect(Collectors.toList());
    }


}
