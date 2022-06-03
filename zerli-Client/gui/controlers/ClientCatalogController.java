package controlers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Entities.ClientCart;
import Entities.ItemInOrder;
import Entities.Item_In_Catalog;
import Entities.Message;
import Entities.MessageType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.ClientUI;

public class ClientCatalogController extends AbstractController implements Initializable {
	
    
	public static boolean catalog_Initilaize = false ; 
	public static ArrayList<Item_In_Catalog> Catalog= new ArrayList<>();
	public static ArrayList<Item_In_Catalog> assembledCatalog = new ArrayList<>();
	Item_In_Catalog tempItem;
	public String TypeOfProduct;
	private ClientCart cart;
	int itemId;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
   
    @FXML
    public ChoiceBox<String> listOfProduct;

    @FXML
    private Button BridelBouquetBtn;

    @FXML
    private Button FlowerPotsBtn;

    @FXML
    private Button CartBtn;

    @FXML
    private Label NumberOfProductLbl;
    
    @FXML
    private Button ColorfulBouquetsBtn;

    @FXML
    private Button BackBtn;

    @FXML
    private Label UpdateTypeProLabel;

    @FXML
    private Button PlusBtn;

    @FXML
    private Label QuanLbl;

    @FXML
    private Label TotalPriceLbl;

    @FXML
    private Label productNameLbl;
    
    @FXML
    private Label productPriceLbl;

    @FXML
    private Label productColorLbl;
    
    @FXML
    private ImageView productImage;

    @FXML
    private ImageView cartImage;

    @FXML
    private Button SingleProductsBtn;


    @FXML
    void Back(ActionEvent event) throws IOException {
    	start(event, "ClientCreateOrderScreen", "Create Order", "");
    }
    @FXML
    void Tocart(ActionEvent event) throws IOException {
       start(event, "ClientCartScreen", "Cart Screen", "");
    }
    
    @FXML
    void AddQuan(ActionEvent event) {
    	if(tempItem!=null ) {
    		 cart.AddToCart(tempItem);
    		 updateslbl();
  
          }
    }

    @FXML
    void DecQuan(ActionEvent event) {
    	if(tempItem!=null) {
    	    cart.DecFromCart(tempItem);
    	    updateslbl();
    	}
    }
    
    private void updateslbl() {
    	TotalPriceLbl.setText("Price : "+cart.getTotalPrice(itemId).toString()+"$");
 	    QuanLbl.setText(cart.getQuanOfProInCart(itemId).toString());
 	    this.NumberOfProductLbl.setText(ClientCartScreenController.cart.getNumberOfItems().toString());
    }
    	
    

    @FXML
    void ShowBridelProductsCatalog(ActionEvent event) {
    	InitialPerType("Bridel bouquets");
    }

    @FXML
    void ShowColorfulBouquetScreen(ActionEvent event) {
    	InitialPerType("colorful bouquets");
    }

    @FXML
    void showFlowerPotsScreen(ActionEvent event) {
    	InitialPerType("pots");
    }
    
    @FXML
    void SingleProducts(ActionEvent event) {
      	InitialPerType("Assembled Items");
    }
    
    @FXML
    void getItemDetails(ActionEvent event) {
        String Choosing_Item = listOfProduct.getValue();
    	try {
    	   if(!TypeOfProduct.equals("Assembled Items")) {
              for (Item_In_Catalog i : Catalog) {
              	if (i.getName().compareTo(Choosing_Item)==0){
              		this.tempItem = new Item_In_Catalog(i.getId(), i.getColor(), i.getName(),
         				   i.getType(), i.getPrice(), i.isAssembleItem());
              		this.itemId=i.getId();
         		     break;
        	 }
              }
              	
              	
           }
    	   else {
               for (Item_In_Catalog i : assembledCatalog) {
                 	if (i.getName().compareTo(Choosing_Item)==0){
                 				this.tempItem = new Item_In_Catalog(i.getId(), i.getColor(), i.getName(),
            				   i.getType(), i.getPrice(), i.isAssembleItem());
                 				this.itemId=i.getId();
                 				break;
                 		}
               }
    	   }
     
     	
              productNameLbl.setText(tempItem.getName());
              productPriceLbl.setText("Price :"+(tempItem.getPrice()).toString()+" $");
              productColorLbl.setText("Dominant color : "+(tempItem.getColor()).toString());
              Image im= new Image("/images/"+tempItem.getName()+".JPG");
              this.productImage.setImage(im);
              

              updateslbl();
             }catch(Exception e) {}
    }
    

    /*
     * This function initialize the choice box according the product type choosing by the user
     */
    
    private void InitialPerType(String typeName) {
    	setSelection();
        this.TypeOfProduct = typeName;
     	this.listOfProduct.setDisable(false);
    	listOfProduct.getItems().clear();
    	ArrayList<String> Items =  new ArrayList<String>();
    	if(!typeName.equals("Assembled Items")) {
    		for (Item_In_Catalog i : Catalog) {
    			if (i.getType().compareTo(typeName)==0) {
    				Items.add(i.getName());
    			}	
    		}
    	}
    	else {
    		for (Item_In_Catalog i : assembledCatalog) {
    			if (i.getType().compareTo(typeName)==0) {
    				Items.add(i.getName());

    			}
    		}
    	}
    	listOfProduct.getItems().addAll(Items);
    	UpdateTypeProLabel.setText(TypeOfProduct);
    	
    	}

    private void setSelection() {
    	tempItem =null;
    	TotalPriceLbl.setText("Price : "+cart.getTotalPrice(itemId).toString()+"$");
 	    QuanLbl.setText("0");
        productNameLbl.setText("");
        productPriceLbl.setText("");
        productColorLbl.setText("");
        this.productImage.setImage(null);
    	listOfProduct.setValue("choose product from list");
    	
    }
	@Override
	public void display(String string) {
	
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ClientUI.chat.accept(new Message(MessageType.Initialize_Catalog,"0"));
		ClientUI.chat.accept(new Message(MessageType.Initialize_Catalog,"1"));
		this.cart = ClientCartScreenController.cart;
		setSelection();
    	this.listOfProduct.setDisable(true);
		this.NumberOfProductLbl.setText(ClientCartScreenController.cart.getNumberOfItems().toString());
		CartBtn.setStyle("-fx-background-color: transparent;");
		CartBtn.setGraphic(cartImage);
    	UpdateTypeProLabel.setText("<--Choose category");
	}

}