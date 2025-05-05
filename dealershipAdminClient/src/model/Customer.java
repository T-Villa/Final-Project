package model;
/**
 * 
 **/
import java.util.UUID;

public class Customer {
	private final String ID;
	private final String name;
	private final String email;
	private final String phone;

//CONSTRUCTOR
	public Customer(String name, String email, String phone) {
		this.ID = UUID.randomUUID().toString();
		this.name = required(name,"Name");
		this.email = required(email,"Email");
		this.phone = required(phone,"Phone #");
	}
	
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
}