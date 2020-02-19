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
		// Game initialization
		
		int mapSize = pickSize();
		Game game = new Game(mapSize);
		game.generateMap();
		// Tracing for debugging
		System.out.println("Map created!\n");
		Player player = createUser();
		// Tracing for debugging
		System.out.println("Welcome, " + player.Name + "\n");
		/*System.out.println("RoomType: " + game.roomArr[1].RoomType + "\n"); For bugifixing*/
		// Gives initial description, no check on RNG at beginning
		String startDesc = game.roomArr[1].checkType();
		System.out.println(startDesc.trim() + "\n");
		/* Debugging tool
		Game.debugConnections(game);
		*/
		
		// Gameplay start
		
		CurrentAction instance = new CurrentAction();
		String input = "";
		// (Temporary) loop to allow for multiple commands
		while (!input.equals("exit"))
		{
			System.out.print("> ");
			input = kbd.nextLine();
			input.toLowerCase();
			System.out.println("");
			if (!input.equals("exit"))
				instance.takeCommand(input, game, player);
		}
		System.exit(0);
	}
	/**
	 * Creates a new Player object w/ input name
	 * @return Initialized Player object
	 */
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
	/**
	 * Allows user to select the size to be used for generating the map
	 * @return The desired size as an integer
	 */
	public static int pickSize()
	{
		System.out.println("Select your map size:");
		System.out.println("> Small\n> Medium\n> Large\n");
		System.out.print("> ");
		String size = kbd.nextLine();
		System.out.println();
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
