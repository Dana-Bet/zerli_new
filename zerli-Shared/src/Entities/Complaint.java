package Entities;

import java.io.Serializable;
import java.sql.Timestamp;

public class Complaint implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String id,status,reason,refund,priceOfOrder,content;
	private int orderNum;
	Timestamp complaintTime;
	
	public Complaint(int orderNum, String id, Timestamp complaintTime,String status, String reason,String refund,String price,String content) {
		this.orderNum = orderNum;
		this.id = id;
		this.complaintTime = complaintTime;
		this.status = status;
		this.reason = reason;
		this.refund = refund;
		this.priceOfOrder = price;
		this.content = content;
	
	}
	

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Timestamp getComplaintTime() {
		return complaintTime;
	}
	public void setComplaintTime(Timestamp complaintTime) {
		this.complaintTime = complaintTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getRefund() {
		return refund;
	}
	public void setRefund(String refund) {
		this.refund = refund;
	}
	public int getOrderNum() {
		return orderNum;
	
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}


	public String getPriceOfOrder() {
		return priceOfOrder;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String text) {
		this.content = text;
		
	}

    public String toString() {
		return this.getReason()+" "+this.getId()+" "+this.getRefund()+" "+this.priceOfOrder;
    	
    }
}
