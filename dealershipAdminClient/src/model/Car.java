/**
* Represents a Car object in the dealership inventory system.
* Encapsulates properties such as year, make, model, color, trim level, accessories, price
* Each car has a unique SKU identifier.
* 
*  @author Thomas Villareale
*/
package model;

import java.time.LocalDate;

import java.util.*;

public class Car {
	private final int year;
	private final String make;
	private final String model;
	private final String SKU;
	
	private final String color;
	private final String trimLvl;
	
	private double price;
	private boolean availability;

/**
* Constructs a Car object with the specified details.
* @param year        the manufacturing year of the car (must be between 1990 and current year)
* @param make        the manufacturer of the car
* @param model       the model of the car
* @param trimLvl     the trim level of the car
* @param color       the color of the car
* @param accessories list of accessories (optional, can be null)
* @param price       the price of the car (must be non-negative)
* @throws IllegalArgumentException if any required field is missing or invalid
*/
	public Car(int year, String make, String model, String trimLvl, String color, double price) {
		this.make = requiredField(make,"Make");
		this.model = requiredField(model,"Model");
		this.color = requiredField(color, "Color");
		this.trimLvl = requiredField(trimLvl, "Trim Level");
		
		//Arguments
		if(year < 1990 || year > LocalDate.now().getYear()) {
			throw new IllegalArgumentException("Year must be between 1990 and current year");
		}
		this.year = year;
		
		if(price < 0) {
			throw new IllegalArgumentException("Price must be positive");
		}
		this.price = price; 
		
		this.availability = true;
		this.SKU = UUID.randomUUID().toString();	
	}
	
/**
* Validates that a required field is not null or empty.
* @param val   the value to check
* @param field the name of the field
* @return the validated value
* @throws IllegalArgumentException if value is null or empty
*/
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
	public boolean isAvailable() {
		return availability;
	}
	public double getPrice() {
		return price;
	}
	
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