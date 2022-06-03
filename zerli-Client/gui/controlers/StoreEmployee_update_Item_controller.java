
	package controlers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import Entities.Item_In_Catalog;
import Entities.Message;
import Entities.MessageType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import main.ClientUI;

public class StoreEmployee_update_Item_controller extends AbstractController implements Initializable {
		public static ArrayList<Item_In_Catalog> All_Items_In_Catalog; 
		@FXML
		private ResourceBundle resources;

		@FXML
		private URL location;
	    
	    @FXML
	    private TableView<Item_In_Catalog> table;

	    @FXML
	    private TableColumn<Item_In_Catalog, String> ProNameCol;

	    @FXML
	    private TableColumn<Item_In_Catalog, String> IdCol;

	    @FXML
	    private TableColumn<Item_In_Catalog, String> TypeCol;

	    @FXML
	    private TableColumn<Item_In_Catalog, Float> PriceCol;

	    @FXML
	    private TableColumn<Item_In_Catalog, String> ColorCol;

	    @FXML
	    private Button BackBtn;

	    @FXML
	    private Label UpLbl;

	    @FXML
	    private TextField newPriceText;

	    @FXML
	    private Button UpdateBtn;
	    
	    
	    @FXML
	    void UpdatePrice(ActionEvent event) {
	    	this.UpLbl.setText("");
	        Item_In_Catalog Item = null;
	        Item = table.getSelectionModel().getSelectedItem();
	        if(Item!=null) {
	            String newPrice = null;
	            newPrice = this.newPriceText.getText();
	            if (newPrice==null || !(Integer.parseInt(newPrice)>0)||newPrice.isEmpty()) {
			    	this.UpLbl.setText("Please insert valid value");
			    	return;
	            }
	        	ArrayList<String> details = new ArrayList<String>();
	        	details.add(newPrice);
	        	details.add(Item.getType());
	        	details.add(Item.getName());

				ClientUI.chat.accept(new Message(MessageType.UpdatePriceToItem,details));
			    initialize(location, resources) ;
	        
	        }
	        else {
		    	this.UpLbl.setText("Please select item from table");
		    	return;
	        }
	        
	        
	        }
	        
	        
	    
	    @FXML
	    void Back(ActionEvent event) throws IOException {
	    	start(event, "StoreEmployee_update_Catalog", "Update catalog", "");
	    }


		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
	    	this.UpLbl.setText("");
			ClientUI.chat.accept(new Message(MessageType.Get_All_Items_In_Catalog,""));
			ObservableList<Item_In_Catalog> observableList = FXCollections.observableArrayList(All_Items_In_Catalog);
			table.getItems().clear();
			ProNameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
			IdCol.setCellValueFactory(new PropertyValueFactory<>("Id"));
			TypeCol.setCellValueFactory(new PropertyValueFactory<>("Type"));
			PriceCol.setCellValueFactory(new PropertyValueFactory<>("Price"));
			ColorCol.setCellValueFactory(new PropertyValueFactory<>("Color"));
			table.setItems(observableList);	
			
		}

		@Override
		public void display(String string) {
			// TODO Auto-generated method stub
			
		}

}
