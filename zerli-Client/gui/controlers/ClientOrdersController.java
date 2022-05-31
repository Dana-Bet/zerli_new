package controlers;

import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import main.ClientUI;

public class ClientOrdersController extends AbstractController implements Initializable {
	public static ArrayList<Order> list; 
	public static ArrayList<String> recipt=null;
    private Timestamp OrderTime;
    private String SuppDate;
	private String SuppTime;
    private String date;
    private String Status;
    private int  OrderNumber;
    public String Price;
    public String Refund;
	private long difference_In_Hours;

    
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
    private Button displayOrder;

    @FXML
    private TextArea ViewRecipt;

    @FXML
    private Button CancelBtn;

    
    @FXML
    private Label upLbl;


    @FXML
    void CancelSelectedOrder(ActionEvent event) throws IOException {
    	upLbl.setText("");
        Order order = null;
        order = table.getSelectionModel().getSelectedItem();
        if(order!=null) {
        	update_Selected_Order_Fileds(order);
        	if(Status.equals("canceled")||Status.equals("There is a request to cancel")) {
            	upLbl.setText("Your order is "+Status);
            	return;
        	}
        	 calc_difference_In_Hours(order);
            if(difference_In_Hours<=0) {
                upLbl.setText("Order delivery time has passed.");
                return;
            }
            if(difference_In_Hours>0 && difference_In_Hours<=1) {
          	    Refund = "Not refund";
            }
            if(difference_In_Hours>1 && difference_In_Hours<3) {
            	  Refund = "50% refund"; 
            }
            if(difference_In_Hours>=3) {
            	   Refund = "refund all"; 
            }

    		StringBuilder arr =new StringBuilder();
    		arr.append(Refund);
    		arr.append("#");
    		arr.append(String.valueOf(OrderNumber));
    		arr.append("#");
    		arr.append(Price);
    		arr.append("#");
    		arr.append(LoginScreenController.user.getId());

        	startPopUp(event, "CancelPopUpScreen", "Cancel screen",arr.toString());
            initialize(location, resources) ;
        }
        
        else {
        	upLbl.setText("Please select order from the table.");
        	return;
        }
    }

    @FXML
    void DisplaySelectedOrder(ActionEvent event) {
    	upLbl.setText("");
        Order order = null;
        order = table.getSelectionModel().getSelectedItem();
        if(order!=null) {
            int OrderNum =order.getOrderNumber();
			ClientUI.chat.accept(new Message(MessageType.getRecipt,OrderNum));
			updateTextRecipt();
        }
        else {
        	upLbl.setText("Please select order from the table.");
        	return;
        }
    }

        
    private void calc_difference_In_Hours(Order order) {
    	
  
    		DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    		LocalDate localDate = LocalDate.parse(SuppDate, formatter1);

    		DateTimeFormatter parser = DateTimeFormatter.ISO_LOCAL_TIME;
    		LocalTime localTime = LocalTime.parse(SuppTime, parser);

    		LocalDateTime t =   LocalDateTime.of(localDate, localTime);
    		Timestamp suppTimeOfOrder = Timestamp.valueOf(t);
    		
    		Timestamp currentTime = Timestamp.valueOf(LocalDateTime.now());
    		long difference_In_Time= suppTimeOfOrder.getTime() - currentTime.getTime();
    		int seconds = (int) difference_In_Time / 1000;
    		long difference= seconds/3600 ;

    		this.difference_In_Hours = difference;

    }   
    
    private void update_Selected_Order_Fileds(Order order) {

    	OrderTime =order.getOrderTime();
    	SuppDate=order.getSuppDate();
    	

    	String[] s = order.getSuppTime().split(" ");
    	SuppTime=s[0];
    			
    	Status=order.getStatus();
    	Price =order.getPrice();
    	OrderNumber=order.getOrderNumber();
    }
    
    @FXML
    void backMainPage(ActionEvent event) throws IOException {
    	start(event, "ClientMainPage", "Customer Screen", "");
    }
    
    private void updateTextRecipt() {
    	this.ViewRecipt.clear();
    	for (String s : ClientOrdersController.recipt ) {
    		this.ViewRecipt.appendText(s);
    		this.ViewRecipt.appendText("\n");
    	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	this.upLbl.setText("");
		ClientUI.chat.accept(new Message(MessageType.Get_All_Order_by_id,LoginScreenController.user.getId()));
		ObservableList<Order> observableList = FXCollections.observableArrayList(list);
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
        Order order = null;
	}

	@Override
	public void display(String string) {
		// TODO Auto-generated method stub
		
	}

}
