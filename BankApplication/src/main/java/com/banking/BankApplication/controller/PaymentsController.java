package com.banking.BankApplication.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.banking.BankApplication.Service.AccountService;
import com.banking.BankApplication.Service.PaymentService;
import com.banking.BankApplication.model.Account;
import com.banking.BankApplication.model.DepositRequest;
import com.banking.BankApplication.model.PaymentRequest;
import com.banking.BankApplication.model.Transaction;

@RestController
public class PaymentsController {
	
	@Autowired
	PaymentService paymentService;
	
	@Autowired
	AccountService accountService;
	
	@PostMapping("/payments/{accountId}/transfer")
	public String makePayment(
	    @PathVariable Long accountId,
	    @RequestBody PaymentRequest request) {

	    Account account = accountService.getAccountInfo(accountId);
	    
	    if (account != null) {
	    	return paymentService.makePayment(account,request);
	    } else {
	        return "Account not found";
	    }
	}
	
	@PostMapping("/payments/{accountId}/deposits")
	public ResponseEntity<String> deposit(
	    @PathVariable Long accountId,
	    @RequestBody DepositRequest request) {

		Account account = accountService.getAccountInfo(accountId);

	    if (account != null) {
	    	return paymentService.depositAmount(account,request);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	
	@GetMapping("/payments/{accountId}/transactions")
	public ResponseEntity<List<Transaction>> getTransactions(
	    @PathVariable Long accountId) {

		Account account = accountService.getAccountInfo(accountId);

	    if (account != null) {
	    	return paymentService.getTransactions(account);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	
}
