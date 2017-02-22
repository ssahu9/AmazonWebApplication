package com.project.bean;

public class BillHistory {
	private int billId;
	private int productId;
	private int quantity;
	private int discount;
	private double productPrice;

	public BillHistory() {

	}

	@Override
	public String toString() {
		return "BillHistory [billId=" + billId + ", productId=" + productId + ", quantity=" + quantity + ", discount="
				+ discount + ", productPrice=" + productPrice + "]";
	}

	public BillHistory(int billId, int productId, int quantity, int discount, double productPrice) {
		super();
		this.billId = billId;
		this.productId = productId;
		this.quantity = quantity;
		this.discount = discount;
		this.productPrice = productPrice;
	}

	public int getBillId() {
		return billId;
	}

	public void setBillId(int billId) {
		this.billId = billId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

}
