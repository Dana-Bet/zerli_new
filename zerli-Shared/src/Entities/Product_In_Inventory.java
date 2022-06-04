package Entities;

import java.io.Serializable;

public class Product_In_Inventory implements Serializable { 

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ProName;
	private String ProId;
	private String store;
	private int quantity;
	
	public Product_In_Inventory(String proName, String proId, String store, int quantity) {
		ProName = proName;
		ProId = proId;
		this.store = store;
		this.quantity = quantity;
	}

	
	public void setProName(String proName) {
		ProName = proName;
	}

	public void setProId(String proId) {
		ProId = proId;
	}

	public void setStore(String store) {
		this.store = store;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String getProName() {
		return ProName;
	}


	public String getProId() {
		return ProId;
	}


	public String getStore() {
		return store;
	}


	public int getQuantity() {
		return quantity;
	}


}
