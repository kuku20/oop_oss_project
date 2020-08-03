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
	public static ArrayList<Boolean> hasOrdered = new ArrayList<Boolean>();
	public static String ordered;
	
	public static void main(String[] args) throws IOException {
		 int n;
		// this to create the file to store the customer information
		file();
		optionCustomerSupplier();
		}
		
	

	private static void optionCustomerSupplier() throws IOException {
		UIManager.put("OptionPane.messageFont", new Font("System", Font.PLAIN, 20));
		UIManager.put("OptionPane.buttonFont", new Font("System", Font.PLAIN, 16));
		UIManager.put("OptionPane.inputFont", new Font("System", Font.PLAIN, 16));
		String[] options = { "CUSTOMER", "SUPPLIER", "CANCEL" };
		int x = JOptionPane.showOptionDialog(null, "Are you a CUSTOMER or are you a SUPPLIER?",
				"Welcome to Online Shopping", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
				options[0]);
		if (x == 0) {
			// this is ask the customer login or create a new account
			String[] customerC = { "1.Login", "2.Create New Account", "3. Cancel" };
			int Cx = JOptionPane.showOptionDialog(null,
					"Do you have an account ? \n If Yes click 1 to login \n If No click 2 to create one ",
					"Welcome Customer Options", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,
					customerC, customerC[0]);
			if (Cx == 0) {
				System.out.println("Login");
				// customer Login
				customerLogin();
			} else if (Cx == 1) {
				System.out.println("Create Acount");
				// create new account
				customerAccount();
			} else {
				System.out.println("cancel");
				System.exit(0);
			}
		} else if (x == 1) {
			// this is ask the supplier login or create a new account

			String[] supplierC = { "1.Login", "2.Create New Account", "3. Cancel" };
			int sx = JOptionPane.showOptionDialog(null,
					"Do you have an account ? \n If Yes click 1 to login \n If No click 2 to create one",
					"Welcome Supplier Options", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,
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
			System.out.println("you chose cancel");
			System.exit(2);
		}

	}

	private static void file() throws IOException {

		File readCustomer = new File("Customer.txt");
		 if (!readCustomer.exists()) {
			 PrintWriter output = new PrintWriter(new File("Customer.txt"));
		 }
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
			ordered=getCustomer.nextLine();
			if(ordered.equals("true")) {
				hasOrdered.add(true);
			}else if(ordered.equals("false")){
				hasOrdered.add(false);
			}
	
		}
		getCustomer.close();
		// get all data in the exist file to the arraylist
		File readSupplier = new File("Supplier.txt");
		 if (!readSupplier.exists()) {
			 PrintWriter output = new PrintWriter(new File("Supplier.txt"));
		 }
		Scanner getSupplier = new Scanner(readSupplier);
		while (getSupplier.hasNext()) {
			newSupplierId = getSupplier.nextLine();
			newSupplierPass = getSupplier.nextLine();
			IdSupplier.add(newSupplierId);
			passSupplier.add(newSupplierPass);
		}
		getSupplier.close();
	}

	private static void supplierAccount() throws IOException {
		JTextField Id = new JTextField();
		JTextField password = new JPasswordField();
		Object[] accountInfor = { "Username:", Id, "Password:", password };
		int option = JOptionPane.showConfirmDialog(null, accountInfor, "Supplier Create New Account!!!",
				JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			if (Id.getText().isEmpty() || password.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please Enter all text fields");
				supplierAccount();
			} else {
				// main
				CreateAccount newSupplier = new CreateAccount(Id.getText(), password.getText());
				File cus = new File(".");
				FileWriter customer = new FileWriter(cus.getCanonicalPath() + File.separator + "Supplier.txt", true);
				BufferedWriter output = new BufferedWriter(customer);
				boolean exist = IdSupplier.contains(newSupplier.getId());
				if (exist) {
					int choice = JOptionPane.showOptionDialog(null, "THIS ID IS TAKEN, Do you have an existing account?", "Quit?",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

					// interpret the user's choice
					if (choice == JOptionPane.YES_OPTION) {
						supplierLogin();
					} else {
						JOptionPane.showMessageDialog(null, "Choose a different ID");
						supplierAccount();
					}
				} else {
					output.write(newSupplier.toStringSuppier());
					output.newLine();
					JOptionPane.showMessageDialog(null, "Your account has been created.");
					output.close();
					// update the id and password to the arraylist
					IdSupplier.add(newSupplier.getId());
					passSupplier.add(newSupplier.getPassword());
					supplierLogin();
				}
			}
		} else {
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
						
						
						String[] SupplierAct = { "View Orders","View Orders ready to ship","LOG OUT" };
						int Request = JOptionPane.showOptionDialog(null, "Check delivery orders Status!!!",
								"Hello Supplier!!!", JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null,
								SupplierAct, SupplierAct[0]);
						if(Request==0)
						{
							vieworder supplierView = new supplierView();
							supplierView.vieworder("ordered");
							if(supplierView.readingFileStatus()!=null) {
								supplierView.readingFileId().size();
								JTextField cusId = new JTextField();
								Object[] orderSelect = { "Enter the Customer ID of the order you wish to select: \n"
										+supplierView.readingFileId() , cusId};
								int options = JOptionPane.showConfirmDialog(null, orderSelect, "Supplier select an order",
										JOptionPane.OK_CANCEL_OPTION);
								if(options == JOptionPane.OK_OPTION) {
								
									if(cusId.getText().isEmpty()) {
										JOptionPane.showMessageDialog(null, "Please enter a Customer ID");
										while(cusId.getText().isEmpty()) {
											supplierView.vieworder("ordered");
											int optioned = JOptionPane.showConfirmDialog(null, orderSelect, "Supplier select an order",
													JOptionPane.OK_CANCEL_OPTION);

										}
										supplierRequestInvertory reInvertory=(supplierRequestInvertory) supplierView;
										reInvertory.inventoryCheck(cusId.getText());
									}else {
										supplierRequestInvertory reInvertory=(supplierRequestInvertory) supplierView;
										reInvertory.inventoryCheck(cusId.getText());
									}
								}
							}else {
								System.out.println("There is no more ordered waiting!!");
							}
							
						}else if(Request==1) {
							vieworder supplierView = new supplierView();
							supplierView.vieworder("ready");
							
							// do you want to see the delivery order. in console
							//	get id customer order the order
							if(supplierView.readingFileStatus()!=null) {
								JTextField cusId = new JTextField();
								Object[] orderSelect = { "Enter the Customer ID of the order you wish to select:"
										+ supplierView.readingFileId(), cusId};
								int options = JOptionPane.showConfirmDialog(null, orderSelect, "Supplier select an order",
										JOptionPane.OK_CANCEL_OPTION);
								if(options == JOptionPane.OK_OPTION) {
									if(cusId.getText().isEmpty()) {
										JOptionPane.showMessageDialog(null, "Please enter a Customer ID");
										while(cusId.getText().isEmpty()) {
											supplierView.vieworder("ready");
											int optioned = JOptionPane.showConfirmDialog(null, orderSelect, "Supplier select an order",
													JOptionPane.OK_CANCEL_OPTION);

										}
										supplierRequestInvertory reInvertory=(supplierRequestInvertory) supplierView;
										reInvertory.inventoryCheck(cusId.getText());
									}
									else {
										accountAndBank idCformOrder=(accountAndBank) supplierView;
										// you seletc a delivery order
										// 3. this will get the indexof the idcomtomer
										if(IdCustomer.contains(cusId.getText())) {
											int INDEXCC = IdCustomer.indexOf(cusId.getText());
											// this is the credit card of the customer:
											String CCC = creditCardCustomer.get(INDEXCC);
											// the system request bank charging the purchase amount of order using the
											// CCC
											idCformOrder.charging(CCC,cusId.getText());
										}else {
											while(!IdCustomer.contains(cusId.getText())) {
												int optioned = JOptionPane.showConfirmDialog(null, orderSelect, "Supplier select an order",
														JOptionPane.OK_CANCEL_OPTION);
											}
											int INDEXCC = IdCustomer.indexOf(cusId.getText());
											// this is the credit card of the customer:
											String CCC = creditCardCustomer.get(INDEXCC);
											// the system request bank charging the purchase amount of order using the
											// CCC
											idCformOrder.charging(CCC,cusId.getText());
											System.out.println(""+cusId.getText());
										}
									}
								}
							}else {
								System.out.println("There is not ready for shipping");
							}
							
						}
					}
					if (supplierLogin.getId().equals(IdSupplier.get(i))
							&& !supplierLogin.getPassword().equals(passSupplier.get(i))) {
						JOptionPane.showMessageDialog(null, "Wrong Password!!! Try to login again..");
						supplierLogin();
					}
				}
			} else {
				String[] createOne = { "Create Account", "Exit" };
				int newAC = JOptionPane.showOptionDialog(null, "This ID doesn't exist, do you want to create one?",
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
					int choice = JOptionPane.showOptionDialog(null, "This id is in use, is this you?", "Quit?",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

					// interpret the user's choice
					if (choice == JOptionPane.YES_OPTION) {
						customerLogin();
					} else {
						JOptionPane.showMessageDialog(null, "Choose a new ID");
						customerAccount();
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
					hasOrdered.add(newcustomer.getCreatedOrder());
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
				LogIn customerLogin = new LogIn(cusLogId.getText(), cusLogPass.getText());
				boolean idexist = IdCustomer.contains(customerLogin.getId());
				if (idexist) {
					for (int i = 0; i < IdCustomer.size(); i++) {
						if (customerLogin.getId().equals(IdCustomer.get(i))
								&& customerLogin.getPassword().equals(passCustomer.get(i))) {
							String wellcome = " Welcome to main page. Customer ID: " + customerLogin.getId();
							customerLogin.toLogin();
							// this is where customer can choice to logout or go to the catalog
							if(!hasOrdered.get(i)) 
							{
								String[] options = { "1. Go to the Catalog", "2. Log OUT", "3. Exit" };
								int views = JOptionPane.showOptionDialog(null, "Do you want to go shopping?", wellcome,
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

									// get the total price form the card detal
									amountDue = Cchoice.totalPrice();

									// connect and check to the bank
									OrderRequest cusCheckBank = new OrderRequest(ccnc, amountDue);
									cusCheckBank.orderAmountCheck(amountDue, ccnc);
									System.out.println("Customer has ordered items.");
									// if cc doesn't work update new card

									String newCCNC = cusCheckBank.NewCCN();
									if (!(newCCNC == null)) {
										//// update new card to the array
										creditCardCustomer.remove(indexCustomer);
										creditCardCustomer.add(indexCustomer, newCCNC);
										// rewrite to customer file
										File tempXFile = new File("template.txt");
										File originalXFile = new File("Customer.txt");
										// Create a Scanner for input and a PrintWriter for output
										Scanner inXput = new Scanner(originalXFile);
										PrintWriter outXput = new PrintWriter(tempXFile);

										while (inXput.hasNext()) {
											String CustomerId = inXput.nextLine();
											String CustomerPass = inXput.nextLine();
											String n = inXput.nextLine();
											String ad = inXput.nextLine();
											String ph = inXput.nextLine();
											String Cc = inXput.nextLine();
											String ordered = inXput.nextLine();
											if (CustomerId.equals(customerLogin.getId())) {
												String newString = CustomerId + "\n" + CustomerPass + "\n" + n + "\n" + ad + "\n" + ph + "\n"+newCCNC;
												String s2x = Cc.replaceAll(Cc, newString);
												outXput.println(s2x);
												outXput.println(""+ordered);
											} else {
												String newString = CustomerId + "\n" + CustomerPass + "\n" + n + "\n" + ad + "\n" + ph + "\n"+Cc;
												String s2x = Cc.replaceAll(Cc, newString);
												outXput.println(s2x);
												outXput.println(""+ordered);
											}
										}
										inXput.close();
										outXput.close();
										originalXFile.delete();
										// Rename the new file to the filename the original file had.
										if (!tempXFile.renameTo(originalXFile))
											System.out.println("Could not rename file");
//		=========================================================================================================
									}
									File tempXFile = new File("template.txt");
									File originalXFile = new File("Customer.txt");
									// Create a Scanner for input and a PrintWriter for output
									Scanner inXput = new Scanner(originalXFile);
									PrintWriter outXput = new PrintWriter(tempXFile);

									while (inXput.hasNext()) {
										String CustomerId = inXput.nextLine();
										String CustomerPass = inXput.nextLine();
										String n = inXput.nextLine();
										String ad = inXput.nextLine();
										String ph = inXput.nextLine();
										String Cc = inXput.nextLine();
										String ordered =inXput.nextLine();
										if (CustomerId.equals(customerLogin.getId())) {
											ordered="true";
											String newString = CustomerId + "\n" + CustomerPass + "\n" + n + "\n" + ad + "\n" + ph + "\n"+Cc;
											outXput.println(newString);
											outXput.println(""+ordered);
										} else {
											String newString = CustomerId + "\n" + CustomerPass + "\n" + n + "\n" + ad + "\n" + ph + "\n"+Cc;
											outXput.println(newString);
											outXput.println(""+ordered);
										}
									}
									inXput.close();
									outXput.close();
									originalXFile.delete();
									// Rename the new file to the filename the original file had.
									if (!tempXFile.renameTo(originalXFile))
										System.out.println("Could not rename file");
									// store a delivery order in textfile
									FileWriter orderFile = new FileWriter("order.txt",true);
									// store a delivery order in file
									String price = ""+Cchoice.totalPrice();
									orderFile.write(customerLogin.getId()+"\n");
									orderFile.write(Cchoice.ItemId().toString()+"\n");
									orderFile.write(Cchoice.ItemName().toString()+"\n");
									orderFile.write(Cchoice.ItemQuan().toString()+"\n");
									orderFile.write(price+"\n");
									orderFile.write(cusCheckBank.checkApprove()+"\n");
									orderFile.write(cusCheckBank.bankapproved()+"\n");
									
									orderFile.close();
									Cchoice.ItemId().clear();
									Cchoice.ItemName().clear();
									Cchoice.ItemQuan().clear();
									hasOrdered.set(i, true);
									// ===================================
									// View order
									// ===================================
//									view customerView = new view();
//									customerView.vieworder(customerLogin.getId());
									customerLogin();
								} else if (views == 1) {
									// =========
									// logout
									// =============
									// log out the system go back to the main page!!!
									JOptionPane.showMessageDialog(null, "Customer has logged out.");
									optionCustomerSupplier();
									// System.exit(1);

								} else {
									System.exit(1);
								}
								
							}else {
								String[] options = { "1. View your Order", "2. Log OUT", "3. Exit" };
								int views = JOptionPane.showOptionDialog(null, "Do you want to go shopping?", wellcome,
										JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
										options[0]);
								if(views==0) {
									vieworder customerView = new view();
									customerView.vieworder(customerLogin.getId());
								}else if (views == 1) {
									// =========
									// logout
									// =============
									// log out the system go back to the main page!!!
									JOptionPane.showMessageDialog(null, "Customer has logged out.");
									optionCustomerSupplier();
									// System.exit(1);

								} else {
									System.exit(1);
								}
							}
						}
						if (customerLogin.getId().equals(IdCustomer.get(i))
								&& !customerLogin.getPassword().equals(passCustomer.get(i))) {
							JOptionPane.showMessageDialog(null, "Wrong password!!! Try to login again..");
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
		}else
	{
		JOptionPane.showMessageDialog(null, "you cancel");
		System.exit(0);
	}

}

}
