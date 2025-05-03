package repository;
import model.Customer;

import java.util.*;
//Abstracts access to customer data.
public interface InterfaceCustomerRepo {
	void addCustomer(Customer customer);
	void removeCustoemr(Customer customer);
	
	Optional<Customer> findById(String id);
	List<Customer> getAllCustomers();
}