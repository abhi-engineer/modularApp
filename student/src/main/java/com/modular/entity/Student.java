package com.modular.entity;

import com.modular.dto.StudentResponseDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    private String name;
    private int standard;
    private LocalDateTime admittedAt;

    @PrePersist
    protected void onCreate() {
        if (this.admittedAt == null) {
            this.admittedAt = LocalDateTime.now();
        }
    }

    public StudentResponseDto toDto() {
        return new StudentResponseDto(
                this.studentId,
                this.name,
                this.standard,
                this.admittedAt
        );
    }
}
