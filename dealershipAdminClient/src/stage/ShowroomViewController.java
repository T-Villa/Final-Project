package stage;

import model.Car;
import control.InventoryManager;

import java.util.List;

import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class ShowroomViewController {
	private final InventoryManager inventoryManager;
	
	public Node getView(){
		VBox carList = new VBox(10);
		carList.setPadding(new Insets(10));
		
		List<Car> cars = inventoryManager.getAllCars();
		
		for(Car car : cars) {
			HBox carListing = new HBox(10);
			carListing.setPadding(new Insets(5));
			//be able to show image of car
		}
		ScrollPane scrollPane = new ScrollPane(carList);
		Scene scene = new Scene(scrollPane,800,6000);
		
		return scrollPane; // not done
	}
	public ShowroomViewController(InventoryManager inventoryManager) {
		this.inventoryManager = inventoryManager;
	}
	/*public Node getView() {
		VBox Layout = new VBox();
		//car cards
		return new ScrollPane(Layout); 
	}*/
}
