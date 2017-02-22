package com.project.helper;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.project.bean.Customer;

public class UpdateCustomerEntry {

	private Customer customer = new Customer();

	public Customer update() {
		Scanner sc = new Scanner(System.in);
		try {
			System.out.println("Enter new First Name : ");
			customer.setFirstName(sc.next().toUpperCase());
			System.out.println("Enter new  Last Name : ");
			customer.setLastName(sc.next().toUpperCase());
			System.out.println(" Enter Current Email Id : ");
			customer.setEmail(sc.next().toUpperCase());
			System.out.println(" Enter new Password : ");
			customer.setPassword(sc.next());
			System.out.println(" Enter new Phone Number : ");
			customer.setPhoneNumber(sc.next());
		} catch (InputMismatchException e) {
			System.out.println("Please, Enter Correct Data!!!!!!");
		}
		return customer;
	}
}
