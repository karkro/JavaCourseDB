/*
 * The LinkedList class is a collection based on a Linked List instead of an array.
 * They are particularly good when you expect to perform many additions and deletions
 * with a collection. When using a linked list you don't have to move items around
 * when you add or delete an item. They aren't particularly suited to providing access
 * based off of index searches like an array though. Each object in a linked list
 * contains a pointer to the objects that proceed and follow it.
 * When you change an ArrayList a new array is created by it.
 */

import java.util.Arrays;
import java.util.LinkedList; // LinkedList library methods

public class JavaLesson12 {

	public static void main(String[] args) {
		
		// Create a LinkedList object
		LinkedList linkedListOne = new LinkedList();
		
		// Create a LinkedList that contains Strings
		LinkedList<String> names = new LinkedList<String>();
		
		// You use add to add items to the linked list
		names.add("Derek Banas");
		names.add("Jaga");
		names.add("Drac");
		
		// addLast places the object last in the list
		names.addLast("Tutorial");
		System.out.println(names);
		
		// addFirst places the object first in the list
		names.addFirst("Joshua");
		
		// You can define what position to place to the object in
		names.add(0, "Peter Griffin");
		System.out.println(names);
		
		// You can replace a value in an index with set()
		names.set(4, "Heisenberg");
		System.out.println(names);
		
		// You remove items either by providing the index, or the value
		names.remove(1);
		names.remove("Tutorial");
		System.out.println(names);
		
		/*
		 * removeFirst() removes the first element
		 * removeLast() removes the last element
		 * removeFirstOccuredObject() removes the first object that matches what you passed
		 */
		
		// You can use the enhanced for the print them out
		for (String name : names) {
			System.out.println(name);
		}
		System.out.println();
		
		// You van retrieve values with get()
		System.out.println("First index: " + names.get(0));
		
		// Retrieve the first value with getFirst()
		System.out.println("First value: " + names.getFirst());
		
		// Retrieve the last value with getLast()
		System.out.println("Last value: " + names.getLast());
		
		LinkedList<String> namesCopy = new LinkedList<String>(names);
		
		// When you print out the LinkedList itself the toString method is called
		System.out.println("nameCopy: " + namesCopy);
		
		// You can check if an object is in the list with contains()
		if (names.contains("Jaga")) {
			System.out.println("Jaga is here!");
		}
		
		// You can check if everything in one list is in another
		if (names.containsAll(namesCopy)) {
			System.out.println("Collection are the same!");
		}
		
		// Return the index for an object with indexOf
		System.out.println("Index of Jaga is " + names.indexOf("Jaga"));
		
		// Check if a list is empty with isEmpty()
		System.out.println("List empty: " + names.isEmpty());
		
		// Get the number of items in the list with size()
		System.out.println("How many values?: " + names.size());
		
		// peek() retrieves the first element, but doesn't throw an error if there is no element retrieve
		System.out.println("Look first element without error: " + names.peek());
		
		// poll() returns the first value and deletes it from the list
		System.out.println("Show and remove first element: " + names.poll());
		System.out.println(names);
		
		// pollLast() returns the last value and deletes it from the list
		System.out.println("Show and remove last element: " + names.pollLast());
		System.out.println(names);
		
		// push() puts a new element on the front of the list
		System.out.println();
		
		names.push("Peter Griffin");
		System.out.println(names);
		
		// pop() removes an element on the front of the list
		System.out.println(names.pop());
		System.out.println(names);
		
		// Create a new array to hold values from the LinkedList
		Object[] namesArray = new Object[4];
		
		// toArray converts the LinkedList into an array of objects
		namesArray = names.toArray();
		// System.out.println(namesArray); - result [Ljava.lang.Object;@15db9742
		
		// toString converts item in the array into a String
		System.out.println("Item in array converted to String: " + Arrays.toString(namesArray));
		
		// clear() deletes everything in the linked list
		names.clear();
		System.out.println("LinkedList \"names\" is empty?: " + names.isEmpty());
	}
}