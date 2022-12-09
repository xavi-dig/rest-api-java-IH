package com.example.demo.models.DTOS;

import java.math.BigDecimal;

public class TransferDTO {

    private Long sendingId;

    private Long receivingId;

    private BigDecimal amount;

    private String receivingName;

    public TransferDTO(Long sendingId, Long receivingId, BigDecimal amount, String receivingName) {
        this.sendingId = sendingId;
        this.receivingId = receivingId;
        this.amount = amount;
        this.receivingName = receivingName;
    }

    public Long getSendingId() {
        return sendingId;
    }

    public void setSendingId(Long sendingId) {
        this.sendingId = sendingId;
    }

    public Long getReceivingId() {
        return receivingId;
    }

    public void setReceivingId(Long receivingId) {
        this.receivingId = receivingId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getReceivingName() {
        return receivingName;
    }

    public void setReceivingName(String receivingName) {
        this.receivingName = receivingName;
    }
}

