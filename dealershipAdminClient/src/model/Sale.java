package model;
/**
 * 
 **/
import java.util.UUID;

public class Sale {
	private final String saleID;
	private final String carSKU;
	private final String customer;
	private final String saleDay;
	private final double price;
	
	public Sale(String carSKU, String customer,double price) {
		this.saleID = UUID.randomUUID().toString();
		this.carSKU = carSKU;
		this.customer = customer;
		this.saleDay = ""; // work on
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
	public String getSaleDate() { 
		return saleDay;
	}
	public double getPrice() {
		return price;
	}
}