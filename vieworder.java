package oop_OSS;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;

public interface vieworder {
	void vieworder(String Cid) throws IOException;

	String orderStatus();
}

interface supplierRequestInvertory {
	String itemName();

	String itemQuantity();

	void inventoryCheck(String Name, String Quantity);
}

interface accountAndBank {
	String idCgetorder();

	void charging(String CCC);
}

class view implements vieworder {

	public void vieworder(String Cid) throws IOException {
		int choice = JOptionPane.showOptionDialog(null, "Do you want to view your order?", "Quit?",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

		// interpret the user's choice
		if (choice == JOptionPane.YES_OPTION) {
			JOptionPane.showMessageDialog(null, "Customer orders details: see in the Console!!!");
			readingOrderfile();
			System.out.println("==========YOUR ORDER DETAIL========");
			for (int i = 0; i < yourItemNameArray.length; i++) {
				System.out.println("You have order: " + yourItemQuaArray[i] + " " + yourItemNameArray[i] + "(s)");
			}
			System.out.println("You have to pay the toal amount: $" + itemTotalA);
			System.out.println("Your order status: " + status + "\n and the  authorization number is: " + auCode);
			System.out.println("===================================");
			JOptionPane.showMessageDialog(null, " You has viewed order !!!");

		}

	}

	protected static String itemId, itemName, itemQua, itemTotalA, IdC, auCode, CCN, status;
	protected static String[] yourItemNameArray, yourItemQuaArray;

	protected void readingOrderfile() throws IOException {

		File Read = new File("order.txt");
		if (!Read.exists()) {
			System.out.println("The file order.txt does not exist.");
			System.exit(0);
		}
		Scanner reading = new Scanner(Read);
		// read the contents of the first file,
		itemId = reading.nextLine();
		itemName = reading.nextLine();
		itemQua = reading.nextLine();
		itemTotalA = reading.nextLine();
		IdC = reading.nextLine();
		auCode = reading.nextLine();
		status = reading.nextLine();
		reading.close();
		yourItemNameArray = itemName.substring(1, (itemName.lastIndexOf("]"))).toString().split(",");
		yourItemQuaArray = itemQua.substring(1, (itemQua.lastIndexOf("]"))).toString().split(",");

	}

	@Override
	public String orderStatus() {
		// TODO Auto-generated method stub
		return null;
	}

}

class supplierView extends view implements vieworder, supplierRequestInvertory {
	public String itemNameInvertory, itemQuantityInvertory;

	@Override
	public void vieworder(String Cid) throws IOException {
		int choice = JOptionPane.showOptionDialog(null, "You want to see the delivery orders!!",
				"Supplier requests delivery orders.", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				null, null);

		// interpret the user's choice
		if (choice == JOptionPane.YES_OPTION) {
			readingOrderfile();
			itemNameInvertory = itemName.substring(1, (itemName.lastIndexOf("]"))).toString();
			itemQuantityInvertory = itemQua.substring(1, (itemQua.lastIndexOf("]"))).toString();
			System.out.println("======SUPPLIER VIEW=========");
			System.out.println("The customer with the ID: " + IdC);
			for (int i = 0; i < yourItemNameArray.length; i++) {
				System.out.println("has ordered: " + yourItemQuaArray[i] + " " + yourItemNameArray[i] + "(s)");
			}
			System.out.println("The total amount is: " + itemTotalA);
			System.out
					.println("The customer order status: " + status + "\n and the authorization number is: " + auCode);
			System.out.println("=========================");
			// JOptionPane.showMessageDialog(null, " You has viewed order !!!");
			JOptionPane.showMessageDialog(null, "Supplier see orders details: see in the Console!!!");
		}

	}

	@Override
	public String itemName() {
		// TODO Auto-generated method stub
		return itemNameInvertory;
	}

	@Override
	public String itemQuantity() {
		// TODO Auto-generated method stub
		return itemQuantityInvertory;
	}

	@Override
	public void inventoryCheck(String Name, String Quantity) {
		String newStatus = "ready";
		JOptionPane.showMessageDialog(null,
				". Supplier selects a delivery order and requests inventory check on items for the delivery order");
		for (int i = 0; i < yourItemNameArray.length; i++) {
			System.out.println("Inventory check on: " + yourItemNameArray[i] + "s quantity " + yourItemQuaArray[i]);
		}
		String[] inventory = { "Available", "Out of stock" };
		int inventoryChoice = JOptionPane.showOptionDialog(null, "INVENTORY CHECKING ITEM", "Inventory Cheking....",
				JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, inventory, inventory[0]);
		if (inventoryChoice == 0) {
			System.out.println(auCode);
			System.out.println("order status orderred to ready");
			status = newStatus;
			// re store order with the new status
			// store a delivery order to the same file
			PrintWriter supplierChangeToOrdered;
			try {
				supplierChangeToOrdered = new PrintWriter("order.txt");
				// itemId,itemName,itemQua,itemTotalA,IdC,auCode,CCN,status;
				supplierChangeToOrdered.println(itemId);
				supplierChangeToOrdered.println(itemName);
				supplierChangeToOrdered.println(itemQua);
				supplierChangeToOrdered.println(itemTotalA);
				supplierChangeToOrdered.println(IdC);
				supplierChangeToOrdered.println(auCode);
				supplierChangeToOrdered.println(status);
				supplierChangeToOrdered.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// store a delivery order in file
			JOptionPane.showMessageDialog(null, " System has reserved items for delivery order. ");

		} else if (inventoryChoice == 1) {
			System.out.println("an inventory order is required for the item(s).");
			for (int i = 0; i < yourItemNameArray.length; i++) {
				System.out.println("Inventory need to supply more: " + yourItemNameArray[i]);
			}
		}

	}

	@Override
	public String orderStatus() {
		try {
			readingOrderfile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}
}

class shipment extends view implements vieworder, accountAndBank {
	public void vieworder(String Cid) throws IOException {
		readingOrderfile();
		JOptionPane.showMessageDialog(null,
				". Supplier selects a delivery order and requests inventory check on items for the delivery order");
		System.out.println("=========================");
		System.out.println("The customer with the ID: " + IdC);
		for (int i = 0; i < yourItemNameArray.length; i++) {
			System.out.println("has ordered: " + yourItemQuaArray[i] + " " + yourItemNameArray[i] + "(s)");
		}
		System.out.println("The total amount is: " + itemTotalA);
		System.out.println("The customer order status: " + status + "\n and the authorization number is: " + auCode);
		System.out.println("=========================");
		JOptionPane.showMessageDialog(null, "Supplier selects a delivery order.");
	}

	@Override
	public String idCgetorder() {
		// TODO Auto-generated method stub
		return IdC;
	}

	@Override
	public void charging(String CCC) {
		String check = "The bank have charging: $" + itemTotalA + " in the Credit Card number: " + CCC;
		String[] bankCharg = { "Charged", "Denied" };
		int okeOrNot = JOptionPane.showOptionDialog(null, check, "Bank Charging....", JOptionPane.PLAIN_MESSAGE,
				JOptionPane.INFORMATION_MESSAGE, null, bankCharg, bankCharg[0]);
		if (okeOrNot == 0) {
			// generate the authorization number
			System.out.println("Inventory ");
			for (int i = 0; i < yourItemNameArray.length; i++) {
				System.out.println("has updated: " + yourItemQuaArray[i] + " " + yourItemNameArray[i] + "(s)");
			}
			System.out.println("for delivery.");
			status = "shipped";
			PrintWriter shipment;
			try {
				shipment = new PrintWriter("order.txt");
				// itemId,itemName,itemQua,itemTotalA,IdC,auCode,CCN,status;
				shipment.println(itemId);
				shipment.println(itemName);
				shipment.println(itemQua);
				shipment.println(itemTotalA);
				shipment.println(IdC);
				shipment.println(auCode);
				shipment.println(status);
				shipment.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// store a delivery order in file
			JOptionPane.showMessageDialog(null, "delivery order is “shipped”. ");
		} else if (okeOrNot == 1) {
			JOptionPane.showMessageDialog(null, "Your CC has been denied");
			JOptionPane.showMessageDialog(null, "!!!!terminate confirm shipment");

			System.exit(0);

		} else {
			System.exit(0);
		}
	}

}