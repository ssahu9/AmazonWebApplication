package com.project.junit;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.project.bean.Admin;
import com.project.bean.Category;
import com.project.bean.Customer;
import com.project.bean.Product;
import com.project.bl.AdminBL;
import com.project.dao.AdminDao;
import com.project.helper.CreateConnection;

public class AdminBLTest {

	AdminBL adminBL;
	Customer customer;
	Admin admin;
	Category category;
	Product product;

	@Before
	public void setUp() throws Exception {
		adminBL = new AdminBL();
	}

	@After
	public void tearDown() throws Exception {
		adminBL = null;
	}

	@Test(expected = NullPointerException.class)
	public void positiveSignIn() throws ClassNotFoundException, SQLException {
		assertTrue(adminBL.signIn(admin.getMail(), admin.getPassword()));
	}

	@Test(expected = NullPointerException.class)
	public void negativeSignIn() throws ClassNotFoundException, SQLException {
		assertFalse(adminBL.signIn(admin.getMail(), admin.getPassword()));
	}

	@Test(expected = NullPointerException.class)
	public void positiveDeleteCategory() throws ClassNotFoundException, SQLException {
		assertTrue(adminBL.deleteCategory(category.getCategoryName()));
	}

	@Test(expected = NullPointerException.class)
	public void negativeDeleteCategory() throws ClassNotFoundException, SQLException {
		assertFalse(adminBL.deleteCategory(category.getCategoryName()));
	}

	@Test(expected = NullPointerException.class)
	public void positiveDeleteProduct() throws ClassNotFoundException, SQLException {
		assertTrue(adminBL.deleteProduct(product.getProductId()));
	}

	@Test(expected = NullPointerException.class)
	public void negativeDeleteProduct() throws ClassNotFoundException, SQLException {
		assertFalse(adminBL.deleteProduct(product.getProductId()));
	}
}
