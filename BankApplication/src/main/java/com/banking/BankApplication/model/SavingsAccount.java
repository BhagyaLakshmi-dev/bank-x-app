package com.banking.BankApplication.model;

import java.math.BigDecimal;

import javax.persistence.Entity;

@Entity
public class SavingsAccount extends Account {
    
    public SavingsAccount() {
    	
    }
    
    public SavingsAccount(Long acctID, String accountHolderName, BigDecimal balance, String acctStatus) {
    	super();
		setId(acctID);
		setBalance(balance);
		setAccountStatus(acctStatus);
		setAccountHolderName(accountHolderName);
	}
}