import javax.swing.JOptionPane;

public class BankAccountDatabase {
public static void main(String[] args) {
	BankAccount[] accounts = new BankAccount[30];
	int count[] = {0};
	String userChoice;
	//ask for choice
	userChoice = AccInfo(accounts,count);
	
	//while they didn't chose quit ...
	while(!userChoice.toLowerCase().startsWith("q")) {
		//if they want to add..
		if(userChoice.toLowerCase().startsWith("a")) {
			//check if DB is not full
			if(count[0] != 30) {
				//ask for details
				userChoice = AccInfo(accounts,count);
				
			}
			//if DB is full
			else {
				JOptionPane.showMessageDialog(null, "Sorry, the database if full");
				userChoice = JOptionPane.showInputDialog("Do you want to (D)elete or (C)hange a record or (Q)uit >>");
			}
		}
		//if user chosen to delete record
		else if(userChoice.toLowerCase().startsWith("d")) {
			if(count[0] !=0) {
			//run delete function
			userChoice = DeleteInfo(accounts, count);
			//decrement count since the record is deleted
			}
			//if DB is empty
			else {
				JOptionPane.showMessageDialog(null, "Sorry, the database if empty");
				userChoice = JOptionPane.showInputDialog("Do you want to (A)dd, (D)elete, or (C)hange a record or (Q)uit >>");
			}
		}
		else if(userChoice.toLowerCase().startsWith("c")) {
			if(count[0] != 0) {
			userChoice = changeAcc(accounts, count);
			}
			else {
				userChoice = JOptionPane.showInputDialog("Sorry, The DB is empty.Do you want to (A)dd a record or (Q)uit >>");
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "Sorry, unknown command");
			userChoice = JOptionPane.showInputDialog("Do you want to (A)dd, (D)elete, or (C)hange a record or (Q)uit >>");
		}
	}
	if(userChoice.toLowerCase().startsWith("q")) {
		System.exit(0);
	}
	
}
//ask for info
public static String AccInfo(BankAccount[] accounts, int[] count) {
	
	boolean unique;
	int accNum;
	String accHolder, accType, userChoice, accBalStr, accNumStr;
	double accBal;
	

	boolean validNum = true;
	//ask for account number, holder name,account balance and type
	accNumStr = JOptionPane.showInputDialog("Account " + (count[0]+1)+ " number: ");
//	check if there is a letter
	for(int x = 0; x < accNumStr.length(); x++) {
		if(!Character.isDigit(accNumStr.charAt(x))) {
			validNum = false;
		}
	}
	if(accNumStr.isEmpty()) {validNum = false;}
//	if yes
	while(!validNum) {
		accNumStr = JOptionPane.showInputDialog("Account number should be numberic. Enter another number for Account " + (count[0]+1));
		for(int x = 0; x < accNumStr.length(); x++) {
			if(!Character.isDigit(accNumStr.charAt(x))) {
				validNum = false;
			}
			else {
				validNum=true;
			}
		}
	}
	accNum = Integer.parseInt(accNumStr);
	
	//check if the account number does not exist
	unique = CheckAccNum(accounts, accNum, count);
	//while it exists ask for another account number
	while(!unique) {
		accNum = Integer.parseInt(JOptionPane.showInputDialog("Sorry, this account already exists. Enter another number for account " + (count[0]+1)+ " >>"));
		unique = CheckAccNum(accounts, accNum,count);
	}
	//ask rest of info
	accHolder = JOptionPane.showInputDialog("Account " + (count[0]+1)+ " holder name: ");
	accBalStr=JOptionPane.showInputDialog("Account " + (count[0]+1)+ " balance: ");
	
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
		accBalStr=JOptionPane.showInputDialog("Account balance should be numberic. Enter another balance: ");
		countChars=0;
		//count digits till '.'
		for(int y= 0;y<accBalStr.length(); y++) {
		if(accBalStr.charAt(y) != '.') {
			countChars++;
		}
		else {
			break;
		}
		}

		for(int j =0; j<countChars; j++) {
			if(!Character.isDigit(accBalStr.charAt(j))) {balValid = false;}
			else {balValid = true;}
			}
	}
	//parse balance to double
	accBal = Double.parseDouble(accBalStr);
	
	String typeMessage = "Account" + (count[0]+1)+ " type: \nb - for business\np -for private";
	accType =JOptionPane.showInputDialog(typeMessage);
	boolean validType = true;
	if(!accType.toLowerCase().startsWith("b") && !accType.toLowerCase().startsWith("p")) {
		validType = false;
	}
	while(!validType) {
		typeMessage = "please enter either 'B' or 'P': \nb - for business\np -for private";
		accType =JOptionPane.showInputDialog(typeMessage);
		if(!accType.toLowerCase().startsWith("b") && !accType.toLowerCase().startsWith("p")) {
			validType = false;
		}
		else {
			validType =true;
		}
	}
	//create instance of BankAccount class
	BankAccount ba = new BankAccount(accNum,accHolder,accBal, accType);
	//add this instance to the array
	accounts[count[0]] = ba;
	//ask for next option
	count[0]++;
	sortDB(accounts, count);
	userChoice = JOptionPane.showInputDialog("Do you want to (A)dd, (D)elete, or (C)hange a record or (Q)uit >>");
	
	return userChoice;
	
}
//checking number
public static boolean CheckAccNum(BankAccount[] accounts, int number, int[] count) {
	
	int thereIsNum=0;
	//check every account in DB
	if(count[0] != 0) {
	for(int x =0; x<count[0]; x++) {
		if(accounts[x].getAccNum() == number) {
			//increment the occurance
			thereIsNum++;
		}
	}
	//if number exist
	if(thereIsNum != 0) {
		return false;
	}
	else {
		return true;
	}
	
	}
	else {
		return true;
	}
}

//Delete function
public static String DeleteInfo(BankAccount[] accounts, int[] count) {
	
	String userChoice;
	int accNum;
	boolean unique;
	//ask for account number
	accNum = Integer.parseInt(JOptionPane.showInputDialog("Enter account number to delete >>"));
	//check if it is in DB
	unique = CheckAccNum(accounts, accNum,count);
	
	if(!unique) {
		
	for(int x = 0; x<count[0]-1; x++) {
		if(accounts[x].getAccNum() == accNum){
			for(int y =x; y< count[0]-1;y++) {
				accounts[y] = accounts[y+1];
			}
		}
	}
	count[0]--;
	}
	
	sortDB(accounts, count);
	userChoice = JOptionPane.showInputDialog("Do you want to (A)dd, (D)elete, or (C)hange a record or (Q)uit >>");
	return userChoice;
}

//Sorting function
public static void sortDB(BankAccount[] accounts, int[] count) {
	BankAccount temp;
	int calcTimes = count[0]-1;
	String text = "Current Database: \n";
	//bubble sort
	for(int x = 0; x< count[0]-1; x++) {
		for(int y=0; y< calcTimes; y++) {
			if(accounts[y].getAccNum() > accounts[y+1].getAccNum()) {
			temp = accounts[y];
			accounts[y] = accounts[y+1];
			accounts[y+1] = temp;
			}
		}
		calcTimes--;
	}
	//retrieve text from all accounts
	for(int j = 0; j< count[0]; j++){
		text += accounts[j].getAccNum() + "\t| " + accounts[j].getAccBal() + "\t| " + accounts[j].getAccHolder() + "\t| " + accounts[j].getAccType() + "\n";
	}
	//show current database
	JOptionPane.showMessageDialog(null, text);
}

//Changing function
public static String changeAcc(BankAccount[] accounts, int[] count) {
	
	String changes="", changeOpt, userChoice;
	int accNum;
	boolean unique;
	//ask for acc number to change
	accNum = Integer.parseInt(JOptionPane.showInputDialog("Enter account  number to change: "));
	//check if number is in DB
	unique = CheckAccNum(accounts, accNum, count);
	
	
	//if not unique ask other options
	if(!unique) {
		//ask option		
		changeOpt = JOptionPane.showInputDialog("You want to change (N)ame or account (T)ype?");
		//if it is unknown ask one more time
		while(!changeOpt.toLowerCase().startsWith("n") && !changeOpt.toLowerCase().startsWith("t")) {
			changeOpt = JOptionPane.showInputDialog("Sorry. Unknown command. You want to change (N)ame or account (T)ype?");
		}
		//if choice equals t ask which Account type customer wants
			if(changeOpt.toLowerCase().startsWith("t")) {
				changes = JOptionPane.showInputDialog("You want to change it to (P)ersonal or (B)usines?");
				//if user chose neither "P" nor "B"
				while(!changes.toLowerCase().startsWith("p") &&!changes.toLowerCase().startsWith("b")) {
					changes = JOptionPane.showInputDialog("Sorry. Unknown type. You want to change it to (P)ersonal or (B)usines?");
				}
				//find the appropriate account number and change type
					if(changes.toLowerCase().startsWith("p")) {
						changes = "Personal";
						
					}
					else if(changes.toLowerCase().startsWith("b")) {
						changes = "Business";
					}
					
				for(int y = 0; y< count[0]; y++) {
					if(accounts[y].getAccNum() == accNum) {
						accounts[y].setAccType(changes);
						
					}
				}			
				sortDB(accounts, count);
			}
			//if choice equals n
			else if(changeOpt.toLowerCase().startsWith("n")) {
				changes = JOptionPane.showInputDialog("What name you want to change it to?");
				for(int y = 0; y< count[0]; y++) {
					if(accounts[y].getAccNum() == accNum) {
						accounts[y].setAccHold(changes);
					}
				}
				sortDB(accounts, count);
			}

		
	} //if(valid)closes	
	else {
	userChoice = JOptionPane.showInputDialog("Sorry, this account number doesn't exist. Do you want to (A)dd, (D)elete, or (C)hange another record or (Q)uit >>");
	}
	
	userChoice = JOptionPane.showInputDialog("Do you want to (A)dd, (D)elete, or (C)hange a record or (Q)uit >>");
	return userChoice;
}//function closes
} //class closes
