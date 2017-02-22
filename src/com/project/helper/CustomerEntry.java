package com.project.helper;

import java.util.InputMismatchException;
import java.util.Scanner;
import com.project.bean.Customer;

public class CustomerEntry {

	private Customer customer = new Customer();

	public Customer input() {
		Scanner sc = new Scanner(System.in);
		try {
			System.out.println("Enter First Name : ");
			customer.setFirstName(sc.next().toUpperCase());
			System.out.println("Enter Last Name : ");
			customer.setLastName(sc.next().toUpperCase());
			System.out.println(" Enter Email Id : ");
			customer.setEmail(sc.next().toUpperCase());
			System.out.println(" Enter Password : ");
			customer.setPassword(sc.next());
			System.out.println(" Enter Phone Number : ");
			customer.setPhoneNumber(sc.next());
		} catch (InputMismatchException e) {
			System.out.println("Please Enter correct Data!!");
		}
		return customer;
	}

}
