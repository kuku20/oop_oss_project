package oop_OSS;

public class CreateAccount {
String id,name,address,password,phone,creditCard;
CreateAccount(){};
CreateAccount(String id,String password){
	setId(id);
	setPassword(password);
}
CreateAccount(String id,String name, String address,String password ,String phone,String creditCard ){
	setId(id);
	setName(name);
	setAddress(address);
	setPassword(password);
	setPhone(phone);
	setCreditCard(creditCard);
};
void setId(String id) {
	this.id=id;
}
void setName(String name) {this.name=name;}
void setAddress(String address) {this.address=address;}
void setPassword(String password) {this.password=password;}
void setPhone(String phone) {this.password=phone;}
void setCreditCard(String creditCard) {this.creditCard=creditCard;}
String getId() {
	return id;}
String getName() {
	return name ;}
String getAddress() {
	return address;}
String getPassword() {
	return password;}
String getPhone() {
	return phone;}
String getCreditCard() {
	return creditCard;}

public String toString() {
	return name;}
}
