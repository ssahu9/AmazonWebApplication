package com.project.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import com.project.bean.BillDetails;
import com.project.bean.Cart;
import com.project.bean.CartDetails;
import com.project.bean.Category;
import com.project.bean.Customer;
import com.project.bean.Product;
import com.project.bl.CustomerBl;
import com.project.helper.AddToCart;
import com.project.helper.UpdateCustomerEntry;

public class CustomerFirstUI {

	public CustomerFirstUI(int customerId) {

		this.customerId = customerId;

	}

	private boolean status = false;
	private Scanner sc = null;
	private CustomerBl customerbl = new CustomerBl();
	private int customerId;

	public void displayMenu() {
		System.out.println("1. View Category");
		System.out.println("2. Update Profile");
		System.out.println("3. Search By Category");
		System.out.println("4. Search By Product");
		System.out.println("5. View Cart");
		System.out.println("6. Remove From Cart");
		System.out.println("7. View Bill History");
		System.out.println("8. Exit");

		System.out.println("Press 1/2/3/4/5/6/7");
	}

	public void choice(int choice) {

		sc = new Scanner(System.in);

		switch (choice) {
		case 1: {
			// view list of categories and then proceed to select the product
			// and add to cart then proceed to pay.
			try {
				List<Category> categorylist = customerbl.viewCategory();
				// displaying categories
				for (Category category : categorylist) {
					System.out.println(category.getCategoryName());
				}
				// providing option to select the category
				CustomerSecondUI customersecond = new CustomerSecondUI(customerId);
				customersecond.displayMenu();
				int choice2 = sc.nextInt();
				customersecond.choice(choice2);
			} catch (SQLException | ClassNotFoundException e) {
				System.out.println("Sorry, somthing went wrong!!!  " + e);
			} catch (InputMismatchException e) {
				System.out.println("please enter correct option");
			}
			break;
		}

		case 2:// update customer
			Customer customer = new UpdateCustomerEntry().update();
			try {
				customer.setCustomerId(customerId);
				status = customerbl.updateDetails(customer);
				if (status)
					System.out.println("Details Updated ");
				else
					System.out.println("  Not Updated, Please try again!!!1");
			} catch (ClassNotFoundException | SQLException e) {
				System.out.println("Sorry, somthing went wrong!!!");
			}
			break;

		// search category
		case 3:
			System.out.println("Enter the category name you want to search: \n");
			String category = sc.next().toUpperCase();
			List<Product> productlist;
			try {
				productlist = customerbl.viewProduct(category);
				if (productlist.isEmpty()) {
					System.out.println("NO Product is available in this category!!!!");
					break;
				}
				for (Product prodlist : productlist) {
					System.out.println(prodlist);
				}
				// Choose Category
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
			} catch (SQLException | ClassNotFoundException e) {
				System.out.println("Sorry, somthing went wrong!!!");
			}
			break;
		case 4:// search product
			System.out.println("Enter the product name you want to search: \n");
			String productname = sc.next().toUpperCase();
			// Product product=new Product();
			try {
				Product product = customerbl.searchProductByName(productname);
				if (product == null) {
					System.out.println("NO Product found!!!!");
					break;

				}
				System.out.println(product);
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

			} catch (SQLException | ClassNotFoundException e) {
				System.out.println("Sorry, somthing went wrong!!!" + e);
			}
			break;

		case 5:// cart list view
			try {
				List<CartDetails> cartlist;

				cartlist = customerbl.viewCart(customerId);
				if (cartlist.isEmpty()) {
					System.out.println("CART IS EMPTY");
					break;
				}
				for (CartDetails clist : cartlist) {
					System.out.println(clist);
				}
				ProceedToPay paymentUI = new ProceedToPay();
				paymentUI.display();

				paymentUI.payOption(customerId);// payment

			} catch (SQLException | ClassNotFoundException e) {
				System.out.println("Sorry, somthing went wrong!!!" + e);
			}
			break;

		case 6:
			try {
				List<CartDetails> cartlist;

				cartlist = customerbl.viewCart(customerId);
				if (cartlist.isEmpty()) {
					System.out.println("CART IS EMPTY");
					break;
				}
				for (CartDetails clist : cartlist) {
					System.out.println(clist);
				}
				System.out.println("Enter the product id you want to remove : ");
				int productId = sc.nextInt();
				customerbl.removeFromCart(productId, customerId);
				ProceedToPay paymentUI = new ProceedToPay();

			} catch (SQLException | ClassNotFoundException e) {
				System.out.println("Sorry, somthing went wrong!!!" + e);
			} catch (InputMismatchException e) {
				System.out.println("please enter correct option");
			}
			break;

		case 7:// bill details list
			try {
				List<BillDetails> billList;
				billList = customerbl.getBillDetails(customerId);
				if (billList.isEmpty()) {
					System.out.println("CART IS EMPTY");
					break;
				}
				for (BillDetails blist : billList) {
					System.out.println(blist);
				}
			} catch (SQLException | ClassNotFoundException e) {
				System.out.println("Sorry, somthing went wrong!!!");
			}
			break;

		case 8:
			MainMenu menu = new MainMenu();
			menu.displayMenu();
			System.out.println("enter choice : ");
			int ch = sc.nextInt();
			menu.choice(ch);
		default:
			System.out.println("Invalid choice");
			break;
		}
		displayMenu();
		System.out.println("Enter your choice : ");
		choice(sc.nextInt());
	}
}
