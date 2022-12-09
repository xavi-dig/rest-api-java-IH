package com.example.demo.models.DTOS;

import java.math.BigDecimal;

public class TransferThirdPartyDTO {

    private Long accountId;

    private Long thirdPartyId;

    private String accountSecretKey;

    private BigDecimal amount;

    public TransferThirdPartyDTO(Long accountId, Long thirdPartyId, String accountSecretKey, BigDecimal amount) {
        this.accountId = accountId;
        this.thirdPartyId = thirdPartyId;
        this.accountSecretKey = accountSecretKey;
        this.amount = amount;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getAccountSecretKey() {
        return accountSecretKey;
    }

    public void setAccountSecretKey(String accountSecretKey) {
        this.accountSecretKey = accountSecretKey;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getThirdPartyId() {
        return thirdPartyId;
    }

    public void setThirdPartyId(Long thirdPartyId) {
        this.thirdPartyId = thirdPartyId;
    }

}
