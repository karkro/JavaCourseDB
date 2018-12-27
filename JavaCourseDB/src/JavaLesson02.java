// To import an external class you use import
// You can import whole libraries of classes like this import java.util.*;

import java.util.Scanner;

public class JavaLesson02 {
	
	/*
	 * Static means that only a class can call for this function to execute
	 * Creates a new scanner object name userInput
	 * You create the scanner object by calling new and passing the Scanner constructor
	 * the input stream to look at (System.in = keyboard input)
	 */
	
	static Scanner userInput = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		System.out.print("Your favorite number: "); // Same as println without a new line
		
		/*
		 * The if statement will only execute  the code that lies between {} if the value
		 * inside is true.
		 * userInput.hasNextDouble() returns true if the next value entered is a Double
		 * There are similar methods for the other data types
		 * hasNextInt() : Integer input
		 * hasNextFloat() : Float input
		 * There are other for Boolean, Byte, Long and Short
		 */
		
		if (userInput.hasNextInt()) {
			int numberEntered = userInput.nextInt();
			/*
			 * userInput.nextDouble() receives user input and stores it in the variable numberEntered
			 * You have to use a different method based on the type of input
			 * nextInt() : works for Integers
			 * nextDouble() : works for Doubles
			 * nextFloat() : works for Floats
			 * nextLine() : works for Strings
			 * There are others for Boolean, Byte, Long and Short
			 * If the user enters a value of the wrong type the program crashes
			 */
			
			System.out.println("You entered " + numberEntered);
			
			// Here I perform basic mathematics calculations
			int numEnteredPlus = numberEntered + numberEntered;
			System.out.println(numberEntered + " + " + numberEntered + " = " + numEnteredPlus);
			
			int numEnteredMinus2 = numberEntered - 2;
			System.out.println(numberEntered + " - 2 = " + numEnteredMinus2);
			
			int numEnteredTimesSelf = numberEntered * numberEntered;
			System.out.println(numberEntered + " * " + numberEntered + " = " + numEnteredTimesSelf);
			
			// Without the cast the value wouldn't consider fraction
			double numberEnteredDivide2 = (double) numberEntered / 2;
			System.out.println(numberEntered + " / 2 = " + numberEnteredDivide2);
			
			// % Modulus returns the remainder of a division
			int numEnteredRemainder = numberEntered % 2;
			System.out.println(numberEntered + " % 2 = " + numEnteredRemainder);
			
			// Shorthand way to add to 2 to a variable and assign the result to self
			numberEntered += 2;
			numberEntered -= 2;
			
			// Shorthand way to add 1 to a variable
			numberEntered++;
			
			// Shorthand way to minus 1 from a variable
			numberEntered--;
			
			// Returns the absolute value
			int numberEnteredABS = Math.abs(numberEntered);
			
			// Returns the larger of the two arguments (They must be the same type)
			int whichIsBigger = Math.max(numberEntered, 24);
			
			// Returns the smaller of the two arguments (They must be the same type)
			int whichIsSmaller = Math.min(numberEntered, 15);
			
			// Returns the square root argument
			double numSqrt = Math.sqrt(6.38);
			System.out.println(numSqrt);
			
			// Rounds the number provided up
			int numCeiling = (int) Math.ceil(numSqrt);
			System.out.println("Ceiling: " + numCeiling);
			
			//Rounds the number provided down
			int numFloor = (int) Math.floor(numSqrt);
			System.out.println("Floor: " + numFloor);
			
			// Rounds the number based on the fraction
			int numRound = (int) Math.round(numSqrt);
			System.out.println("Rounded: " + numRound);
			
			// Generates random number between 0 to 9
			int randomNumber = (int) (Math.random() * 10);
			System.out.println("A random number: " + randomNumber);
			
			// If the above condition is false, the code following else is executed
			
		} else {
			System.out.println("You must enter an Integer!!!");
		}
		
	}

}