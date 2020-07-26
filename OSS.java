package oop_OSS;

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class OSS {
	public static void main(String[] args) throws IOException {
		// this create the array list that store the id of customer have create account
		ArrayList<String> IdCustomer = new ArrayList<String>();
		ArrayList<String> passCustomer = new ArrayList<String>();
		
		System.out.println("Wellcome to Online Shopping");
		System.out.println("Are you customer or are you suplier?");
		System.out.println("  1. customer");
		System.out.println("  2. suplier");
		// this to create the file to store the customer information
		File file = new File(".");
		String store = file.getCanonicalPath() + File.separator + "Customer.txt";
		FileWriter fstream = new FileWriter(store, true);

		
		CheckId  idcheck =new accountCheck();
		File Read = new File("Customer.txt");
		if (!Read.exists()) {
			System.out.println("The file does not exist.");
			System.exit(0);
		}
		Scanner input = new Scanner(Read);
		while (input.hasNext()) {
			String id = input.nextLine();
			String pw = input.nextLine();
			String n = input.nextLine();
			String ad = input.nextLine();
			String ph = input.nextLine();
			String cc = input.nextLine();
			IdCustomer.add(id);
			passCustomer.add(pw);
		}
		// this is ask the customer login or create a new account
		char choice = (char) System.in.read();
		switch (choice) {
		case '1':
			System.out.println("Do you have an account ? \n 1. log in \n 2. create new account");
			Scanner sc = new Scanner(System.in);
			char choiceX = sc.next().charAt(0);
			switch (choiceX) {
			case '1':
				LogIn customerLogin = new LogIn(null, null);
				customerLogin.setId("12345");
				customerLogin.setPassword("oopsumer2020");
				
				boolean idexist = IdCustomer.contains(customerLogin.getId());
				if (idexist) {
					for (int i = 0; i < IdCustomer.size(); i ++) {
						if (customerLogin.getId().equals(IdCustomer.get(i))
								&& customerLogin.getPassword().equals(passCustomer.get(i))) {
							System.out.println("You log in");
						}if(customerLogin.getId().equals(IdCustomer.get(i))
								&& !customerLogin.getPassword().equals(passCustomer.get(i))) {
							System.out.println("Wrong pass");
						}
					}
				} else {
					System.out.println(" There is no account");
				}

				break;
			case '2':
				CreateAccount newcustomer = new CreateAccount("12345", "oopsumer20201", "loc", "Bushy Creek Houston TX",
						"8325322153", "***********9865");
				// create file and store the information of customer
				BufferedWriter output = new BufferedWriter(fstream);
				boolean exist = IdCustomer.contains(newcustomer.getId());
				if (exist) {
					System.out.println("it has existed");
				} else {
					output.write(newcustomer.toStringCustomer());
					output.newLine();
					System.out.println("Your account have been created.");
					output.close();
					LogIn();
				}
				break;
			}
			break;
		case '2':
			CreateAccount Supplier = new CreateAccount();
			Supplier.setId("2 ");
			Supplier.setPassword("afdsfa ");
			break;
		}
	}
	private static void LogIn() {
		
		
	}
}
