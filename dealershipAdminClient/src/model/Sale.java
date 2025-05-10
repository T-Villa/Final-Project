/**
* Represents a completed sale transaction within the dealership system.
* Stores information about the car sold, buyer, seller, sale date, and price.
* Each sale is uniquely identified by a sale ID.
* 
* @author Thomas Villareale 
*/
package model;

import java.time.LocalDate;
import java.util.UUID;


public class Sale {
	private final String saleID;
	private final String carSKU;
	private final String description;
	private final Customer customer;
	private final String seller;
	private final LocalDate saleDay;
	private final double price;
	
/**
* Constructs a Sale object with the specified details. 
* @param carSKU      the SKU of the car sold
* @param description textual description of the car
* @param car         the car object being sold
* @param customer    the customer who purchased the car
* @param seller      the employee (seller) who handled the sale
* @param price       the final price of the sale
*/
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