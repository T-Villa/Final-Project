/** Handles sales processing updates inventory and customer data
 * Methods:
 * processSale(), generateReport(), getSalesByCustomer()
 **/
package control;

import model.Sale;
import model.Car;
import model.Customer;

import java.util.*;

public class SalesManager {
	private final List<Sale> sales = new ArrayList<>();
	
	public void processSale(Car car, Customer customer, double price) {
		if(car == null || customer == null) {
			throw new IllegalArgumentException("Car and Customer must be filled out");
		}
		
		Sale newSale = new Sale(car.getSKU(), customer.getID(),price);
		sales.add(newSale);
		
		car.setAvailability(false);
	}
	
	public List<Sale> genReport(){
		return new ArrayList<>(sales);
	}
	
	public List<Sale> getSalesByCustomer(String customer){
		return sales.stream().filter(sale -> sale.getCustomer().equals(customer)).toList();
	}
}