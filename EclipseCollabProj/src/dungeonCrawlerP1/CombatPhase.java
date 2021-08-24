/**
 * @author - James Kent
 * Equivalent of CurrentAction but for combat
 * Can be extended for special combat phases (boss fight(s), etc.)
 */

package dungeonCrawlerP1;

import java.util.*;
import java.util.Random;

public class CombatPhase
{
	private int enemyDiff;			// Difficulty of enemy encounter
	private int enemyNum;			// Number of enemies
	private int maxEnemies = 10;	// Max number of enemies in normal encounter
	
	private boolean tracing = false;
	
	/**
	 * Two parameter constructor, sets enemy difficulty & number of enemies
	 * @param range		Max enemy # to generate enemy from
	 * @param num		Number of enemies to generate
	 * Also sets number of enemies to 5 if it exceeds the intended limit
	 */
	public CombatPhase(int range, int num)
	{
		enemyDiff = range;
		enemyNum = num;
		if (num > maxEnemies) enemyNum = maxEnemies;
	}
	/**
	 * One parameter constructor, sets enemy difficulty & defaults num to 1
	 * @param range		Max enemy # to generate enemy from
	 */
	public CombatPhase(int range)
	{
		enemyDiff = range;
		enemyNum = 1;
	}
	/**
	 * Two parameter w/ option for tracing
	 */
	public CombatPhase(int range, int num, boolean trce)
	{
		enemyDiff = range;
		enemyNum = num;
		if (num > maxEnemies) enemyNum = maxEnemies;
		tracing = trce;
		
		System.out.println("Tracing for combat enabled");
	}
	/**
	 * One parameter w/ option for tracing
	 */
	public CombatPhase(int range, boolean trce)
	{
		enemyDiff = range;
		enemyNum = 1;
		tracing = trce;
		
		System.out.println("Tracing for combat enabled");
	}
	
	// METHODS
	
	/**
	 * Default command loop for handling combat
	 * @param pInput	Input from user
	 * @param g			Current game
	 * @param p			Player in combat
	 */
	public void initializeFight(Scanner kbd, Game g, Player p)
	{
		Random enemyRand = new Random();
		
		// Creates array for enemies
		Enemy enemyArr[] = new Enemy[maxEnemies + 1];
		// First cell isn't used (no enemy 0)
		enemyArr[1] = null;
		
		int enemyType, count = 0;
		
		// Creates new enemies (as applicable)
		for (int i = 1; i <= enemyNum; i++)
		{
			enemyType = enemyRand.nextInt(enemyDiff) + 1;
			enemyArr[i] = new Enemy(enemyType);
		}
		// Makes remaining cells null
		for (int i = enemyNum + 1; i <= maxEnemies; i++)
		{
			enemyArr[i] = null;
		}
		
		if (tracing) System.out.println(count + " enemies generated");
		
		boolean combat = true;	// Boolean for maintaining combat loop
		String pInput;			// Player input
		
		// Combat loop w/in CombatPhase to maintain local variables (enemies) / keep main clean
		while (combat)
		{	
			pInput = kbd.nextLine();
			pInput.toLowerCase();
			
			switch(pInput)
			{
				// Reacts to player input, should probably include
				// input menu at all times
			}
		}
	}
}
