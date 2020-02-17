/**
 * @author - Jack
 * Main staging area for method calls, etc.
 */

package dungeonCrawlerP1;
import java.util.*;

public class MainForGame
{
	public static void main(String[] args)
	{
		int mapSize = pickSize();
		// Generates the map, imagine that
		/*
		generateMap(mapSize);
		*/
		
		// Tracing for debugging
		System.out.println("Map created!\n");
		
		Player player = createUser();
		
		// Tracing for debugging
		System.out.println("Welcome, " + player.Name);
		
		// Gives initial description, no check on RNG at beginning
		/*
		roomArr[1].checkType();
		*/
	}
	
	public static Player createUser()
	{
		System.out.print("Enter your name: ");
		Scanner kbd = new Scanner(System.in);
		String name = kbd.nextLine();
		kbd.close();
		// Calls apropriate constructor based on user input
		Player player = null;
		if (!name.trim().isEmpty())
			return player = new Player(name);
		else
			return player = new Player();
	}
	
	public static int pickSize()
	{
		System.out.println("Select your map size:");
		System.out.println("> Small\n> Medium\n> Large\n");
		Scanner kbd = new Scanner(System.in);
		String size = kbd.nextLine();
		size = size.toLowerCase();
		kbd.close();
		if (size.equals("small") || size.equals("medium") || size.equals("large"))
		{
			switch(size)
			{
			case "small":	return 20;
			case "medium":	return 30;
			case "large":	return 40;
			}
		}
		else
		{
			System.out.println("Invalid input! Try again\n");
			pickSize();
		}
		return 0;
	}
}
