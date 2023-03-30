package com.banking.BankApplication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer {

	@Id
	@Column
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long acctID;
	
	private String custname;
	private String city;
	private String state;
	private String country;
	
	@Column(name="phoneno")
	private int phoneNo;
	private String password;

	public Customer() {

	}

	public Customer(Long acctID, String custName, String city, String state, String country, int phoneNo,
			String password) {
		super();
		this.acctID = acctID;
		this.custname = custName;
		this.city = city;
		this.state = state;
		this.country = country;
		this.phoneNo = phoneNo;
		this.password = password;
	}

	public Long getAcctID() {
		return acctID;
	}

	public void setAcctID(Long acctID) {
		this.acctID = acctID;
	}

	public String getCustname() {
		return custname;
	}

	public void setCustname(String custname) {
		this.custname = custname;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(int phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
