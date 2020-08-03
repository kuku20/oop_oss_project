package oop_OSS;

import javax.swing.JOptionPane;

public class LogIn extends CreateAccount {

	LogIn(String id,String password){
		super(id,password);
	}
	
public void toLogin() {
	JOptionPane.showMessageDialog(null, "You have been logged in!!");
}
}

