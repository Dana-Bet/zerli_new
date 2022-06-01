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
import javafx.scene.control.Label;
import main.ClientUI;

public class PopupCancelOrderController extends AbstractController implements Initializable {
    private String  OrderNumber;
    private String Price;
    private String Refund;
    private String Userid;
    private String reason = "Customer want to cancel";
    private double refundCalc;

    @FXML
    private Button cancelBtn;

    @FXML
    private Button ConfirmBtn;

    @FXML
    private Label msgLbl;

    @FXML
    private Label queLbl;

    @FXML
    void CancelAction(ActionEvent event) throws IOException {
    	stopPopUp(event);
    }

    @FXML
    void ConfirmCancel(ActionEvent event) throws IOException {
		ArrayList<String> arr = new ArrayList<String>();
		arr.add("There is a request to cancel");
		arr.add(String.valueOf(OrderNumber));
		ClientUI.chat.accept(new Message(MessageType.UpdateOrderStatus,arr));
		arr.set(0, Userid);
		int Amount = (int) (Float.valueOf(Price)*refundCalc); 
		arr.add(Refund);
		arr.add(String.valueOf(Amount));
		ClientUI.chat.accept(new Message(MessageType.UpdateOrderCancel,arr));
		stopPopUp(event);
    }

	@Override
	public void display(String string) {
		String[] data = string.split("#");
	    OrderNumber = data[1];
	    Refund = data[0];
	    Price = data[2];
	    Userid =data[3]; 
		
		switch(Refund) {
		case "Not refund":{
			 msgLbl.setText("The time remaining to deliver your order is less then hour."
			 		+ " If you choose to cancel this order you will pay a full amount.");
			 refundCalc=0;
			 msgLbl.setWrapText(true);
			 break;
	
		}
		case "50% refund":{
			 msgLbl.setText("The time remaining to deliver your order is between 1 and 3 hours."
				 		+ " If you choose to cancel this order you will receive 50% of its cost as refund.");
			 refundCalc=0.5;
			 msgLbl.setWrapText(true);
			 break;
		}
		case "refund all":{
			 msgLbl.setText("The time remaining to deliver your order is more than 3 hours."
				 		+ " If you choose to cancel this order you will receive 100% of its cost as refund.");
			 refundCalc=1;
			 msgLbl.setWrapText(true);
			 break;
		}		
		 
		}
		queLbl.setText("Are you sure you want to continue the action?");
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		 msgLbl.setText("");
	     queLbl.setText("");
		
	}

}
