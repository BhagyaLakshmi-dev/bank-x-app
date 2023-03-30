package com.banking.BankApplication.model;

import java.math.BigDecimal;

public class PaymentRequest {
    private BigDecimal amount;
    private String accountNumber;
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
}
