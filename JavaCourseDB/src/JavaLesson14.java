// Animal, Cat


/*
 * Polymorphism, Inheritance, Protected, Final, InstanceOf
 */


public class JavaLesson14 {

	public static void main(String[] args) {
		
		// I create an Animal object named genericAnimal
		Animal genericAnimal = new Animal();
		System.out.println(genericAnimal.getName());
		System.out.println(genericAnimal.favFood);
		
		// I create a Cat class
		Cat morris = new Cat("Morris", "Tuna", "Rubber Mouse");
		
		// Print out the name, favFood and favToy
		System.out.println();
		System.out.println(morris.getName());
		System.out.println(morris.favFood);
		System.out.println(morris.favToy);
		
		// You can also create classes based on the super class
		Animal teddy = new Cat("Teddy", "Salmon", "Ball");
		
		// You pass objects like any other fields
		acceptAnimal(teddy);
		
	}
	
	
	public static void acceptAnimal(Animal randAnimal) {
		
		// Gets the name and favFood for the Animal passed
		System.out.println();
		System.out.println(randAnimal.getName());
		System.out.println(randAnimal.favFood);
		
		/*
		 * This is Polymorphism
		 * The interpreter automatically figures out what type of Animal it's dealing with and checks
		 * to make sure if methods were overwritten that they are called instead
		 */
		randAnimal.walkAround();
		
		/*
		 * The interpreter won't find anything that doesn't oryginally exist in the Animal class however
		 * System.out.println(randAnimal.favToy); throws an ERROR
		 * 
		 * If you want to access to fields or methods only found in the Cat class
		 * you have to cast the object to that specific class first                  // RZUTOWANIE!
		 */
		
		Cat tempCat = (Cat) randAnimal;   // IMPORTANT! CAST! RZUTOWANIE!
		
		System.out.println(tempCat.favToy);
		
		// You could also cast the object directly like this
		System.out.println(((Cat) randAnimal).favToy);
		
		// You can use instanceOf to check what type object you have. This result is a positive for Animal and for Cat 
		if (randAnimal instanceof Cat) {
			System.out.println(randAnimal.getName() + " is a Cat!");
		}
		
	}
	
}