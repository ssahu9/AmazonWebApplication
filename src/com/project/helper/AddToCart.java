package com.project.helper;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.project.bean.Cart;

// method to add the products in list then return the list back for further process.
public class AddToCart {

	public List<Cart> addingToCart(int customerId) throws InputMismatchException {
		Scanner sc = new Scanner(System.in);
		List<Cart> cartList = new ArrayList<Cart>();
		do {
			System.out.println(" Enter Product ID to Add to Cart \n Or 0 : if done");
			int optionChosen = sc.nextInt();
			if (optionChosen == 0)
				break;
			Cart cart = new Cart();
			cart.setCustomerId(customerId);
			cart.setProductId(optionChosen);
			System.out.println("Enter Quantity");
			int quantity = sc.nextInt();
			cart.setQuantity(quantity);
			long millis = System.currentTimeMillis();
			java.sql.Date date = new java.sql.Date(millis);
			cart.setCartDate(date);
			if (quantity > 0) {
				cartList.add(cart);
				System.out.println("Added To Cart.");
			} else {
				DisplayErrorMessage.displayError("Quantity should be more than 0 !!!");
			}
		} while (true);
		System.out.println("ccc" + cartList);
		return cartList;
	}
}
