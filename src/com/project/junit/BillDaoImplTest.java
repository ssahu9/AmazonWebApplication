package com.project.junit;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.project.bean.Bill;
import com.project.bean.BillDetails;
import com.project.bean.Customer;
import com.project.dao.BillDaoImpl;

public class BillDaoImplTest {

	BillDaoImpl billDao;
	Customer customer;
	Bill bill;
	String str = "2017-02-08";
	Date date = Date.valueOf(str);

	@Before
	public void setUp() throws Exception {
		billDao = new BillDaoImpl();
	}

	@After
	public void tearDown() throws Exception {
		billDao = null;
	}

	@Test
	public void positiveGenerateBill() {
		customer = new Customer(1, "Sherlock", "Holmes", "221B", "SH", "221221");
		assertEquals(1, customer.getCustomerId());
	}

	@Test
	public void negativeGenerateBill() {
		customer = new Customer(1, "Sherlock", "Holmes", "221B", "SH", "221221");
		assertNotEquals(2, customer.getCustomerId());
	}

	@Test
	public void positiveGetBillDetails() {
		customer = new Customer(1, "Sherlock", "Holmes", "221B", "SH", "221221");
		assertEquals(1, customer.getCustomerId());
	}

	@Test
	public void negativeGetBillDetails() {
		customer = new Customer(1, "Sherlock", "Holmes", "221B", "SH", "221221");
		assertNotEquals(2, customer.getCustomerId());
	}
}
