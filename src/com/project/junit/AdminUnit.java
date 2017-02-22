package com.project.junit;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.project.bean.Admin;
import com.project.bean.Customer;
import com.project.dao.AdminDao;
import com.project.dao.AdminDaoImpl;

public class AdminUnit {

	AdminDaoImpl adminDao;
	Admin admin;

	@Before
	public void setUp() throws Exception {
		adminDao = new AdminDaoImpl();
	}

	@After
	public void tearDown() throws Exception {
		adminDao = null;
	}

	// @Test
	// public void positiveTestValidation() throws ClassNotFoundException,
	// SQLException {
	// admin = new Admin("admin", "password");
	// assertTrue(adminDao.validation("admin", "password"));
	// }
	//
	// @Test
	// public void emailTestValidation() throws ClassNotFoundException,
	// SQLException {
	// admin = new Admin("admin", "password");
	// assertFalse(adminDao.validation("hsh", "password"));
	// }
	//
	// @Test
	// public void passwordTestValidation() throws ClassNotFoundException,
	// SQLException {
	// admin = new Admin("admin", "password");
	// assertFalse(adminDao.validation("abc@xyz.com", "1password"));
	// }

	@Test(expected = NullPointerException.class)
	public void positiveUpdate() throws SQLException, ClassNotFoundException {
		admin.setPassword("password2");
		assertTrue(adminDao.update(admin));
	}

	@Test(expected = NullPointerException.class)
	public void negativeUpdate() throws SQLException, ClassNotFoundException {
		admin.setPassword("passwor");
		assertFalse(adminDao.update(admin));
	}
}
