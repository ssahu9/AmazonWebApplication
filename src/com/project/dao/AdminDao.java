package com.project.dao;

import java.sql.SQLException;

import com.project.bean.Admin;

public interface AdminDao {

	boolean validation(String mail, String password) throws ClassNotFoundException, SQLException;

	boolean update(Admin a) throws ClassNotFoundException, SQLException;
}
