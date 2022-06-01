
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
import Entities.Complaint;
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

	public class CustomerEmTableComplaintsScreenController extends AbstractController implements  Initializable{
        
		public static ArrayList<Complaint> complaints ;
		public static Complaint complaint;
		@FXML
		private ResourceBundle resources;

		@FXML
		private URL location;
		
	    @FXML
	    private TableView<Complaint> table;

	    @FXML
	    private TableColumn<Complaint, String> clientIdCol;

	    @FXML
	    private TableColumn<Complaint, String>  StatusCol;

	    @FXML
	    private TableColumn<Complaint, Time>  ComplaintTimeCol;

	    @FXML
	    private TableColumn<Complaint, String>  ReasonCol;

	    @FXML
	    private TableColumn<Complaint, String>  RefundCol;

	    @FXML
	    private Button BackBtn;

	    @FXML
	    private Label tableComplaintlbl;

	    @FXML
	    private TextArea compalintText;

	    @FXML
	    private Button DisplayComBtn;

	    @FXML
	    private Button InsertNewComBtn;

	    @FXML
	    private Button HandlingComBtn;

	    @FXML
	    private Label UpLbl;

		private long difference_In_Hours;
	    
	    @FXML
	    void Back(ActionEvent event) throws IOException {
	    	start(event, "CustomerEm_main_page", "Customers service employee screen", "");
	    }

	    @FXML
	    void DispalyComplaint(ActionEvent event) throws IOException {
	    	this.compalintText.setText("");
	    	UpLbl.setText("");
	        CustomerEmTableComplaintsScreenController.complaint = null;
	        CustomerEmTableComplaintsScreenController.complaint = table.getSelectionModel().getSelectedItem();
	        if(CustomerEmTableComplaintsScreenController.complaint!=null) {
				this.compalintText.setText(complaint.toString());
	        }
	        else {
	        	UpLbl.setText("Please first select complaint from the table.");
	        	return;
	        }
	        calc_difference_In_Hours(complaint);
	        if(complaint.getStatus().equals("Waiting for response")&& Math.abs(this.difference_In_Hours)>=24 && complaint.getAlert()!=1) {
	        	startPopUp(event, "CustomerEm_DelayAlert", "","");
               
	        }
	    }
	    
	    private void calc_difference_In_Hours(Complaint complaint) {
	    	
    		Timestamp TimeOfComplaint = complaint.getComplaintTime();
    		Timestamp currentTime = Timestamp.valueOf(LocalDateTime.now());
    		long difference_In_Time= TimeOfComplaint.getTime() - currentTime.getTime();
    		int seconds = (int) difference_In_Time / 1000;
    		long difference= seconds/3600 ;
    		this.difference_In_Hours = difference;
    }   
	    	    
	    @FXML
	    void HandlingComFunc(ActionEvent event) throws IOException {
	    	this.compalintText.setText("");
	    	UpLbl.setText("");
	        CustomerEmTableComplaintsScreenController.complaint = null;
	        CustomerEmTableComplaintsScreenController.complaint = table.getSelectionModel().getSelectedItem();
	        if(CustomerEmTableComplaintsScreenController.complaint!=null) {
	        	if(CustomerEmTableComplaintsScreenController.complaint.getStatus().equals("Customer service responded")) {
		        	UpLbl.setText("Customer Service has already replied to the complaint.");
		        	return;
	        	}
	        	startPopUp(event, "CustomerEm_Handeling_Com", "","");
                initialize(location, resources) ;
	        }
	        else {
	        	UpLbl.setText("Please first select complaint from the table.");
	        	return;
	        }
	    	
	    	
	    }

	    @FXML
	    void InsertNewFunc(ActionEvent event) throws IOException {
	    	startPopUp(event, "CustomerEm_Insert_Com", "","");
            initialize(location, resources) ;
	    }



	@Override
	public void display(String string) {
		
	}
	


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ClientUI.chat.accept(new Message(MessageType.UpdateCompList,""));
		ObservableList<Complaint> observableList = FXCollections.observableArrayList(complaints);
		table.getItems().clear();
		clientIdCol.setCellValueFactory(new PropertyValueFactory<>("ClientId"));
		StatusCol.setCellValueFactory(new PropertyValueFactory<>("Status"));
		ReasonCol.setCellValueFactory(new PropertyValueFactory<>("Reason"));
		RefundCol.setCellValueFactory(new PropertyValueFactory<>("Refund"));
		ComplaintTimeCol.setCellValueFactory(new PropertyValueFactory<>("ComplaintTime"));
		table.setItems(observableList);		
       
	}
		
}
