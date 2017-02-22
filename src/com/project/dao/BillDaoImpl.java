package com.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import com.project.bean.Bill;
import com.project.bean.BillDetails;
import com.project.bean.BillHistory;
import com.project.helper.CreateConnection;
import com.project.helper.DisplayErrorMessage;

public class BillDaoImpl implements BillDao {
	//private CreateConnection createCon = new CreateConnection();
	private Connection connection = null;
	private ResultSet rs = null;
	private ResultSet rs2 = null;
	private ResultSet rs3 = null;
	private PreparedStatement pstmt = null;
	Bill bill = new Bill();

	
	// method to generate bill according to the products are in cart for particular customer
	@Override
	public Bill generateBill(int customerId) throws ClassNotFoundException, SQLException {
		List<BillHistory> billRecords = new LinkedList<BillHistory>();
		BillHistory bDetail;
		connection = CreateConnection.getCon();
		Statement statement = connection.createStatement();	
		pstmt = connection.prepareStatement("SELECT PRODUCT_ID,PRODUCT_QUANTITY FROM CART WHERE CUSTOMER_ID=?");
		pstmt.setInt(1, customerId);
		rs = pstmt.executeQuery();
		
		int productId = 0;
		int productQuantity = 0;
		int productDiscount = 0;
		double productPrice = 0;
		int productQuantityOriginal = 0;
		int rows = 0;
		while (rs.next()) {
			productId = rs.getInt("PRODUCT_ID");
			productQuantity = rs.getInt("PRODUCT_QUANTITY");
			pstmt = connection.prepareStatement("SELECT PRODUCT_PRICE,PRODUCT_DISCOUNT,PRODUCT_QUANTITY FROM PRODUCT_INFO WHERE PRODUCT_ID=?");
			pstmt.setInt(1, productId);
			rs2 = pstmt.executeQuery();
			rs2.next();
			productPrice = rs2.getInt("PRODUCT_PRICE");
			productDiscount = rs2.getInt("PRODUCT_DISCOUNT");
			productQuantityOriginal = rs2.getInt("PRODUCT_QUANTITY");
		
			if(productQuantity<=productQuantityOriginal){
				bDetail= new  BillHistory();
				bDetail.setDiscount(productDiscount);
				bDetail.setProductId(productId);
				bDetail.setProductPrice(productPrice);
				bDetail.setQuantity(productQuantity);
				billRecords.add(bDetail);
			}
			else{
				DisplayErrorMessage.displayError(productId+" available quantity is "+productQuantityOriginal);
				return null;
			}
		}
		
		System.out.println(billRecords);
			rs3 = statement.executeQuery("SELECT MAX(BILL_ID) FROM BILL");
			int billId;
			rs3.next();
			billId = rs3.getInt(1) ;
			billId= billId+1;
			// entering new record in bill table with new bill id and customer id 
			pstmt = connection.prepareStatement("insert into bill  values(?,?,?)");
			
			pstmt.setInt(1, customerId);
			pstmt.setInt(2, billId);
			pstmt.setDate(3, java.sql.Date.valueOf(java.time.LocalDate.now()));
			
			rows= pstmt.executeUpdate();
			if(rows<=0)
				return null;
			
			// setting values of bill object later it will be return back to caller
			
			bill.setCustomerId(customerId);
			bill.setBillId(billId);
			bill.setBillDate(java.sql.Date.valueOf(java.time.LocalDate.now()));

			// entering details in bill history table one by one
			for (BillHistory billHistory : billRecords) {
			pstmt = connection.prepareStatement("insert into bill_history  values(?,?,?,?,?)");
			pstmt.setInt(1, billId);
			pstmt.setInt(2, billHistory.getProductId());
			pstmt.setInt(3, billHistory.getQuantity());
			pstmt.setInt(4, billHistory.getDiscount());
			pstmt.setInt(5, (int)billHistory.getProductPrice());
	
			rows = pstmt.executeUpdate();
			}
			
			//  calculating new current quantity of product.
			int newProductQuantity = productQuantityOriginal - productQuantity;
			pstmt = connection.prepareStatement("UPDATE PRODUCT_INFO SET PRODUCT_QUANTITY=? where PRODUCT_ID=?");
			pstmt.setInt(1, newProductQuantity);
			pstmt.setInt(2, productId);
			rows =pstmt.executeUpdate();
			
			// clearing cart for particular customer
			pstmt= connection.prepareStatement("delete from cart where customer_id=?");
			pstmt.setInt(1, customerId);
			pstmt.executeUpdate();
			connection.close();
			// checking for successfully update of product quantity.   
			if (rows > 0) {
			return bill;
				}
			return null;
	}
	//method to display the bill deatils for particular transaction
	@Override
	public List<BillDetails> getBillDetails(int customerId) throws ClassNotFoundException, SQLException {

		List<BillDetails> billList = new LinkedList<BillDetails>();
		connection = CreateConnection.getCon();
		pstmt = connection.prepareStatement("select bill_id, bill_date from bill where customer_id=?");
		pstmt.setInt(1, customerId);
	
		rs = pstmt.executeQuery();
		BillDetails billdetail = null;
		double totalPrice = 0.0;
		pstmt = connection.prepareStatement("select product_id,product_quantity,product_discount,product_price from bill_history where bill_id=?");
		while (rs.next()) {
	
			int billId= rs.getInt("bill_id");
			pstmt.setInt(1,billId);
	
			rs2 = pstmt.executeQuery();
	
			while (rs2.next()) {
			
				billdetail = new BillDetails();
				billdetail.setCustomerId(customerId);
				billdetail.setBillId(rs.getInt(1));
				billdetail.setProductId(rs2.getInt("product_id"));
				billdetail.setPrice(rs2.getDouble("product_price"));
				billdetail.setDiscount(rs2.getInt("product_discount"));
				billdetail.setQuantity(rs2.getInt("product_quantity"));
				billdetail.setDate(rs.getDate("bill_date"));
				totalPrice = (rs2.getDouble("product_price") * rs2.getInt("product_quantity"))
						* (100 - rs2.getInt("product_discount") / 100);
				billdetail.setTotalPrice(totalPrice);
				billList.add(billdetail);
			}
		}
		connection.close();
		return billList;
	}

	@Override
	public List<BillDetails> getAllBillDetails() throws SQLException, ClassNotFoundException {
		List<BillDetails> billList = new LinkedList<BillDetails>();
		connection = CreateConnection.getCon();
		Statement statement = connection.createStatement();
		rs = statement.executeQuery("select customer_id, bill_id, bill_date from bill order by customer_id,bill_date ");
		BillDetails billdetail = null;
		double totalPrice = 0.0;
		pstmt = connection.prepareStatement(
				"select product_id,product_quantity,product_discount,product_price from bill_history where bill_id=?");
		while (rs.next()) {
			pstmt.setInt(1, rs.getInt("bill_id"));
			rs2 = pstmt.executeQuery();
			while (rs2.next()) {
				billdetail = new BillDetails();
				billdetail.setCustomerId(rs.getInt("customer_id"));
				billdetail.setBillId(rs.getInt(1));
				billdetail.setProductId(rs2.getInt("product_id"));
				billdetail.setPrice(rs2.getDouble("product_price"));
				billdetail.setDiscount(rs2.getInt("product_discount"));
				billdetail.setQuantity(rs2.getInt("product_quantity"));
				billdetail.setDate(rs.getDate("bill_date"));
				totalPrice = ((rs2.getInt("product_price")) * (rs2.getInt("product_quantity")))
						* (100 - rs2.getInt("product_discount") / 100);
				billdetail.setTotalPrice(totalPrice);
				billList.add(billdetail);
			}
		}
		connection.close();
		return billList;
	}

	@Override
	public List<BillDetails> getCurrentBill(Bill bill) throws ClassNotFoundException, SQLException {

		List<BillDetails> billList = new LinkedList<BillDetails>();
		double totalPrice = 0.0;
		
		connection = CreateConnection.getCon();
		pstmt = connection.prepareStatement("select product_id,product_quantity,product_discount,product_price from bill_history where bill_id=?");
		int billId= bill.getBillId();
		pstmt.setInt(1,billId);
		
		rs2 = pstmt.executeQuery();
		while (rs2.next()) {
			BillDetails billdetail = new BillDetails();
			billdetail.setBillId(billId);
			billdetail.setProductId(rs2.getInt("product_id"));
			billdetail.setPrice(rs2.getInt("product_price"));
			billdetail.setDiscount(rs2.getInt("product_discount"));
			billdetail.setQuantity(rs2.getInt("product_quantity"));
			billdetail.setDate(bill.getBillDate());
			totalPrice = ((rs2.getInt("product_price")) * (rs2.getInt("product_quantity")))
					* (100 - rs2.getInt("product_discount") / 100);
			billdetail.setTotalPrice(totalPrice);

			billList.add(billdetail);

		}
		return billList;
	}

}
