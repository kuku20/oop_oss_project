package oop_OSS;

import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.Font;
import java.io.BufferedWriter;
import java.util.ArrayList;

public class OSS {
	// this create the array list that store the id of customer have create account
	public static ArrayList<String> IdCustomer = new ArrayList<String>();
	public static ArrayList<String> IdSupplier = new ArrayList<String>();
	public static ArrayList<String> passCustomer = new ArrayList<String>();
	public static ArrayList<String> passSupplier = new ArrayList<String>();
	public static ArrayList<String> creditCardCustomer = new ArrayList<String>();
	public static String newSupplierId, newSupplierPass;
	public static String newCustomerId, newCustomerPass, newCustomerCc;

	public static void main(String[] args) throws IOException {
		// this to create the file to store the customer information
		file();
		optionCustomerSupplier();
	}

	private static void optionCustomerSupplier() throws IOException {
		UIManager.put("OptionPane.messageFont", new Font("System", Font.PLAIN, 20));
		UIManager.put("OptionPane.buttonFont", new Font("System", Font.PLAIN, 16));
		UIManager.put("OptionPane.inputFont", new Font("System", Font.PLAIN, 16));
		String[] options = { "CUSTOMER", "SUPPLIER", "CANCEL" };
		int x = JOptionPane.showOptionDialog(null, "Are you CUSTOMER or are you SUPPLIER?",
				"Wellcome to Online Shopping", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
				options[0]);
		if (x == 0) {
			// this is ask the customer login or create a new account
			System.out.println("you choice customer");
			String[] customerC = { "1.Login", "2.Create New Account", "3. Cancel" };
			int Cx = JOptionPane.showOptionDialog(null,
					"Do you have an account ? \n If Yes click 1 to login \n If No click 2 to create one ",
					"Wellcome Customer Options", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,
					customerC, customerC[0]);
			if (Cx == 0) {
				System.out.println("login");
				// customer Login
				customerLogin();
			} else if (Cx == 1) {
				System.out.println("create acount");
				// create new account
				customerAccount();
			} else {
				System.out.println("cancle");
				System.exit(0);
			}
		} else if (x == 1) {
			// this is ask the supplier login or create a new account
			System.out.println("you choi supplier");

			String[] supplierC = { "1.Login", "2.Create New Account", "3. Cancel" };
			int sx = JOptionPane.showOptionDialog(null,
					"Do you have an account ? \n If Yes click 1 to login \n If No click 2 to create one",
					"Wellcome Supplier Options", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,
					supplierC, supplierC[0]);
			if (sx == 0) {
				System.out.println("supplier login");
				supplierLogin();
			} else if (sx == 1) {
				System.out.println("supplier create new");
				supplierAccount();
			} else {
				System.exit(1);
			}
		} else {
			System.out.println("you choice cancel");
			System.exit(2);
		}

	}

	private static void file() throws IOException {
		// CheckId idcheck = new accountCheck();
		File cus = new File(".");
		FileWriter customer = new FileWriter(cus.getCanonicalPath() + File.separator + "Customer.txt", true);

		File sup = new File(".");
		FileWriter supplier = new FileWriter(sup.getCanonicalPath() + File.separator + "Supplier.txt", true);
		File readCustomer = new File("Customer.txt");
		
//		PrintWriter invFile = new PrintWriter("Inventory.txt");
//		int inventory=100;
//		invFile.println(inventory);
//		invFile.println(inventory);
//		invFile.println(inventory);
//		invFile.println(inventory);
//		invFile.println(inventory);
//		invFile.close();
		// if (!Read.exists()) {
		// System.out.println("The file does not exist.");
		// System.exit(0);
		// }
		Scanner getCustomer = new Scanner(readCustomer);
		while (getCustomer.hasNext()) {
			newCustomerId = getCustomer.nextLine();
			newCustomerPass = getCustomer.nextLine();
			String n = getCustomer.nextLine();
			String ad = getCustomer.nextLine();
			String ph = getCustomer.nextLine();
			newCustomerCc = getCustomer.nextLine();
			IdCustomer.add(newCustomerId);
			passCustomer.add(newCustomerPass);
			creditCardCustomer.add(newCustomerCc);
		}
		// get all data in the exist file to the arraylist
		File readSupplier = new File("Supplier.txt");
		Scanner getSupplier = new Scanner(readSupplier);
		while (getSupplier.hasNext()) {
			newSupplierId = getSupplier.nextLine();
			newSupplierPass = getSupplier.nextLine();
			IdSupplier.add(newSupplierId);
			passSupplier.add(newSupplierPass);
		}

	}

	private static void supplierAccount() throws IOException {
		JTextField Id = new JTextField();
		JTextField password = new JPasswordField();
		Object[] accountInfor = { "Username:", Id, "Password:", password };
		int option = JOptionPane.showConfirmDialog(null, accountInfor, "Supplier Create New Account!!!",
				JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			if (Id.getText().isEmpty() || password.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please Enter all the text field");
				supplierAccount();
			} else {
				// main
				CreateAccount newSupplier = new CreateAccount(Id.getText(), password.getText());
				File cus = new File(".");
				FileWriter customer = new FileWriter(cus.getCanonicalPath() + File.separator + "Supplier.txt", true);
				BufferedWriter output = new BufferedWriter(customer);
				boolean exist = IdSupplier.contains(newSupplier.getId());
				if (exist) {
					int choice = JOptionPane.showOptionDialog(null, "THIS ID IS EXISTED, DO YOU WANT LOGIN?", "Quit?",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

					// interpret the user's choice
					if (choice == JOptionPane.YES_OPTION) {
						supplierLogin();
					} else {
						System.exit(0);
					}
				} else {
					output.write(newSupplier.toStringSuppier());
					output.newLine();
					JOptionPane.showMessageDialog(null, "Your account have been created.");
					output.close();
					// update the id and password to the arraylist
					IdSupplier.add(newSupplier.getId());
					passSupplier.add(newSupplier.getPassword());
					supplierLogin();
				}
			}
		} else {
			System.out.println("you cancle");
			System.exit(0);
		}
	}

	private static void supplierLogin() throws IOException {
		JTextField IdLog = new JTextField();
		JTextField passwordLog = new JPasswordField();
		Object[] accountLogin = { "Username:", IdLog, "Password:", passwordLog };
		int option = JOptionPane.showConfirmDialog(null, accountLogin, "Supplier Login", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			LogIn supplierLogin = new LogIn(IdLog.getText(), passwordLog.getText());
			boolean idexist = IdSupplier.contains(supplierLogin.getId());
			if (idexist) {
				for (int i = 0; i < IdSupplier.size(); i++) {
					if (supplierLogin.getId().equals(IdSupplier.get(i))
							&& supplierLogin.getPassword().equals(passSupplier.get(i))) {
						supplierLogin.toLogin();
						// main supplier, where to see and request customer order
						// Use Case: Process Delivery Order
						String[] SupplierAct = { "REQUESTS DELIVERY ORDERS","Request Ready Orders" ,"LOG OUT" };
						int Request = JOptionPane.showOptionDialog(null, "Check delivery orders!!!",
								"Hello Supplier!!!", JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null,
								SupplierAct, SupplierAct[0]);
						if (Request == 0) {
							// 2. System retrieves and displays delivery orders to supplier.
							supplierView supplierView = new supplierView();
							supplierView.vieworder(supplierLogin.getId());
							// 3. Supplier selects a delivery order and requests inventory check on items
							//
							// for the delivery order.
							String[] supplier = {"Order 1","Exit to login"};
							int Request2 = JOptionPane.showOptionDialog(null, "Chose delivery orders!!!",
									"Hello Supplier!!!", JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null,
									supplier, supplier[0]);
							if(Request2==0) {
								supplierView.changeOrder();
								supplierLogin();
							}else if(Request2==1) {
								supplierLogin();
							}
						} else if (Request == 2) {
							// logout back to main option
							optionCustomerSupplier();
						}else if(Request==1) {
							supplierView supplierView = new supplierView();
							supplierView.confirmShipment();
						} else {
							System.exit(0);
						}

					}
					if (supplierLogin.getId().equals(IdSupplier.get(i))
							&& !supplierLogin.getPassword().equals(passSupplier.get(i))) {
						JOptionPane.showMessageDialog(null, "Wrong pass!!! Try to login again..");
						supplierLogin();
					}
				}
			} else {
				String[] createOne = { "Create account", "Exit" };
				int newAC = JOptionPane.showOptionDialog(null, "This ID doesnt't exist, do you want to create one?",
						"WARNING!!", JOptionPane.PLAIN_MESSAGE, JOptionPane.WARNING_MESSAGE, null, createOne,
						createOne[0]);
				if (newAC == 0) {
					supplierAccount();
				} else {
					System.exit(0);
				}
			}
		} else {
			System.exit(0);
		}
	}

	private static void customerAccount() throws IOException {
		JTextField cusId = new JTextField();
		JTextField cusPass = new JPasswordField();
		JTextField cusN = new JTextField();
		JTextField cusAd = new JTextField();
		JTextField cusPh = new JTextField();
		JTextField cusCc = new JTextField();
		Object[] accountInfor = { "ID:", cusId, "Password:", cusPass, "Name:", cusN, "Address:", cusAd, "Phone:", cusPh,
				"Credit Card:", cusCc };
		int option = JOptionPane.showConfirmDialog(null, accountInfor, "Customer Create New Account !!!",
				JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			if (cusId.getText().isEmpty() || cusPass.getText().isEmpty() || cusN.getText().isEmpty()
					|| cusAd.getText().isEmpty() || cusPh.getText().isEmpty() || cusCc.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please Enter all the text field");
				customerAccount();
			} else {
				// main
				CreateAccount newcustomer = new CreateAccount(cusId.getText(), cusPass.getText(), cusN.getText(),
						cusAd.getText(), cusPh.getText(), cusCc.getText());

				// CreateAccount newcustomer = new CreateAccount("1", "1", "Kim", "UK",
				// "8924233215", "************9986");
				// // create file and store the information of customer
				File cus = new File(".");
				FileWriter customer = new FileWriter(cus.getCanonicalPath() + File.separator + "Customer.txt", true);
				BufferedWriter output = new BufferedWriter(customer);
				boolean exist = IdCustomer.contains(newcustomer.getId());
				if (exist) {
					System.out.println("it existed");
					int choice = JOptionPane.showOptionDialog(null, "This id EXISTED, DO YOU WANT LOGIN?", "Quit?",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

					// interpret the user's choice
					if (choice == JOptionPane.YES_OPTION) {
						customerLogin();
					} else {
						System.exit(0);
					}
				} else {
					output.write(newcustomer.toStringCustomer());
					output.newLine();
					JOptionPane.showMessageDialog(null, "Your account have been created.");
					output.close();
					// update new account
					IdCustomer.add(newcustomer.getId());
					passCustomer.add(newcustomer.getPassword());
					creditCardCustomer.add(newcustomer.getCreditCard());
					customerLogin();
				}
			}
		} else {
			System.out.println("you cancel");
		}

	}

	private static void customerLogin() throws IOException {
		JTextField cusLogId = new JTextField();
		JTextField cusLogPass = new JPasswordField();
		Object[] CusLogin = { "Id:", cusLogId, "Password:", cusLogPass };
		int option = JOptionPane.showConfirmDialog(null, CusLogin, "Customer Login", JOptionPane.OK_CANCEL_OPTION);

		if (option == JOptionPane.OK_OPTION) {
			if (cusLogId.getText().isEmpty() || cusLogPass.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please Enter all the text field");
				customerLogin();
			} else {
				// main customer page

				LogIn customerLogin = new LogIn(cusLogId.getText(), cusLogPass.getText());
				boolean idexist = IdCustomer.contains(customerLogin.getId());
				if (idexist) {
					// maincustomer();
					for (int i = 0; i < IdCustomer.size(); i++) {
						if (customerLogin.getId().equals(IdCustomer.get(i))
								&& customerLogin.getPassword().equals(passCustomer.get(i))) {
							String wellcome = " Wellcome to main page. Customer ID: " + customerLogin.getId();
							customerLogin.toLogin();
							// this is where customer can choice to logout or go to the catalog
							String[] options = { "1. Go to the Catalog","2. View Order " ,"3. Log OUT", "4. Exit" };
							int views = JOptionPane.showOptionDialog(null, "Do you want to shopping?", wellcome,
									JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
									options[0]);
							catalogList firstC = new customerChoice();
							choiceInlist Cchoice = (choiceInlist) firstC;
							String ccnc;
							double amountDue = 0;

							if (views == 0) {
								// ===================================
								// Select Items
								// ===================================
								// catalog
								firstC.catalogList(customerLogin.getId());
								// Customer has browsed items and added them to a cart.
								// ==================================
								// Make Order Request
								// ==================================
								// the customer order the items with the order details
								firstC.cartDetail(customerLogin.getId());
								// 3. this will get the indexof the idcomtomer
								int indexCustomer = IdCustomer.indexOf(customerLogin.getId());
								// this is the credit card of the customer:
								ccnc = creditCardCustomer.get(indexCustomer);

								// get the total price form the card detail
								amountDue = Cchoice.totalPrice();

								// connect and check to the bank
								Bank cusCheckBank = new Bank(ccnc, amountDue);
								cusCheckBank.orderAmountCheck(amountDue, ccnc);
								System.out.println("Customer has ordered items.");
								// if cc doesn't work update new card

								String newCCNC = cusCheckBank.NewCCN();
								if (!(newCCNC == null)) {
									//// update new card
									creditCardCustomer.remove(indexCustomer);
									creditCardCustomer.add(indexCustomer, newCCNC);
								}
								// store a delivery order in textfile
								PrintWriter orderFile = new PrintWriter("order.txt");
								// store a delivery order in file
								orderFile.println(Cchoice.ItemId());
								orderFile.println(Cchoice.ItemName());
								orderFile.println(Cchoice.ItemQuan());
								orderFile.println(Cchoice.totalPrice());
								orderFile.println(customerLogin.getId());
								orderFile.println(cusCheckBank.checkApprove());
								orderFile.println(cusCheckBank.bankapproved());
								orderFile.println(creditCardCustomer.get(indexCustomer));
								orderFile.close();

								// ===================================
								// View order
								// ===================================
								view customerView = new view(); 
								customerView.vieworder(customerLogin.getId());
								optionCustomerSupplier();
							} else if (views == 2) {
								// =========
								// logout
								// =============
								// log out the system go back to the main page!!!
								JOptionPane.showMessageDialog(null, "Customer has logged out.");
								optionCustomerSupplier();
								// System.exit(1);

							} else if(views==1)
							{
								view customerView= new view();
								customerView.vieworder(customerLogin.getId());
							}
							else {
								System.exit(1);
							}

						}
						if (customerLogin.getId().equals(IdCustomer.get(i))
								&& !customerLogin.getPassword().equals(passCustomer.get(i))) {
							JOptionPane.showMessageDialog(null, "Wrong pass!!! Try to login again..");
							customerLogin();
						}
					}

				} else {
					//
					String[] createOne = { "Create account", "Exit" };
					int newAC = JOptionPane.showOptionDialog(null, "This ID doesnt't exist, do you want to create one?",
							"WARNING!!", JOptionPane.PLAIN_MESSAGE, JOptionPane.WARNING_MESSAGE, null, createOne,
							createOne[0]);
					if (newAC == 0) {
						customerAccount();
					} else {
						System.exit(0);
					}

				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "you cancel");
			System.exit(0);
		}
	}
}
