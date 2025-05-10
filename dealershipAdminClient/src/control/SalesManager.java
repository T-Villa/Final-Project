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
	
	public void processSale(Car car, Customer customer,String seller, double price) {
		if(car == null || customer == null) {
			throw new IllegalArgumentException("Car and Customer must be filled out");
		}
		
		Sale newSale = new Sale(car.getSKU(),seller, car, customer,seller, price);
		sales.add(newSale);
		
		car.setAvailability(false);
	}
	
	public List<Sale> genReport(){
		return new ArrayList<>(sales);
	}
	
	public List<Sale> getSalesBySeller(String seller){
		return sales.stream().filter(sale -> sale.getSeller().equals(seller)).toList();
	}
}