import java.util.Arrays;
import org.apache.commons.lang3.ArrayUtils;

/*
 * Basic class definition
 * Public means this class can be used by other classes
 * Class names should begin with a capital letter
 * A file can't contain two public classes. It can contain classes that are not public
 * If you place class files in the same folder the java compiler will be able to find them
 */

/*
 * The Goal of this tutorial is to make a game like this
 *  ------------------------------
 *  |*||*||*||*||*||*||*||*||*||*|
 *  |*||*||*||*||*||*||*||*||*||*|
 *  |*||*||*||*||*||*||*||*||*||*|
 *  |*||*||M||*||*||*||*||*||*||*|
 *  |*||*||*||*||*||F||*||*||*||*|
 *  |*||*||*||*||*||*||*||*||*||*|
 *  |*||*||*||*||*||*||*||*||*||*|
 *  |*||*||P||*||*||*||*||D||*||*|
 *  |*||*||*||*||*||*||*||*||*||*|
 *  |*||*||*||*||*||*||*||*||*||*|
 *  ------------------------------
 *  
 *  [9, 9]
 *  
 */


public class JavaLesson08 {

	public static void main(String[] args) {
		
		MonsterTwo.buildBattleBoard();
		
		char[][] tempBattleBoard = new char[10][10];
		
		// ObjectName[] arrayName = new objectName[4];
		MonsterTwo[] Monsters = new MonsterTwo[4];
		
		// MonsterTwo(int health, int attack, int movement, String name)
		Monsters[0] = new MonsterTwo(60, 20, 1, "Drako");
		Monsters[1] = new MonsterTwo(88, 12, 2, "Heisenberg");
		Monsters[2] = new MonsterTwo(56, 78, 1, "Fredie");
		Monsters[3] = new MonsterTwo(77, 82, 3, "Razen");
		
		MonsterTwo.redrawBoard();
	}
}
