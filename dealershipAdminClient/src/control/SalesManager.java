/** Handles sales processing updates inventory and customer data
 * Methods:
 * processSale(), generateReport(), getSalesByCustomer()
 **/
package control;

import model.Sale;
import repository.InterfaceSaleRepo;
import model.Car;
import model.Customer;

import java.util.*;

public class SalesManager implements InterfaceSaleRepo {
	private final List<Sale> sales = new ArrayList<>();
	
	@Override
    public void processSale(Car car, Customer customer, String seller, double price) {
        if (car == null || customer == null) {
            throw new IllegalArgumentException("Car and Customer must be filled out");
        }
        if (seller == null || seller.isEmpty()) {
            throw new IllegalArgumentException("Seller must be provided");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        }

        Sale newSale = new Sale(car.getSKU(), seller, car, customer, seller, price);
        sales.add(newSale);
        car.setAvailability(false);
    }
	
    @Override
    public List<Sale> genReport() {
        return new ArrayList<>(sales);
    }
	
    @Override
    public List<Sale> getSalesBySeller(String seller) {
        if (seller == null) return List.of();
        return sales.stream().filter(sale -> sale.getSeller().equals(seller)).toList();
    }
    
	public static double calculateTotalRev(List<Sale> sales) {
		return sales.stream().mapToDouble(Sale::getPrice).sum();	
		}
	
	public static int countSales(List<Sale> sales) {
		return sales.size(); 
	}
}