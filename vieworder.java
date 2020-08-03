package oop_OSS;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public interface vieworder {
	void vieworder(String Cid) throws IOException;
	String readingFileStatus();
	ArrayList<String> readingFileId();
}

interface supplierRequestInvertory {
	String itemName();

	String itemQuantity();

	void inventoryCheck(String orderID) throws IOException;
}

interface accountAndBank {

	void charging(String CCC,String idC) throws IOException;
}

class view implements vieworder {

	public void vieworder(String Cid) throws IOException {
		// interpret the user's choice

			JOptionPane.showMessageDialog(null, "Customer orders details: Available in Console!!!");
			readingOrderfile(Cid);
			System.out.println("==========YOUR ORDER DETAIL========");
			for (int i = 0; i < yourItemNameArray.length; i++) {
				System.out.println("You have ordered: " + yourItemQuaArray[i] + " " + yourItemNameArray[i] + "(s)");
			}
			System.out.println("You have to pay the total amount: $" + itemTotalA);
			System.out.println("Your order status: " + status + "\nAuthorization number is: " + auCode);
			System.out.println("===================================");

	}

	protected static String itemId, itemName, itemQua, itemTotalA, IdC, auCode, CCN, status,readingFileStatus;
	protected static String[] yourItemNameArray, yourItemQuaArray;
	protected static ArrayList<String> readingFileId = new ArrayList<String>();
	protected void readingOrderfile(String Cid) throws IOException {

		File Read = new File("order.txt");
		if (!Read.exists()) {
			System.out.println("The file order.txt does not exist.");
			System.exit(0);
		}
		Scanner reading = new Scanner(Read);
		// read the contents of the first file,
		while(reading.hasNext()) {
			IdC = reading.nextLine();
			if(IdC.equals(Cid)) {
				itemId = reading.nextLine();
				itemName = reading.nextLine();
				itemQua = reading.nextLine();
				itemTotalA = reading.nextLine();
				auCode = reading.nextLine();
				status = reading.nextLine();
			}
		}

		reading.close();
		yourItemNameArray = itemName.substring(1, (itemName.lastIndexOf("]"))).toString().split(",");
		yourItemQuaArray = itemQua.substring(1, (itemQua.lastIndexOf("]"))).toString().split(",");

	}

	@Override
	public ArrayList<String> readingFileId() {
		// TODO Auto-generated method stub
		return readingFileId;
	}

	@Override
	public String readingFileStatus() {
		// TODO Auto-generated method stub
		return readingFileStatus;
	}



}

class supplierView extends view implements vieworder, accountAndBank,supplierRequestInvertory{
	public String itemNameInvertory, itemQuantityInvertory;
	@Override
	public void vieworder(String x) throws IOException {
		// interpret the user's choice
		File Read = new File("order.txt");
		if (!Read.exists()) {
			System.out.println("The file order.txt does not exist.");
			System.exit(0);
		}
		Scanner reading = new Scanner(Read);
		// read the contents of the first file,
		while(reading.hasNext()) {
			IdC = reading.nextLine();
			itemId = reading.nextLine();
			itemName = reading.nextLine();
			itemQua = reading.nextLine();
			itemTotalA = reading.nextLine();
			auCode = reading.nextLine();
			status = reading.nextLine();
			if(status.equals(x)) 
			{
				yourItemNameArray = itemName.substring(1, (itemName.lastIndexOf("]"))).toString().split(",");
				yourItemQuaArray = itemQua.substring(1, (itemQua.lastIndexOf("]"))).toString().split(",");
				System.out.println("======SUPPLIER VIEW=========");
				System.out.println("The customer with the ID: " + IdC);
				for (int i = 0; i < yourItemNameArray.length; i++) {
					System.out.println("has ordered: " + yourItemQuaArray[i] + " " + yourItemNameArray[i] + "(s)");
				}
				System.out.println("The total amount is: " + itemTotalA);
				System.out.println("The customer order status: " + status + "\nAuthorization Number is: " + auCode);
				System.out.println("=========================");
				readingFileId.add(IdC);
				readingFileStatus=status;
			}
		}
		reading.close();
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
public void inventoryCheck(String orderID) throws IOException {
	File tempXFile = new File("template.txt");
	File originalXFile = new File("order.txt");
	// Create a Scanner for input and a PrintWriter for output
	Scanner inXput = new Scanner(originalXFile);
	PrintWriter outXput = new PrintWriter(tempXFile);
	while (inXput.hasNext()) {
		IdC = inXput.nextLine();
		itemId = inXput.nextLine();
		itemName = inXput.nextLine();
		itemQua = inXput.nextLine();
		itemTotalA = inXput.nextLine();
		auCode = inXput.nextLine();
		status = inXput.nextLine();
		if(IdC.equals(orderID)) {
			yourItemNameArray = itemName.substring(1, (itemName.lastIndexOf("]"))).toString().split(",");
			yourItemQuaArray = itemQua.substring(1, (itemQua.lastIndexOf("]"))).toString().split(",");
			JOptionPane.showMessageDialog(null,
					"Supplier selected a delivery order and requests inventory check on items for the delivery order");
			for (int i = 0; i < yourItemNameArray.length; i++) {
				System.out.println("Inventory check on: " + yourItemNameArray[i] + "s Quantity " + yourItemQuaArray[i]);
			}
			String[] inventory = { "Available", "Out of stock" };
			int inventoryChoice = JOptionPane.showOptionDialog(null, "INVENTORY CHECKING ITEM", "Inventory Checking....",
					JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, inventory, inventory[0]);
			if (inventoryChoice == 0) {
			status="ready";
			JOptionPane.showMessageDialog(null, "Order has been readied");
			outXput.println(IdC+"\n"+itemId+"\n"+itemName+"\n"+itemQua+"\n"+itemTotalA+"\n"+auCode+"\n"+status);
			}else if(inventoryChoice ==1) {
				System.out.println("an inventory order is required for the item(s).");
				for (int i = 0; i < yourItemNameArray.length; i++) {
					System.out.println("Inventory needs to supply more: " + yourItemNameArray[i]);
				}
				outXput.println(IdC+"\n"+itemId+"\n"+itemName+"\n"+itemQua+"\n"+itemTotalA+"\n"+auCode+"\n"+status);
			}
			
		}else {
			outXput.println(IdC+"\n"+itemId+"\n"+itemName+"\n"+itemQua+"\n"+itemTotalA+"\n"+auCode+"\n"+status);
		}
	}
	inXput.close();
	outXput.close();
	originalXFile.delete();
	// Rename the new file to the filename the original file had.
	if (!tempXFile.renameTo(originalXFile))
		System.out.println("Could not rename file");

}


	@Override
	public void charging(String CCC,String idC) throws IOException {

			File tempXFile = new File("template.txt");
			File originalXFile = new File("order.txt");
			// Create a Scanner for input and a PrintWriter for output
			Scanner inXput = new Scanner(originalXFile);
			PrintWriter outXput = new PrintWriter(tempXFile);
			while (inXput.hasNext()) {
				IdC = inXput.nextLine();
				itemId = inXput.nextLine();
				itemName = inXput.nextLine();
				itemQua = inXput.nextLine();
				itemTotalA = inXput.nextLine();
				auCode = inXput.nextLine();
				status = inXput.nextLine();
				if(IdC.equals(idC)) {
					String check = "The bank has charged: $" + itemTotalA + " to the Credit Card number: " + CCC;
					String[] bankCharg = { "Charged", "Denied" };
					int okeOrNot = JOptionPane.showOptionDialog(null, check, "Bank Charging....", JOptionPane.PLAIN_MESSAGE,
							JOptionPane.INFORMATION_MESSAGE, null, bankCharg, bankCharg[0]);
					if (okeOrNot == 0) {
						// generate the authorization number
						yourItemNameArray = itemName.substring(1, (itemName.lastIndexOf("]"))).toString().split(",");
						yourItemQuaArray = itemQua.substring(1, (itemQua.lastIndexOf("]"))).toString().split(",");
						System.out.println("Inventory: ");
						for (int i = 0; i < yourItemNameArray.length; i++) {
							System.out.println("has updated: " + yourItemQuaArray[i] + " " + yourItemNameArray[i] + "(s) for delivery");
						}
						//System.out.println("for delivery.");
						status="shipped";
						outXput.println(IdC+"\n"+itemId+"\n"+itemName+"\n"+itemQua+"\n"+itemTotalA+"\n"+auCode+"\n"+status);
						JOptionPane.showMessageDialog(null, "Delivery order is shipped. ");
					} else if (okeOrNot == 1) {
						JOptionPane.showMessageDialog(null, "Your CreditCard has been denied");
						JOptionPane.showMessageDialog(null, "Confirmation of terminating shipment");
						outXput.println(IdC+"\n"+itemId+"\n"+itemName+"\n"+itemQua+"\n"+itemTotalA+"\n"+auCode+"\n"+status);
					} else {
						System.exit(0);
					}
				}else {
					outXput.println(IdC+"\n"+itemId+"\n"+itemName+"\n"+itemQua+"\n"+itemTotalA+"\n"+auCode+"\n"+status);
				}
			}
			inXput.close();
			outXput.close();
			originalXFile.delete();
			// Rename the new file to the filename the original file had.
			if (!tempXFile.renameTo(originalXFile))
				System.out.println("Could not rename file");

			// store a delivery order in file
	}
}

