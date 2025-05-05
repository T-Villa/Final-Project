package model;

import java.util.UUID;
import java.time.LocalDate;


public class Sale {
	private final String saleID;
	private final String carSKU;
	private final String customer;
	private final LocalDate saleDay;
	private final double price;
	
	public Sale(String carSKU, String customer,double price) {
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
	public String getCustomer() {
		return customer;
	}
	public LocalDate getSaleDate() { 
		return saleDay;
	}
	public double getPrice() {
		return price;
	}
}