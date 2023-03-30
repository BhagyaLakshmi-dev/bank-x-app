package com.banking.BankApplication.controller;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.BankApplication.Service.AccountService;
import com.banking.BankApplication.model.Account;
import com.banking.BankApplication.model.CurrentAccount;
import com.banking.BankApplication.model.SavingsAccount;

@RestController
public class AccountController {
	@Autowired
	private AccountService accountService;

	// createAccount happens upon createCustomer
	public void createAccount(Long acctID, String accountHolderName, BigDecimal balance, String acctStatus) {
		Account currentAccount = new CurrentAccount(acctID, accountHolderName, balance, acctStatus);
		
		// Credit savings account with joining bonus of R500
        Account savingsAccount = new SavingsAccount(acctID, accountHolderName, new BigDecimal("500"), acctStatus);
        
        currentAccount.setAccountNumber(UUID.randomUUID().toString());
        savingsAccount.setAccountNumber(UUID.randomUUID().toString());
        
        // Save accounts to repository
		accountService.createAccount(currentAccount,savingsAccount);
	}

	// checkBalance
	@GetMapping("/account/{acctID}/balance")
	public int getBalance(@PathVariable Long acctID) {
		return accountService.getBalance(acctID);
	}

	// transferAmount between two accounts
	@PutMapping("/account/{acctID}/transfer/{destAcctID}/{amount}")
	public void transferAmount(@PathVariable int acctID, @PathVariable int destAcctID, @PathVariable int amount) {
		accountService.transferAmount(acctID, destAcctID, amount);
	}

	// deleteAccount
	@DeleteMapping("/account/{acctID}")
	public void deleteAccount(@PathVariable Long acctID) {
		accountService.deleteAccount(acctID);
	}

	// getAccountInfo
	@GetMapping("/account/{acctID}/accountInfo")
	public Account getAccountInfo(@PathVariable Long acctID) {
		return accountService.getAccountInfo(acctID);
	}

}
