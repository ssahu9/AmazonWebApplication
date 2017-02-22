package com.project.junit;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.project.bean.Product;
import com.project.dao.ProductDaoImpl;

public class ProductDaoTest {

	ProductDaoImpl productDao;
	Product product;

	@Before
	public void setUp() throws Exception {
		productDao = new ProductDaoImpl();
	}

	@After
	public void tearDown() throws Exception {
		productDao = null;
	}

	@Test(expected = SQLException.class)
	public void positiveTestAddProductId() throws ClassNotFoundException, SQLException {
		product = new Product(4, "aa", "bb", 10, 10, 10);
		assertTrue(productDao.addProduct(product));
	}

	@Test(expected = SQLException.class)
	public void negativeTestAddProductId() throws ClassNotFoundException, SQLException {
		product = new Product(-4, "aa", "bb", 10, 10, 10);
		assertFalse(productDao.addProduct(product));
		System.out.println("Product id can't be negative");
	}

	@Test(expected = SQLException.class)
	public void zeroTestAddProductId() throws ClassNotFoundException, SQLException {
		product = new Product(0, "aa", "bb", 10, 10, 10);
		assertFalse(productDao.addProduct(product));
		System.out.println("Product id can't be negative");
	}

	@Test(expected = SQLException.class)
	public void negativeTestAddPrice() throws ClassNotFoundException, SQLException {
		product = new Product(4, "aa", "bb", -10, 10, 10);
		assertFalse(productDao.addProduct(product));
		System.out.println("Product id can't be negative");
	}

	@Test(expected = SQLException.class)
	public void negativeTestAddQuantity() throws ClassNotFoundException, SQLException {
		product = new Product(4, "aa", "bb", 10, -10, 10);
		assertFalse(productDao.addProduct(product));
		System.out.println("Product id can't be negative");
	}

	@Test(expected = SQLException.class)
	public void zeroTestAddQuantity() throws ClassNotFoundException, SQLException {
		product = new Product(4, "aa", "bb", 10, 0, 10);
		assertFalse(productDao.addProduct(product));
		System.out.println("Product id can't be negative");
	}

	@Test(expected = SQLException.class)
	public void negativeTestAddDiscount() throws ClassNotFoundException, SQLException {
		product = new Product(4, "aa", "bb", 10, 10, -10);
		assertFalse(productDao.addProduct(product));
		System.out.println("Product id can't be negative");
	}

	@Test(expected = SQLException.class)
	public void zeroTestAddDiscount() throws ClassNotFoundException, SQLException {
		product = new Product(4, "aa", "bb", 10, 10, 0);
		assertFalse(productDao.addProduct(product));
		System.out.println("Product id can't be negative");
	}

}
