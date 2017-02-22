package com.project.dao;

import java.sql.SQLException;
import java.util.List;
import com.project.bean.Bill;
import com.project.bean.BillDetails;

public interface BillDao {
	Bill generateBill(int customerId) throws ClassNotFoundException, SQLException;

	List<BillDetails> getBillDetails(int customerId) throws ClassNotFoundException, SQLException;

	List<BillDetails> getAllBillDetails() throws ClassNotFoundException, SQLException;

	List<BillDetails> getCurrentBill(Bill bill) throws ClassNotFoundException, SQLException;
}
