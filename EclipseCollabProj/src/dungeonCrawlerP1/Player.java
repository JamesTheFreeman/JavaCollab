/**
 * @author - Jack
 * Stores information pertaining to player-character and provides necessary methods
 */

package dungeonCrawlerP1;
import java.util.*;
import java.io.*;

public class Player
{
	String Name;
	double HP;
	double MaxHP;
	int local;
	int cameFrom;
	
	/**
	 * Default constructor, in case the player doesn't want to provide a name
	 * Gives player defaulted name of "Nohbdy"
	 */
	public Player()
	{
		Name = "Nohbdy";
		HP = 100;
		MaxHP = 100;
		local = 1;
		cameFrom = 0;
	}
	/**
	 * Constructor for typical use, takes player name and sets all other variables
	 * @param name		Name entered by user
	 */
	public Player(String name)
	{
		Name = name;
		HP = 100;
		MaxHP = 100;
		local = 1;
		cameFrom = 0;
	}
	
	// METHODS
	
	/**
	 * If the player has items in playerinventory.txt, this method reads them off in
	 * a list format (Requires that items in text document are all on separate lines)
	 */
	public void checkInv()
	{
		Scanner invReader = null;
		// Try/catch to handle possible error
		try
		{
			invReader = new Scanner(new File("playerinventory.txt"));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Err: playerinventory.txt not found");
			System.exit(1);
		}
		if (!invReader.hasNextLine())
			System.out.println("No items in inventory!");
		else
		{
			System.out.println("Your items:\n============");
			// Reads inventory line by line, as long as there's lines to read
			while (invReader.hasNextLine())
			{
				System.out.println("> " + invReader.nextLine());
			}
		}
		invReader.close();
	}
	/**
	 * Reads the players entire inventory, adds the new item, and reinserts it in the
	 * text document
	 * @param addMe		Name of item to be added to inventory
	 */
	public void addToInventory(String addMe)
	{
		Scanner invReader = null;
		// Try/catch to handle possible error
		try
		{
			invReader = new Scanner(new File("playerinventory.txt"));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Err: playerinventory.txt not found");
			System.exit(1);
		}
		String append = "";
		// Adds item being added to current inventory, if needed
		if (invReader.hasNextLine())
		{
			while (invReader.hasNextLine())
			{
				append += invReader.nextLine() + "\n";
			}
			append += addMe;
			invReader.close();
		}
		else
			append = addMe;
		// For printing to text file
		PrintWriter newInv = null;
		// Try/catch to handle possible error
		try
		{
			newInv = new PrintWriter("playerinventory.txt");
		}
		catch (IOException e)
		{
			System.out.println("Err: can't open playerinventory.txt for writing");
			System.exit(1);
		}
		newInv.print(append);
		newInv.close();
		// Message printed upon success
		System.out.println("Item added to inventory");
	}
	/**
	 * Similar to addToInventory in that it reads the entire inventory, except this method
	 * just omits the desired String when reassembling the contents of the inventory
	 * (Player shouldn't have access to this command, should only be called by other methods)
	 * @param removeMe		Name of item to be removed from inventory
	 */
	public void removeFromInv(String removeMe)
	{
		Scanner invReader = null;
		// Try/catch to handle possible error
		try
		{
			invReader = new Scanner(new File("playerinventory.txt"));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Err: playerinventory.txt not found");
			System.exit(1);
		}
		String append = "";
		if (!invReader.hasNextLine())
			System.out.println("Your inventory is empty!");
		else
		{
			while (invReader.hasNextLine())
			{
				String bffr = invReader.nextLine();
				if (bffr != removeMe)
					append = bffr + "\n";
			}
			invReader.close();
		}
		PrintWriter newInv = null;
		// Try/catch to handle possible error
		try
		{
			newInv = new PrintWriter("playerinventory.txt");
		}
		catch (IOException e)
		{
			System.out.println("Err: can't open playerinventory.txt for writing");
			System.exit(1);
		}
		newInv.print(append);
		newInv.close();
	}
	/**
	 * Method for moving the player from one room to another
	 * @param roomNum		ID of room being traveled to
	 */
	public void traverseRoom(int roomNum)
	{
		cameFrom = local;
		local = roomNum;
		// For the future, putting here so we don't forget
		/*
		checkRNG(roomNum);
		describeRoom(roomNum);
		checkImportant(roomNum);
		*/
	}
	/**
	 * Method for clearing playerinventory.txt - assumes the file already exists
	 * If the file doesn't exist, it's going to make a new one (probably fine)
	 */
	public void resetInv()
	{
		PrintWriter nukeInv = null;
		// Try/catch to handle possible error
		try
		{
			nukeInv = new PrintWriter("playerinventory.txt");
		}
		catch (IOException e)
		{
			System.out.println("Err: can't open playerinventory.txt for writing");
			System.exit(1);
		}
		nukeInv.print("");
		nukeInv.close();
	}
	/**
	 * Method allowing for use of item(s) on interactable items
	 * @param used
	 */
	public void useItem(String used)
	{
		boolean compat = false;
		// Possible future implementation of usable items, 'use' is method in Interactable
		// To be used like: Interactable.useItem(used);
		// Returns boolean based on whether item was actually used
		/*
		compat = use(used);
		*/
		
		// Only removes item if it was used
		if (compat)
			removeFromInv(used);
	}
	/**
	 * Relies on object of class NPChar, maybe not necessary but it can't hurt
	 * To be used like: NPChar.chatTo();
	 * startConvo is method w/in NPChar class
	 */
	public void chatTo()
	{
		/*
		startConvo();
		*/
	}
	/**
	 * Just realized how strangely formatted this is, may need reworking for implementation
	 * @param R		Name of the RoomNode being checked
	 */
	public void checkPaths(RoomNode R)
	{
		// Maybe don't need this method, could just potentially use availablePaths directly
		R.availablePaths(this);
	}
}
