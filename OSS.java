package oop_OSS;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.io.BufferedWriter;
import java.util.Scanner;

public class OSS {
public static void main(String[] args) throws IOException {
	CreateAccount newcustomer =new CreateAccount("123456 ","oopsumer2020"," loc "
			,"Bushy Creek Houston TX"," 8325322153 ","***********9865");

//	System.out.println(newcustomer);
	CreateAccount Supplier = new CreateAccount();
	Supplier.setId("2 ");
	Supplier.password="afdsfa ";
	newcustomer.setId("123456 ");
//	create file and store the information
	File file = new File(".");
	String store = file.getCanonicalPath() + File.separator + "store.txt";
	FileWriter fstream = new FileWriter(store,true);
	BufferedWriter  output = new BufferedWriter (fstream);
	output.write(newcustomer.toString());
	
	output.newLine();
	output.write(Supplier.getId());
	output.newLine();
	output.close();
	//	LogIn customerLogin = new LogIn(null, null, null, null, null, null);
	LogIn customerLogin = new LogIn(null, null);
	LogIn SupplierLogin = new LogIn(null, null);
	customerLogin.setId("1=gghjh");
	//Create a scanner for the file
	Scanner input = new Scanner(new File("store.txt"));
	String id= input.next();
	String pw= input.next();
	String n= input.next();
	String ad= input.next();
	String y= input.nextLine();
	System.out.println(id);
	System.out.println(pw);
	System.out.println(n);
	
	
	System.out.println(y);
	System.out.println(customerLogin.getId());
	if(id.equals(customerLogin.getId())) {
		System.out.println("You log in");
	}else {
		System.out.println("not exist");
	}
	
}
}
