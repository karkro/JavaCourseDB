import java.io.*;

// A binary stream is a series of data type values
// To read and write to them you use different methods based on the type of data that you are using

public class JavaLesson33 {

	public static void main(String[] args) {
		
		// Create an array of type Customer
		Customer[] customers = getCustomers();
		
		// A DataInputStream allows you to print primitive data types to a file
		DataOutputStream custOutput = createFile("/home/karkro/git2/JavaLesson33/customers.dat");
		
		// Enhanced for loop for arrays
		// Cycles through all of the people in the customers array
		for (Customer person: customers) {
			createCustomers(person, custOutput);
		}
		
		// Closes the connection to the DataInputStream		
		try {
			
			custOutput.close();
			
		} catch (IOException e) {
			
			System.out.println("I/O Error");
			
			// Closes the program
			System.exit(0);
		}
		
		getFileInfo();
		
	}
	
	
	// Class that defines all the fields for my customers
	private static class Customer {
		
		public String custName;
		public int custAge;
		public double custDebt;
		public boolean oweMoney;
		public char custSex;
		
		// Constructor that's called when a customer is made
		public Customer(String custName, int custAge, double custDebt, boolean oweMoney, char custSex) {
			
			this.custName = custName; // String
			this.custAge = custAge; // Integer
			this.custDebt = custDebt; // Double
			this.oweMoney = oweMoney; // Boolean
			this.custSex = custSex;  // Character
			
		}
		
	}
	
	
	// Creates an array of Customer Objects
	private static Customer[] getCustomers() {
		
		Customer[] customers = new Customer[5];
		
		customers[0] = new Customer("John Smith", 21, 24.15, true, 'M');
		customers[1] = new Customer("Johny Bravo", 23, 21.75, true, 'M');
		customers[2] = new Customer("Mel Gibson", 55, 44.15, true, 'M');
		customers[3] = new Customer("Edward Norton", 45, 28.55, true, 'M');
		customers[4] = new Customer("Bruce Lee", 33, 44.35, true, 'M');
		
		return customers;
		
	}
	
	
	// Create the file and the DataOutputStream that will write to the file
	private static DataOutputStream createFile(String fileName) {
		
		try {
			
			// Creates a File Object that allows you to work with files on the hard drive.
			// There is no difference between file for character or binary stream writing, or reading.
			File listOfNames = new File(fileName);
			
			// FileOutputStream is used to write streams of data to a file. You define whether a new file
			// is created versus appended to based on if you add a boolean to the FileOutputStream
			// FileOutputStream(file, true) : Appends to the file
			// FileOutputStream(file, false) : Creates a new file
			
			// BufferedOutputStream gathers all the data and then writes it all at one time(Speeds up the program)
			// DataOutputStream is used to write primitive data to the file
			DataOutputStream infoToWrite = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(listOfNames)));
			
			return infoToWrite;
		
		// You have to catch this when you call FileWriter	
		} catch (IOException e) {
			
			System.out.println("I/O Error");
			
			// Closes the program
			System.exit(0);
		}
		
		return null;
	} // END OF createFile
	
	
	// Create a string with the customer info and write it to the file
	private static void createCustomers(Customer customer, DataOutputStream custOutput) {
		
		try {
			
			// Write primitive data to the file
			
			// Writes a String in UTF format
			custOutput.writeUTF(customer.custName);
			
			// Writes an Integer
			custOutput.writeInt(customer.custAge);
			
			// Writes a Double
			custOutput.writeDouble(customer.custDebt);
			
			// Writes a Boolean
			custOutput.writeBoolean(customer.oweMoney);
			
			// Writes a Character
			custOutput.writeChar(customer.custSex);
			
			// You also have writeByte, writeFloat, writeLong and writeShort
			
		} catch (IOException e) {
			
			System.out.println("I/O Error");
			System.exit(0);
		}
	}
	
	
	// Read info from the file and write it to the screen
	private static void getFileInfo() {
		System.out.println("Info written to File\n");
		
		// Open a new connection to the file
		File listOfNames = new File("/home/karkro/git2/JavaLesson33/customers.dat");
		
		boolean eof = false;
		
		try {
			
			// A DataInputStream object has the methods for reading the data
			// The BufferedInputStream gathers the data in blocks
			// FileInputStream gets data from the file
			DataInputStream getInfo = new DataInputStream(new BufferedInputStream(new FileInputStream(listOfNames)));
			
			// Using a while loop that pulls data until EOFException is thrown			
			while (!eof) {
				
				// You have to read data in the exact order it was put in the file
				String custName = getInfo.readUTF();
				int custAge = getInfo.readInt();
				double custDebt = getInfo.readDouble();
				boolean oweMoney = getInfo.readBoolean();
				char custSex = getInfo.readChar();
				
				System.out.println(custName);
				System.out.println(custAge);
				System.out.println(custDebt);
				System.out.println(oweMoney);
				System.out.println(custSex);
				
			} // END OF TRY
			
		} catch (EOFException e) {
			
			eof = true;
		
		// Can be thrown by FileInputStream	
		} catch (FileNotFoundException e) {
			
			System.out.println("No file");
			System.exit(0);
			
		} catch (IOException e) {
			System.out.println("I/O Error");
			System.exit(0);
		}
		
	
	}
	
	
} 
