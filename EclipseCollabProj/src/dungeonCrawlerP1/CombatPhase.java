/**
 * @author - James Kent
 * Equivalent of CurrentAction but for combat
 * Can be extended for special combat phases (boss fight(s), etc.)
 */

package dungeonCrawlerP1;

import java.util.Random;

public class CombatPhase
{
	private int enemyDiff;	// Difficulty of enemy encounter
	private int enemyNum;	// Number of enemies (Max of 5)
	
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
		if (num > 5) enemyNum = 5;
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
		if (num > 5) enemyNum = 5;
		tracing = true;
		
		System.out.println("Tracing for combat enabled");
	}
	/**
	 * One parameter w/ option for tracing
	 */
	public CombatPhase(int range, boolean trce)
	{
		enemyDiff = range;
		enemyNum = 1;
		tracing = true;
		
		System.out.println("Tracing for combat enabled");
	}
	
	// METHODS
	
	/**
	 * Default command loop for handling combat
	 * @param pInput	Input from user
	 * @param g			Current game
	 * @param p			Player in combat
	 */
	public void initializeFight(String pInput, Game g, Player p)
	{
		Random enemyRand = new Random();
		int enemyType, count = 0;
		
		// Creates new enemies (as applicable)
		switch (enemyNum)
		{
			case 5:
				enemyType = enemyRand.nextInt(enemyDiff) + 1;
				Enemy enemy5 = new Enemy(enemyType);
				count++;
			case 4:
				enemyType = enemyRand.nextInt(enemyDiff) + 1;
				Enemy enemy4 = new Enemy(enemyType);
				count++;
			case 3:
				enemyType = enemyRand.nextInt(enemyDiff) + 1;
				Enemy enemy3 = new Enemy(enemyType);
				count++;
			case 2:
				enemyType = enemyRand.nextInt(enemyDiff) + 1;
				Enemy enemy2 = new Enemy(enemyType);
				count++;
			case 1:
				enemyType = enemyRand.nextInt(enemyDiff) + 1;
				Enemy enemy1 = new Enemy(enemyType);
				count++;
		}
		if (tracing) System.out.println(count + " enemies generated");
		
		pInput.toLowerCase();
		switch(pInput)
		{
			// Reacts to player input, should probably include input
			// menu at all times
		}
	}
}
