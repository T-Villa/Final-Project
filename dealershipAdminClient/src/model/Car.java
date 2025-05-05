package model;

import java.time.LocalDate;
import java.util.*;
/**
 * 
 **/
public class Car {
	private final int year;
	private final String make;
	private final String model;
	//private final String vin;
	private final String SKU;
	
	private final String color;
	private final String trimLvl;
	private final List<String> accessories;
	
	private double price;
	private boolean availability;
	
	
	
//Constructors
	public Car(int year, String make, String model, String color, String trimLvl, List<String> accessoies, double price) {
		this.make = requiredField(make,"Make");
		this.model = requiredField(model,"Model");
		this.color = requiredField(color, "Color");
		this.trimLvl = requiredField(trimLvl, "Trim Level");
		//this.vin = requiredField(vin, "VIN"); randomly generate VIN?
		
		//Arguments
		if(year < 1990 || year > LocalDate.now().getYear()) {
			throw new IllegalArgumentException("Year must be between 1990 and current year");
		}
		this.year = year;
		
		if(price < 0) {
			throw new IllegalArgumentException("Price must be positive");
		}
		this.price = price;
		
		this.accessories = null; // change 
		
		this.availability = true;
		this.SKU = UUID.randomUUID().toString();//possbily change

		
	}
	public static String requiredField(String val, String field) {
		if(val == null || val.trim().isEmpty()) {
			throw new IllegalArgumentException(field + " Must be filled");
		}
		return val;
	}
	
//Getters
	public int getYear() {
		return year;
	}
	public String getMake() {
		return make;
	}
	public String getModel() {
		return model;
	}
	public String getColor() {
		return color;
	}
	public String getTrimLvl() {
		return trimLvl;
	}
	public String getSKU() {
		return SKU;
	}
	public List<String> getAccessories() {
		return accessories;
	}
	public boolean isAvailable() {
		return availability;
	}
	public double getPrice() {
		return price;
	}
	
//Setters
	public void setPrice(double price) {
		if(price < 0) {
			throw new IllegalArgumentException("Price must be positive.");
		}
		this.price = price;
	}
	public void setAvailability(boolean availability) {
		this.availability = availability;
	}
}