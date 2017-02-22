package com.project.ui;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.project.bean.Admin;
import com.project.bean.BillDetails;
import com.project.bean.Category;
import com.project.bean.Product;
import com.project.bl.AdminBL;
import com.project.helper.ProductEntry;
import com.project.helper.UpdateProductEntry;

public class AdminFirstUI {

	private Scanner sc = null;
	private AdminBL adminBl = new AdminBL();

	public void displayMenu() {
		System.out.println("1. View Product Category");
		System.out.println("2. Add New Category");
		System.out.println("3. Delete Category");
		System.out.println("4. View Products");
		System.out.println("5. Add New Product");
		System.out.println("6. Delete Product ");
		System.out.println("7. Update Products");
		System.out.println("8. Display All Bill Details");
		System.out.println("9. Update Password");
		System.out.println("10. Exit");
		System.out.println("Press 1/2/3/4/5/6/7/8/9/10");
	}

	public void choice(int choice) {
		boolean status = false;
		sc = new Scanner(System.in);
		String name;
		String pcategory;

		switch (choice) {

		case 1:
			List<Category> categorylist;
			try {
				categorylist = adminBl.viewCategory();
				for (Category category : categorylist) {
					System.out.println(category.getCategoryName());
				}
				// Choose Category
			} catch (SQLException | ClassNotFoundException e) {
				System.out.println("Sorry, somthing went wrong!!!" + e);
				displayMenu();
				System.out.println("Enter your choice : ");
				choice(sc.nextInt());
			} catch (InputMismatchException e) {
				System.out.println("Please Enter Correct data!!!");
			}

			break;
		case 2:// add new category
			System.out.println("Enter New Category Name ");
			try {
				name = sc.next().toUpperCase();
				status = adminBl.addCategory(name);
				if (status)
					System.out.println(name + " Category Added");
				else
					System.out.println(name + "  Not Added, Please try again!!!1");
			} catch (ClassNotFoundException | SQLException e) {
				System.out.println("Sorry, somthing went wrong!!!" + e);
				displayMenu();
				System.out.println("Enter your choice : ");
				choice(sc.nextInt());
			} catch (InputMismatchException e) {
				System.out.println("Please Enter Correct data!!!");
			}

			displayMenu();
			System.out.println("Enter your choice : ");
			choice(sc.nextInt());
			break;
		case 3:// delete category
			System.out.println("Enter Category Name which you want to Delete ");
			try {
				name = sc.next().toUpperCase();
				status = adminBl.deleteCategory(name);
				if (status)
					System.out.println(name + " Category Deleted");
				else
					System.out.println(name + "  Not Deleted, Please try again!!!1");
			} catch (ClassNotFoundException | SQLException e) {
				System.out.println("Sorry, somthing went wrong!!!" + e);
				displayMenu();
				System.out.println("Enter your choice : ");
				choice(sc.nextInt());
			} catch (InputMismatchException e) {
				System.out.println("Please Enter Correct data!!!");
			}

			break;

		case 4:// view category list
			List<Product> prodlist;
			pcategory = null;
			try {
				prodlist = adminBl.viewProduct(pcategory);
				for (Product product1 : prodlist) {
					System.out.println(product1);
				}
				// Choose Category
			} catch (SQLException | ClassNotFoundException e) {
				System.out.println("Sorry, somthing went wrong!!!" + e);
				displayMenu();
				System.out.println("Enter your choice : ");
				choice(sc.nextInt());
			} catch (InputMismatchException e) {
				System.out.println("Please Enter Correct data!!!");
			}

			break;

		case 5:// add product

			try {
				ProductEntry productentry = new ProductEntry();

				status = adminBl.addProduct(productentry.input());

				if (status)
					System.out.println(" Product Added");
				else
					System.out.println("  Not Added, Please try again!!!1");
			} catch (ClassNotFoundException | SQLException e) {
				System.out.println("Sorry, somthing went wrong!!!" + e);
				displayMenu();
				System.out.println("Enter your choice : ");
				choice(sc.nextInt());
			} catch (InputMismatchException e) {
				System.out.println("Please Enter Correct data!!!");
			}

			displayMenu();
			System.out.println("Enter your choice : ");
			choice(sc.nextInt());
			break;
		case 6:// delete product
			System.out.println("Enter product id to delete product:");
			int pid = sc.nextInt();
			try {
				status = adminBl.deleteProduct(pid);
				if (status)
					System.out.println(" Product Deleted");
				else
					System.out.println("  Not Deleted, Please try again!!!1");
			} catch (SQLException | ClassNotFoundException e) {
				System.out.println("Sorry, somthing went wrong!!!" + e);
				displayMenu();
				System.out.println("Enter your choice : ");
				choice(sc.nextInt());
			} catch (InputMismatchException e) {
				System.out.println("Please Enter Correct data!!!");
			}

			break;
		case 7:// product update
			UpdateProductEntry updateproductentry = new UpdateProductEntry();

			try {
				status = adminBl.updateProduct(updateproductentry.updateProduct());
				if (status)
					System.out.println(" Product updated");
				else
					System.out.println("  Not updated, Please try again!!!1");
			} catch (SQLException | ClassNotFoundException e) {
				System.out.println("Sorry, somthing went wrong!!!" + e);
				displayMenu();
				System.out.println("Enter your choice : ");
				choice(sc.nextInt());
			} catch (InputMismatchException e) {
				System.out.println("Please Enter Correct data!!!");
			}

			break;
		case 8:
			// view bill list
			List<BillDetails> billList;
			pcategory = null;
			try {
				billList = adminBl.getAllBillDetails();
				for (BillDetails list : billList) {
					System.out.println(list);
				}
				// Choose Category
			} catch (SQLException | ClassNotFoundException e) {
				System.out.println("Sorry, somthing went wrong!!!" + e);
				displayMenu();
				System.out.println("Enter your choice : ");
				choice(sc.nextInt());
			} catch (InputMismatchException e) {
				System.out.println("Please Enter Correct data!!!");
			}

			break;
		case 9:// password update
			Admin admin = new Admin();
			System.out.println("Enter Current email : ");
			admin.setMail(sc.next().toUpperCase());
			System.out.println("enter new password");
			admin.setPassword(sc.next());
			try {
				status = adminBl.updatePassword(admin);
				if (status)
					System.out.println(" Password updated");
				else
					System.out.println("  Not updated, Please try again!!!1");
			} catch (SQLException | ClassNotFoundException e) {
				System.out.println("Sorry, somthing went wrong!!!" + e);
				displayMenu();
				System.out.println("Enter your choice : ");
				choice(sc.nextInt());
			} catch (InputMismatchException e) {
				System.out.println("Please Enter Correct data!!!");
			}

			break;
		case 10:
			try {
				MainMenu menu = new MainMenu();
				menu.displayMenu();
				System.out.println("enter choice : ");
				int ch = sc.nextInt();
				menu.choice(ch);
				break;
			} catch (InputMismatchException e) {
				System.out.println("Please Enter Correct data!!!");
			}

		default:
			System.out.println("Invalid choice");
			break;
		}

		displayMenu();
		System.out.println("Enter your choice : ");
		choice(sc.nextInt());

	}

}
