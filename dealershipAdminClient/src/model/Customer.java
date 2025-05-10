/**
* Represents a customer in the dealership system.
* Stores customer contact information and generates a unique ID for each customer.
* Used for tracking sales and customer management.
* 
* @author Thomas Villareale
*/
package model;

import java.util.UUID;

public class Customer {
	private final String ID;
	private final String name;
	private final String email;
	private final String phone;

/**
* Constructs a Customer object with specified name, email, and phone number.
* @param name  the customer's name (cannot be blank)
* @param email the customer's email (cannot be blank)
* @param phone the customer's phone number (cannot be blank)
* @throws IllegalArgumentException if any required field is blank
*/
	public Customer(String name, String email, String phone) {
		this.ID = UUID.randomUUID().toString();
		this.name = required(name,"Name");
		this.email = required(email,"Email");
		this.phone = required(phone,"Phone #");
	}
	
/**
* Validates that a required field is not null or empty.
* @param val   the value to check
* @param field the name of the field
* @return the validated value
* @throws IllegalArgumentException if value is null or empty
*/
	private String required(String val, String field) {
		if(val == null||val.isBlank()) {
			throw new IllegalArgumentException("Please fill out "+field);
		}
		return val;
	}
	
//GETTER
	public String getID() {
		return ID;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public String getPhone() {
		return phone;
	}
	
	@Override
	public String toString() {
		return name + " ("+email+")";
	}
}