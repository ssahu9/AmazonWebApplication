package com.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.project.bean.Admin;
import com.project.helper.CreateConnection;

public class AdminDaoImpl implements AdminDao {

	//private CreateConnectionnectionnection CreateConnectionnection = new CreateConnectionnectionnection();
	private Connection connection = null;
	private ResultSet rs = null;
	private PreparedStatement pstmt = null;

	@Override
	public boolean validation(String email, String password) throws ClassNotFoundException, SQLException {
		connection = CreateConnection.getCon();
		pstmt = connection.prepareStatement("SELECT * FROM ADMIN_INFO WHERE EMAIL=? AND PASSWORD=?");

		pstmt = connection.prepareStatement("SELECT * FROM ADMIN WHERE EMAIL=? AND PASSWORD=?");
		pstmt.setString(1, email);
		pstmt.setString(2, password);
		int row = pstmt.executeUpdate();
		connection.close();
		if (row > 0)
			return true;
		else
			return false;
	}

	@Override
	public boolean update(Admin admin) throws SQLException, ClassNotFoundException {
		connection = CreateConnection.getCon();
		pstmt = connection.prepareStatement("UPDATE ADMIN SET PASSWORD=? WHERE EMAIL=?");
		pstmt.setString(2, admin.getMail());
		pstmt.setString(1, admin.getPassword());
		int row = pstmt.executeUpdate();
		connection.close();
		if (row > 0)
			return true;
		else
			return false;
	}

}
