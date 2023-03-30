package com.banking.BankApplication.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.BankApplication.Repository.AccountRepository;
import com.banking.BankApplication.model.Account;

@Service
public class AccountService {
	@Autowired
	private AccountRepository accountRepository;

	public void createAccount(Account currentAccount, Account savingsAccount) {
		accountRepository.save(currentAccount);
		accountRepository.save(savingsAccount);
	}
	
	public Account getAccountInfo(Long acctID) {
		return accountRepository.findByAcctID(acctID);
	}

	public void deleteAccount(Long acctID) {
		accountRepository.deleteById(acctID.intValue());
	}

	public int getBalance(Long acctID) {
		return accountRepository.findBalanceByAcctID(acctID);
	}

	public void transferAmount(int acctID, int destAcctID, int amount) {
		accountRepository.withdrawAmountByAcctID(acctID, amount);
		accountRepository.saveBalanceByAcctID(destAcctID, amount);
	}
	
	/*public void depositAmount(int acctID, int amount) {
		accountRepository.saveBalanceByAcctID(acctID, amount);
	}

	public void withdrawAmount(int acctID, int amount) {
		accountRepository.withdrawAmountByAcctID(acctID, amount);
	}*/

}
