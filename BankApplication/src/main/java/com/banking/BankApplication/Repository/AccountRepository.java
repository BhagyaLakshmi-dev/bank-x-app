package com.banking.BankApplication.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.banking.BankApplication.model.Account;

public interface AccountRepository extends CrudRepository<Account, Integer>, JpaRepository<Account, Integer> {
	@Query("select a from Account a where id = ?1")
	public Account findByAcctID(Long acctID);
	
	@Query("select balance from Account where id = ?1")
	public int findBalanceByAcctID(Long acctID);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("update Account set balance = balance+?2 where id=?1")
	public void saveBalanceByAcctID(int acctID, int balance);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("update Account set balance = balance-?2 where id=?1")
	public void withdrawAmountByAcctID(int acctID, int balance);

}
