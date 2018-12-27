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


public class JavaLesson10 {

	public static void main(String[] args) {
		MonsterThree.buildBattleBoard();
		// char[][] tempBattleBoard = new char[10][10];
		// objectName[] ArrayName = new ObjectName[4];
		
		MonsterThree[] Monsters = new MonsterThree[4];
		
		// MonsterThree(int health, int attack, int movement, String name);
		
		Monsters[0] = new MonsterThree(60, 20, 1, "Drako");
		Monsters[1] = new MonsterThree(88, 12, 2, "Heisenberg");
		Monsters[2] = new MonsterThree(56, 78, 1, "Fredie");
		Monsters[3] = new MonsterThree(77, 82, 3, "Razen");
		MonsterThree.redrawBoard();
		
		for (MonsterThree m : Monsters) {
			if (m.getAlive()) {
				int arrayItemIndex = ArrayUtils.indexOf(Monsters, m);
				m.moveMonster(Monsters, arrayItemIndex);
			}
		}
		
		MonsterThree.redrawBoard();
	}
}