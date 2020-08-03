package oop_OSS;

public class CreateAccount {
String id,name,address,password,phone,creditCard;
boolean createdOrder;
CreateAccount(){};
//for the supplier
CreateAccount(String id,String password){
	setId(id);
	setPassword(password);
}
//for the customer
CreateAccount(String id,String password,String name, String address ,String phone,String creditCard ){
	setId(id);
	setName(name);
	setAddress(address);
	setPassword(password);
	setPhone(phone);
	setCreditCard(creditCard);
	createdOrder=false;
};
void setId(String id) {this.id=id;}
void setName(String name) {this.name=name;}
void setAddress(String address) {this.address=address;}
void setPassword(String password) {this.password=password;}
void setPhone(String phone) {this.phone=phone;}
void setOrdered(boolean ordered) {createdOrder=ordered;}
void setCreditCard(String creditCard) {this.creditCard=creditCard;}
String getId() {return id;}
String getName() {return name ;}
String getAddress() {return address;}
String getPassword() {
	return password;}
String getPhone() {
	return phone;}
String getCreditCard() {
	return creditCard;}
boolean getCreatedOrder() {
	return createdOrder;
}

//the return for the customer
public String toStringCustomer() {
	return id + "\n"+password + "\n"+name + "\n"+address + "\n"+phone + "\n"+creditCard+"\n"+createdOrder;}
//the retuern for the suppier
public String toStringSuppier() {
	return id+ "\n"+password;}
}
