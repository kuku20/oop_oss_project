package oop_OSS;

import java.util.Scanner;

public interface catalogList {
	void catalogList(String Cid);
	void cart(String Cid);
}

interface choiceInlist {
	String ItemId();

	String ItemQuan();

	String Cid();
}

class customerChoice implements catalogList, choiceInlist {

	String idItem, amountItem, Cid;

	@Override
	public void catalogList(String Cidin) {
		// TODO Auto-generated method stub
		System.out.println("There are the list of items: " + "\n 1. phone 8 price $800" + "\n 2. laptop price $1200"
				+ "\n 3. tv price $100" + "\n 4. hat price $1200" + "\n 5. guita price $1200");
		Scanner choiceI = new Scanner(System.in);
		// First employee objects with the input
		System.out.print("Enter the Item : ");
		idItem = choiceI.next();
		System.out.println("how many??");
		amountItem = choiceI.next();
		Cid=Cidin;
	}
	@Override
	public String ItemId() {
		// TODO Auto-generated method stub
		return idItem;
	}

	@Override
	public String ItemQuan() {
		// TODO Auto-generated method stub
		return amountItem;
	}
	@Override
	public String Cid() {
		// TODO Auto-generated method stub
		return Cid;
	}
	@Override
	public void cart(String Cid) {
		System.out.println("The id customer: "+ Cid+ "has browed and add item: "+idItem+ "To his cart." );
		
	}

}