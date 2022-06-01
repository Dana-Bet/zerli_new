 
package controlers;

import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.util.ArrayList;
import java.util.ResourceBundle;
import Entities.Message;
import Entities.MessageType;
import Entities.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.ClientUI;

public class ManagerOrdersController extends AbstractController implements Initializable {
	public static ArrayList<Order> listOfOrders ;
    private String Status;
    private int OrderNum;
    
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;
	
    @FXML
    private TableView<Order> table;

    @FXML
    private TableColumn<Order, Integer> orderNumberCol;

    @FXML
    private TableColumn<Order, String> storeCol;

    @FXML
    private TableColumn<Order, String> greetingCardCol;

    @FXML
    private TableColumn<Order, Float> priceCol;

    @FXML
    private TableColumn<Order, String> DeliveryMethodCol;

    @FXML
    private TableColumn<Order, String> suppTimeCol;

    @FXML
    private TableColumn<Order, String> suppDateCol;

    @FXML
    private TableColumn<Order,Time> OrderTimeCol;
    
    @FXML
    private TableColumn<Order, String> statusCol;

    @FXML
    private Button backBtn;
    
    @FXML
    private Button CancelBtn;

    @FXML
    private Button ConfirmBtn;
    
    @FXML
    private Label upLbl;


    @FXML
    void CancelSelectedOrder(ActionEvent event) throws IOException {
    	upLbl.setText("");
        ObservableList<Order> list;
        list = this.table.getSelectionModel().getSelectedItems();
        if(list!=null) {
        	Status=list.get(0).getStatus();
        	OrderNum=list.get(0).getOrderNumber();
        	if(Status.equals("There is a request to cancel")) {
        		ArrayList<String> arr = new ArrayList<String>();
        		arr.add("canceled");
        		arr.add(String.valueOf(OrderNum));
        		ClientUI.chat.accept(new Message(MessageType.UpdateOrderStatus,arr));
        		arr.set(0,list.get(0).getClientId());
        		ClientUI.chat.accept(new Message(MessageType.UpdateCreditForClient,arr));
                initialize(location, resources) ;
        	}
        	else 
        	{
            	upLbl.setText("This order already canceled");
            	return;
        	}

       }
        else 
        {
        	upLbl.setText("Please select order from the table.");
        	return;
        }
    }

    @FXML
    void ConfirmOrder(ActionEvent event) {

    }
    
    @FXML
    void backMainPage(ActionEvent event) throws IOException {
    	start(event, "MainManagerScreen", "Customer Screen", "");
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	this.upLbl.setText("");
		ClientUI.chat.accept(new Message(MessageType.Get_Orders_by_Store,LoginScreenController.user.getHomeStore()));
		ObservableList<Order> observableList = FXCollections.observableArrayList(listOfOrders);
		table.getItems().clear();
		orderNumberCol.setCellValueFactory(new PropertyValueFactory<>("OrderNumber"));
		storeCol.setCellValueFactory(new PropertyValueFactory<>("Store"));
		priceCol.setCellValueFactory(new PropertyValueFactory<>("Price"));
		greetingCardCol.setCellValueFactory(new PropertyValueFactory<>("GreetingCard"));
		statusCol.setCellValueFactory(new PropertyValueFactory<>("Status"));
		DeliveryMethodCol.setCellValueFactory(new PropertyValueFactory<>("SupplimentMethod"));
		suppTimeCol.setCellValueFactory(new PropertyValueFactory<>("SuppTime"));
		suppDateCol.setCellValueFactory(new PropertyValueFactory<>("SuppDate"));
		OrderTimeCol.setCellValueFactory(new PropertyValueFactory<>("OrderTime"));
		table.setItems(observableList);	
	}

	@Override
	public void display(String string) {
		// TODO Auto-generated method stub
		
	}

}
