/**
 * @author - Jack
 * Used to represent the different rooms/nodes comprising the map
 */

package dungeonCrawlerP1;
import java.util.*;
import java.io.*;

public class RoomNode
{
	int ID;				// Room # corresponding to place in room array
	int RoomType;		// Determines which description will be assigned to the room
	boolean clear;		// Whether or not the room has been cleared/visited by the player
	int importantThing;	// Determines description/type of important thing located in room
	int N, E, S, W;		// Contain the room # of the room connected at this point of the current room
	int x, y;			// The x & y coordinates of the given room
	
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
			/*System.out.println(chk); For bugfixing*/
			desc = reader.nextLine();
		} while (chk != RoomType && chk != 0);
		reader.close();
		
		if (importantThing != 0)
			desc += checkImport();
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
		String isItImportant = "Nothing of importance here.";
		// Checks number at beginning of line, then chooses whether to take next line
		do
		{
			chk = reader.nextInt();
			isItImportant = reader.nextLine();
		} while (chk != importantThing && chk != 0);
		
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
		System.out.print("Looking around yourself, you see door(s)...");
		boolean other = false;
		if (N != plyr.cameFrom && N != 0) {System.out.print("\nto the North"); other = true;}
		if (E != plyr.cameFrom && E != 0) {System.out.print("\nto the East"); other = true;}
		if (S != plyr.cameFrom && S != 0) {System.out.print("\nto the South"); other = true;}
		if (W != plyr.cameFrom && W != 0) {System.out.print("\nto the West"); other = true;}
		if (plyr.cameFrom != 0)
		{
			if (other) System.out.print("... and ");
			else System.out.print("\n... ");
			System.out.println("back the way you came");
		}
		else System.out.println();
		System.out.println();
	}
	
	/**
	 * Indicates if node (N-W) is unoccupied or not
	 * @param x		Direction N-W in question
	 * @return Whether that direction node equals 0
	 */
	public boolean avail(int x)
    {
    	switch (x)
    	{
    		case 1:
    			return N == 0;
    		case 2:
    			return E == 0;
    		case 3:
    			return S == 0;
    		case 4:
    			return W == 0;
    	}
    	return true;
    }
	
	/**
	 * Sets apropriate roomID for selected direction
	 * @param x			Direction N-W in int form
	 * @param room		Room being placed
	 */
	public void setDir(int x, int room)
    {
    	switch (x)
    	{
    		case 1:
    			N = room;
    			break;
    		case 2:
    			E = room;
    			break;
    		case 3:
    			S = room;
    			break;
    		case 4:
    			W = room;
    			break;
    	}
    }
	
	public boolean occupied(int z, Game g)
	{
		int xv, yv;
		switch(z)
		{
			case 1:
				xv = g.x;
				yv = g.y + 1;
				break;
			case 2:
				xv = g.x + 1;
				yv = g.y;
				break;
			case 3:
				xv = g.x;
				yv = g.y - 1;
				break;
			case 4:
				xv = g.x - 1;
				yv = g.y;
				break;
			default:
				xv = 59;
				yv = 59;
				break;
		}
		String compare = xv + " " + yv;
		for (int i = 1; i <= g.numOfRooms; i++)
		{
			if (g.xy[i] != null)
			{
				if (g.xy[i].equals(compare))
				{
					System.out.println(xv + " " + yv + " occupied");
					return true;
				}
			}
		}
		return false;
	}
	
	public void setXY(int d, Game g, int orig)
	{
		switch(d)
		{
			case 1:
				y = g.roomArr[orig].y + 1;
				x = g.roomArr[orig].x;
				break;
			case 2:
				x = g.roomArr[orig].x + 1;
				y = g.roomArr[orig].y;
				break;
			case 3:
				y = g.roomArr[orig].y - 1;
				x = g.roomArr[orig].x;
				break;
			case 4:
				x = g.roomArr[orig].x - 1;
				y = g.roomArr[orig].y;
				break;
		}
	}
}
