package com.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.project.bean.Product;
import com.project.helper.CreateConnection;
import com.project.helper.DisplayErrorMessage;

public class ProductDaoImpl implements ProductDao {
	//private CreateConnection cd = new CreateConnection();
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private Product product = null;
	private ResultSet rs = null;

	// Method to add new product details in databse.
	public boolean addProduct(Product product) throws SQLException, ClassNotFoundException {
		con = CreateConnection.getCon();
		String s = product.getCategory();
		pstmt = con.prepareStatement("SELECT COUNT(*) FROM CATEGORY WHERE CATEGORY_NAME=?");
		pstmt.setString(1, s);
		rs = pstmt.executeQuery();
		rs.next();
		// verifying the entered product category exist or not
		// if not then display error message.
		if (rs.getInt(1) == 0) {
			DisplayErrorMessage
					.displayError(product.getCategory() + " Category Does not exit,please enter correct one!!!");
			return false;
		}
		pstmt = con.prepareStatement("insert into product_info  values(?,?,?,?,?,?)");
		pstmt.setInt(1, product.getProductId());
		pstmt.setString(2, product.getName());
		pstmt.setString(3, product.getCategory());
		pstmt.setDouble(4, product.getPrice());
		pstmt.setInt(5, product.getQuantity());
		pstmt.setInt(6, product.getDiscount());
		int rows = pstmt.executeUpdate(); // entering product details.
		con.close();
		if (rows > 0) {
			return true;
		}

		return false;
	}

	// Method to delete the particular product by using product id .

	@Override
	public Boolean deleteProduct(int pid) throws SQLException, ClassNotFoundException {
		con = CreateConnection.getCon();
		Statement stmt = con.createStatement();
		String sql = "DELETE FROM product_info " + "WHERE product_id =" + pid;
		int rows = stmt.executeUpdate(sql);
		if (rows > 0) {
			return true;
		}

		return false;
	}

	// Method to update the product details such as quantity, price, discount by
	// using product id.

	@Override
	public boolean updateProduct(Product product) throws SQLException, ClassNotFoundException {
		con = CreateConnection.getCon();
		pstmt = con.prepareStatement(
				"UPDATE PRODUCT_INFO SET  product_name=?,product_price=? , product_quantity=?,product_discount=? WHERE product_id=?");

		pstmt.setString(1, product.getName());
		pstmt.setDouble(2, product.getPrice());
		pstmt.setInt(3, product.getQuantity());
		pstmt.setInt(4, product.getDiscount());
		pstmt.setInt(5, product.getProductId());
		int row = pstmt.executeUpdate();
		con.close();
		if (row > 0)
			return true;
		else
			return false;
	}

	// method to display all product of particular category
	// and if category string is null then display all product from all
	// category.
	@Override
	public List<Product> viewProduct(String pcategory) throws SQLException, ClassNotFoundException {
		List<Product> prodList = new ArrayList<Product>();
		con = CreateConnection.getCon();
		Statement stmt = con.createStatement();
		pstmt = con.prepareStatement("select * from product_info where product_category=?");
		pstmt.setString(1, pcategory);
		rs = null;
		if (pcategory == null)
			rs = stmt.executeQuery("select * from product_info");
		else
			rs = pstmt.executeQuery();
		while (rs.next()) {
			int pid = rs.getInt("product_id");
			String productname = rs.getString("product_name");
			String category = rs.getString("product_category");
			int productprice = rs.getInt("product_price");
			int productquantity = rs.getInt("product_quantity");
			product = new Product();
			product.setProductId(pid);
			product.setName(productname);
			product.setCategory(category);
			product.setPrice(productprice);
			product.setQuantity(productquantity);
			prodList.add(product); // generating list of product.
		}
		con.close();
		return prodList; // returning back product list to Business Logic layer.

	}

	// searching product by using product name.
	@Override
	public Product searchProductByName(String productName) throws SQLException, ClassNotFoundException {
		con = CreateConnection.getCon();
		pstmt = con.prepareStatement("select * from product_info where product_name=?");
		pstmt.setString(1, productName);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			int pid = rs.getInt("product_id");
			String productname = rs.getString("product_name");

			String category = rs.getString("product_category");
			int productprice = rs.getInt("product_price");
			int productquantity = rs.getInt("product_quantity");
			product = new Product();
			product.setProductId(pid);
			product.setName(productname);
			product.setCategory(category);
			product.setPrice(productprice);
			product.setQuantity(productquantity);

		}
		con.close();
		return product;

	}

}
