package stage;

import control.CustomerManager;
import control.SalesManager;
import model.Customer;
import model.Sale;
import model.Customer;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// sale processing interface
public class SalesViewController {
	private final SalesManager salesManager;
	
	public Node getView() {
		TableView<Sale> table = new TableView<>();
		ObservableList<Sale> salesData = FXCollections.observableArrayList(salesManager.genReport());
		table.setItems(salesData);

		//Columns 
		TableColumn<Sale,LocalDate> saleDateColumn = new TableColumn<>();
			saleDateColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleLocalDateProperty(data.getValue().getSaleDate()));
		TableColumn<Sale,String> idColumn = new TableColumn<>();
			idColumn.setCellValueFacotry(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getSaleID()));
		TableColumn<Sale,String> buyerColumn = new TableColumn<>();
			buyerColumn.setCellValueFacotry(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getCustomer()));
		TableColumn<Sale,Stirng> priceColumn = new TableColumn<>();
			priceColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleDoubleProperty(data.getValue().getPrice()));

		table.getColumns().addAll(saleDateColumn,idColumn,buyerColumn,priceColumn);

		//Buttons

		//Stage
		VBox layout = new VBox(10, table, buttons);
		layout.setPadding(new Insets(10));
		
		return layout;
	}
	public SalesViewController(SalesManager salesManager) {
		this.salesManager = salesManager;
	}
}
