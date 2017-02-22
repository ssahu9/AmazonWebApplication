package com.project.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.bean.Product;
import com.project.bl.AdminBL;


/**
 * Servlet implementation class ProductEntry
 */
public class ProductEntry extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

   		PrintWriter pw = response.getWriter();
   		Product product = new  Product();
   		product.setCategory(request.getParameter("category"));
   		product.setDiscount(Integer.parseInt(request.getParameter("discount")));
   		product.setName(request.getParameter("name"));
   		product.setProductId(Integer.parseInt(request.getParameter("id")));
   		product.setQuantity(Integer.parseInt(request.getParameter("quantity")));
   		product.setPrice(Integer.parseInt(request.getParameter("price")));
   		
   		AdminBL admin = new AdminBL();
   		try {
			admin.addProduct(product);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   		response.setContentType("text/html");
		//PrintWriter pw=response.getWriter();
		pw.println("<HTML>");
		pw.println("<HEAD>");
		pw.println("<TITLE>My First Servlet</TITLE>");
		pw.println("</HEAD>");
		pw.println("<BODY>");
		String name=request.getParameter("name");
		pw.println("<H1 style=\"color:red\">Sorry "+name+" Login Failed!!!</H1>");
		pw.println("</BODY>");
		pw.println("</HTML>");
		
   		
   		
//		LoginCheck lc=new LoginCheck();
//		if(lc.check(user)){
//			response.sendRedirect("./Success");
//		}
//		else{
//			RequestDispatcher dispatch=request.getRequestDispatcher("./Failure");
//			dispatch.forward(request, response);
//		}
//	
   	
   	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
