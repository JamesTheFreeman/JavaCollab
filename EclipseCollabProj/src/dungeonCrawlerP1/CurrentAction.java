/**
 * @author - Stephen
 * Takes user input and determines command being input
 */

package dungeonCrawlerP1;

public class CurrentAction {
	
	public static void takeCommand(String input, Game game, Player player) {
		
		/**
		 * Array index 0 = Command Word
		 * Array index 1 = Item Word
		 */
		String[] inputs = input.toLowerCase().split(" ");
		
		switch(inputs[0]) {
			case "n":
			case "north":
				//Traverse North
				 	if (game.roomArr[player.local].N != 0) {
						player.traverseRoom(game.roomArr[player.local].N, game);
					} else {System.out.println("You cannot go that way");}
				break;
			case "s":
			case "south":
				//Traverse South
			 		if (game.roomArr[player.local].S != 0) {
						player.traverseRoom(game.roomArr[player.local].S, game);
					} else {System.out.println("You cannot go that way");}
				break;
			case "e":
			case "east":
				//Traverse East
					if (game.roomArr[player.local].E != 0) {
						player.traverseRoom(game.roomArr[player.local].E, game);
					} else {System.out.println("You cannot go that way");}
				break;
			case "w":
			case "west":
				//Traverse West
					if (game.roomArr[player.local].W != 0) {
						player.traverseRoom(game.roomArr[player.local].W, game);
					} else {System.out.println("You cannot go that way");}
			break;
			case "inventory":
				//Check Inventory
				player.checkInv();
				break;
			case "room":
				//Describe room
				String desc = "";
				desc = game.roomArr[player.local].checkType();
				System.out.println(desc);
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
			case "help":
				//list commands
				System.out.println("Available Commands\n"
						+ "Help - List of Commands\n"
						+ "N/North - Move North\n"
						+ "S/South - Move South\n"
						+ "E/East - Move East\n"
						+ "W/West - Move West\n"
						+ "Inventory - Check Inventory\n"
						+ "Room - Description of current room\n"
						+ "Take - Add a specified object to your inventory\n"
						+ "Use - Use an item in your inventory");
				break;
			default:
				System.out.print("Unknown Command, typing 'help' to see list of available commands");
				break;
		}
	}
}
