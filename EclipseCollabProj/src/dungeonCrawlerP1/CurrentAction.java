/**
 * @author - Stephen
 * Takes user input and determines command being input
 */

package dungeonCrawlerP1;

public class CurrentAction {
	
	public void takeCommand(String input, Game game, Player player, RNGObj rng) {
		
		/**
		 * Array index 0 = Command Word
		 * Array index 1 = Item Word
		 */
		String[] inputs = input.toLowerCase().split(" ");
		
		// Special case for if player wishes to travel
		if (inputs[0].equals("go"))
		{
			switch(inputs[1]) {
				case "n":
				case "north":
					//Traverse North
				 	if (game.roomArr[player.local].N != 0) {
						player.traverseRoom(game.roomArr[player.local].N, game, rng);
					} else {System.out.println("You cannot go that way\n");}
					break;
				case "s":
				case "south":
					//Traverse South
			 		if (game.roomArr[player.local].S != 0) {
						player.traverseRoom(game.roomArr[player.local].S, game, rng);
					} else {System.out.println("You cannot go that way\n");}
					break;
				case "e":
				case "east":
					//Traverse East
					if (game.roomArr[player.local].E != 0) {
						player.traverseRoom(game.roomArr[player.local].E, game, rng);
					} else {System.out.println("You cannot go that way\n");}
					break;
				case "w":
				case "west":
					//Traverse West
					if (game.roomArr[player.local].W != 0) {
						player.traverseRoom(game.roomArr[player.local].W, game, rng);
					} else {System.out.println("You cannot go that way\n");}
					break;
				case "back":
					//Traverse Back
					if (player.cameFrom > 0) {
						player.traverseRoom(player.cameFrom, game, rng);
					} else {System.out.println("Nowhere to go back to\n");}
					break;
				default:
					System.out.println("Unknown command, type 'help' to see a list of available commands\n");
					break;
			}
		}
		// Case if player doesn't wish to travel
		else
		{
			switch(inputs[0]) {
				case "inventory":
					//Check Inventory
					player.checkInv();
					break;
				case "room":
					//Describe room
					String desc = "";
					desc = game.roomArr[player.local].checkType();
					System.out.println(desc.trim() + "\n");
					break;
				case "take":
					//add item to inventory
					/*
					if (game.roomArr[player.local].contains(inputs[1])) {
						game.roomArr[player.local].removeItem(inputs[1]);
						player.addToInventory(inputs[1]);
					} else {System.out.println("Item not found");}
					*/
					break;
				case "use":
					//use an item from inventory
					/**
					if (player.checkFor(inputs[1])) {
					//Send inputs[1] to interactables useItem class
					} else {System.out.println("Item not found");}
					 */
					break;
				case "check":
					// Really gross method call
					game.roomArr[player.local].availablePaths(player);
					break;
				case "help":
					//list commands
					System.out.println("Available Commands:\n"
							+ "Help - List of Commands\n"
							+ "Go - Travel North, East, South, West, or back (if applicable)\n"
							+ "Map - Check your location on the map\n"
							+ "Inventory - Check Inventory\n"
							+ "Room - Description of current room\n"
							+ "Check - Checks available paths\n"
							+ "Take - Add a specified object to your inventory\n"
							+ "Use - Use an item in your inventory\n");
					break;
				case "map":
					// Open map (might remove later?)
					Game.printMap(game, player);
					break;
				default:
					System.out.println("Unknown command, type 'help' to see a list of available commands\n");
					break;
			}
		}
	}
}
