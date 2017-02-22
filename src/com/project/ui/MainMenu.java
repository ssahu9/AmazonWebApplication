package com.project.ui;

import java.sql.SQLException;
import java.util.Scanner;
import com.project.bean.Customer;
import com.project.bl.AdminBL;
import com.project.bl.CustomerBl;
import com.project.helper.CustomerEntry;

public class MainMenu {
	// private MainMenu mainMenu = new MainMenu();
	Scanner sc = new Scanner(System.in);
	int ch;
	private CustomerBl customerBl = new CustomerBl();
	private AdminBL adminBl = new AdminBL();

	public void displayMenu() {
		System.out.println("WELCOME TO AMAZON");
		System.out.println("1: Sign In As Admin. \n2: Sign In As Customer. \n3: SignUp As Customer \n4: Exit");
	}

	public void choice(int ch) {

		int customerId = 0;
		String email;
		String password;
		boolean status;
		switch (ch) {

		case 1:// admin sign in
			System.out.println("Enter your email id/username : ");
			email = sc.next().toUpperCase();
			System.out.println("Enter your password : ");
			password = sc.next();
			try {
				status = adminBl.signIn(email, password);
				if (status) {
					System.out.println("signed In");
					AdminFirstUI adminFUI = new AdminFirstUI();
					adminFUI.displayMenu();
					System.out.println("Enter your Choice :  ");
					adminFUI.choice(sc.nextInt());
				} else {
					System.out.println("Sorry, Please try again!!!!");

					displayMenu();
					System.out.println("Enter your choice : ");
					choice(sc.nextInt());

				}
			} catch (ClassNotFoundException | SQLException e1) {
				System.out.println("Sorry, Please try again!!!!");
				displayMenu();
				System.out.println("Enter your choice : ");
				ch = sc.nextInt();
				choice(ch);
			}
			break;
		case 2:// customer sign in

			System.out.println("Enter your email id/username : ");
			email = sc.next().toUpperCase();
			System.out.println("Enter your password : ");
			password = sc.next();
			try {
				customerId = customerBl.signIn(email, password);
				if (customerId != 0) {
					System.out.println("signed In");
					CustomerFirstUI customerFUI = new CustomerFirstUI(customerId);
					customerFUI.displayMenu();
					System.out.println("Enter your choice : ");
					customerFUI.choice(sc.nextInt());

				} else {
					System.out.println("Sorry, Please try again!!!!");
					System.out.println("INVALID OPTION");
					displayMenu();
					System.out.println("Enter your choice : ");
					choice(sc.nextInt());

				}
			} catch (ClassNotFoundException | SQLException e1) {
				System.out.println("Sorry, Please try again!!!!");
				displayMenu();
				System.out.println("Enter your choice : ");
				ch = sc.nextInt();
				choice(ch);
			}
			break;
		case 3:// customer sign up
			Customer customer = new CustomerEntry().input();
			try {
				customerId = customerBl.signUp(customer);
				if (customerId != 0) {
					System.out.println("signed Up!!! ");
					CustomerFirstUI customerFUI = new CustomerFirstUI(customerId);
					customerFUI.displayMenu();
					System.out.println("Enter your choice : ");
					customerFUI.choice(sc.nextInt());
				} else {
					System.out.println("Sorry, Please try again!!!!");
					System.out.println("INVALID OPTION");
					displayMenu();
					System.out.println("Enter your choice : ");
					choice(sc.nextInt());
				}

			} catch (ClassNotFoundException | SQLException e) {
				System.out.println("Sorry, Please try again!!!!");
				displayMenu();
				System.out.println("Enter your choice : ");
				choice(sc.nextInt());
			}
			break;

		case 4:
			System.exit(0);
			break;
		default:
			System.out.println("INVALID OPTION");
			displayMenu();
			System.out.println("Enter your choice : ");
			choice(sc.nextInt());
		}
	}
}
