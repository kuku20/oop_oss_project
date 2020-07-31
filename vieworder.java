package oop_OSS;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JOptionPane;

public interface vieworder {
	void vieworder(String Cid) throws IOException;
}
class view implements vieworder {

	// viewOrder(String CCN, double amount) {
	// super(CCN, amount);
	//
	// }

	public void vieworder(String Cid) throws IOException {
		int choice = JOptionPane.showOptionDialog(null, "Do you want to view your order?", "Quit?",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

		// interpret the user's choice
		if (choice == JOptionPane.YES_OPTION) {
			JOptionPane.showMessageDialog(null, "Customer orders details: see in the Console!!!");
			File Read = new File("order.txt");
			if (!Read.exists()) {
				System.out.println("The file order.txt does not exist.");
				System.exit(0);
			}
			Scanner reading = new Scanner(Read);
			// read the contents of the first file,
			String itemId = reading.nextLine();
			String itemName = reading.nextLine();
			String itemQua = reading.nextLine();
			String itemTotalA = reading.nextLine();
			String IdC = reading.nextLine();
			String auCode = reading.nextLine();
			String status = reading.nextLine();
			String CCN = reading.nextLine();
			reading.close();
			String[] yourItemNameArray = itemName.substring(1, (itemName.lastIndexOf("]"))).toString().split(",");
			String[] yourItemQuaArray = itemQua.substring(1, (itemQua.lastIndexOf("]"))).toString().split(",");
			System.out.println("==========YOUR ORDER DETAIL========");
			for (int i = 0; i < yourItemNameArray.length; i++) {
				System.out.println("You have order: " + yourItemQuaArray[i] + " " + yourItemNameArray[i] + "(s)");
			}
			System.out.println("You have to pay the toal amount: $" + itemTotalA);
			System.out.println("Using the creadit card number: "+CCN);
			System.out.println("Your order status: " + status + "\n and the  authorization number is: " + auCode);
			System.out.println("===================================");
			JOptionPane.showMessageDialog(null, " You has viewed order !!!");

		}

	}

	}
class supplierView implements vieworder{

	@Override
	public void vieworder(String Cid) throws IOException {
		int choice = JOptionPane.showOptionDialog(null, "You want to see the delivery orders!!", "Quit?",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

		// interpret the user's choice
		if (choice == JOptionPane.YES_OPTION) {
			JOptionPane.showMessageDialog(null, "Supplier see orders details: see in the Console!!!");
			File Read = new File("order.txt");
			if (!Read.exists()) {
				System.out.println("The file order.txt does not exist.");
				System.exit(0);
			}
			Scanner reading = new Scanner(Read);
			// read the contents of the first file,
			String itemId = reading.nextLine();
			String itemName = reading.nextLine();
			String itemQua = reading.nextLine();
			String itemTotalA = reading.nextLine();
			String IdC = reading.nextLine();
			String auCode = reading.nextLine();
			String status = reading.nextLine();
			String CCN = reading.nextLine();
			reading.close();
			String[] yourItemNameArray = itemName.substring(1, (itemName.lastIndexOf("]"))).toString().split(",");
			String[] yourItemQuaArray = itemQua.substring(1, (itemQua.lastIndexOf("]"))).toString().split(",");
			System.out.println("The customer with the ID: "+ IdC);
			for (int i = 0; i < yourItemNameArray.length; i++) {
				System.out.println("has ordered: " + yourItemQuaArray[i] + " " + yourItemNameArray[i] + "(s)");
			}
			System.out.println("The customer order status: " + status + "\n and the authorization number is: " + auCode);
			System.out.println("=========================");
//			JOptionPane.showMessageDialog(null, " You has viewed order !!!");

		}
		
	}}