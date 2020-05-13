public class BankAccount {
private int accNum;
private String accHolder;
private double accBal;
private String accType;

public BankAccount(int accNum, String accHolder, double accBal, String accType) {
	this.accNum = accNum;
	this.accHolder = accHolder;
	this.accBal = accBal;
	if(accType.toLowerCase().startsWith("p")) {
		this.accType = "Private";
	}
	else if(accType.toLowerCase().startsWith("b")) {
		this.accType = "Business";
	}
	
}

public void setAccNum(int accNum) {
	this.accNum = accNum;
}
public int getAccNum() {
	return accNum;
}
public void setAccHold(String accHolder) {
	this.accHolder = accHolder;
}
public String getAccHolder() {
	return accHolder;
}
public void setAccBal(double accBal) {
	this.accBal = accBal;
}
public double getAccBal() {
	return accBal;
}
public void setAccType(String accType) {
	this.accType = accType;
}
public String getAccType() {
	return accType;
}

//if delete. Move all data next in the previous gap
//ask user how to sort
}
