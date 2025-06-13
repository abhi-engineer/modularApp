package com.modular.dto;

import com.modular.entity.Ledger;
import com.modular.entity.Student;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LedgerResponseDto {

    private Long ledgerId;

    private float totalFee;
    private float paidAmount;
    private float dueAmount;
    private Student student;

    public LedgerResponseDto(Long ledgerId, float totalFee, float paidAmount, float dueAmount, Student student, Object o) {
    }

    public Ledger toEntity() {
        Ledger ledger = new Ledger();
        ledger.setLedgerId(this.ledgerId);
        ledger.setTotalFee(this.totalFee);
        ledger.setPaidAmount(this.paidAmount);
        ledger.setDueAmount(this.dueAmount);
        ledger.setStudent(this.student);
        return ledger;
    }
}
