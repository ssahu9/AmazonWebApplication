package com.project.junit;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.project.bean.Category;
import com.project.dao.CategoryDaoImpl;

public class CategoryDaoImplTest {

	CategoryDaoImpl categoryDao;
	Category category;

	@Before
	public void setUp() throws Exception {
		categoryDao = new CategoryDaoImpl();
	}

	@After
	public void tearDown() throws Exception {
		categoryDao = null;
	}

	@Test(expected = NullPointerException.class)
	public void positiveDeleteCategory() throws ClassNotFoundException, SQLException {
		assertTrue(categoryDao.deleteCategory(category.getCategoryName()));
	}
}
