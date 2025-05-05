package repository;
import model.Sale;

import java.util.*;
///Handles sale transaction records.
public interface InterfaceSaleRepo {
	void recordSale(Sale sale);
	
	List<Sale> getSaleByCustomerId(String customerId);
	List<Sale> getAllSales();
}