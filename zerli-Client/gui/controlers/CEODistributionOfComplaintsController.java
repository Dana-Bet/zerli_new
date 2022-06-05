package controlers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Entities.Message;
import Entities.MessageType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import main.ClientUI;

public class CEODistributionOfComplaintsController extends AbstractController implements  Initializable{

	@FXML
    private ComboBox<String> quarterlyBtn;

    @FXML
    private BarChart<String,Number> table1;

    @FXML
    private Button show1Btn;
    
    @FXML
    private ComboBox<String> year1Btn;

    @FXML
    private Button backBtn;
    
    public static ArrayList<String> quarterly = new ArrayList<String>();
    public static ArrayList<String> years = new ArrayList<String>();
    public static ArrayList<String> details = new ArrayList<String>();
    public static int counter;
    @FXML
    void show1(ActionEvent event) {
    	details.add(quarterlyBtn.getValue());
    	details.add(year1Btn.getValue());
    	ClientUI.chat.accept(new Message(MessageType.getForCEOComplaintsDistribution,details));
    	 Series<String, Number> series = new XYChart.Series<>();
		 series.getData().add(new XYChart.Data<String, Number>("Complaints",counter));
		 table1.getData().add(series);
    	details.clear();
    }
	
    @FXML
    void Back(ActionEvent event) throws IOException {
    	start(event,"CEOMainScreen","","");
    }
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		quarterly.add("1");
		quarterly.add("2");
		quarterly.add("3");
		quarterly.add("4");	
		ObservableList<String> observableList = FXCollections.observableArrayList(quarterly);
		quarterlyBtn.setItems(observableList);
		years.add("2021");
		years.add("2022");
		ObservableList<String> observableList5 = FXCollections.observableArrayList(years);
		year1Btn.setItems(observableList5);
	}

	@Override
	public void display(String string) {
		// TODO Auto-generated method stub
		
	}

}
