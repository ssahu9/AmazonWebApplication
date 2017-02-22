package com.project.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.project.bean.Cart;
import com.project.bean.CartDetails;
import com.project.bean.Category;
import com.project.bean.Customer;
import com.project.bean.Product;
import com.project.bl.CustomerBl;
import com.project.dao.CartDaoImpl;
import com.project.helper.AddToCart;

public class CustomerSecondUI {

	private int customerId;

	public CustomerSecondUI(int customerId) {
		this.customerId = customerId;
	}

	CustomerBl customerbl = new CustomerBl();

	public void displayMenu() {
		System.out.println("1. Choose Category ");
		System.out.println("2. Back");
		System.out.println("3. Exit");
		System.out.println("Press 1/2/3");
	}

	public void choice(int ch) throws SQLException, ClassNotFoundException, InputMismatchException {

		Scanner sc = new Scanner(System.in);

		boolean status = false;
		switch (ch) {

		case 1:
			// category name input list output
			System.out.println("Enter category name to view products : ");
			String category = sc.next().toUpperCase();
			List<Product> productlist = customerbl.viewProduct(category);
			if (productlist.isEmpty()) {
				System.out.println("NO Product is available in this category!!!!");
				break;
			}
			for (Product product : productlist) {
				System.out.println(product);
			}
			AddToCart add = new AddToCart();// add to cart
			ArrayList<Cart> cartList = (ArrayList<Cart>) add.addingToCart(customerId);
			if (cartList.isEmpty())
				break;
			status = customerbl.addToCart(cartList);
			if (status) {
				List<CartDetails> list = new ArrayList<CartDetails>();
				list = customerbl.viewCart(customerId);// view cart
				if (!list.isEmpty()) {
					System.out.println("....YOUR CART DETAILS....\n");
					for (CartDetails cartDetails : list) {
						System.out.println(cartDetails);
					}
				} else {
					System.out.println("....YOUR CART IS EMPTY....\n");
				}
				// call payment ui
				ProceedToPay paymentUI = new ProceedToPay();
				paymentUI.display();

				paymentUI.payOption(customerId);// payment
			} else
				System.out.println("Sorrry, somthing went wrong!!!!");
			break;
		case 2:
			break;
		case 3:
			MainMenu menu = new MainMenu();
			menu.displayMenu();
			System.out.println("enter choice : ");
			ch = sc.nextInt();
			menu.choice(ch);
			break;
		default:
			System.out.println("INVALID OPTION");
			displayMenu();
			System.out.println("Enter your choice : ");
			choice(sc.nextInt());
		}

	}
}
