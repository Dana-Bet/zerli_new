	package controlers;

	import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Entities.Complaint;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class ClientSendComplaintController  extends AbstractController implements Initializable {  
	    Complaint complaint ;
        private String Topic = null;
        
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

	    
	    
	    
	    @FXML
	    void BackToOrders(ActionEvent event) throws IOException {
			stopPopUp(event);
	    }

	    @FXML
	    void SendComplaint(ActionEvent event) throws IOException {
			stopPopUp(event);
	    }

	    @FXML
	    void chooseTopic(ActionEvent event) {

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
			String[] data = string.split("#");
			this.complaint = new Complaint(Integer.valueOf(data[0]), data[2],null, null, null, null,  data[1], null);

		    
			
		}

	}


