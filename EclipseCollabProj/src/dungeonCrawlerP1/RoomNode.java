/**
 * @author - James Kent
 * Used to represent the different rooms/nodes comprising the map
 */

package dungeonCrawlerP1;
import java.util.*;
import java.io.*;

public class RoomNode
{
	int ID;
	int RoomType;
	boolean enemies;
	int importantThing;
	int N, E, S, W;
	
	/**
	 * Constructor for generation of map: sets N-W as 0 so the room connecting a given room
	 * to room 1 (if applicable) will need to be manually set after the constructor is run
	 * @param id		ID assigned in map generation
	 * @param rt		RoomType assigned in map generation
	 * @param enms		boolean value for enemies assigned in map generation
	 * @param imprt		importantThings value/id assigned in map generation
	 */
	public RoomNode(int id, int rt, boolean enms, int imprt)
	{
		ID = id;
		RoomType = rt;
		enemies = enms;
		importantThing = imprt;
	}
	
	// METHODS
	
	/**
	 * Uses the RoomType variable assigned to it to pull the correct description from a text
	 * document (doesn't print on its own)
	 * @return String containing appropriate description
	 */
	public String checkType()
	{
		Scanner inStream = null;
		try
		{
			inStream = new Scanner(new File("roomtypes.txt"));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Err: roomtypes.txt not found");
			System.exit(1);
		}
		// Checks number at beginning of line, then chooses whether to take next line
		int chk = RoomType;
		String desc = "Err: no description found";
		do
		{
			chk = inStream.nextInt();
			desc = inStream.nextLine();
		} while (chk != RoomType);
		
		return desc;
	}
}
