import java.io.*;

// A character stream is just a series of characters
// Important information is normally separated by a comma, space or tab

public class JavaLesson32 {

	public static void main(String[] args) {
		
		// Create an array of type Customer
		Customer[] customers = getCustomer();
		
		// PrintWriter is used to write characters to a file in this situation
		PrintWriter custOutput = createFile("/home/karkro/git2/JavaLesson32/customers.txt");
		
		// Enhanced for loop for array
		// Cycles through all of the people in the customers array
		for (Customer person : customers) {
			createCustomers(person, custOutput);
		}
		
		// Closes the connection to the PrintWriter
		custOutput.close();
		
		getFileInfo();

	}
	
	// Class that defines all the fields for my customers
	private static class Customer {
		
		public String firstName, lastName;
		public int custAge;
		
		// constructor that's called when a customer is made
		public Customer(String firstName, String lastName, int custAge) {
			
			this.firstName = firstName;
			this.lastName = lastName;
			this.custAge = custAge;
		}
	}
	
	// Creates an array of Customer Objects
	private static Customer[] getCustomer() {
		
		Customer[] customers = new Customer[5];
		
		customers[0] = new Customer("Johny", "Bravo", 22);
		customers[1] = new Customer("Sylwester", "Stallone", 55);
		customers[2] = new Customer("Edward", "Norton", 44);
		customers[3] = new Customer("Brad", "Pitt", 33);
		customers[4] = new Customer("Ace", "Ventura", 26);
		
		return customers;
	}
	
	// Create the file and the PrintWriter that will write to the file
	private static PrintWriter createFile(String fileName) {
		
		try {
			
			// Creates a File object that allows you to work with files on the hardrive
			File listOfNames = new File(fileName);
			
			// FileWriter is used to write stream of characters to a file
			// BufferedWriter gathers a bunch of characters and then writes them all at one time (speeds up program)
			// PrintWriter is used to write characters to the console, file
			PrintWriter infoToWrite = new PrintWriter(new BufferedWriter(new FileWriter(listOfNames)));
			return infoToWrite;
		
		// You have to catch this when you call FileWriter	
		} catch (IOException e) {
			
			System.out.println("An I/O Error Occured");
			
			// Closes the program
			System.exit(0);
		}
		
		return null;
	}
	
	// Create a string with the customer info and write it to the file
	private static void createCustomers(Customer customer, PrintWriter custOutput) {
		
		// Create the String that contains the customer info
		String custInfo = customer.firstName + " " + customer.lastName + " ";
		custInfo += Integer.toString(customer.custAge);
		
		// Writes the String to the file
		custOutput.println(custInfo);
	}
	
	
	// Read info from the file and write it to the screen
	private static void getFileInfo() {
		
		System.out.println("Info Written to File\n");
		
		// Open a new connection to the file
		File listOfNames = new File("/home/karkro/git2/JavaLesson32/customers.txt");
		
		try {
			
			// FileReader reads character files
			// BufferedReader reads as many characters as possible
			
			BufferedReader getInfo = new BufferedReader(new FileReader(listOfNames));
			
			// Reads a whole line from the file and saves it in a String
			String custInfo = getInfo.readLine();
			
			// readline returns null when the end of the file is reached
			while (custInfo != null) {
				
				// System.out.println(custInfo);
				// Break lines into pieces
				String[] indivCustData = custInfo.split(" ");
				
				// Convert the String into an integer with parseInt
				int custAge = Integer.parseInt(indivCustData[2]);
				System.out.print("Customer " + indivCustData[0] + " is " + custAge + " years old." + "\n");
				custInfo = getInfo.readLine();
			}
		
		// Can be thrown by FileReader	
		} catch (FileNotFoundException e) {
			
			System.out.println("Couldn't Find File");
			System.exit(0);
			
		} catch (IOException e) {
			
			System.out.println("An I/O Error Occured2");
			System.exit(0);
			
		}
		
	}
}
