package com.banking.BankApplication.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.BankApplication.Service.CustomerService;
import com.banking.BankApplication.model.Customer;

@RestController
@RequestMapping("/customerDetails")
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	@Autowired
	private AccountController accountController;

	@PostMapping("/customer")
	public void createCustomer(@RequestBody Customer customer) {
		customerService.createCustomer(customer);
		accountController.createAccount(customer.getAcctID(), customer.getCustname(),new BigDecimal(0), "Active");
	}

	@GetMapping("/customerInfo/{acctID}")
	public Customer getCustomerInfo(@PathVariable Long acctID) {
		return customerService.getCustomerInfo(acctID);
	}

	@DeleteMapping("/deleteCustomer/{acctID}")
	public void deleteCustomer(@PathVariable int acctID) {
		customerService.deleteCustomer(acctID);
	}

}
