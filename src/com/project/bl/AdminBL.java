package com.project.bl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.project.bean.Admin;
import com.project.bean.BillDetails;
import com.project.bean.Category;
import com.project.bean.Product;
import com.project.dao.AdminDao;
import com.project.dao.AdminDaoImpl;
import com.project.dao.BillDao;
import com.project.dao.BillDaoImpl;
import com.project.dao.CategoryDao;
import com.project.dao.CategoryDaoImpl;
import com.project.dao.ProductDao;
import com.project.dao.ProductDaoImpl;

public class AdminBL {

	private AdminDao admin1 = new AdminDaoImpl();
	private ProductDao product1 = new ProductDaoImpl();
	private BillDao bill1 = new BillDaoImpl();
	private CategoryDao category1 = new CategoryDaoImpl();

	public boolean signIn(String email, String password) throws ClassNotFoundException, SQLException {
		// check email
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		java.util.regex.Matcher m = p.matcher(email);
		if (m.matches()) {
			return admin1.validation(email, password);
		} else
			System.out.println("email ID is not valid");

		return false;
	}

	// check password length
	public boolean updatePassword(Admin admin) throws SQLException, ClassNotFoundException {
		if ((admin.getPassword().length() < 8)) {
			System.out.println("Password needs to be atleast 8 character longer");
			return false;
		}
		return admin1.update(admin);
	}

	public boolean addCategory(String categoryname) throws SQLException, ClassNotFoundException {
		return category1.insertCategory(categoryname);
	}

	public List<Category> viewCategory() throws ClassNotFoundException, SQLException {
		return category1.viewCategory();

	}

	public boolean deleteCategory(String categoryname) throws SQLException, ClassNotFoundException {
		return category1.deleteCategory(categoryname);
	}

	public boolean addProduct(Product product) throws SQLException, ClassNotFoundException {
		return product1.addProduct(product);
	}

	public List<Product> viewProduct(String pcategory) throws ClassNotFoundException, SQLException {
		return product1.viewProduct(pcategory);
	}

	public boolean updateProduct(Product product) throws ClassNotFoundException, SQLException {
		return product1.updateProduct(product);

	}

	public Boolean deleteProduct(int pid) throws ClassNotFoundException, SQLException {
		return product1.deleteProduct(pid);
	}

	public List<BillDetails> getAllBillDetails() throws SQLException, ClassNotFoundException {
		return bill1.getAllBillDetails();
	}
}
