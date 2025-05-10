package model;

import model.Customer;

import java.util.UUID;
import java.time.LocalDate;


public class Sale {
	private final String saleID;
	private final String carSKU;
	private final String description;
	private final Customer customer;
	private final String seller;
	private final LocalDate saleDay;
	private final double price;
	
	public Sale(String carSKU,String description,Car car,Customer customer,String seller, double price) {
		this.saleID = UUID.randomUUID().toString();
		this.carSKU = carSKU;
		this.description = car.getYear()+" "+car.getMake()+" "+car.getModel()+" "+car.getTrimLvl();
		this.customer = customer;
		this.seller = seller;
		this.saleDay = LocalDate.now(); // work on
		this.price = price;
	}
	
	public String getSaleID() {
		return saleID;
	}
	public String getCarSKU() {
		return carSKU;
	}
	public String getDescription() {
		return description;
	}
	public Customer getCustomer() {
		return customer;
	}
	public String getSeller() {
		return seller;
	}
		public LocalDate getSaleDate() { 
		return saleDay;
	}
	public double getPrice() {
		return price;
	}
}