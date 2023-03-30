package com.banking.BankApplication.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.banking.BankApplication.model.Account;
import com.banking.BankApplication.model.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, Integer>, JpaRepository<Transaction, Integer>{

	List<Transaction> findByAccount(Account account);

}
