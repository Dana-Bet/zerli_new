package controlers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class Client_msg_new_client_controller extends AbstractController implements Initializable {

    @FXML
    private Button backBtn;

    @FXML
    void Back(ActionEvent event) throws IOException {
    	stopPopUp(event);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void display(String string) {
		// TODO Auto-generated method stub
		
	}

}
