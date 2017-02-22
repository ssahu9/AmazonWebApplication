package com.project.dao;

import java.sql.SQLException;
import java.util.List;

import com.project.bean.Category;
import com.project.bean.Product;

public interface CategoryDao {
	public List<Category> viewCategory() throws ClassNotFoundException, SQLException;

	public boolean insertCategory(String categoryname) throws SQLException, ClassNotFoundException;

	public boolean deleteCategory(String categoryname) throws SQLException, ClassNotFoundException;

}
