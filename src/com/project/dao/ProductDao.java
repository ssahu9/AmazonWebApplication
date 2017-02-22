package com.project.dao;

import java.sql.SQLException;
import java.util.List;

import com.project.bean.Product;

public interface ProductDao {
	boolean addProduct(Product p) throws SQLException, ClassNotFoundException;

	Boolean deleteProduct(int pid) throws ClassNotFoundException, SQLException;

	boolean updateProduct(Product p) throws ClassNotFoundException, SQLException;

	List<Product> viewProduct(String category) throws ClassNotFoundException, SQLException;

	Product searchProductByName(String product) throws ClassNotFoundException, SQLException;

}
