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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.ClientUI;

public class CustomerEm_Handeling_Cop_controller  extends AbstractController implements  Initializable{
    private String Clientid;
    
    @FXML
    private Button backBtn;
    

    @FXML
    private TextField RefunText;

    @FXML
    private Label upLbl;

    @FXML
    private Button finishBtn;

    @FXML
    void Back(ActionEvent event) throws IOException {
    	stopPopUp(event);
    }


	@FXML
    void Update_Complaint_det(ActionEvent event) throws IOException {
		try {
        String refund =this.RefunText.getText();
        if(Integer.parseInt(refund)>0) 
        {
        	ArrayList<String> arr = new ArrayList<String>();
        	arr.add(Clientid);
        	arr.add("Customer service responded");
        	arr.add(refund);
        	ClientUI.chat.accept(new Message(MessageType.UpdateCompLaintDetails,arr));
        	ClientUI.chat.accept(new Message(MessageType.Update_refund,arr));
        	stopPopUp(event);
        }
		}catch(NumberFormatException e){
			
		}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println("nnnn");
		if( CustomerEmTableComplaintsScreenController.complaint.getAlert()==1) {
			this.upLbl.setText("Its recommended to compensate that client because he waited a long time for the response.");
		}
		Clientid=CustomerEmTableComplaintsScreenController.complaint.getClientId();
		
	}

	@Override
	public void display(String string) {
		
	}

}