package control;
/**Manages customer records and search
**/
import model.Customer;
import java.util.*;

public class CustomerManager {
	private final Map<String, Customer> customers = new HashMap<>();
	
	public void addCustomer(Customer customer) {
		if(customers.containsKey(customer.getID())){
			throw new IllegalArgumentException("This customer ID already exsists");
		}
		else{
			customers.put(customer.getID(),customer);
		}
	}
	
	public Customer getCustomer(String ID) {
		return customers.get(ID);
	}
	
	public List<Customer> getAllCustomers(){
		return new ArrayList<>(customers.values());
	}
	public boolean removeCustomer(String ID) {
		return customers.remove(ID) != null;
	}
}