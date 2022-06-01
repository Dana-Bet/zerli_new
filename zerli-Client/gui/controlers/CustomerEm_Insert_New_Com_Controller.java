
package controlers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

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

public class CustomerEm_Insert_New_Com_Controller extends AbstractController implements Initializable{

    @FXML
    private Button backBtn;

    @FXML
    private Button BtnSendComplaint;

    @FXML
    private ChoiceBox<String> TopicsList;

    @FXML
    private TextField IdText;

    @FXML
    private Label upLbl;

	private String Topic;
	private String ClientId;;
	public static boolean ClientEx;
    @FXML
    void Back(ActionEvent event) throws IOException {
    	stopPopUp(event);
    }

    @FXML
    void SendComplaint(ActionEvent event) throws IOException {
    	upLbl.setText("");
         this.ClientId=IdText.getText();
         ClientUI.chat.accept(new Message(MessageType.ClientExist,ClientId));
         if(!ClientEx) {
        	 upLbl.setText("No client has that ID");
        	 return;
         }
         else {
        	 if(TopicsList.getValue().isEmpty()||TopicsList.getValue()==null) {
            	 upLbl.setText("Please choose the complaint reason");
            	 return;
        	 }
        	 ArrayList<String> Com_details = new ArrayList<String>();
        	 Com_details.add(ClientId);
           	 Com_details.add("Waiting for response");
        	 Com_details.add(Topic);
        	 Com_details.add("Not refund");	 
             ClientUI.chat.accept(new Message(MessageType.Upload_Complaint, Com_details));
         	 stopPopUp(event);
         }
    }

    @FXML
    void chooseTopic(ActionEvent event) {
       	this.upLbl.setText("");
         Topic = this.TopicsList.getValue();      
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
		// TODO Auto-generated method stub
		
	}

}
