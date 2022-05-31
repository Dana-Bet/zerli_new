package Entities;

import java.io.Serializable;
import java.sql.Timestamp;

public class Complaint implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String clientId,status,reason,refund;
	Timestamp complaintTime;
	private int alert=0;
	
	

	public Complaint(String clientId,String status , Timestamp time, String reason,String refund) {

		this.clientId = clientId;
		this.status = status;
		this.complaintTime = time;
		this.reason = reason;
	}
	public int getAlert() {
		return alert;
	}
	
	public String getClientId() {
		return clientId;
	}

	public String getStatus() {
		return status;
	}

	public String getReason() {
		return reason;
	}

	public String getRefund() {
		return refund;
	}

	public Timestamp getComplaintTime() {
		return complaintTime;
	}
	
	public void setComplaintTime(Timestamp time) {
		this.complaintTime =time;
	}
	
	public void setAlert(int f) {
		this.alert=f;
	}
	
	public String toString() {
		return "Client: "+clientId+"\n"+"Topic: "+reason +"\n"+"Status: "+status+"\n";
		
	}

}
