package com.modular.entity;

import com.modular.dto.LedgerResponseDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ledger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ledgerId;

    private float totalFee;
    private float paidAmount;
    private float dueAmount;

    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public LedgerResponseDto toDto() {
        return new LedgerResponseDto(
                this.ledgerId,
                this.totalFee,
                this.paidAmount,
                this.dueAmount,
                this.student
        );
    }
}
