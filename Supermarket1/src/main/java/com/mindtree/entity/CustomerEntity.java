package com.mindtree.entity;

import java.util.Date;
import java.util.Set;

public class CustomerEntity {
	private int id;
	private String customerName;
	private String phoneNumber;
	private String dateOfShopping;
	private double totalBill;
	private Set<ItemEntity> item;

	public CustomerEntity() {
	}

	public CustomerEntity(int id, String customerName, String phoneNumber, String dateOfShopping, double totalBill,
			Set<ItemEntity> item) {
		this.id = id;
		this.customerName = customerName;
		this.phoneNumber = phoneNumber;
		this.dateOfShopping = dateOfShopping;
		this.totalBill = totalBill;
		this.item = item;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getDateOfShopping() {
		return dateOfShopping;
	}

	public void setDateOfShopping(String dateOfShopping) {
		this.dateOfShopping = dateOfShopping;
	}

	public double getTotalBill() {
		return totalBill;
	}

	public void setTotalBill(double totalBill) {
		this.totalBill = totalBill;
	}

	public Set<ItemEntity> getItem() {
		return item;
	}

	public void setItem(Set<ItemEntity> item) {
		this.item = item;
	}

}
