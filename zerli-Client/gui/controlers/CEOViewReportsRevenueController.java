package controlers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Entities.Message;
import Entities.MessageType;
import Entities.OrdersReport;
import Entities.RevenueReport;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.ClientUI;

public class CEOViewReportsRevenueController extends AbstractController implements  Initializable{

    @FXML
    private ComboBox<String> storeBtn;

    @FXML
    private TableView<RevenueReport> table;

    @FXML
    private TableColumn<RevenueReport,String> monthcol;

    @FXML
    private TableColumn<RevenueReport,String> yearcol;

    @FXML
    private TableColumn<RevenueReport,String> amountcol;

    @FXML
    private TableColumn<RevenueReport,String> incomecol;

    @FXML
    private TableColumn<RevenueReport,String> Quarterlycol;

    @FXML
    private Button showBtn;
    
    @FXML
    private Button backBtn;
    
    public static ArrayList<String> stores = new ArrayList<String>();
    public static String store;
    public static ArrayList<RevenueReport> revenue= new ArrayList<RevenueReport>();
    @FXML
    void ShowRevenueReports(ActionEvent event) {
    	store = storeBtn.getValue();
    	ClientUI.chat.accept(new Message(MessageType.showRevenueReportsForCEO,store));
     	ObservableList<RevenueReport> observableList3 = FXCollections.observableArrayList(revenue);
     	monthcol.setCellValueFactory(new PropertyValueFactory<RevenueReport, String>("month"));
     	yearcol.setCellValueFactory(new PropertyValueFactory<RevenueReport, String>("year"));
     	incomecol.setCellValueFactory(new PropertyValueFactory<RevenueReport, String>("income"));
     	Quarterlycol.setCellValueFactory(new PropertyValueFactory<RevenueReport, String>("Quarterly"));
     	amountcol.setCellValueFactory(new PropertyValueFactory<RevenueReport, String>("ordersamount"));
    	System.out.println(revenue);
    	table.setItems(observableList3);
    }
    
    @FXML
    void Back(ActionEvent event) throws IOException {
    	start(event,"CEOMainScreen","","");
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ClientUI.chat.accept(new Message(MessageType.getStoresForCEORevenueReports,null));
		System.out.println("line 68");
		ObservableList<String> observableList2 = FXCollections.observableArrayList(stores);
		storeBtn.setItems(observableList2);	
		}

	@Override
	public void display(String string) {
		// TODO Auto-generated method stub
	}
	


}
