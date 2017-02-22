package com.project.bean;

import java.sql.Date;

public class Bill {
	private int customerId;
	private int billId;
	private Date billDate;

	public Bill() {

	}

	public Bill(int customerId, int billId, Date billDate) {
		this.customerId = customerId;
		this.billId = billId;
		this.billDate = billDate;
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

	public Date getBillDate() {
		return billDate;
	}

	@Override
	public String toString() {
		return "Bill [customerId=" + customerId + ", billId=" + billId + ", billDate=" + billDate + "]";
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}
}
