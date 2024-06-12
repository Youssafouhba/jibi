package com.ensa.jibi.backend.domain.requests;

import lombok.Data;

@Data
public class TransferRequest {
    private String fromAccount;
    private String toAccount;
    private Double amount;
}
