package com.project.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import com.project.bean.Cart;
import com.project.bean.CartDetails;
import com.project.helper.CreateConnection;

public class CartDaoImpl implements CartDao {

	//private CreateConnection createCon = new CreateConnection();
	private Connection connection = null;
	private ResultSet rs = null;
	private PreparedStatement pstmt = null;

	// Method to add the product to customer cart
	@Override
	public boolean addToCart(List<Cart> cartList) throws ClassNotFoundException, SQLException {
		int count = 0;
		connection = CreateConnection.getCon();
		pstmt = connection.prepareStatement("INSERT INTO CART VALUES(?,?,?,?)");
		for (Cart cart : cartList) {
			pstmt.setInt(1, cart.getCustomerId());
			pstmt.setInt(2, cart.getProductId());
			pstmt.setInt(3, cart.getQuantity());
			pstmt.setDate(4, cart.getCartDate());
			pstmt.executeQuery();
			count++;
			connection.close();
		}
		if (count == cartList.size())
			return true;
		else
			return false;
	}

	// Method to remove the product from cart
	@Override
	public boolean removeFromCart(int productId, int customerId) throws SQLException, ClassNotFoundException {
		connection = CreateConnection.getCon();
		pstmt = connection.prepareStatement("DELETE FROM cart WHERE product_id =? AND customer_Id=?");
		pstmt.setInt(1, productId);
		pstmt.setInt(2, customerId);
		int rows = pstmt.executeUpdate();
		connection.close();
		if (rows > 0) {
			return true;
		}
		return false;
	}

	// method to return the list of product in customer cart
	@Override
	public List<CartDetails> viewCart(int customerId) throws SQLException, ClassNotFoundException {

		List<CartDetails> listOfItem = new LinkedList<CartDetails>();
		connection = CreateConnection.getCon();
		Date date;
		int pid, quantity, discount;
		Statement stmt = connection.createStatement();
		pstmt = connection
				.prepareStatement("SELECT  product_id,product_quantity,cart_date FROM CART WHERE CUSTOMER_ID=?");
		pstmt.setInt(1, customerId);
		rs = pstmt.executeQuery();
		ResultSet product = null;
		while (rs.next()) {
			pid = rs.getInt("product_id");
			quantity = (rs.getInt("product_quantity"));
			date = rs.getDate("cart_date");
			product = stmt.executeQuery("select product_name, product_category,product_price,"
					+ "product_discount from product_info where product_id=" + pid);
			product.next();
			String pn = product.getString("product_name");
			String cat = product.getString("product_category");
			double price = (int) product.getInt("product_price");
			discount = product.getInt("product_discount");
			double totalPrice = (quantity * price) * (100 - discount) / 100;
			CartDetails cd = new CartDetails();
			cd.setPrice(totalPrice);
			cd.setDiscount(discount);
			cd.setProductId(pid);
			cd.setCategory(cat);
			cd.setQuantity(quantity);
			cd.setDate(date);
			cd.setTotalPrice(totalPrice);
			cd.setProductName(pn);
			listOfItem.add(cd);
		}
		connection.close();
		return listOfItem;
	}

}
