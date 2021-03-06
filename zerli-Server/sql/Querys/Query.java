
package Querys;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import Entities.Client;
import Entities.Complaint;
import Entities.CreditCard;
import Entities.Item_In_Catalog;
import Entities.MyFile;
import Entities.Order;
import Entities.OrdersReport;
import Entities.Product_In_Inventory;
import Entities.RevenueReport;
import Entities.Store;

public class Query {

	public static String Login(String userName, String password) {
		StringBuilder result = new StringBuilder();
		String role = null, ID = null, status = null;
		PreparedStatement stmt, stmt1;
		try {
			if (DBConnect.conn != null) {
				stmt = DBConnect.conn.prepareStatement(
						"SELECT Role,ID,FirstName,LastName,userName,password,isLoggedIn,phone,email,homeStore FROM zerli_db.users WHERE userName=? AND password=?");
				stmt.setString(1, userName);
				stmt.setString(2, password);
				ResultSet rs = stmt.executeQuery();
				
				while (rs.next()) {
					if ((rs.getString(7)).equals("1")) {
						return "Already";
					}
					role = rs.getNString(1);
					result.append(rs.getString(1));
					result.append("#");
					ID = rs.getString(2);
					result.append(rs.getString(2));
					result.append("#");
					result.append(rs.getString(3));//firstName
					result.append("#");
					result.append(rs.getString(4));//lastName
					result.append("#");
					result.append(rs.getString(5));//userName
					result.append("#");
					result.append(rs.getString(6));//password
					result.append("#");
					result.append(rs.getString(7));//isLoogin
					result.append("#");
					result.append(rs.getString(8));//phone
					result.append("#");
					result.append(rs.getString(9));//email
					result.append("#");
					result.append(rs.getString(10));//homeStore

				}
				rs.close();
				if (result.length() == 0) {
					return "WrongInput";
				}
				if (role.equals("Customer")) {
					stmt1 = DBConnect.conn.prepareStatement("SELECT status FROM zerli_db.client WHERE client_id=?");
					stmt1.setString(1, ID);
					ResultSet rs1 = stmt1.executeQuery();
					while (rs1.next()) {
						status = rs1.getString(1);
					}
					rs1.close();
					if (status.equals("Freeze")) {
						return "Freeze";
					}
				}
				stmt = DBConnect.conn.prepareStatement("UPDATE zerli_db.users SET isLoggedIn='1' WHERE userName=? AND password=?");
				stmt.setString(1, userName);
				stmt.setString(2, password);
				stmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result.toString();
	}

	public static void UpdateisLoggedIn(String UserName) {
		PreparedStatement stmt;
		try {
	    	stmt = DBConnect.conn.prepareStatement("UPDATE zerli_db.users SET isLoggedIn='0' WHERE userName=?");
	    	stmt.setString(1, UserName);
		    stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//the method check if account exist
	public static boolean IfAccountExist(Client Account) {
		System.out.println("line 75 - query");
		boolean flag = false;
		 try {
			if (DBConnect.conn != null) {
		
				Statement st = DBConnect.conn.createStatement();
				String sql = "SELECT * FROM zerli_db.account";
				ResultSet rs = st.executeQuery(sql);
				while(rs.next()) {
					String id_col = rs.getString("ID");
					if(id_col.compareTo(Account.getId()) == 0) {
							flag = true;
							break;
					}
				}
				
		} else {
			System.out.println("Conn is null");
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}

		return flag;
}
	public static void addNewAccount(Client Account) {
		if (DBConnect.conn != null) {
			try {
					PreparedStatement stmt2 = DBConnect.conn.prepareStatement(
					"INSERT INTO zerli_db.users (userName,password,Role,FirstName,LastName,ID,email,phone,isLoggedIn) VALUES(?,?,?,?,?,?,?,?,?)");
					stmt2.setString(1, Account.getUserName());
					stmt2.setString(2, Account.getPassword());
					stmt2.setString(3, Account.getRole());
					stmt2.setString(4, Account.getFirstN());
					stmt2.setString(5, Account.getLastN());
					stmt2.setString(6, Account.getId());
					stmt2.setString(7, Account.getEmail());
					stmt2.setString(8, Account.getPhone());
					stmt2.setInt(9, 0); //The account insert in looggedId = 0
					stmt2.executeUpdate();
					PreparedStatement stmt3 = DBConnect.conn.prepareStatement(
							"INSERT INTO zerli_db.creditcards (client_id,creditcardsNum) VALUES(?,?)");
					stmt3.setString(1, Account.getId());
					stmt3.setString(2, Account.getCreditCardNumber());
					stmt3.executeUpdate();
					PreparedStatement stmt4 = DBConnect.conn.prepareStatement(
							"INSERT INTO zerli_db.client (client_id,status,Credit,newClient) VALUES(?,?,?,?)");
					stmt4.setString(1, Account.getId());
					stmt4.setString(2,"Active");
					stmt4.setInt(3,0);
					stmt4.setInt(4,1);
					stmt4.executeUpdate();
			} catch (SQLException s) {
				s.printStackTrace();
			}
		}
	}
	
	public static ArrayList<Item_In_Catalog> Initialize_products(String assembledItem) {
		ArrayList<Item_In_Catalog> Catalog= new ArrayList<>();
		PreparedStatement stmt;
		try {
			if (DBConnect.conn != null) {
				stmt = DBConnect.conn
						.prepareStatement("SELECT * FROM zerli_db.item_in_catalog WHERE assembleItem =?");
				stmt.setString(1, assembledItem);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					Item_In_Catalog Item = new Item_In_Catalog(rs.getInt("id"), rs.getString("color"),
							rs.getString("name"), rs.getString("type"), rs.getFloat("price"),
							rs.getString("assembleItem"));
					Catalog.add(Item);
				}
				rs.close();
			} else {
				System.out.println("Conn is null");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Catalog;
		}
	
	//this method take the years in revenue_reports for DB
		public static ArrayList<String> getYear() {
			ArrayList<String> years = null;
			Statement stmt;
			try {
				stmt = DBConnect.conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT DISTINCT year FROM zerli_db.revenue_reports");
				years = new ArrayList<String>();
				while (rs.next()) {
				years.add(rs.getString(1));
				}
					rs.close();
				} catch (SQLException e) {
			e.printStackTrace();
				}
			return years;
		}
		//this method take the month in revenue_reports for DB
		public static ArrayList<String> getMonth() {
			ArrayList<String> month = null;
			Statement stmt;
			try {
				stmt = DBConnect.conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT DISTINCT month FROM zerli_db.revenue_reports");
				month = new ArrayList<String>();
				while (rs.next()) {
					month.add(rs.getString(1));
				}
					rs.close();
				} catch (SQLException e) {
			e.printStackTrace();
				}
			return month;
		}
		
		public static ArrayList<RevenueReport> getRevenueReports(ArrayList<String> details) {
			ArrayList<RevenueReport> revenue = new ArrayList<>();
			PreparedStatement stmt;
			try {
				if (DBConnect.conn != null) {
					stmt = DBConnect.conn.prepareStatement("SELECT * FROM zerli_db.revenue_reports WHERE store=? AND year=? AND month=?");
					stmt.setString(1,details.get(0)); //id
					stmt.setString(2, details.get(1)); //year
					stmt.setString(3,details.get(2)); //month
					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						String store = rs.getString("store");
						String month = rs.getString("month");
						String year = rs.getString("year");
						String orders_amount = rs.getString("ordersamount");
						String income = rs.getString("income");
						String Quarterly = rs.getString("Quarterly");
						String id = rs.getString("id");
						revenue.add(new RevenueReport(store,month,year,orders_amount,income,Quarterly,id));
					}
					rs.close();
				} else {
					System.out.println("Conn is null");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return revenue;
		}

		public static ArrayList<String> getProductType() {
			ArrayList<String> types = new ArrayList<String>();
			PreparedStatement stmt;
			try {
				stmt = DBConnect.conn.prepareStatement("SELECT DISTINCT type FROM zerli_db.item_in_catalog");
				
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
				types.add(rs.getString(1));
				}
					rs.close();
				} catch (SQLException e) {
			e.printStackTrace();
				}
			return types;
			
		}

		public static ArrayList<OrdersReport> getTypeOrders(ArrayList<String> details) {
			ArrayList<OrdersReport> orders = new ArrayList<>();
			PreparedStatement stmt;
			try {
				if (DBConnect.conn != null) {
					stmt = DBConnect.conn.prepareStatement("SELECT * FROM zerli_db.orders_report WHERE type=? AND store =?");
					stmt.setString(1,details.get(0)); 
					stmt.setString(2,details.get(1)); 
					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						String month = rs.getString("month");
						String year = rs.getString("year");
						String store = rs.getString("store");
						String Quantity = rs.getString("Quantity");
						String type = rs.getString("type");
						orders.add(new OrdersReport(month,year,store,Quantity,type));
					}
					rs.close();
				} else {
					System.out.println("Conn is null");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return orders;
		}

		public static ArrayList<String> getCustomerToFreeze() {
			ArrayList<String> customerList = null;
			PreparedStatement stmt;
			try {
				stmt = DBConnect.conn.prepareStatement("SELECT DISTINCT id FROM zerli_db.users WHERE Role = ?");
				stmt.setString(1, "Customer");
				ResultSet rs = stmt.executeQuery();
				customerList = new ArrayList<String>();
				while (rs.next()) {
				customerList.add(rs.getString(1));
				}
					rs.close();
				} catch (SQLException e) {
			e.printStackTrace();
				}
			System.out.println(customerList);
			return customerList;
		}

		public static void FreezeCustomer(String customerToFreeze) {
			PreparedStatement stmt;
			try {
		    	stmt = DBConnect.conn.prepareStatement("UPDATE zerli_db.client SET status='Freeze' WHERE client_id=?");
		    	stmt.setString(1, customerToFreeze);
			    stmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}

		public static ArrayList<String> getstorelist() {
			ArrayList<String> storelist = null;
			Statement stmt;
			try {
				stmt = DBConnect.conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT DISTINCT storeCode FROM zerli_db.stores");
				storelist = new ArrayList<String>();
				while (rs.next()) {
					storelist.add(rs.getString(1));
				}
					rs.close();
				} catch (SQLException e) {
			e.printStackTrace();
				}
			return storelist;
		}

		public static ArrayList<String> getNamesList(String type) {
			ArrayList<String> names = null;
			PreparedStatement stmt;
			try {
				stmt = DBConnect.conn.prepareStatement("SELECT DISTINCT name FROM zerli_db.item_in_catalog WHERE type=?");
				stmt.setString(1,type);
				ResultSet rs = stmt.executeQuery();
				names = new ArrayList<String>();
				while (rs.next()) {
				names.add(rs.getString(1));
				}
					rs.close();
				} catch (SQLException e) {
			e.printStackTrace();
				}
			return names;
		}

		public static ArrayList<String> setDetailsInItem(ArrayList<String> details) {
			PreparedStatement stmt;
			try {
		    	stmt = DBConnect.conn.prepareStatement("UPDATE zerli_db.item_in_catalog SET price=? WHERE type=? AND name=?");
		    	stmt.setFloat(1,Float.valueOf(details.get(0)));
		    	stmt.setString(2,details.get(1));
		    	stmt.setString(3,details.get(2));
			    stmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}

		public static ArrayList<String> getProductType1() {
			ArrayList<String> types = new ArrayList<String>();
			PreparedStatement stmt;
			try {
				stmt = DBConnect.conn.prepareStatement("SELECT DISTINCT type FROM zerli_db.item_in_catalog");
				
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
				types.add(rs.getString(1));
				}
					rs.close();
				} catch (SQLException e) {
			e.printStackTrace();
				}
			return types;			
		}
		
		
		public static ArrayList<Store> InitialShopsList (){
			ArrayList<Store> stores = new ArrayList<Store>();
			PreparedStatement stmt;
			try {
				stmt = DBConnect.conn.prepareStatement("SELECT * FROM zerli_db.stores");
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					stores.add(new Store(rs.getString("storeCode"),rs.getString("city"),rs.getString("address")));
				}
					rs.close();
				} catch (SQLException e) {
			e.printStackTrace();
				}
	
		return stores;
		
		}

		public static ArrayList<CreditCard> CreditCardList(String userId) {
			ArrayList<CreditCard> creditCards = new ArrayList<CreditCard>();
			PreparedStatement stmt;
			try {
				stmt = DBConnect.conn.prepareStatement("SELECT creditcardsNum FROM zerli_db.creditcards WHERE client_id = ?");
				stmt.setString(1,userId);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					creditCards.add(new CreditCard(rs.getString("creditcardsNum")));
				}
					rs.close();
				} catch (SQLException e) {
			e.printStackTrace();
				}
	
		return creditCards;
		}

		public static Integer getCreditValue(String userId) {
			PreparedStatement stmt;
			Integer credit = new Integer(0);
			try {
				stmt = DBConnect.conn.prepareStatement("SELECT Credit FROM zerli_db.client WHERE client_id = ?");
				stmt.setString(1,userId);
				ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
				  credit =rs.getInt("Credit");
				}
				rs.close();
				} catch (SQLException e) {
			e.printStackTrace();
				}
	
		return credit;
		}

		public static void setCreditValue(String userId,String divededUandCredit) {
			int credit =Integer.valueOf(divededUandCredit);
			PreparedStatement stmt;
			try {
				stmt = DBConnect.conn.prepareStatement("UPDATE zerli_db.client SET Credit=? WHERE client_id =?");
				stmt.setInt(1,credit);
				stmt.setString(2,userId);
				stmt.executeUpdate();


				} catch (SQLException e) {
			e.printStackTrace();
				}

		}

		public static void AddCreditCard(String Userid, String CreditCardNum) {
			System.out.println(Userid +"+"+CreditCardNum);
			PreparedStatement stmt;
			try {
				stmt = DBConnect.conn.prepareStatement("INSERT INTO zerli_db.creditcards (client_id,creditcardsNum) VALUES(?,?)");
				stmt.setString(1,Userid);
				stmt.setString(2,CreditCardNum);
				stmt.executeUpdate();
				} catch (SQLException e) {
			e.printStackTrace();
				}
			
		}

		public static int AddOrderToDb(String store, String clientId, String price, String greeting,String status, String suppMeth,
			String suppDate, String suppTime) {
			PreparedStatement stmt;
            int lastorder=0;
			try {
				stmt = DBConnect.conn.prepareStatement("SELECT OrderNum From zerli_db.orders ORDER BY OrderNum DESC LIMIT 1");
				 ResultSet rs = stmt.executeQuery();
					while(rs.next()) {
						lastorder =rs.getInt("OrderNum");
						}
		 		} catch (SQLException e) {
		         	e.printStackTrace();
				}   

			try {
				stmt = DBConnect.conn.prepareStatement("INSERT INTO zerli_db.orders (OrderNum,store,clientId,price,greeting,status,supplimentMethod,supplimentTime,supplimentDate,OrderTime) VALUES(?,?,?,?,?,?,?,?,?,now())");
				stmt.setInt(1,lastorder+1);
				stmt.setString(2,store);
				stmt.setString(3,clientId);
				stmt.setString(4,price);
				stmt.setString(5,greeting);
				stmt.setString(6,status);
				stmt.setString(7,suppMeth);
				stmt.setString(8,suppDate);
				stmt.setString(9,suppTime);
	
				stmt.executeUpdate();
				} catch (SQLException e) {
			e.printStackTrace();
				}
			return lastorder+1;
		}

		public static ArrayList<Integer> IsNewClient(String userId) {
			PreparedStatement stmt;
			ArrayList<Integer>res = new ArrayList<Integer>();
			try {
				stmt = DBConnect.conn.prepareStatement("SELECT newClient FROM zerli_db.client WHERE client_id = ?");
				stmt.setString(1,userId);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					res.add(rs.getInt("newClient"));
				}
				} catch (SQLException e) {
			e.printStackTrace();
				}
			return res;
		}

		public static void UpdateNewClientDisc(String userId) {
			PreparedStatement stmt;
			try {
				stmt = DBConnect.conn.prepareStatement("UPDATE zerli_db.client SET newClient=? WHERE client_id =?");
				stmt.setInt(1,0);
				stmt.setString(2,userId);
				stmt.executeUpdate();

				} catch (SQLException e) {
			e.printStackTrace();
				}
		}
		
		public static ArrayList<Order> get_Orders_list(String userId) 
		{
			ArrayList<Order> orders =new ArrayList<Order>(); 
			PreparedStatement stmt;
			try {
				stmt = DBConnect.conn.prepareStatement("SELECT * From zerli_db.orders WHERE clientId = ?");
				stmt.setString(1,userId);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					orders.add(new Order(rs.getString("clientId"),rs.getInt("OrderNum"),rs.getString("store"),rs.getString("greeting"),rs.getString("status"),rs.getString("price"),
							rs.getString("supplimentMethod"),rs.getString("supplimentTime"),rs.getString("supplimentDate"),rs.getTimestamp("OrderTime")));
				}
				
				rs.close();

				} catch (SQLException e) {
			e.printStackTrace();
				}

			return orders;
		}
		
		public static ArrayList<Order> get_Orders_list_by_store(String store)	{
			ArrayList<Order> orders =new ArrayList<Order>(); 
			PreparedStatement stmt;
			try {
				stmt = DBConnect.conn.prepareStatement("SELECT * From zerli_db.orders WHERE store = ?");
				stmt.setString(1,store);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					orders.add(new Order(rs.getString("clientId"),rs.getInt("OrderNum"),rs.getString("store"),rs.getString("greeting"),rs.getString("status"),rs.getString("price"),
							rs.getString("supplimentMethod"),rs.getString("supplimentTime"),rs.getString("supplimentDate"),rs.getTimestamp("OrderTime")));
				}
				
				rs.close();

				} catch (SQLException e) {
			e.printStackTrace();
				}

			return orders;
		}

			
		public static ArrayList<String> getIDFromComplaintDB() {
			ArrayList<String> listID = new ArrayList<String>();
			PreparedStatement stmt;
			try {
				stmt = DBConnect.conn.prepareStatement("SELECT DISTINCT id FROM zerli_db.complaints");
				
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					listID.add(rs.getString(1));
				}
					rs.close();
				} catch (SQLException e) {
			e.printStackTrace();
				}
			return listID;				
		}


		public static ArrayList<String> getListOfStoreForCeo() {
				ArrayList<String> stores = new ArrayList<String>();
				Statement stmt;
				try {
					stmt = DBConnect.conn.createStatement();
					ResultSet rs = stmt.executeQuery("SELECT DISTINCT store FROM zerli_db.orders_report");
					while (rs.next()) {
						stores.add(rs.getString(1));
					}
						rs.close();
					} catch (SQLException e) {
				e.printStackTrace();
					}
				return stores;			
		}

		public static ArrayList<OrdersReport> getOrdersReportForCEO(ArrayList<String> details) {
			ArrayList<OrdersReport> orders = new ArrayList<>();
			PreparedStatement stmt;
			try {
				if (DBConnect.conn != null) {
					stmt = DBConnect.conn.prepareStatement("SELECT * FROM zerli_db.orders_report WHERE store=? AND type=?");
					stmt.setString(1,details.get(0)); 
					stmt.setString(2,details.get(1));
					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						String month = rs.getString("month");
						String year = rs.getString("year");
						String store = rs.getString("store");
						String Quantity = rs.getString("Quantity");
						String type = rs.getString("type");
						orders.add(new OrdersReport(month,year,store,Quantity,type));
					}
					rs.close();
				} else {
					System.out.println("Conn is null");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println(orders);
			return orders;
		}

		public static ArrayList<String> getProductTypeForCEOreportOrders() {
			ArrayList<String> types = new ArrayList<String>();
			PreparedStatement stmt;
			try {
				stmt = DBConnect.conn.prepareStatement("SELECT DISTINCT type FROM zerli_db.orders_report");
				
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
				types.add(rs.getString(1));
				}
					rs.close();
				} catch (SQLException e) {
			e.printStackTrace();
				}
			return types;	
		}

		public static void AddRecipt(String OrderNum, String Recipt) {
			PreparedStatement stmt;
			try {
				stmt = DBConnect.conn.prepareStatement("INSERT INTO zerli_db.orders_recipts (OrderNum,Recipts) VALUES(?,?)");
				stmt.setInt(1,Integer.valueOf(OrderNum));
				stmt.setString(2,Recipt);
				stmt.executeUpdate();
				} catch (SQLException e) {
			e.printStackTrace();
				}
	
			
		}

		public static ArrayList<String> getRecipt(int orderNum) {
			PreparedStatement stmt;
			String recipt = null;
			ArrayList<String> ReciptList = new ArrayList<String>();
			try {
				stmt = DBConnect.conn.prepareStatement("SELECT Recipts FROM zerli_db.orders_recipts WHERE OrderNum = ?");
				stmt.setInt(1,orderNum);
				ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
					recipt =rs.getString("Recipts");
				}
				rs.close();
				} catch (SQLException e) {
			e.printStackTrace();
				}
			String [] splitRecipt = recipt.split("#");
	        for(String i : splitRecipt) {
	        	ReciptList.add(i);
	        }
		return ReciptList;
		}

		public static void UpdateOrderSt(ArrayList<String> details) {
			PreparedStatement stmt;
			try {
				stmt = DBConnect.conn.prepareStatement("UPDATE zerli_db.orders SET status=? WHERE OrderNum = ?");
				stmt.setString(1,details.get(0));
				stmt.setInt(2,Integer.valueOf(details.get(1)));
				stmt.executeUpdate();

				} catch (SQLException e) {
			e.printStackTrace();
				}
			
		}


		public static ArrayList<Complaint> getComplaints() {
			ArrayList<Complaint> complaints =new ArrayList<Complaint>(); 
			PreparedStatement stmt;
			try {
				stmt = DBConnect.conn.prepareStatement("SELECT * From zerli_db.complaints");
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					complaints.add(new Complaint(rs.getString("clientId"),rs.getString("status"),rs.getTimestamp("complaintTime"),rs.getString("reason"),rs.getString("refund")));
				}
				
				rs.close();

				} catch (SQLException e) {
			e.printStackTrace();
				}

			return complaints;
		}

		public static ArrayList<String> check_If_Client_Exist(String clientId) {
			ArrayList<String> clients =new ArrayList<String>(); 
			PreparedStatement stmt;
			try {
				stmt = DBConnect.conn.prepareStatement("SELECT client_id From zerli_db.client WHERE client_id = ?");
				stmt.setString(1,clientId);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					clients.add(rs.getString("client_id"));
				}
				
				rs.close();

				} catch (SQLException e) {
			e.printStackTrace();
				}

			return clients;
			
		}

		public static void Update_Complaint(ArrayList<String> details) {
			PreparedStatement stmt;
			try {
				stmt = DBConnect.conn.prepareStatement("INSERT INTO zerli_db.complaints (clientId,complaintTime,status,reason,refund) VALUES(?,now(),?,?,?)");
				stmt.setString(1,details.get(0));
				stmt.setString(2,details.get(1));
				stmt.setString(3,details.get(2));
				stmt.setString(4,details.get(3));
				stmt.executeUpdate();

				} catch (SQLException e) {
			e.printStackTrace();
				}
			
		}

		public static void Update_Complaint_details(ArrayList<String> details) {
			PreparedStatement stmt;
			try {
				stmt = DBConnect.conn.prepareStatement("UPDATE zerli_db.complaints SET status=? , refund=? WHERE clientId=? ");
				stmt.setString(1,details.get(1));
				stmt.setString(2,details.get(2));
				stmt.setString(3,details.get(0));
				stmt.executeUpdate();

				} catch (SQLException e) {
			e.printStackTrace();
				}
			
			
		}

		public static void Update_refund(ArrayList<String> details) {
			int creditVal = getCreditValue(details.get(0));
			int newCredit = creditVal +Integer.valueOf(details.get(2));
			setCreditValue(details.get(0),String.valueOf(newCredit)) ;
		}
		
		

		public static void UpdateOrderCancel(ArrayList<String> details) {
			PreparedStatement stmt;
			try {
				stmt = DBConnect.conn.prepareStatement("INSERT INTO zerli_db.orders_cancel (clientID,OrderNum,Refund,Amount) VALUES(?,?,?,?)");
				stmt.setString(1,details.get(0));
				stmt.setString(2,details.get(1));
				stmt.setString(3,details.get(2));
				stmt.setInt(4,Integer.valueOf(details.get(3)));
				stmt.executeUpdate();

				} catch (SQLException e) {
			e.printStackTrace();
				}
			
		}

		public static Integer Update_refund_of_cancel_order(ArrayList<String> details) {
			PreparedStatement stmt;
			Integer amount = new Integer(0);
			try {
				stmt = DBConnect.conn.prepareStatement("SELECT Amount FROM zerli_db.orders_cancel WHERE clientID = ? AND OrderNum = ?");
				stmt.setString(1,details.get(0));
				stmt.setString(2,details.get(1));
				ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
					amount =rs.getInt("Amount");
				}
				rs.close();
				} catch (SQLException e) {
			e.printStackTrace();
				}
			details.add(String.valueOf(amount));
			Update_refund(details);
			return amount;
			}

		public static ArrayList<String> getClientEmailAndPhone(String clientId) {
			ArrayList<String> Email_Phone=new ArrayList<String>(); 
			PreparedStatement stmt;
			try {
				stmt = DBConnect.conn.prepareStatement("SELECT email,phone From zerli_db.users WHERE ID = ? ");
				stmt.setString(1,clientId);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					 Email_Phone.add(rs.getString("email"));
					 Email_Phone.add(rs.getString("phone"));
				}
				rs.close();

				} catch (SQLException e) {
			e.printStackTrace();
				}

			return  Email_Phone;
			
		}

		public static Object get_All_Catalog() {
			ArrayList<Item_In_Catalog> Catalog= new ArrayList<>();
			PreparedStatement stmt;
			try {
				if (DBConnect.conn != null) {
					stmt = DBConnect.conn
							.prepareStatement("SELECT * FROM zerli_db.item_in_catalog WHERE price > 0 ");
					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						Item_In_Catalog Item = new Item_In_Catalog(rs.getInt("id"), rs.getString("color"),
								rs.getString("name"), rs.getString("type"), rs.getFloat("price"),
								rs.getString("assembleItem"));
						Catalog.add(Item);
					}
					rs.close();
				} else {
					System.out.println("Conn is null");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return Catalog;
		}

		public static ArrayList<Product_In_Inventory> get_Inventories(String store) {
			ArrayList<Product_In_Inventory> inventories= new ArrayList<>();
			PreparedStatement stmt;
			try {
				if (DBConnect.conn != null) {
					stmt = DBConnect.conn
							.prepareStatement("SELECT * FROM zerli_db.product_inventory WHERE store= ? ");
					stmt.setString(1,store);
					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						inventories.add(new Product_In_Inventory(rs.getString("ProductName"), rs.getString("ProductId"), rs.getString("store"),rs.getInt("Quantity"))) ;

					}
					rs.close();
				} else {
					System.out.println("Conn is null");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return inventories;
		}

		public static void Update_Quantity_of_product(ArrayList<String> details) {
			PreparedStatement stmt;
			if (details.get(3).equals("0")) {
				try {
					stmt = DBConnect.conn.prepareStatement("UPDATE zerli_db.product_inventory SET Quantity=Quantity-? WHERE store=? AND ProductId=? ");
					System.out.println(details.toString());
					stmt.setInt(1,Integer.valueOf(details.get(0)));
					stmt.setString(2,details.get(1));
					stmt.setString(3,details.get(2));
					stmt.executeUpdate();

					} catch (SQLException e) {
				e.printStackTrace();
					}
				
			}
			else {
			try {
				stmt = DBConnect.conn.prepareStatement("UPDATE zerli_db.product_inventory SET Quantity=? WHERE store=? AND ProductId=? ");
				System.out.println(details.toString());
				stmt.setInt(1,Integer.valueOf(details.get(0)));
				stmt.setString(2,details.get(1));
				stmt.setString(3,details.get(2));
				stmt.executeUpdate();

				} catch (SQLException e) {
			e.printStackTrace();
				}
			
			
			}
		}

		public static ArrayList<String> get_All_Clients() {
			ArrayList<String> ClientsId= new ArrayList<String>();
			PreparedStatement stmt;
			try {
				if (DBConnect.conn != null) {
					stmt = DBConnect.conn.prepareStatement("SELECT client_id FROM zerli_db.client");
					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						ClientsId.add(rs.getString("client_id"));
					}
					rs.close();
				} else {
					System.out.println("Conn is null");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return ClientsId;
		}

		public static ArrayList<String> get_Surveys_Id() {
			ArrayList<String> Surveys= new ArrayList<String>();
			PreparedStatement stmt;
			try {
				if (DBConnect.conn != null) {
					stmt = DBConnect.conn.prepareStatement("SELECT DISTINCT SurveyId FROM zerli_db.surveys");
					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						Surveys.add(rs.getString("SurveyId"));
					}
					rs.close();
				} else {
					System.out.println("Conn is null");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return Surveys;
		}

		public static void Updload_survey_results(ArrayList<Integer> results) {
			PreparedStatement stmt;
			try {
				stmt = DBConnect.conn.prepareStatement("INSERT INTO zerli_db.surveys (clientId,SurveyId,Q1,Q2,Q3,Q4,Q5,Q6) VALUES(?,?,?,?,?,?,?,?)");
				stmt.setInt(1, results.get(0));
				stmt.setInt(2, results.get(7));
				stmt.setInt(3, results.get(1));		
				stmt.setInt(4, results.get(2));
				stmt.setInt(5, results.get(3));
				stmt.setInt(6, results.get(4));
				stmt.setInt(7, results.get(5));
				stmt.setInt(8, results.get(6));
				stmt.executeUpdate();

				} catch (SQLException e) {
			e.printStackTrace();
				}
			
		}
		public static ArrayList<String> GetStoreListForCEORevenueReports() {
			ArrayList<String> stores = new ArrayList<String>();
			Statement stmt;
			try {
				stmt = DBConnect.conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT DISTINCT store FROM zerli_db.revenue_reports");
				while (rs.next()) {
					stores.add(rs.getString(1));
				}
					rs.close();
				} catch (SQLException e) {
			e.printStackTrace();
				}
			return stores;
		}

		public static ArrayList<RevenueReport> get_Revenue_Reports_ForCEO(String store) {
			ArrayList<RevenueReport> revenue = new ArrayList<>();
			PreparedStatement stmt;
			try {
				if (DBConnect.conn != null) {
					stmt = DBConnect.conn.prepareStatement("SELECT * FROM zerli_db.revenue_reports WHERE store=?");
					stmt.setString(1,store);
					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						String store1 = rs.getString("store");
						String month = rs.getString("month");
						String year = rs.getString("year");
						String orders_amount = rs.getString("ordersamount");
						String income = rs.getString("income");
						String Quarterly = rs.getString("Quarterly");
						String id = rs.getString("id");
						revenue.add(new RevenueReport(store1,month,year,orders_amount,income,Quarterly,id));
					}
					rs.close();
				} else {
					System.out.println("Conn is null");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return revenue;
		}

		public static void updateFile(MyFile file) {
			// TODO Auto-generated method stub
			
		}
		public static ArrayList<String> GetStoreListForCEORordersDistribution() {
			ArrayList<String> stores = new ArrayList<String>();
			Statement stmt;
			try {
				stmt = DBConnect.conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT DISTINCT storeCode FROM zerli_db.stores");
				while (rs.next()) {
					stores.add(rs.getString(1));
				}
					rs.close();
				} catch (SQLException e) {
			e.printStackTrace();
				}
			return stores;
		}

		public static String Get_details_for_orders_Distribution(ArrayList<String> details) {
			PreparedStatement stmt;
			String amount = null;
			int cnt=0;
			int cnt2=0;
			try {
				stmt = DBConnect.conn.prepareStatement("SELECT income FROM zerli_db.revenue_reports WHERE store = ? AND Quarterly = ? AND year=?");
				stmt.setString(1,details.get(0));
				stmt.setString(2,details.get(1));
				stmt.setString(3,details.get(2));
				ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
					amount = rs.getString("income");
					cnt  = Integer.parseInt(amount);
					cnt2 += cnt;
				}
				rs.close();
				} catch (SQLException e) {
			e.printStackTrace();
				}
			amount  = String.valueOf(cnt2);
			 System.out.println(amount);
			return amount;
			}

		public static ArrayList<String> GetYearsListForCEORordersDistribution() {
			ArrayList<String> years = new ArrayList<String>();
			Statement stmt;
			try {
				stmt = DBConnect.conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT DISTINCT year FROM zerli_db.revenue_reports");
				while (rs.next()) {
					years.add(rs.getString(1));
				}
					rs.close();
				} catch (SQLException e) {
			e.printStackTrace();
				}
			return years;
			
}

		public static int GetComplaintsListForCEOComplaintsDistribution(ArrayList<String> details) {
			ArrayList<Timestamp> temp = new ArrayList<Timestamp>();
			ArrayList<String> years = new ArrayList<String>();
			ArrayList<String> Quarterlys = new ArrayList<String>();
			int year,Quarterly,cnt = 0;
			Statement stmt;
			try {
				stmt = DBConnect.conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT complaintTime FROM zerli_db.complaints");
				while (rs.next()) {
					temp.add(rs.getTimestamp("complaintTime"));
				}
				for(Timestamp t : temp) {
					year = t.getYear()+1900;
					Quarterly = ((t.getMonth()/3)+1);
					years.add(String.valueOf(year));
					Quarterlys.add(String.valueOf(Quarterly));
				}
				String quarterly_db = details.get(0);
				String year_db = details.get(1);
				for(int i=0; i<years.size(); i++)
						if(year_db.equals(years.get(i)) && quarterly_db.equals(Quarterlys.get(i)))
							cnt++;
				
					rs.close();
				} catch (SQLException e) {
			e.printStackTrace();
				}
			years.clear();
			Quarterlys.clear();
			return cnt;
		}
			
}

		

			
			

		


		







