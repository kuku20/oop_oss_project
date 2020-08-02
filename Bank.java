package oop_OSS;

import java.util.Random;

import javax.swing.JOptionPane;

public interface Bank {

	void orderAmountCheck(double amount, String CCN);

	void CCCheck(String CCN);

	void toDisplayOrder(String Display);

	int checkApprove();

	String bankapproved();

	String CCN();

	String NewCCN();

}

class OrderRequest implements Bank {
	public double amount;
	public int number;
	public String CCN, NewCCN;
	public String status;

	OrderRequest() {
	};

	OrderRequest(String CCN, double amount) {
		this.CCN = CCN;
		this.amount = amount;
	}

	@Override
	public void orderAmountCheck(double amount, String CCN) {

		String check = "The bank have check the cc number: " + CCN + " for the amount purchase: $" + amount;
		String[] bankCheck = { "Approved", "Denied" };
		int okeOrNot = JOptionPane.showOptionDialog(null, check, "Bank Cheking....", JOptionPane.PLAIN_MESSAGE,
				JOptionPane.INFORMATION_MESSAGE, null, bankCheck, bankCheck[0]);
		if (okeOrNot == 0) {
			// generate the authorization number
			Random r = new Random();
			number = r.nextInt();
			status = "ordered";
		} else if (okeOrNot == 1) {
			String ccupdate = JOptionPane.showInputDialog("Your CC has been denied, please provide a new one!!");
			if (ccupdate == null) {
				System.out.println("This is cancel button");
				System.exit(0);
			} else if (ccupdate.equalsIgnoreCase("")) {
				System.exit(0);
				System.out.println("This is OK button without input");
			} else {
				NewCCN = ccupdate;

				orderAmountCheck(amount, NewCCN);
			}
		} else {
			System.exit(0);
		}
	}

	@Override
	public int checkApprove() {
		return (int) number;
	}

	@Override
	public String bankapproved() {
		// TODO Auto-generated method stub
		return status;
	}

	@Override
	public void CCCheck(String CCN) {
		// if invalid credit card or over credit limit
		System.out.println("please use order credit card number.");
		// enter different ccn or cance the order
		// update new card number to customer's account
		CCN = CCN;
	}

	@Override
	public void toDisplayOrder(String Display) {
		// TODO Auto-generated method stub
		System.out.println("to customer: this is your order detail...");
	}

	@Override
	public String CCN() {
		// TODO Auto-generated method stub
		return CCN;
	}

	@Override
	public String NewCCN() {
		// TODO Auto-generated method stub
		return NewCCN;
	}
}
