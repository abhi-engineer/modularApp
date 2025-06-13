package com.modular.entity;

import com.modular.dto.FeeResponseDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class FeeTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private float amount;

    @Enumerated(EnumType.STRING)
    private FeeOptions feeOptions;

    private String transactionId;

    private LocalDateTime paymentInitiatedAt;
    private String paymentReceivedAt;

    private String paymentSlipUrl;

    @ManyToOne
    @JoinColumn(name = "ledger_id")
    private Ledger ledger;

    @PrePersist
    protected void onCreate() {
        if (this.paymentInitiatedAt == null) {
            this.paymentInitiatedAt = LocalDateTime.now();
        }
    }

    public FeeResponseDto toDto() {
        return new FeeResponseDto(
                this.id,
                this.amount,
                this.feeOptions,
                this.transactionId,
                this.paymentInitiatedAt,
                this.paymentReceivedAt,
                this.paymentSlipUrl,
                this.ledger.getLedgerId()
        );
    }
}
