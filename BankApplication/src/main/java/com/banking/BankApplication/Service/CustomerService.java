package com.banking.BankApplication.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.BankApplication.Repository.CustomerRepository;
import com.banking.BankApplication.model.Customer;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository customerRepository;

	public void createCustomer(Customer customer) {
		customerRepository.save(customer);
	}

	public Customer getCustomerInfo(Long acctID) {
		return customerRepository.findByCustID(acctID);
	}

	public void deleteCustomer(int acctID) {
		customerRepository.deleteById(acctID);
	}

}
