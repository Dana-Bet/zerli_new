package Entities;

public enum MessageType {

	/*Server Message*/
	Update_succesfuly,Show_Orders_succ,login,Disconected,Update_Not_succesfuly,Initialize_Catalog_succ,
	
	/*Client Message*/
	Update_Orders,Show_Orders,Show_Catalog,userlogin,add_account,ShowRevenueReport,ConfirmOpenNewAccount,Initialize_Catalog,
	getYear,getRevenueReports,RevenueReports_succ,getMonth, getTypeProduct,
	
	
	Error, getTypeProduct_succ, getTypeOrders, getTypeProductOrders_succ, getCustomerToFreeze, getCustomerToFreeze_succ, customerFreeze, getHomeStore, getHomwStore_succ,
	getTypeNames, getNamesitems_succ, UpdatePriceToItem,getTypeProductForUpdateCatalog,getTypeProductForCatalog_succ, getTypeProductForUpdateCatalog1,
	getIdFromComplaitnDB, getIdFromComplaitnDB_succ, ShowTableComlaintInDB, getTableComplaintsFromDB_succ, setRefundToClient, GetStore,
	getStore_succ, getTypesForCEOordersReports, getTypeProductForCEOordersReports_succ, getStoresForCEOordersReports, getHomwStoreForCEOordersReports_succ, getCEOordersReport, getCEOordersReports_succ, 
	InitialShopsList, CreditCardList, CreditValue, CreditUsed, Add_New_Payment_Method, Add_Order, IsNewClient, UpdateNewClientDiscount, UpdateNewClientDiscount_succ, IsNewClient_succ,
	Add_Order_succ, Add_New_Payment_Method_succ, CreditUsed_succ, CreditValue_succ, CreditCardList_succ, InitialShopsList_succ,
	Add_Recipt, Add_Recipt_succ, Get_All_Order_by_id, Get_All_Order_by_id_succ, getRecipt, getRecipt_succ, UpdateOrderStatus,
	UpdateOrderStatus_succ, showRevenueReportsForCEO, getStoresForCEORevenueReports, getHomwStoreForCEORevenenueReports_succ,
	getRevenueReportForCEO_succ, UpdateCompList, UpdateCompList_succ, ClientExist, ClientExist_succ, Upload_Complaint, Upload_Complaint_succ, UpdateCompLaintDetails, Update_refund, Get_Orders_by_Store, Get_Orders_by_Store_succ, UpdateOrderCancel, UpdateOrderCancel_succ, UpdateCreditForClient, 
}