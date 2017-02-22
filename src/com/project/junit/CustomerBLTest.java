package com.project.junit;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.project.bean.Customer;
import com.project.bean.Product;
import com.project.bl.CustomerBl;

public class CustomerBLTest {

	CustomerBl customerBL;
	Product product;
	Customer customer;

	@Before
	public void setUp() throws Exception {
		customerBL = new CustomerBl();
	}

	@After
	public void tearDown() throws Exception {
		customerBL = null;
	}

	@Test(expected = NullPointerException.class)
	public void positiveSignIn() throws ClassNotFoundException, SQLException {
		assertEquals(1, customerBL.signIn(customer.getEmail(), customer.getPassword()));
	}

	@Test(expected = NullPointerException.class)
	public void negativeSignIn() throws ClassNotFoundException, SQLException {
		assertNotEquals(1, customerBL.signIn(customer.getEmail(), customer.getPassword()));
	}

	@Test(expected = NullPointerException.class)
	public void positiveRemoveFromCart() throws ClassNotFoundException, SQLException {
		assertTrue(customerBL.removeFromCart(product.getProductId(), customer.getCustomerId()));
	}

	@Test(expected = NullPointerException.class)
	public void negativeRemoveFromCart() throws ClassNotFoundException, SQLException {
		assertFalse(customerBL.removeFromCart(product.getProductId(), customer.getCustomerId()));
	}

}
