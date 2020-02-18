/**
 * @author - Jack
 * Main staging area for method calls, etc.
 */

package dungeonCrawlerP1;
import java.util.*;

public class MainForGame
{
	// DO NOT USE .close(), as this also closes System.in, preventing
	// further use of Scanners <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	static Scanner kbd = new Scanner(System.in);
	
	public static void main(String[] args)
	{
		
		int mapSize = pickSize();
		Game game = new Game(mapSize);
		game.generateMap();
		
		// Tracing for debugging
		System.out.println("Map created!\n");
		
		Player player = createUser();
		
		// Tracing for debugging
		System.out.println("Welcome, " + player.Name + "\n");
		
		// System.out.println(game.roomArr[1].RoomType); For bugifixing
		// Gives initial description, no check on RNG at beginning
		String startDesc = game.roomArr[1].checkType();
		System.out.println(startDesc.trim() + "\n");
	}
	
	public static Player createUser()
	{
		System.out.print("Enter your name: ");
		String name = kbd.nextLine();
		// Calls apropriate constructor based on user input
		if (!name.trim().isEmpty())
			return new Player(name);
		else
			return new Player();
	}
	
	public static int pickSize()
	{
		System.out.println("Select your map size:");
		System.out.println("> Small\n> Medium\n> Large\n");
		String size = kbd.nextLine();
		size = size.toLowerCase();
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
