	package controlers;

	import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Entities.Complaint;
import Entities.Message;
import Entities.MessageType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.ClientUI;


public class ClientSendComplaintController  extends AbstractController implements Initializable {  
	    Complaint complaint ;
        private String Topic = null;
        
        private int OrderNum;
        private String price;
        private String userid;
        
        public static Object comExist= null;
        
	    @FXML
	    private Button backBtn;

	    @FXML
	    private Button BtnSendComplaint;

	    @FXML
	    private ChoiceBox<String> TopicsList;

	    @FXML
	    private TextField ContentOfComplaint;

	    @FXML
	    private Label upLbl;
		private String text;

	    
	    
	    
	    @FXML
	    void BackToOrders(ActionEvent event) throws IOException {
	    	this.complaint=null;
			stopPopUp(event);
	    }

	    @FXML
	    void SendComplaint(ActionEvent event) throws IOException {
	    	this.upLbl.setText("");
	    	text = ContentOfComplaint.getText();
	    	if(text ==null || text.isEmpty()) {
	    		this.upLbl.setText("Please insert the complaint content");
	    		return;
	    	}
	    	complaint.setContent(text);
	    	complaint.setComplaintTime(Timestamp.valueOf(LocalDateTime.now()));
	    	if(Topic==null) {
	    		this.upLbl.setText("Please choose topic");
	    		System.out.println("here65");
	    		return;
	    	}

	    	System.out.println("here84");
			ClientUI.chat.accept(new Message(MessageType.UpdateComplaint,complaint));
			//stopPopUp(event);
	    }

	    @FXML
	    void chooseTopic(ActionEvent event) {
	       	this.upLbl.setText("");
             Topic = this.TopicsList.getValue();
             complaint.setReason(Topic);
             
	    }
	     

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			
			ArrayList<String> Topics = new ArrayList<String>();
			Topics.add("I'm not satisfied with the order I received");
			Topics.add("Order didn't arrive on time");
			Topics.add("That's not what I ordered");
			this.TopicsList.getItems().addAll(Topics);
					
		
			
		}

		@Override
		public void display(String string) {
//			ArrayList<String> complaint = string.split("#");
//			userid = data[2];
//			this.complaint = new Complaint(Integer.valueOf(data[0]), data[2],Timestamp.valueOf(LocalDateTime.now()),"waiting for response", " "," ",  data[1], " ");
//
//		    
			
		}

	}


