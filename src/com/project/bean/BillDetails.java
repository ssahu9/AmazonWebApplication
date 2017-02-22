package com.project.bean;

import java.sql.Date;

public class BillDetails {
	private int customerId;
	private int billId;
	private int productId;
	private double price;
	private int discount;
	private int quantity;
	private double totalPrice;
	private Date date;

	@Override
	public String toString() {
		return "BillDetails [customerId=" + customerId + ", billId=" + billId + ", productId=" + productId + ", price="
				+ price + ", discount=" + discount + ", quantity=" + quantity + ", totalPrice=" + totalPrice + ", date="
				+ date + "]";
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
