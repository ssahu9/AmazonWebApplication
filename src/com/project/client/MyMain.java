package com.project.client;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.project.ui.MainMenu;

public class MyMain {

	public static void main(String[] args) {
		MainMenu menu = new MainMenu();
		Scanner sc = new Scanner(System.in);
		int d = 0;
		int ch = 1;
		do {
			try {
				// calling welcome menu
				// try block to check the input mismatch
				menu.displayMenu();
				System.out.println("enter choice : ");
				ch = sc.nextInt();
				menu.choice(ch);
				System.out.println(" ");
				System.out.println("Press 1 to continue");

				d = sc.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Please Enter Correct data!!!");
				MyMain.main(null);
			}
		} while (d == 1);
		System.out.println("Exited");
	}

}
