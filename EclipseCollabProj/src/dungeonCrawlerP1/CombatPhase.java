/**
 * @author - James Kent
 * Equivalent of CurrentAction but for combat
 * Can be extended for special combat phases (boss fight(s), etc.)
 */

package dungeonCrawlerP1;

public class CombatPhase
{
	private int enemyType;
	
	/**
	 * Default constructor, takes one parameter
	 * @param x		Integer representing enemy type
	 */
	public CombatPhase(int x)
	{
		enemyType = x;
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
		switch(enemyType)
		{
			// Use to construct objects of type Enemy
			// Stats can be pulled from txt (a la roomtypes.txt)
		}
		
		pInput.toLowerCase();
		switch(pInput)
		{
			// Reacts to player input, should probably include input
			// menu at all times
		}
	}
}
