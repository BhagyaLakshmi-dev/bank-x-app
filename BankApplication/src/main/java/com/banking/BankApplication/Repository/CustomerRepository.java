package com.banking.BankApplication.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.banking.BankApplication.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
	@Query("select a from Customer a where id = ?1")
	public Customer findByCustID(Long acctID);
}
