package com.project.bl;

import java.sql.SQLException;
import java.util.List;

import com.project.bean.Bill;
import com.project.bean.BillDetails;
import com.project.bean.Cart;
import com.project.bean.CartDetails;
import com.project.bean.Category;
import com.project.bean.Customer;
import com.project.bean.Product;
import com.project.dao.BillDao;
import com.project.dao.BillDaoImpl;
import com.project.dao.CartDao;
import com.project.dao.CartDaoImpl;
import com.project.dao.CategoryDao;
import com.project.dao.CategoryDaoImpl;
import com.project.dao.CustomerDao;
import com.project.dao.CustomerDaoImpl;
import com.project.dao.ProductDao;
import com.project.dao.ProductDaoImpl;

public class CustomerBl {

	private CustomerDao user = new CustomerDaoImpl();
	private CartDao cart = new CartDaoImpl();
	private CategoryDao category1 = new CategoryDaoImpl();
	private BillDao bill = new BillDaoImpl();
	private ProductDao product = new ProductDaoImpl();

	public Customer signIn(String email, String password) throws ClassNotFoundException, SQLException {
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		java.util.regex.Matcher m = p.matcher(email);
		if (m.matches()) {
			return user.validation(email, password);
		} else
			System.out.println("email ID is not valid");

		return null;

	}

	public int signUp(Customer customer) throws ClassNotFoundException, SQLException {

		if ((customer.getPassword().length() < 8)) {
			System.out.println("Password needs to be atleast 8 character longer");
			return 0;
		}
		if (customer.getPhoneNumber().contains("[a-zA-Z]+") == true || customer.getPhoneNumber().length() < 10) {
			System.out.println("Please check Phone Number");
			return 0;
		}
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		java.util.regex.Matcher m = p.matcher(customer.getEmail());
		if (!m.matches()) {
			System.out.println("Invalid Email ID");
			return 0;
		}
		System.out.println(customer); // test
		return user.insert(customer);
	}

	public boolean updateDetails(Customer customer) throws ClassNotFoundException, SQLException {

		if ((customer.getPassword().length() < 8)) {
			System.out.println("Password needs to be atleast 8 character longer");
			return false;
		}
		if (customer.getPhoneNumber().contains("[a-zA-Z]+") == true || customer.getPhoneNumber().length() < 10) {
			System.out.println("Please check Phone Number");
			return false;
		}
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		java.util.regex.Matcher m = p.matcher(customer.getEmail());
		if (!m.matches()) {
			System.out.println("Invalid Email ID");
			return false;
		}
		return user.update(customer);
	}

	public List<CartDetails> viewCart(int customerId) throws ClassNotFoundException, SQLException {
		return cart.viewCart(customerId);
	}

	public boolean addToCart(List<Cart> cartList) throws ClassNotFoundException, SQLException {

		return cart.addToCart(cartList);
	}

	public boolean removeFromCart(int productId, int customerId) throws ClassNotFoundException, SQLException {
		return cart.removeFromCart(productId, customerId);
	}

	public List<Category> viewCategory() throws ClassNotFoundException, SQLException {
		return category1.viewCategory();
	}

	public Bill generateBill(int customerId) throws ClassNotFoundException, SQLException {
		return bill.generateBill(customerId);
	}

	public List<BillDetails> getBillDetails(int customerId) throws ClassNotFoundException, SQLException {
		return bill.getBillDetails(customerId);
	}

	public List<BillDetails> getCurrentBill(Bill bill1) throws ClassNotFoundException, SQLException {
		return bill.getCurrentBill(bill1);
	}

	public List<Product> viewProduct(String pcategory) throws ClassNotFoundException, SQLException {
		return product.viewProduct(pcategory);
	}

	public Product searchProductByName(String productName) throws ClassNotFoundException, SQLException {
		return product.searchProductByName(productName);
	}

}
