package com.project.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.bean.Customer;
import com.project.bl.CustomerBl;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CustomerBl customerBL = new CustomerBl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		Customer customer = new Customer();
		try {
			customer = customerBL.signIn(email, password);
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
		
		try {
			if(customerBL.signIn(email, password) != null) { // login failed
				out.print("Sorry, you entered the wrong credentials");
				request.getRequestDispatcher("index.jsp").include(request, response); // redirecting to index.jsp
			}
			else { // login successful
				out.print("Welcome, "+customer.getFirstName()+" "+customer.getLastName()+"!");
				request.getRequestDispatcher("index.jsp").forward(request, response); // redirecting to index.jsp
				HttpSession session = request.getSession(); // creating session
				session.setAttribute("email", email); // setting session attribute
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
