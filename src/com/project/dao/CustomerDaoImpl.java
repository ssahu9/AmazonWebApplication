package com.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.project.bean.Customer;
import com.project.helper.CreateConnection;

public class CustomerDaoImpl implements CustomerDao {

	//private CreateConnection createCon = new CreateConnection();
	private Connection connection = null;
	private ResultSet rs = null;
	private PreparedStatement pstmt = null;

	@Override
	public int insert(Customer customer) throws ClassNotFoundException, SQLException {
		connection = CreateConnection.getCon();
		Statement statement = connection.createStatement();
		rs = statement.executeQuery("SELECT MAX(CUSTOMER_ID) FROM CUSTOMER_INFO");
		rs.next();
		int cId = rs.getInt(1);
		customer.setCustomerId((cId + 1));
		pstmt = connection.prepareStatement("INSERT INTO CUSTOMER_INFO VALUES(?,?,?,?,?,?)");
		pstmt.setInt(1, customer.getCustomerId());
		pstmt.setString(2, customer.getFirstName());
		pstmt.setString(3, customer.getLastName());
		pstmt.setString(4, customer.getEmail());
		pstmt.setString(5, customer.getPassword());
		pstmt.setString(6, customer.getPhoneNumber());
		int row = 0;
		try {
			row = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
		if (row > 0) {
			connection.close();
			return (cId + 1);
		} else
			return 0;

	}

	@Override
	public Customer validation(String email, String password) throws ClassNotFoundException, SQLException {
		connection = CreateConnection.getCon();
		pstmt = connection.prepareStatement("SELECT* FROM CUSTOMER_INFO WHERE EMAIL=? AND PASSWORD=?");
		pstmt.setString(1, email);
		pstmt.setString(2, password);
		rs = pstmt.executeQuery();
		rs.next();
		Customer customer=new Customer();
		customer.setCustomerId( rs.getInt(1));
		customer.setFirstName( rs.getString(2));
		customer.setLastName( rs.getString(3));
		customer.setEmail( rs.getString(4));
		customer.setPassword( rs.getString(5));
		customer.setPhoneNumber( rs.getString(6));
		
		
//		int customerId = rs.getInt(1);
		connection.close();
		return customer;
	}

	@Override
	public boolean update(Customer customer) throws ClassNotFoundException, SQLException {
		connection = CreateConnection.getCon();
		pstmt = connection.prepareStatement(
				"UPDATE CUSTOMER_INFO SET FIRST_NAME=?,LAST_NAME=?,PASSWORD=?,PHONE_NUMBER=? WHERE EMAIL=?");
		pstmt.setString(1, customer.getFirstName());
		pstmt.setString(2, customer.getLastName());
		pstmt.setString(3, customer.getPassword());
		pstmt.setString(4, customer.getPhoneNumber());
		pstmt.setString(5, customer.getEmail());
		int row = pstmt.executeUpdate();
		connection.close();
		if (row > 0)
			return true;
		else
			return false;

	}
}
