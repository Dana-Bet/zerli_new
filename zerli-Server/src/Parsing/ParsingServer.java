package Parsing;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import Entities.Client;
import Entities.Complaint;
import Entities.CreditCard;
import Entities.Item_In_Catalog;
import Entities.Message;
import Entities.MessageType;
import Entities.MyFile;
import Entities.Order;
import Entities.OrdersReport;
import Entities.RevenueReport;
import Entities.Store;
import Querys.Query;
import controllers.LogicController;
import ocsf.server.ConnectionToClient;

public class ParsingServer {

	public static Message parsing(Object msg, ConnectionToClient client) {
		Message receivedMessage = (Message) msg;
		switch (receivedMessage.getMessageType()) {
		
		case userlogin: {
			String result;
			String[] DivededUandP = ((String) receivedMessage.getMessageData()).split("#");
			result = Query.Login(DivededUandP[0], DivededUandP[1]);
			if (!result.equals("Already") && !result.equals("WrongInput") && !result.equals("Freeze")) {
				LogicController.UpdateClientTable(msg, client);
			}
			return (new Message(MessageType.userlogin, result));
		}
		case Disconected: {
			Query.UpdateisLoggedIn((String) receivedMessage.getMessageData()); //sign user "isLoggedin" to 0
			LogicController.UpdateClientTable(msg, client);
			return ( new Message(MessageType.Disconected, null));
		} 
		case Initialize_Catalog:{
			String assembledProducts = (String) receivedMessage.getMessageData();
			ArrayList<Item_In_Catalog> Catalog= Query.Initialize_products(assembledProducts);
			return ( new Message(MessageType.Initialize_Catalog_succ, Catalog));
		}
		case add_account: {
			Client Nclient = (Client) receivedMessage.getMessageData();
				Query.addNewAccount(Nclient);
				return (new Message(MessageType.ConfirmOpenNewAccount, null));
			}
		case getYear:{
			ArrayList<String> years = (ArrayList<String>) Query.getYear();
			return (new Message(MessageType.getYear, years));
		}
		case getMonth:{
			ArrayList<String> months = (ArrayList<String>) Query.getMonth();
			return (new Message(MessageType.getMonth, months));
		}
		case getRevenueReports: {
			ArrayList<String> details = (ArrayList<String>)receivedMessage.getMessageData();
			ArrayList<RevenueReport> revenue = Query.getRevenueReports(details);
			return (new Message(MessageType.RevenueReports_succ,revenue));
		}
		case getTypeProduct: {
			ArrayList<String> productype = (ArrayList<String>) Query.getProductType();
			return (new Message(MessageType.getTypeProduct_succ,productype));
		}
		case getTypeOrders:{
			ArrayList<String> details = (ArrayList<String>) receivedMessage.getMessageData();
			ArrayList<OrdersReport> typeOrders = Query.getTypeOrders(details);
			return (new Message(MessageType.getTypeProductOrders_succ,typeOrders));
		}
		case getCustomerToFreeze:{
			String storeManager = (String)(receivedMessage.getMessageData());
			ArrayList<String> customerList = Query.getCustomerToFreeze(storeManager);
			return (new Message(MessageType.getCustomerToFreeze_succ, customerList));
		}
		
		case customerFreeze:{
			String customerToFreeze = (String)(receivedMessage.getMessageData());
			Query.FreezeCustomer(customerToFreeze);
		}

		case getTypeNames:{
			String type = (String) receivedMessage.getMessageData();
			ArrayList<String> Namelist = (ArrayList<String>) Query.getNamesList(type);
			return (new Message(MessageType.getNamesitems_succ,Namelist));
		}
		case UpdatePriceToItem:{
			ArrayList<String> details = (ArrayList<String>)(receivedMessage.getMessageData());
			ArrayList<String> updatePrice = Query.setDetailsInItem(details);
		}
		case getTypeProductForUpdateCatalog1: {
			ArrayList<String> productype = (ArrayList<String>) Query.getProductType1();
			return (new Message(MessageType.getTypeProductForCatalog_succ,productype));
		}
		case InitialShopsList:{
			ArrayList<Store> Stores= Query.InitialShopsList();
			return (new Message(MessageType.InitialShopsList_succ,Stores));
			
		}
		case CreditCardList:{
			String userId = (String) receivedMessage.getMessageData();
			ArrayList<CreditCard> creditCards= Query.CreditCardList(userId);
			return (new Message(MessageType.CreditCardList_succ,creditCards));
		}
		case CreditValue:{
			String userId = (String) receivedMessage.getMessageData();
			Integer credit= Query.getCreditValue(userId);
			return (new Message(MessageType.CreditValue_succ,credit));
		}
		case CreditUsed:{
			String[] DivededUandCredit = ((String) receivedMessage.getMessageData()).split("@");
			Query.setCreditValue(DivededUandCredit[0], DivededUandCredit[1]);
			return (new Message(MessageType.CreditUsed_succ,""));
		}
		case Add_New_Payment_Method : {
			String[] DivededUandCredit = ((String) receivedMessage.getMessageData()).split("@");
			System.out.println(DivededUandCredit[0]+ DivededUandCredit[1]);
			Query.AddCreditCard(DivededUandCredit[0], DivededUandCredit[1]);
			return (new Message(MessageType.Add_New_Payment_Method_succ,""));
		}
		case Add_Order:{
			String[] DivedOrderDet = ((String) receivedMessage.getMessageData()).split("@");
			int OrderNum =Query.AddOrderToDb(DivedOrderDet[0],DivedOrderDet[1],DivedOrderDet[2],DivedOrderDet[3],DivedOrderDet[4],
					DivedOrderDet[5],DivedOrderDet[6],DivedOrderDet[7]);
			return (new Message(MessageType.Add_Order_succ, OrderNum));
		}
		case IsNewClient:{
			String userId = (String) receivedMessage.getMessageData();
			int res= Query.IsNewClient(userId);
			return (new Message(MessageType.IsNewClient_succ,res));
		}
		case  UpdateNewClientDiscount:{
			String userId = (String) receivedMessage.getMessageData();
			Query.UpdateNewClientDisc(userId);
			return (new Message(MessageType.UpdateNewClientDiscount_succ,""));
		}
		case getIdFromComplaitnDB:{
			ArrayList<String> listID = (ArrayList<String>) Query.getIDFromComplaintDB();
			return (new Message(MessageType.getIdFromComplaitnDB_succ,listID));
		}

		case GetStore:{
			ArrayList<String> store = (ArrayList<String>)Query.getListOfStoreForCeo();
			return (new Message(MessageType.getStore_succ,store)); 
		}
		case getStoresForCEOordersReports:{
			ArrayList<String> storelist = (ArrayList<String>) Query.getListOfStoreForCeo();
			return (new Message(MessageType.getHomwStoreForCEOordersReports_succ,storelist));
		}
		case getTypesForCEOordersReports:{
			ArrayList<String> productype = (ArrayList<String>) Query.getProductTypeForCEOreportOrders();
			return (new Message(MessageType.getTypeProductForCEOordersReports_succ,productype));
		}
		case getCEOordersReport:{
			System.out.println("line 124");
			ArrayList <String> details = (ArrayList<String>) receivedMessage.getMessageData();
			System.out.println("line 126");
			ArrayList<OrdersReport> typeOrders = Query.getOrdersReportForCEO(details);
			System.out.println(typeOrders);
			return (new Message(MessageType.getCEOordersReports_succ,typeOrders));
		}
		case Add_Recipt:{
			String[] Order_Num_and_recipt = ((String) receivedMessage.getMessageData()).split("@");
			Query.AddRecipt(Order_Num_and_recipt[0],  Order_Num_and_recipt[1]);
			return (new Message(MessageType.Add_Recipt_succ,""));
			
		}
		case Get_All_Order_by_id :{
			String userId = (String) receivedMessage.getMessageData();
			ArrayList<Order> orders=Query.get_Orders_list(userId);
			return (new Message(MessageType.Get_All_Order_by_id_succ,orders));
		}
		case Get_Orders_by_Store :{
			String store = (String) receivedMessage.getMessageData();
			ArrayList<Order> orders=Query.get_Orders_list_by_store(store);
			return (new Message(MessageType.Get_Orders_by_Store_succ,orders));
		}
		case getRecipt :{
			int orderNum = (int) receivedMessage.getMessageData();
			ArrayList<String> Recipt = Query.getRecipt(orderNum);
			return (new Message(MessageType.getRecipt_succ,Recipt));
			
		} 
		case UpdateOrderStatus :{
			ArrayList<String> details = (ArrayList<String>)(receivedMessage.getMessageData());
			Query.UpdateOrderSt(details);
			return (new Message(MessageType.UpdateOrderStatus_succ,""));
		}
		case UpdateOrderCancel:{
			ArrayList<String> details = (ArrayList<String>)(receivedMessage.getMessageData());
			Query.UpdateOrderCancel(details);
			return (new Message(MessageType.UpdateOrderCancel_succ,""));
			
		}
		case  UpdateCompList:{
			return (new Message(MessageType.UpdateCompList_succ,Query.getComplaints()));

		}
		
		case getStoresForCEORevenueReports :{
			ArrayList<String> storeList = Query.GetStoreListForCEORevenueReports();
			System.out.println(storeList);
			return (new Message(MessageType.getHomwStoreForCEORevenenueReports_succ,storeList));
		}
		case showRevenueReportsForCEO :{
			String store = (String)(receivedMessage.getMessageData());
			ArrayList<RevenueReport> revenue = Query.get_Revenue_Reports_ForCEO(store);
			return (new Message(MessageType.getRevenueReportForCEO_succ,revenue));
		}
		case ClientExist :{
			String clientId = (String)(receivedMessage.getMessageData());
			Query.check_If_Client_Exist(clientId);
			return (new Message(MessageType.ClientExist_succ,Query.check_If_Client_Exist(clientId)));
		}
		case Upload_Complaint :{
			ArrayList<String> details = (ArrayList<String>)(receivedMessage.getMessageData());
			Query.Update_Complaint(details);
			return (new Message(MessageType.Upload_Complaint_succ,""));
		}
		case UpdateCompLaintDetails :{
			ArrayList<String> details = (ArrayList<String>)(receivedMessage.getMessageData());
			Query.Update_Complaint_details(details);
			return (new Message(MessageType.Upload_Complaint_succ,""));
		}
		case Update_refund:{
			ArrayList<String> details = (ArrayList<String>)(receivedMessage.getMessageData());
			Query.Update_refund(details);
		}
		case UpdateCreditForClient:{
			ArrayList<String> details = (ArrayList<String>)(receivedMessage.getMessageData());
			return (new Message(MessageType.RefundForClient_succ,Query.Update_refund_of_cancel_order(details)));
		}
		case getClientEmailAndPhone:{
			String clientId = (String)(receivedMessage.getMessageData());
			return (new Message(MessageType.ClientEmailAndPhone_succ,Query.getClientEmailAndPhone(clientId)));
		}
		case Get_All_Items_In_Catalog :{
			return (new Message(MessageType.Get_All_Items_In_Catalog_succ,Query.get_All_Catalog()));
		}
		case  get_Inventories:{
			String store = (String)(receivedMessage.getMessageData());
			return (new Message(MessageType. get_Inventories_succ,Query.get_Inventories(store)));
		}
		case UpdateQuanInInventory:{
			ArrayList<String> details = (ArrayList<String>)(receivedMessage.getMessageData());
			Query.Update_Quantity_of_product(details);
		}
		case getAllClients :{
			return (new Message(MessageType. getAllClients_succ,Query.get_All_Clients()));
		}
		case getSurveysId :{
			return (new Message(MessageType.getSurveysId_succ,Query.get_Surveys_Id()));
		}
		case Add_Survey_Result :{
			ArrayList<Integer> results = (ArrayList<Integer>)(receivedMessage.getMessageData());
			Query.Updload_survey_results(results);
			
		}
		case send_PDF: {
			MyFile file = (MyFile) receivedMessage.getMessageData();
			DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm:ss");
			LocalDateTime nowTime = LocalDateTime.now();
			Query.updateFile(file);
			Message messageFromServer;
			return messageFromServer = new Message(MessageType.upload_pdf_succ, null);
		}
		
		default:
			break;
		
		}

	
		return receivedMessage;
      }
	


}
