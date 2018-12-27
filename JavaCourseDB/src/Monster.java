// JavaLesson07

/*
 * Basic class definition.
 * Public means this class can be used by other class.
 * Class names should begin with a capital letter.
 * A file can't contain two public classes. It can contain classes that are not public.
 * If you place class files in the same folder the java compiler will be able to find them.
 */

public class Monster {
	
	// Class variables or Fields
	// You declare constants with final
	
	public final String TOMBSTONE = "Here Lies a Dead Monster";
	
	// Private fields are not visible outside of the class
	private int health = 100;
	private int attack = 44;
	private int movement = 2;
	private int xPosition = 0;
	private int yPosition = 0;
	private boolean alive = true;
	
	// Public variables are visible outside of the class
	// You should have as few as possible public fields
	public String name = "Big Monster";
	
	// Class Methods
	// Accessor Methods are used to get and set the values of private fields
	
	public int getHealth() {
		return health;
	}
	
	public int getAttack() {
		return attack;
	}
	
	public int getMovement() {
		return movement;
	}
	
	/*
	 * You can create a multiple versions using the same method name
	 * Now setHealth can except an attack that contains decimals
	 * When overloading a method you can't just change the return type
	 * Focus on creating methods that except different parameters
	 */
	
	public void setHealth(int decreaseHealth) {
		health = health - decreaseHealth;
		if (health <= 0) {
			alive = false;
		}
	}
	
	public void setHealth(double decreaseHealth) {
		int intDecreaseHealth = (int) decreaseHealth;
		health = health - intDecreaseHealth;
		if (health <= 0) {
			alive = false;
		}
	}
	
	
	/*
	 * The Constructor
	 * Code that is executed when an object is created from this class definition
	 * The method name is the same as the class
	 * The constructor is only executed once per object
	 * The constructor can't return a value
	 */
	
	
	public Monster(int health, int attack, int movement) {
		this.health = health;
		this.attack = attack;
		this.movement = movement;
		/*
		 * If the attributes had the same names as the class health, attack, movement
		 * You could refer to the object fields by proceeding them with this
		 * this.health = health;
		 * this.attack = attack;
		 * this.movement = movement;
		 */
	}
	
	
	/*
	 * You can overload constructor like any other method
	 * The following constructor is the one provided by default if you don't create any other constructor
	 * Default constructor
	 */
	
	public Monster() {
		
	}
	
	/*
	 * You can use the this keyword to call other constructor
	 * 
	 * 
	 * public JavaLesson07(int newHealth) {
	 * 		health = newHealth;
	 * }
	 * 
	 * 
	 * public JavaLesson07(int newHealth, int newAttack) {
	 * 		this(newHealth); // Any calls to another constructor must occur on the first line
	 * 		attack = newAttack;
	 * }
	 * 
	 */
	
}