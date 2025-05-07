package model;

import model.Customer;

import java.util.UUID;
import java.time.LocalDate;


public class Sale {
	private final String saleID;
	private final String carSKU;
	private final Customer customer;
	private final LocalDate saleDay;
	private final double price;
	
	public Sale(String carSKU, Customer customer,double price) {
		this.saleID = UUID.randomUUID().toString();
		this.carSKU = carSKU;
		this.customer = customer;
		this.saleDay = LocalDate.now(); // work on
		this.price = price;
	}
	
	public String getSaleID() {
		return saleID;
	}
	public String getCarSKU() {
		return carSKU;
	}
	public Customer getCustomer() {
		return customer;
	}
	public LocalDate getSaleDate() { 
		return saleDay;
	}
	public double getPrice() {
		return price;
	}
}