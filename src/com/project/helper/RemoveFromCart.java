package com.project.helper;

import java.util.Scanner;

import com.project.bean.Cart;

public class RemoveFromCart {

	public int removeFromCart() {
		int productId = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the ID of the product you want to delete from your cart : ");
		productId = sc.nextInt();

		return productId;

	}

}
