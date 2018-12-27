/*
 * Basic class definition.
 * Public means this class can be used by other class.
 * Class names should begin with a capital letter.
 * A file can't contain two public classes. It can contain classes that are not public.
 * If you place class files in the same folder the java compiler will be able to find them.
 */


public class JavaLesson07 {
	
	public static void main(String[] args) {
		
		// You create an object using the blueprint of this class as follows
		// className objectName = new className();
		
		Monster Frank = new Monster();
		
		// Since the object name is public you can change it directly
		
		Frank.name = "Frank";
		
		// You retrieve class field values by listing the objectName.fieldName
		// You execute class methods by listing objectName.methodName()
		
		System.out.println(Frank.name + " has an attack value of " + Frank.getAttack());
	}
}