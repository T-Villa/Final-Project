/**
* Interface for customer data operations.
* Defines methods for managing customer records.
* Implemented by CustomerManager.
* 
* @author Thomas Villareale
*/
package repository;

import model.Customer;
import java.util.*;

public interface InterfaceCustomerRepo {
	void addCustomer(Customer customer);
    Customer getCustomer(String ID);
    List<Customer> getAllCustomers();
    boolean removeCustomer(String ID);

}