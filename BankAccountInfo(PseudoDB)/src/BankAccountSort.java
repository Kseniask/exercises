import javax.swing.JOptionPane;

public class BankAccountSort {
public static void main(String[] args) {
	BankAccount[] accounts = new BankAccount[10];
	int count= 0;
	String accHolder, accType,accNumStr,accBalStr ;
	int accNum;
	double accBal;
	String sortChoice;
	JOptionPane.showMessageDialog(null, "Enter 10 account records");
//ask for data till the array is full
	while(count<10) {
		boolean valid = true;
		//ask for account number, holder name,account balance and type
		accNumStr = JOptionPane.showInputDialog("Account " + (count+1)+ " number: ");
//		check if there is a letter
		for(int x = 0; x < accNumStr.length(); x++) {
			if(!Character.isDigit(accNumStr.charAt(x))) {
				valid = false;
			}
		}
//		if yes
		while(!valid) {
			accNumStr = JOptionPane.showInputDialog("Account number should be numberic. Enter another number for Account " + (count+1));
			for(int x = 0; x < accNumStr.length(); x++) {
				if(!Character.isDigit(accNumStr.charAt(x))) {
					valid = false;
				}
				else {
					valid=true;
				}
			}
		}
		accNum = Integer.parseInt(accNumStr);
		accHolder = JOptionPane.showInputDialog("Account " + (count+1)+ " holder name: ");
		
		accBalStr=JOptionPane.showInputDialog("Account " + (count+1)+ " balance: ");
		
		boolean balValid = true;
		int countChars=0;
		//count digits till '.'
		for(int y= 0;y<accBalStr.length(); y++) {
		if(accBalStr.charAt(y) != '.') {
			countChars++;
		}
		else {
			break;
		}
		}
		
		//check all character till dot if they are digits
		for(int i =0; i<countChars; i++) {
			if(!Character.isDigit(accBalStr.charAt(i))) {balValid = false;}
		}
			//if they are not ask one more time
		while(!balValid) {
			accBalStr=JOptionPane.showInputDialog("Account " + (count+1)+ " balance should be numberic. Enter another balance: ");
			for(int j =0; j<countChars; j++) {
				if(!Character.isDigit(accBalStr.charAt(j))) {balValid = false;}
				else {balValid = true;}
				}
		}
		//parse balance to double
		accBal = Double.parseDouble(accBalStr);
		//string in order to put \n
		String typeMessage = "Account \" + (count+1)+ \" type: \nb - for business\np -for private";
		accType =JOptionPane.showInputDialog(typeMessage);
		boolean validType = true;
		//validate the type input
		if(!accType.toLowerCase().startsWith("p") && !accType.toLowerCase().startsWith("b")) validType = false;
			while (!validType) {
				typeMessage = "Please enter either 'p' or 'b' \nb - for business\np -for private";
				accType =JOptionPane.showInputDialog(typeMessage);
				if(!accType.toLowerCase().startsWith("p") && !accType.toLowerCase().startsWith("b")) {
					validType = false;
			}
				else {
					validType= true;
				}
			}
		
//		create instance of class
		BankAccount ba = new BankAccount(accNum,accHolder,accBal, accType);
//		put object into array
		accounts[count] = ba;
//		increment count of accounts
		count++;
	}
	
	boolean sort = true;
	String ifSort;
//	while user wants to sort
	while (sort){
//		ask for type of sort
	sortChoice = JOptionPane.showInputDialog("How would you like to sort it? by (N)umber, (B)alance or (T)ype? (enter one letter)");
	//sort function
	sortAcc(accounts,sortChoice);
	//print out the result in console
	String text = "Sorted Data:\n";
	for(BankAccount acc : accounts) {
		text += acc.getAccNum() + "; " + acc.getAccBal() + "; " + acc.getAccHolder() + "; " + acc.getAccType() + "\n";
	}
	JOptionPane.showMessageDialog(null, text);
	//ask if want to sort with another option
	ifSort = JOptionPane.showInputDialog("Would you like to sort by other option? y/n");
	// if chose yes - ask to sort one more time
	if(ifSort.toLowerCase().compareTo("y") ==0) {
		sort = true;
	}
//	if no, end
	else if(ifSort.toLowerCase().compareTo("n") ==0){
		sort = false;
		JOptionPane.showMessageDialog(null, "Thank you!");
	}
//	if neither y nor n - show error
	else {
		while(ifSort.toLowerCase().compareTo("n") !=0 && ifSort.toLowerCase().compareTo("y") !=0) {
			ifSort = JOptionPane.showInputDialog("UNKNOWN COMMAND. Would you like to sort by other option? y/n");
	}
	}
	}
}
public static void sortAcc(BankAccount[] ba, String choice) {
	
	BankAccount temp;
	int compares = ba.length -1;
	for(int x =0; x<ba.length-1; x++) {
		for(int y=0; y<compares; y++) {
			//if chose number
			if(choice.toLowerCase().startsWith("n")) {
				if(ba[y].getAccNum() > ba[y+1].getAccNum()) {
					temp = ba[y];
					ba[y] = ba[y+1];
					ba[y+1] = temp;
				}
			}
			//if chose balance
			else if(choice.toLowerCase().startsWith("b")) {
				if(ba[y].getAccBal() > ba[y+1].getAccBal()) {
					temp = ba[y];
					ba[y] = ba[y+1];
					ba[y+1] = temp;
				}
			}
//			if chose type
			else if(choice.toLowerCase().startsWith("t")) {
				if((ba[y].getAccType()).compareTo(ba[y+1].getAccType()) > 0)  {
					temp = ba[y];
					ba[y] = ba[y+1];
					ba[y+1] = temp;
				}
			}
		}
		compares--;
	}
}
}

