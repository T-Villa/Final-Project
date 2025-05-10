/**
* Controller class for the Customer View in the JavaFX application.
* Displays customer table and provides buttons for adding/removing customers and filtering.
* 
* @author Thomas Villareale
*/
package stage;

import control.CustomerManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Customer;
import util.ButtonController;
import util.Filter;

public class CustomerViewController {
	private final CustomerManager customerManager;
	
/**
* Builds and returns the Customer View layout node.
     * @return Node representing the customer view layout
*/
	public Node getView() {
		TableView<Customer> table = new TableView<>();
		ObservableList<Customer> customerData = FXCollections.observableArrayList(customerManager.getAllCustomers());
		table.setItems(customerData);
		
		//Columns
		TableColumn<Customer,String> idColumn = new TableColumn<>("ID");
			idColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getID()));
		TableColumn<Customer,String> nameColumn = new TableColumn<>("Name");
			nameColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getName()));
		TableColumn<Customer,String> emailColumn = new TableColumn<>("Email");
			emailColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getEmail()));
		TableColumn<Customer,String> phoneColumn = new TableColumn<>("Phone #");
			phoneColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getPhone()));
			
		table.getColumns().addAll(idColumn,nameColumn,emailColumn,phoneColumn);
			
		Button addCustomer = ButtonController.addCustomerButton(customerData, customerManager, table);
		Button removeCustomer = ButtonController.removeCustomerButton(customerManager, table);		

		HBox buttons = new HBox(10, addCustomer, removeCustomer);
	     buttons.setPadding(new Insets(10));
	     
	     HBox filter = new HBox(10, Filter.customerFilter(customerManager, customerData));
	        filter.setPadding(new Insets(10));
		
		 VBox layout = new VBox(10, filter, table, buttons);
		 layout.setPadding(new Insets(10));

	     return layout;		
	}
    
/**
* Constructs a CustomerViewController.
     * @param customerManager the CustomerManager instance
*/
		public CustomerViewController(CustomerManager customerManager) {
			this.customerManager = customerManager;
		}
}