/**
 * Manages customer records and provides data access operations.
 * Implements the InterfaceCustomerRepo interface.
 * Handles adding, retrieving, listing, and removing customers.
 * 
 * @author Thomas Villareale
 */
package control;

import model.Customer;
import repository.InterfaceCustomerRepo;

import java.util.*;

public class CustomerManager implements InterfaceCustomerRepo{
	private final Map<String, Customer> customers = new HashMap<>();
	
	@Override
    public void addCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer must not be null");
        }
        if (customers.containsKey(customer.getID())) {
            throw new IllegalArgumentException("This customer ID already exists");
        }
        customers.put(customer.getID(), customer);
    }
	
	@Override
    public Customer getCustomer(String ID) {
        return customers.get(ID);
    }
	
	 @Override
	 public List<Customer> getAllCustomers() {
		 return new ArrayList<>(customers.values());
	 }
	 
	 @Override
	 public boolean removeCustomer(String ID) {
		 return customers.remove(ID) != null;
	 }
}