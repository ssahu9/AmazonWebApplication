package com.project.bean;

import java.sql.Date;

public class CartDetails {
	private int productId;
	private String productName;
	private double price;
	private int quantity;
	private int discount;
	private double totalPrice;
	private String category;
	private Date date;

	public CartDetails() {

	}

	public CartDetails(int productId, String productName, double price, int quantity, int discount, double totalPrice,
			String category, Date date) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.quantity = quantity;
		this.discount = discount;
		this.totalPrice = totalPrice;
		this.category = category;
		this.date = date;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	@Override
	public String toString() {
		return "CartDetails [productId=" + productId + ", productName=" + productName + ", price=" + price
				+ ", quantity=" + quantity + ", discount=" + discount + ", totalPrice=" + totalPrice + ", category="
				+ category + ", date=" + date + "]";
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
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

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
