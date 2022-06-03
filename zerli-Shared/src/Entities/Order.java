package Entities;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;


public class Order implements Serializable{

	private static final long serialVersionUID = 1L;
	private int orderNumber;
	private String price;
	private String greetingCard;
	private String store;
    private String ClientId;
    private String SupplimentMethod;
    private String status;
    

	public String suppTime = null;
	public String suppDate = null;
	public Timestamp orderTime=null;

	public Delivery delivery =null;


	public Order(String ClientId,int OrderNumber,String store,String greetingCard,String Status,
			String price,String suppmethod,String suppTime,String suppDate,Timestamp timestamp)
	{
		this.orderNumber = OrderNumber;
		this.price = price;
		this.greetingCard =greetingCard;
		this.status = Status;
		this.SupplimentMethod = suppmethod;
		this.store = store;
		this.suppDate = suppDate;
		this.suppTime = suppTime;
		this.orderTime = timestamp;
		this.ClientId = ClientId;
		
	}
	

	public Order(String greetingCard,String Status,
			String store,String ClientId,String suppTime,String suppDate,String suppmethod)
	{
		
		this.greetingCard =greetingCard;
		this.status = Status;
		this.SupplimentMethod = suppmethod;
		this.store = store;
		this.ClientId = ClientId;
		this.suppDate = suppDate;
		this.suppTime = suppTime;
	
	}

	
	public int getOrderNumber()
	{
		return orderNumber;	
	}
	
	public void setOrderNumber(int ordernumber)
	{
		orderNumber = ordernumber;
	}
	
	public String getPrice()
	{
		return price;
	}
	
	public void setPrice(String price)
	{
		this.price = price;
	}
	
	public String getGreetingCard()
	{
		return greetingCard;
	}
	
	public void setGreetingCard(String greetingCard)
	{
		this.greetingCard = greetingCard;
	}

	public String getStore() {
		String[] s = store.split(" ");
		return s[0];
	}

	public String getClientId() {
		return ClientId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

    
	public String getSupplimentMethod() {
		return SupplimentMethod;
	}


	public String getSuppTime() {
		
		return suppTime;
	}

	public String getSuppDate() {
		return this.suppDate.toString();
	}


	public Delivery getDelivery() {
		return delivery;
	}


	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}
	
	
	public Timestamp getOrderTime() {
		return orderTime;
	}
	
	public void setOrderTime(Timestamp orderTime) {
		this.orderTime = orderTime;
	}
		
	}
