package Parsing;

import java.util.ArrayList;

import Entities.ClientCart;
import Entities.Complaint;
import Entities.CreditCard;
import Entities.Item_In_Catalog;
import Entities.Message;
import Entities.Order;
import Entities.OrdersReport;
import Entities.Product_In_Inventory;
import Entities.RevenueReport;
import Entities.Store;
import Entities.User;
import controlers.CEODistributionOfComplaintsController;
import controlers.CEODistributionOfOrdersController;
import controlers.CEOViewReportsOrdersController;
import controlers.CEOViewReportsRevenueController;
import controlers.ClientCartScreenController;
import controlers.ClientAssemblyProductController;
import controlers.ClientCatalogController;
import controlers.ClientMainPageController;
import controlers.ClientOrderPageController;
import controlers.ClientOrdersTableController;
import controlers.LoginScreenController;
import controlers.ManagerAddAccountController;
import controlers.ManagerFreezeController;
import controlers.ManagerOrdersController;
import controlers.ManagerSendCancellationEmailController;
import controlers.ManagerViewReportsOrders;
import controlers.ManagerViewReportsRevenueController;
import controlers.ClientPaymentScreenController;
import controlers.StoreEmployee_update_Inventory_controller;
import controlers.StoreEmployee_update_Item_controller;
import controlers.CustomerEmTableComplaintsScreenController;
import controlers.CustomerEm_Insert_New_Com_Controller;
import controlers.CustomerEm_Insert_Survey_Controller;

public class ParsingClient {

	@SuppressWarnings({ "unchecked", "deprecation" })
	public static void Message(Object msg) {
		// TODO Auto-generated method stub
		Message receivedMessage = (Message) msg;
		
		switch (receivedMessage.getMessageType()) {

		case userlogin: {
			String[] DivedMsg = ((String) receivedMessage.getMessageData()).split("#");
			if (!receivedMessage.getMessageData().equals("WrongInput")) {
				if (receivedMessage.getMessageData().equals("Already")) {
					LoginScreenController.statusUser = "The user is already logged in";
					LoginScreenController.user = null;
				} else {
					if (receivedMessage.getMessageData().equals("Freeze")) {
						LoginScreenController.statusUser = "Frozen Account";
						LoginScreenController.user = null;
					} else {
						LoginScreenController.user = new User(DivedMsg[0], DivedMsg[1], DivedMsg[2], DivedMsg[3],
								DivedMsg[4], DivedMsg[5], DivedMsg[6],DivedMsg[7],DivedMsg[8],DivedMsg[9]);
						LoginScreenController.statusUser = "Active";
						ClientCartScreenController.cart = new ClientCart();
					}
				}
			} else {
				LoginScreenController.statusUser = "User name or password are inccorect";
				LoginScreenController.user = null;
			}
		}
		
		case ConfirmOpenNewAccount: {
			ManagerAddAccountController.ConfirmOpenNewAccountFlag = true;
			break;
		}
		case Initialize_Catalog_succ :{
			ArrayList<Item_In_Catalog> Catalog= new ArrayList<>();
			Catalog = (ArrayList<Item_In_Catalog>) (receivedMessage.getMessageData());
			if(Catalog.get(0).isAssembleItem().compareTo("0")==0) {
				ClientCatalogController.Catalog=Catalog;
				ClientCatalogController.catalog_Initilaize = true;
			}
			else {
				ClientAssemblyProductController.Catalog=Catalog;
				ClientCatalogController.assembledCatalog=Catalog;
				ClientAssemblyProductController.catalog_Initilaize = true;
			}
		}
		case getYear: {
			ManagerViewReportsRevenueController.years = (ArrayList<String>) receivedMessage.getMessageData();
			break;
		}
		case getMonth: {
			ManagerViewReportsRevenueController.months = (ArrayList<String>) receivedMessage.getMessageData();
			break;
		}
		case RevenueReports_succ: {
			ManagerViewReportsRevenueController.ViewReportsRevenueFlag = true;
			ManagerViewReportsRevenueController.revenue = (ArrayList<RevenueReport>)(receivedMessage.getMessageData());
			break;
		}
		case getTypeProduct_succ: {
			ManagerViewReportsOrders.productType = (ArrayList<String>)(receivedMessage.getMessageData());
			break;
		}
		case getTypeProductOrders_succ: {
			ManagerViewReportsOrders.productTypeOrders = (ArrayList<OrdersReport>)(receivedMessage.getMessageData());
			break;
		}
		case getCustomerToFreeze_succ:{
			ManagerFreezeController.customerList =  (ArrayList<String>)(receivedMessage.getMessageData());
		    break;
		}

		case InitialShopsList_succ :{
			ArrayList<Store> stores= new ArrayList<>();
			stores = (ArrayList<Store>) (receivedMessage.getMessageData());
			ClientOrderPageController.storesList = stores;
		    break;
		}
		case CreditCardList_succ :{
			ClientPaymentScreenController.cardList =  (ArrayList<CreditCard>) (receivedMessage.getMessageData());
		    break;
		
		}
		case CreditValue_succ :{
			ClientPaymentScreenController.CreditAmmount =receivedMessage.getMessageData() ;
		    break;
		}
		case CreditUsed_succ:{
		    break;
		}
		
		case Add_New_Payment_Method_succ:{
		    break;
		}
		case Add_Order_succ:{
			ClientPaymentScreenController.OrderNum= receivedMessage.getMessageData();	
		    break;
		}
		case IsNewClient_succ:{
			ClientPaymentScreenController.newClient= (ArrayList<Integer>) receivedMessage.getMessageData();
			ClientMainPageController.newClient= (ArrayList<Integer>) receivedMessage.getMessageData();
		    break;
		}
		case UpdateNewClientDiscount_succ:{
		    break;
		}
		case Get_All_Order_by_id_succ:{///+++
			ClientOrdersTableController.list = (ArrayList<Order>) (receivedMessage.getMessageData());
		    break;
		}
		case Update_refund_succ:{
		    break;
		}
		case Get_Orders_by_Store_succ :{
			ManagerOrdersController.listOfOrders = (ArrayList<Order>) (receivedMessage.getMessageData());
		    break;
		}
		case getRecipt_succ:{
			ClientOrdersTableController.recipt =  (ArrayList<String>)( receivedMessage.getMessageData());
		    break;

		}
		case getHomwStoreForCEOordersReports_succ:{
			CEOViewReportsOrdersController.stores = (ArrayList<String>)(receivedMessage.getMessageData());
			 break;
		}
		case getTypeProductForCEOordersReports_succ:{
			CEOViewReportsOrdersController.types = (ArrayList<String>)(receivedMessage.getMessageData());
			 break;
		}
		case getCEOordersReports_succ:{
			CEOViewReportsOrdersController.reports = (ArrayList<OrdersReport>)(receivedMessage.getMessageData());
			 break;
		}

		case UpdateCompList_succ:{
			CustomerEmTableComplaintsScreenController.complaints = (ArrayList<Complaint>) (receivedMessage.getMessageData());
			break;
		}
		case  RefundForClient_succ:{
			ManagerOrdersController.ammount =  receivedMessage.getMessageData();
			break;
		}
		case ClientExist_succ:{
			CustomerEm_Insert_New_Com_Controller.ClientEx=!((ArrayList<String>) (receivedMessage.getMessageData())).isEmpty();
			break;
		}
		case ClientEmailAndPhone_succ :{
			ManagerOrdersController.email_phone = (ArrayList<String>) (receivedMessage.getMessageData());
				break;
		}
		case Get_All_Items_In_Catalog_succ :{
			 StoreEmployee_update_Item_controller.All_Items_In_Catalog =(ArrayList<Item_In_Catalog>) (receivedMessage.getMessageData());
			 break;
		}
		case get_Inventories_succ:{
			StoreEmployee_update_Inventory_controller.Inventories = (ArrayList<Product_In_Inventory>) (receivedMessage.getMessageData());
			 break;
		}
		case getAllClients_succ:{
			CustomerEm_Insert_Survey_Controller.Clients = (ArrayList<String>) (receivedMessage.getMessageData());
			 break;
		}
		case getSurveysId_succ:{
			CustomerEm_Insert_Survey_Controller.Surveys = (ArrayList<String>) (receivedMessage.getMessageData());
			 break;
		}
		case Add_Survey_Result_succ:{
			 break;

		}
		case getHomwStoreForCEORDistributionOfOrders_succ:{
			CEODistributionOfOrdersController.stores = (ArrayList<String>)(receivedMessage.getMessageData());
		}

		case getYearsForCEORDistributionOfOrders_succ:{
			CEODistributionOfOrdersController.years = (ArrayList<String>)(receivedMessage.getMessageData());
		}

		case SetDetailsInTable1ForCEOordersDistribution_succ:{
			CEODistributionOfOrdersController.income = (String) receivedMessage.getMessageData();
		}
	
		case getForCEOComplaintsDistribution_succ: {
			CEODistributionOfComplaintsController.counter = (int)receivedMessage.getMessageData();
		}
		case getHomwStoreForCEORevenenueReports_succ: {
			CEOViewReportsRevenueController.stores = (ArrayList<String>)(receivedMessage.getMessageData());
		}

		case getRevenueReportForCEO_succ: {
			CEOViewReportsRevenueController.revenue = (ArrayList<RevenueReport>)(receivedMessage.getMessageData());
		}

		default:{
			break;
		}
  }
}
	
	
}