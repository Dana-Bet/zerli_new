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

public class CEODistributionOfOrdersController extends AbstractController implements  Initializable{

	 @FXML
	    private ComboBox<String> store1Btn;

	    @FXML
	    private ComboBox<String> quarterlyBtn;

	    @FXML
	    private BarChart<String,Number> table1;

	    @FXML
	    private ComboBox<String> store2Btn;

	    @FXML
	    private ComboBox<String> quarterly2Btn;

	    @FXML
	    private BarChart<String, Number> table2;

	    @FXML
	    private Button show1Btn;

	    @FXML
	    private Button show2Btn;
	    
	    @FXML
	    private ComboBox<String> year1Btn;

	    @FXML
	    private ComboBox<String> year2Btn;

	    @FXML
	    private Button backBtn;
	    
	   public static ArrayList<String> stores = new ArrayList<String>();
	   public static ArrayList<String> quarterly = new ArrayList<String>();
	   public static ArrayList<String> details = new ArrayList<String>();
	   public static ArrayList<String> years = new ArrayList<String>();
	   public static String income = null;
	   public static int temp;
	   @FXML
	    void ShowStore1(ActionEvent event) {
		 details.add(store1Btn.getValue());
		 details.add(quarterlyBtn.getValue());
		 details.add(year1Btn.getValue());
		 System.out.println(details.toString());
		 ClientUI.chat.accept(new Message(MessageType.SetDetailsInTable1ForCEOordersDistribution,details));
		 temp = Integer.parseInt(income);
		 Series<String, Number> series = new XYChart.Series<>();
		 series.getData().add(new XYChart.Data<String, Number>("INCOME",temp));
		 table1.getData().add(series);
		 details.clear();
		 temp = 0;
	    }

	    @FXML
	    void ShowStore2(ActionEvent event) {
			 details.add(store2Btn.getValue());
			 details.add(quarterly2Btn.getValue());
			 details.add(year2Btn.getValue());
			 ClientUI.chat.accept(new Message(MessageType.SetDetailsInTable1ForCEOordersDistribution,details));
			 temp = Integer.parseInt(income);
			 Series<String, Number> series = new XYChart.Series<>();
			 series.getData().add(new XYChart.Data<String, Number>("INCOME",temp));
			 table2.getData().add(series);
			 details.clear();
			 temp = 0;
	    }
	    
	    @FXML
	    void Back(ActionEvent event) throws IOException {
	    	start(event,"CEOMainScreen","","");
	    }
	    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ClientUI.chat.accept(new Message(MessageType.getStoresForCEOordersDistribution,null));
		ObservableList<String> observableList = FXCollections.observableArrayList(stores);
		store1Btn.setItems(observableList);
		quarterly.add("1");
		quarterly.add("2");
		quarterly.add("3");
		quarterly.add("4");	
		ObservableList<String> observableList2 = FXCollections.observableArrayList(quarterly);
		quarterlyBtn.setItems(observableList2);
		ClientUI.chat.accept(new Message(MessageType.getYearsForCEOordersDistribution,null));
		ObservableList<String> observableList5 = FXCollections.observableArrayList(years);
		year1Btn.setItems(observableList5);
		
		ObservableList<String> observableList3 = FXCollections.observableArrayList(stores);
		store2Btn.setItems(observableList3);
		ObservableList<String> observableList4 = FXCollections.observableArrayList(quarterly);
		quarterly2Btn.setItems(observableList4);
		ObservableList<String> observableList6 = FXCollections.observableArrayList(years);
		year2Btn.setItems(observableList6);
	}

	@Override
	public void display(String string) {
		// TODO Auto-generated method stub
	
	}

}
