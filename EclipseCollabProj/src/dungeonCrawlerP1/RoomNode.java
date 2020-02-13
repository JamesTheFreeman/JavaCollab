/**
 * @author - Jack
 * Used to represent the different rooms/nodes comprising the map
 */

package dungeonCrawlerP1;
import java.util.*;
import java.io.*;

public class RoomNode
{
	int ID;
	int RoomType;
	boolean clear;
	int importantThing;
	int N, E, S, W;
	
	/**
	 * Constructor for generation of map: sets N-W as 0 so the room connecting a given room
	 * to room 1 (if applicable) will need to be manually set after the constructor is run
	 * @param id		ID assigned in map generation
	 * @param rt		RoomType assigned in map generation
	 * @param clear		determines whether the room has been previously cleared
	 * @param imprt		importantThings value/id assigned in map generation
	 */
	public RoomNode(int id, int rt, int imprt)
	{
		ID = id;
		RoomType = rt;
		clear = false;
		importantThing = imprt;
		N = 0;
		E = 0;
		S = 0;
		W = 0;
	}
	
	// METHODS
	
	/**
	 * Uses the RoomType variable assigned to it to pull the correct description from a text
	 * document (doesn't print on its own)
	 * @return String containing appropriate description
	 */
	public String checkType()
	{
		Scanner reader = null;
		// Try/catch to handle possible error
		try
		{
			reader = new Scanner(new File("roomtypes.txt"));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Err: roomtypes.txt not found");
			System.exit(1);
		}
		int chk = RoomType;
		String desc = "Err: no description found";
		// Checks number at beginning of line, then chooses whether to take next line
		do
		{
			chk = reader.nextInt();
			desc = reader.nextLine();
		} while (chk != RoomType || chk != 0);
		
		reader.close();
		return desc;
	}
	/**
	 * Uses the importantThing variable assigned to it to pull the correct description from a
	 * text document (doesn't print on its own)
	 * @return String containing appropriate description
	 */
	public String checkImport()
	{
		Scanner reader = null;
		// Try/catch to handle possible error
		try
		{
			reader = new Scanner(new File("importantthings.txt"));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Err: importantthings.txt not found");
			System.exit(1);
		}
		int chk = importantThing;
		String isItImportant = "Nothing of importance here";
		// Checks number at beginning of line, then chooses whether to take next line
		do
		{
			chk = reader.nextInt();
			isItImportant = reader.nextLine();
		} while (chk != importantThing || chk != 0);
		
		reader.close();
		return isItImportant;
	}
	/**
	 * Just checks to see where available paths are located and prints the information to
	 * the screen, also doing a check if the player was just in one of the rooms for a touch
	 * of added detail
	 */
	public void availablePaths(Player plyr)
	{
		System.out.println("Looking around yourself, you see door(s)...");
		
		// cameFrom is variable in Player, which as of writing this doesn't exist
		
		/*
		if (N != plyr.cameFrom && N != 0) System.out.println("to the North");
		if (E != plyr.cameFrom && E != 0) System.out.println("to the East");
		if (S != plyr.cameFrom && S != 0) System.out.println("to the South");
		if (W != plyr.cameFrom && W != 0) System.out.println("to the West");
		if (plyr.cameFrom != 0) System.out.println("... and back the way you came, of course");
		*/
	}
}
