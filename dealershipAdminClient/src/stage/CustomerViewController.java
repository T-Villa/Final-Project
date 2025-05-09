// display customer list, add/edit form, view history
package stage;

import model.Customer;
import control.CustomerManager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CustomerViewController {
	private final CustomerManager customerManager;
	
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
			
		Button addCustomerButton = new Button("Add Customer");
		Button removeCustomerButton = new Button("Remove Customer");
		
		addCustomerButton.setOnAction(event -> { 
			Stage formStage = new Stage();
			formStage.setTitle("Add New Customer");
			
			VBox formLayout = new VBox(10);
				formLayout.setPadding(new Insets(10));
			TextField nameField = new TextField();
				nameField.setPromptText("Enter Name");
			TextField emailField = new TextField();
				emailField.setPromptText("Enter Email");
			TextField phoneField = new TextField();
				phoneField.setPromptText("Enter Phone #");
				
			Button submitButton = new Button("Confirm");
			submitButton.setOnAction(e -> {
				try {
					String name = nameField.getText();
					String email = emailField.getText();
					String phone = phoneField.getText();
					
					required(name,"Name");
					required(email,"Email");
					required(phone,"Phone number");
					
					Customer newCustomer = new Customer(name,email,phone);
					customerManager.addCustomer(newCustomer);
					table.getItems().add(newCustomer);
					
					formStage.close();
				} catch (Exception ex) {
					Alert error = new Alert(Alert.AlertType.ERROR, "Invalid Input: "+ ex.getMessage());
					error.showAndWait();
				}
			});
			formLayout.getChildren().addAll(nameField,emailField,phoneField,submitButton);
			Scene formScene = new Scene(formLayout, 300, 250);
			formStage.setScene(formScene);
			formStage.show();
				
		});
		removeCustomerButton.setOnAction(e->{
			Customer choosen = table.getSelectionModel().getSelectedItem();
			if(choosen != null) {
				boolean removed = customerManager.removeCustomer(choosen.getID());
				if(removed) {
					table.getItems().remove(choosen);
				}
				else {
					new Alert(Alert.AlertType.ERROR,"Customer not found").showAndWait();
				}
			}
			else {
				new Alert(Alert.AlertType.ERROR,"Select customer to remove").showAndWait();
			}
	
		});
		HBox buttons = new HBox(10, addCustomerButton, removeCustomerButton);
	     buttons.setPadding(new Insets(10));
		
		 VBox layout = new VBox(10, table, buttons);
		 layout.setPadding(new Insets(10));

	     return layout;		
	}
	 private void required(Object val, String field) {
		 if(val == null) {
			 throw new IllegalArgumentException(field+" must be filled out");
		 }
	 }
		public CustomerViewController(CustomerManager customerManager) {
			this.customerManager = customerManager;
		}
}