package repository;
///Handles sale transaction records.
public interface InterfaceSaleRepository {
	void recordSale(Sale sale);
	
	List<Sale> getSaleByCustomerId(String customerId);
	List<Sale> getAllSales();
}
