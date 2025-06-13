package com.modular.dto;

import com.modular.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponseDto {

    private Long studentId;
    private String name;
    private int standard;
    private LocalDateTime admittedAt;

    public Student toEntity() {
        return new Student(
                this.studentId,
                this.name,
                this.standard,
                this.admittedAt
        );
    }
}
