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
		/*
		 * GAME START SCREEN
		 * Mostly for dev mode for tracing
		 */
		
		boolean devMode = false;
		System.out.println("\n====================");
		System.out.println("Welcome! Start game?");
		System.out.println("====================\n");
		System.out.print("> ");
		String startAns = kbd.nextLine();
		startAns.toLowerCase();
		System.out.println("\n");
		if (startAns.equals("yes") || startAns.equals("y"));
		// Dev mode to enable/disable tracing
		else if (startAns.equals("dev")) devMode = true;
		else
		{
			System.out.println("Exiting...\n");
			System.exit(0);
		}
		
		/*
		 * GAME INITIALIZATION
		 * Initial map build + player creation
		 */
		
		int mapSize = pickSize();
		Game game = new Game(mapSize);
		if (devMode) game.tracing = true;
		game.generateMap();
		
		Player player = createUser();
		System.out.println("Welcome, " + player.Name + "\n");
		
		RNGObj rng = new RNGObj(); // Initializes RNG
		
		// Gives initial description, no check on RNG at beginning
		String startDesc = game.roomArr[1].checkType();
		System.out.println(startDesc.trim() + "\n");
		// DEBUG TOOLS
		if (devMode)
		{
			Game.debugConnections(game);
			Game.printMap(game, player);
			Game.printXY(game);
		}
		
		/*
		 * GAMEPLAY START
		 * Player interaction starts here
		 */
		
		CurrentAction instance = new CurrentAction();
		String input = "";
		// (Temporary) loop to allow for multiple commands
		while (!input.equals("exit"))
		{
			System.out.print("> ");
			input = kbd.nextLine();	// User input
			input.toLowerCase();	// Set case for uniform input
			System.out.println("");
			if (!input.equals("exit"))
				// Passes user input, as long as input != exit
				instance.takeCommand(input, game, player, rng);
		}
		System.out.println("Exiting...\n");
		System.exit(0);
	}
	
	// METHODS
	
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
		size = easySize(size);
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
	/**
	 * Allows user to use s/m/l in place of full word because I got tired of typing
	 * out the whole thing while testing
	 * @param s			User input being used to set map size
	 * @return String containing either the original input or a full word form s/m/l
	 */
	public static String easySize(String s)
	{
		String ans = s;
		switch(s)
		{
			case "s":
				ans = "small";
				break;
			case "m":
				ans = "medium";
				break;
			case "l":
				ans = "large";
				break;
		}
		return ans;
	}
}
