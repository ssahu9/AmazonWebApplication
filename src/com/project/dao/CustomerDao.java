package com.project.dao;

import java.sql.SQLException;

import com.project.bean.Customer;

public interface CustomerDao {

	int insert(Customer customer) throws ClassNotFoundException, SQLException;

	Customer validation(String mail, String password) throws ClassNotFoundException, SQLException;

	boolean update(Customer customer) throws ClassNotFoundException, SQLException;

}
