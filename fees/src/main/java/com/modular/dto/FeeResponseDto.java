package com.modular.dto;

import com.modular.entity.FeeOptions;
import com.modular.entity.FeeTransaction;
import com.modular.entity.Ledger;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeeResponseDto {

    private long id;
    private float amount;
    private FeeOptions feeOptions;
    private String transactionId;
    private LocalDateTime paymentInitiatedAt;
    private String paymentReceivedAt;
    private String paymentSlipUrl;
    private long ledgerId;

    public FeeTransaction toEntity() {
        Ledger ledger = new Ledger();
        ledger.setLedgerId(this.ledgerId);

        return new FeeTransaction(
                this.id,
                this.amount,
                this.feeOptions,
                this.transactionId,
                this.paymentInitiatedAt,
                this.paymentReceivedAt,
                this.paymentSlipUrl,
                ledger
        );
    }
}
