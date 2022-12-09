package com.example.demo.models.DTOS;

public class AccessBalanceDTO {
    private Long userId;
    private Long accountId;

    public AccessBalanceDTO(Long userId, Long accountId) {
        this.userId = userId;
        this.accountId = accountId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
}
