package com.banking.BankApplication.Service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.banking.BankApplication.Repository.TransactionRepository;
import com.banking.BankApplication.model.Account;
import com.banking.BankApplication.model.CurrentAccount;
import com.banking.BankApplication.model.DepositRequest;
import com.banking.BankApplication.model.PaymentRequest;
import com.banking.BankApplication.model.SavingsAccount;
import com.banking.BankApplication.model.Transaction;
import com.banking.BankApplication.model.TransactionType;

@Service
public class PaymentService {
	
	@Autowired
	TransactionRepository transactionRepository;
	
	public String makePayment(Account account, PaymentRequest request) {
		if (account instanceof CurrentAccount) {
            CurrentAccount currentAccount = (CurrentAccount) account;
            BigDecimal amount = request.getAmount();
            BigDecimal transactionFee = amount.multiply(new BigDecimal("0.05"));
            if (amount.compareTo(BigDecimal.ZERO) <= 0) {
                return "Amount must be greater than zero.";
            }
            
            // Check if the account has enough balance to make the payment
            BigDecimal balance = currentAccount.getBalance();
            if (balance.compareTo(amount) < 0) {
                return "Insufficient Balance";
            }
            
            if (balance.compareTo(amount.add(transactionFee)) >= 0) {
            	// Deduct the payment amount from the account balance
                BigDecimal newBalance = balance.subtract(amount).subtract(transactionFee);
                currentAccount.setBalance(newBalance);

                // Create a new transaction for the payment
                Transaction transaction = new Transaction();
                transaction.setAmount(amount);
                transaction.setType(TransactionType.TRANSFER);
                transaction.setAccount(currentAccount);

                transactionRepository.save(transaction);
                return "Payment successful.";
            }
            else {
            	return "Insufficient Balance";
            }
        } else {
            return "Cannot make payments from a savings account.";
        }
	}

	public ResponseEntity<String> depositAmount(Account account, DepositRequest request) {
		if (account instanceof SavingsAccount) {
			SavingsAccount savingsAccount = (SavingsAccount) account;

			BigDecimal amount = request.getAmount();
			if (amount.compareTo(BigDecimal.ZERO) <= 0) {
				return ResponseEntity.badRequest().body("Amount must be greater than zero.");
			}

			// Credit the deposit amount to the account balance
			BigDecimal balance = savingsAccount.getBalance();
			BigDecimal newBalance = balance.add(amount);
			savingsAccount.setBalance(newBalance);

			// Calculate the interest on the new balance
			BigDecimal interestRate = BigDecimal.valueOf(0.005);
			BigDecimal interest = newBalance.multiply(interestRate);

			// Credit the interest to the account balance
			newBalance = newBalance.add(interest);
			savingsAccount.setBalance(newBalance);

			// Create a new transaction for the deposit
			Transaction transaction = new Transaction();
			transaction.setAmount(amount);
			transaction.setType(TransactionType.DEPOSIT);
			transaction.setAccount(savingsAccount);

			transactionRepository.save(transaction);

			return ResponseEntity.ok("Deposit successful.");
		} else {
			return ResponseEntity.badRequest().body("Cannot make deposits into a current account.");
		}
	}

	public ResponseEntity<List<Transaction>> getTransactions(Account account) {
		List<Transaction> transactions = transactionRepository.findByAccount(account);
        return ResponseEntity.ok(transactions);
	}

}
