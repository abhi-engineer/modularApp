package com.modular.dto;


import com.modular.entity.FeeOptions;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeeDto {
    private float amount;
    private FeeOptions feeOptions;
    private String transactionId;
    private String paymentSlipUrl;
    private String paymentReceivedAt;
}
