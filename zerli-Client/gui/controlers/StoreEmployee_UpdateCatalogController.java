package controlers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class StoreEmployee_UpdateCatalogController extends AbstractController implements Initializable { 

    @FXML
    private Label updateCatalog;

    @FXML
    private Button updatePriceBtn;

    @FXML
    private Button updateQuantityBtn;

    @FXML
    private Button backBtn;

    @FXML
    void Update_Quan(ActionEvent event) throws IOException {
    	start(event, "StoreEmployee_update_Inventory", "Update Catalog page","");
    }

    @FXML
    void backToMain(ActionEvent event) throws IOException {
    	start(event, "StoreEmployeeMainPage", "Customer Screen", "");
    }

    @FXML
    void updatePrice(ActionEvent event) throws IOException {
    	start(event, "StoreEmployee_update_Item", "Update Catalog page","");
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
