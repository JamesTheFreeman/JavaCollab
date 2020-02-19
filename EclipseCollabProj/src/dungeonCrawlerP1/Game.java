/**
 * @author - Michael
 * Class description here
 */

package dungeonCrawlerP1;
import java.util.Random;

public class Game {
    int numOfRooms;			// Total number of rooms
    RoomNode[] roomArr;		// Array containing RoomNodes (size numOfRooms + 1)
    int from;				// Unused variable? Replaced with pastDir
    private Random random;	// Random number

    /**
     * Default Constructor, creates medium room
     */
    public Game() {
        numOfRooms = 0;
        roomArr = new RoomNode[30];
        from = 0;
        random = new Random();
    }

    /**
     * Constructor that allows for map size modification
     * @param size
     */
    public Game(int size) {
        numOfRooms = size;
        roomArr = new RoomNode[size + 1];
        from = 0;
        random = new Random();
    }

    /**
     * Dev Constructor for testing
     */
    public Game(int numOfRooms, RoomNode[] roomArr, int from) {
        this.numOfRooms = numOfRooms;
        this.roomArr = roomArr;
        this.from = from;
        random = new Random();
    }

    /**
     * Generate Map based on size
     */
    public void generateMap()
    {
    	int direction;	// Direction (1-4/N-W) for attempted room creation
    	int origin;		// RoomNode current room is being generated from
    	
    	// Constructs each RoomNode in the array
    	for (int i = 1; i <= numOfRooms; i++)
    	{
    		// Assigns room ID and gives random int 1-20 for room type & important
    		roomArr[i] = new RoomNode(i, random.nextInt(20) + 1, random.nextInt(20) + 1);
    	}

    	int pastDir;		// To indicate previous direction traveled
    	boolean placed;		// Indicator of whether room has been successfully placed
    	
    	// Moves through rooms that need placing, starting w/ 2 since we're working out from 1
    	// Room 1 isn't placable as there's nothing to place it on as of yet
    	for (int i = 2; i <= numOfRooms; i++)
    	{
    		origin = 1;		// Resets origin to room 1 after each placement
    		pastDir = 0;	// Resets pastDir after origin reset
    		placed = false;
    		while (!placed)	// Ensures room placement
    		{
				do // Ensures generateMap doesn't move backwards
				{
					// Random int 1-4 (or 0-3 + 1, technically)
					direction = random.nextInt(4) + 1;
				} while (direction == opp(pastDir));

    			// Tells whether direction is available
    			if (roomArr[origin].avail(direction))
    			{
    				// Sets N-W value for room
    				roomArr[origin].setDir(direction, i);
    				// Sets opposite value for connected
    				roomArr[i].setDir(opp(direction), origin);
    				// Allows loop to be broken
    				placed = true;
    			}

    			else // Moves to new room to generate from
    			{
    				// Moves origin of map generation to room occupying selected node
    				origin = newOrigin(direction, origin);
    				
    				if (origin == 0) // Error message for a bug I fixed
    				{
    					System.out.println("Err: cannot generate from room 0");
    					System.exit(1);
    				}
    				pastDir = direction; // Tracks past direction if origin is moved
    			}
    		}
    	}
    }

    // SOME METHODS LOCATED IN RoomNode.java
    
    // Returns opposite direction
    public int opp(int x)
    {
    	switch (x)
    	{
    		case 1:
    			return 3;
    		case 2:
    			return 4;
    		case 3:
    			return 1;
    		case 4:
    			return 2;
    		case 0:
    			return 0;
    	}
    	// Shouldn't be possible, but Eclipse screams if this isn't here
    	return 0;
    }

    // Returns room to relocate origin to
    public int newOrigin(int dir, int origin)
    {
    	switch (dir)
    	{
    		case 1:
    			return roomArr[origin].N;
    		case 2:
    			return roomArr[origin].E;
    		case 3:
    			return roomArr[origin].S;
    		case 4:
    			return roomArr[origin].W;
    	}
    	// Shouldn't be possible, but Eclipse screams if this isn't here
    	return 0;
    }
    
    // Prints all rooms & connections for debugging
    public static void debugConnections(Game g)
    {
    	for (int i = 1; i <= g.numOfRooms; i++)
    	{
    		System.out.println("ID: " + g.roomArr[i].ID + " | N: " + g.roomArr[i].N + " E: " + g.roomArr[i].E
    				+ " S: " + g.roomArr[i].S + " W: " + g.roomArr[i].W);
    	}
    	System.out.println(); // For neatness
    }
 }
