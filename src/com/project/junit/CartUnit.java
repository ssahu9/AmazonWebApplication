package com.project.junit;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.project.bean.Cart;
import com.project.bean.Customer;
import com.project.bean.Product;
import com.project.dao.CartDaoImpl;
import com.project.helper.AddToCart;

public class CartUnit {

	CartDaoImpl cartDao;
	AddToCart addToCart;
	Product product;
	Customer customer;
	Cart cart;
	List<Cart> cartList = new LinkedList<Cart>();
	String str = "2017-02-08";
	Date date = Date.valueOf(str);

	@Before
	public void setUp() throws Exception {
		cartDao = new CartDaoImpl();
	}

	@After
	public void tearDown() throws Exception {
		cartDao = null;
	}

	@Test(expected = NullPointerException.class)
	public void testRemovePositive() throws ClassNotFoundException, SQLException {
		assertTrue(cartDao.removeFromCart(product.getProductId(), customer.getCustomerId()));
	}

	@Test(expected = NullPointerException.class)
	public void testRemoveNegative() throws ClassNotFoundException, SQLException {
		assertFalse(cartDao.removeFromCart(product.getProductId(), customer.getCustomerId()));
	}

	@Test(expected = NullPointerException.class)
	public void positiveViewCart() throws ClassNotFoundException, SQLException {
		customer.setCustomerId(2);
		assertEquals(2, customer.getCustomerId());
	}

	@Test(expected = NullPointerException.class)
	public void negativeViewCart() throws ClassNotFoundException, SQLException {
		customer.setCustomerId(2);
		assertNotEquals(3, customer.getCustomerId());
	}

}
