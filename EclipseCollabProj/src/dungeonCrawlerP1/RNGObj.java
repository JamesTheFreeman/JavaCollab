/**
 * @author - James Kent
 * Class to enable the implementation of RNG in a multitude of situations
 */

package dungeonCrawlerP1;
import java.util.Random;

public class RNGObj
{
	private Random rand;
	private int randInt;
	
	/**
	 * Default constructor for RNG
	 */
	public RNGObj()
	{
		rand = new Random();
	}
	
	// METHODS
	
	/**
	 * Method for running standard check on RNG whenever needed
	 * @param g		Game currently in session
	 * @param p		Player of current user
	 */
	public void runStandardRNG(Game g, Player p)
	{
		randInt = rand.nextInt(100) + 1; // Generates random int 1-100
		
		if (randInt < 11 && randInt > 0) unclear(g); // 10% chance of (random) room being uncleared (1-10)
		/* Regarding unclear() -- we could allow this method to attempt to unclear rooms that aren't */
		/* clear anyway, to allow the number of cleared rooms to affect how often this happens       */
		
		if (randInt < 16 && randInt > 10) ambush(g, p); // 5% chance of being ambushed (11-15)
		
		if (randInt == 100) // 1% chance of useless message REMOVE LATER (100)
			System.out.println("\nYou're feeling particularly lucky for some reason.");
	}
	/**
	 * When called, sets a random room in Game g to uncleared
	 * @param g			The game containing the rooms to be affected
	 */
	public void unclear(Game g)
	{
		Random randClear = new Random();
		int randClr = randClear.nextInt(g.numOfRooms) + 1;
		// DOES NOT check if it's already uncleared
		g.roomArr[randClr].clear = false;
	}
	
	public void ambush(Game g, Player p)
	{
		// Add once there's combat
		System.out.println("You would've just been ambushed, if that was in the game.");
	}
}
