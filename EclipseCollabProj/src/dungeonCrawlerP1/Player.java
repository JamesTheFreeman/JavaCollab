/**
 * @author - Jack
 * Stores information pertaining to player-character and provides necessary methods
 */

package dungeonCrawlerP1;

public class Player
{
	String Name;
	double HP;
	double MaxHP; // Adjustable stat
	int local;
	int cameFrom;
	String[] inventory;
	int invSize = 30; // Can be changed as needed
	
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
		inventory = new String[invSize];
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
		inventory = new String[invSize];
	}
	
	// METHODS
	
	/**
	 * Prints the contents of the inventory array
	 */
	public void checkInv()
	{
		String printMe = "";
		int i = 0;
		while (inventory[i] != null || i < invSize)
		{
			if (inventory[i] != null)
				printMe += "\n> " + inventory[i];
			i++;
		}
		if (printMe.equals(""))
			printMe = "\nYour inventory is empty!";
		System.out.print("Your items:\n============");
		System.out.println(printMe + "\n");
	}
	/**
	 * Searches for an empty spot in the player's inventory to store item
	 * @param addMe		Item to be added to inventory
	 * @param echo		Boolean that indicates whether to echo item added message
	 */
	public void addToInventory(String addMe, boolean echo)
	{
		int i = 0;
		while (inventory[i] != null && i < invSize)
		{
			i++;
		}
		if (i == invSize)
			System.out.println("Inventory full!\n");
		else
		{
			inventory[i] = addMe;
			if (echo)
				System.out.println(addMe + " added to inventory\n");
		}
	}
	/**
	 * Searches inventory array for item to be removed and sets that index to null
	 * @param removeMe		Item to be removed
	 */
	public void removeFromInv(String removeMe)
	{
		int i = 0;
		while (!inventory[i].equals(removeMe) && i < invSize)
		{
			i++;
		}
		if (i == invSize)
			System.out.println("Err: Could not find item to be removed");
		else
		{
			inventory[i] = null;
		}
	}
	/**
	 * Searches for a specific item in player's inventory, returns true if it can be
	 * located, false otherwise
	 * @param item		Item being searched for
	 * @return true/false based on whether the item exists
	 */
	public boolean checkFor(String item)
	{
		for (int i = 0; i < invSize; i++)
		{
			if (inventory[i].equals(item))
				return true;
		}
		return false;
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
		roomArr[roomNum].checkType();
		roomArr[roomNum].checkRNG();
		*/
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
}
