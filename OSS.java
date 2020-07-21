package oop_OSS;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class OSS {
public static void main(String[] args) throws IOException {
	CreateAccount newcustomer =new CreateAccount();
	CreateAccount Supplier = new CreateAccount();
	Supplier.id="12";
	Supplier.password="afdsfa";
	newcustomer.setName("loc");
	newcustomer.setAddress("asdf");
	System.out.println(newcustomer.getName());
//	storesAccountInformation X =new storesAccountInformation("123", "loc", "haha", null, null, null, null);
	File file = new File("store.txt");
	PrintWriter output = new PrintWriter(file);
	output.println(newcustomer.getName());
	output.println(Supplier.getId());
	output.close();
}
}
