package oop_OSS;

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class OSS {
	// this create the array list that store the id of customer have create account
	public static ArrayList<String> IdCustomer = new ArrayList<String>();
	public static ArrayList<String> IdSuplier = new ArrayList<String>();
	public static ArrayList<String> passCustomer = new ArrayList<String>();
	public static ArrayList<String> passSuplier = new ArrayList<String>();
	public static ArrayList<String> creditCardCustomer = new ArrayList<String>();

	public static void main(String[] args) throws IOException {
		System.out.println("Wellcome to Online Shopping");
		System.out.println("Are you customer or are you suplier?");
		System.out.println("  1. customer");
		System.out.println("  2. suplier");
		// this to create the file to store the customer information
		file();
		// this is ask the customer login or create a new account
		char choice = (char) System.in.read();
		switch (choice) {
		case '1':
			System.out.println("Do you have an account ? \n 1. log in \n 2. create new account");
			Scanner ca = new Scanner(System.in);
			char cChoice = ca.next().charAt(0);
			switch (cChoice) {
			case '1':
				// login
				customerLogin();
				break;
			case '2':
				// create new account
				customerAccount();
				break;
			}
			break;
		case '2':
			System.out.println("Do you have an account ? \n 1.Suplier log in \n 2.Suplier create new account");
			Scanner sc = new Scanner(System.in);
			char sChoice = sc.next().charAt(0);
			switch (sChoice) {
			case '1':
				suplierLogin();
				
				break;
			case '2':
				suplierAccount();
				break;
			}
			break;
		}
	}

	private static void file() throws IOException {
		// CheckId idcheck = new accountCheck();
				File cus = new File(".");
				FileWriter customer = new FileWriter(cus.getCanonicalPath() + File.separator + "Customer.txt", true);

				File sup = new File(".");
				FileWriter suplier = new FileWriter(sup.getCanonicalPath() + File.separator + "Suplier.txt", true);
				File readCustomer = new File("Customer.txt");
				// if (!Read.exists()) {
				// System.out.println("The file does not exist.");
				// System.exit(0);
				// }
				Scanner getCustomer = new Scanner(readCustomer);
				while (getCustomer.hasNext()) {
					String id = getCustomer.nextLine();
					String pw = getCustomer.nextLine();
					String n = getCustomer.nextLine();
					String ad = getCustomer.nextLine();
					String ph = getCustomer.nextLine();
					String cc = getCustomer.nextLine();
					IdCustomer.add(id);
					passCustomer.add(pw);
					creditCardCustomer.add(cc);
				}

				File readSuplier = new File("Suplier.txt");
				Scanner getSuplier = new Scanner(readSuplier);
				while (getSuplier.hasNext()) {
					String id = getSuplier.nextLine();
					String pw = getSuplier.nextLine();
					IdSuplier.add(id);
					passSuplier.add(pw);
				}

	}

	private static void suplierAccount() throws IOException {
		Scanner in = new Scanner(System.in);
		// First employee objects with the input
		System.out.println("Wellcome to create new suplier");
		System.out.print("Enter the id: ");
		String supNewId = in.next();
		System.out.print("Enter the pass: ");
		String supNewPass = in.next();
		CreateAccount newSupplier = new CreateAccount(supNewId, supNewPass);
		File cus = new File(".");
		FileWriter customer = new FileWriter(cus.getCanonicalPath() + File.separator + "Suplier.txt", true);
		BufferedWriter output = new BufferedWriter(customer);
		boolean exist = IdSuplier.contains(newSupplier.getId());
		if (exist) {
			System.out.println("it existed");
			 suplierLogin();
		} else {
			output.write(newSupplier.toStringSuppier());
			output.newLine();
			System.out.println("Your account have been created.");
			output.close();
			 suplierLogin();
		}
	}

	private static void suplierLogin() throws IOException {
		Scanner in = new Scanner(System.in);
		System.out.println("Wellcome to create suplier LOGIN");
		System.out.print("Enter the id: ");
		String sumId = in.next();
		System.out.print("Enter the pass: ");
		String sumPass = in.next();
		LogIn suplierLogin = new LogIn(sumId, sumPass);

		boolean idexist = IdSuplier.contains(suplierLogin.getId());

		if (idexist) {
			for (int i = 0; i < IdSuplier.size(); i++) {
				if (suplierLogin.getId().equals(IdSuplier.get(i))
						&& suplierLogin.getPassword().equals(passSuplier.get(i))) {
					suplierLogin.toLogin();
				}
				if (suplierLogin.getId().equals(IdSuplier.get(i))
						&& !suplierLogin.getPassword().equals(passSuplier.get(i))) {
					System.out.println("Wrong pass");
					 suplierLogin();
				}
			}
		} else {
			System.out.println(" There is no account");
			 suplierAccount();
		}
	}

	private static void customerAccount() throws IOException {
		// customer account
		Scanner in = new Scanner(System.in);
		// First employee objects with the input
//		System.out.println("Wellcome to create new customer Account");
//		System.out.print("Enter the customer id: ");
//		String cusId = in.next();
//		System.out.print("Enter the customer pass: ");
//		String cusPass = in.next();
//		System.out.print("Enter the customer name: ");
//		String cusN = in.next();
//		System.out.print("Enter the customer address: ");
//		String cusAd = in.next();
//		System.out.print("Enter the customer phone: ");
//		String cusPh = in.next();
//		System.out.print("Enter the customer Creadit Card: ");
//		String cusCc = in.next();
//		CreateAccount newcustomer = new CreateAccount(cusId, cusPass, cusN, cusAd, cusPh, cusCc);
		CreateAccount newcustomer = new CreateAccount("1","1","Kim","UK","8924233215","************9986");
		// create file and store the information of customer
		File cus = new File(".");
		FileWriter customer = new FileWriter(cus.getCanonicalPath() + File.separator + "Customer.txt", true);
		BufferedWriter output = new BufferedWriter(customer);
		boolean exist = IdCustomer.contains(newcustomer.getId());
		if (exist) {
			System.out.println("it existed");
			// customerLogin();
		} else {
			output.write(newcustomer.toStringCustomer());
			output.newLine();
			System.out.println("Your account have been created.");
			output.close();
			// customerLogin();
		}
	}

	private static void customerLogin() throws IOException {
		Scanner in = new Scanner(System.in);
		// First employee objects with the input
		System.out.println("Wellcome to customer Login");
		System.out.print("Enter the customer id: ");
		String cusLogId = in.next();
		System.out.print("Enter the customer pass: ");
		String cusLogPass = in.next();
		LogIn customerLogin = new LogIn(cusLogId, cusLogPass);
		boolean idexist = IdCustomer.contains(customerLogin.getId());
		if (idexist) {
			for (int i = 0; i < IdCustomer.size(); i++) {
				if (customerLogin.getId().equals(IdCustomer.get(i))
						&& customerLogin.getPassword().equals(passCustomer.get(i))) {

					customerLogin.toLogin();
//					this is where customer can choice to logout or go to the catalog
//			catalog
					catalogList firstC = new customerChoice();
					firstC.catalogList(customerLogin.getId());
					
					choiceInlist Cchoice =  (choiceInlist) firstC;

//					System.out.println(Cchoice.ItemId());
//					System.out.println(Cchoice.Cid());
					firstC.cart(customerLogin.getId());
					
//					this will get the indexof the idcomtomer
					int indexCustomer= IdCustomer.indexOf(customerLogin.getId());
//					this is the creaditcard of that customer: 
					String ccnc= creditCardCustomer.get(indexCustomer);
					System.out.println(creditCardCustomer.get(indexCustomer));
					double amountDue=102.3;
//					connect and check to the bank
					Bank cusCheckBank = new Bank(ccnc, amountDue);
					cusCheckBank.orderAmountCheck(amountDue);
					System.out.println(cusCheckBank.checkApprove());
//					if cc doesn't work update new card
//					store a delivery order in textfile
					PrintWriter orderFile = new PrintWriter("order.txt");
//					store a delivery order in file
					orderFile.println(customerLogin.getId());
					orderFile.println(Cchoice.ItemId());
					orderFile.println(cusCheckBank.checkApprove());
					orderFile.close();
					
				}
				if (customerLogin.getId().equals(IdCustomer.get(i))
						&& !customerLogin.getPassword().equals(passCustomer.get(i))) {
					System.out.println("Wrong pass");
					// customerLogin();
				}
			}
		} else {
			System.out.println(" There is no account");
		}

	}

//	private static void catalogList() throws IOException {
//		
//		System.out.println("There are the list of items: "
//				+ "\n 1. phone 8 price $800"
//				+ "\n 2. laptop price $1200"
//				+ "\n 3. tv price $100"
//				+ "\n 4. hat price $1200"
//				+ "\n 5. guita price $1200");
//		Scanner choiceI = new Scanner(System.in);
//		// First employee objects with the input
//		System.out.print("Enter the Item : ");
//		String cusItemID = choiceI.next();
//		System.out.println("how many??");
//		String cusItemQ = choiceI.next();
//		catalog list = new catalog(cusItemID, "something", cusItemQ);
//		
//		
//		
//	}

}
