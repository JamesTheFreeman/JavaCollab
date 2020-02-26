/**
 * @author - Michael & Jack
 * Generates a map consisting of RoomNodes
 */

package dungeonCrawlerP1;
import java.util.Random;

public class Game {
    int numOfRooms;					// Total number of rooms
    RoomNode[] roomArr;				// Array containing RoomNodes (size numOfRooms + 1)
    int from;						// Unused variable? Replaced with pastDir
    private Random random;			// Random number
    String[] xy = new String[60];	// xy[i] = x, value at that address = y
   	int x = 0, y = 0;				// For tracking x/y of rooms
   	boolean tracing = false;		// Enable/disable tracing for debugging

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
    	int direction;			// Direction (1-4/N-W) for attempted room creation
    	int origin;				// RoomNode current room is being generated from
    	
    	// Inserts null for cells 0 - 59
    	for (int j = 0; j < 60; j++) xy[j] = null;
    	xy[1] = "0 0";
    	
    	// Constructs each RoomNode in the array
    	for (int i = 1; i <= numOfRooms; i++)
    		// Assigns room ID and gives random int 1-20 for room type & important
    		roomArr[i] = new RoomNode(i, random.nextInt(20) + 1, random.nextInt(20) + 1);
    	
    	// Sets room 1 as (0, 0)
    	roomArr[1].x = 0;
    	roomArr[1].y = 0;

    	int pastDir;		// To indicate previous direction traveled
    	boolean placed;		// Indicator of whether room has been successfully placed
    	
    	// Moves through rooms that need placing, starting w/ 2 since we're working out from 1
    	// Room 1 isn't placable as there's nothing to place it on as of yet
    	for (int i = 2; i <= numOfRooms; i++)
    	{
    		origin = 1;		// Resets origin to room 1 after each placement
    		pastDir = 0;	// Resets pastDir after origin reset
    		x = 0;			// Resets x
    		y = 0;			// Resets y
    		placed = false;
    		boolean placeAtt = false;
    		while (!placed)	// Ensures room placement
    		{
				do // Ensures generateMap doesn't move backwards
				{
					// Random int 1-4 (or 0-3 + 1, technically)
					direction = random.nextInt(4) + 1;
				} while (direction == opp(pastDir));
				
				if (tracing) System.out.println("Attempting to create room from " + x + " " + y);
				
    			// Tells whether direction is connected to room
    			if (roomArr[origin].avail(direction))
    			{
    				if (tracing) System.out.println("Direction " + direction + " not connected");
    				// Ensure no room exists at given x,y coordinate
    				if (!roomArr[origin].occupied(direction, this))
    				{
    					if (tracing) System.out.println("No adjacent room");
	    				// Sets N-W value for room
	    				roomArr[origin].setDir(direction, i);
	    				// Sets opposite value for connected
	    				roomArr[i].setDir(opp(direction), origin);
	    				roomArr[i].setXY(direction, this, origin);
	    				if (tracing) System.out.print("Room placed at ");
	    				xyArr(i, direction, origin);
	    				// Allows loop to be broken
	    				placed = true;
    				}
    				// If room exists, connect to current origin
    				else
    				{
    					String chk = dirCheck(direction, this, origin);
    					int j, z = 99;
    					for (j = 1; j <= numOfRooms; j++)
    					{
    						if (xy[j] != null)
    						{
    							if (tracing) System.out.println("xy[" + j + "] = " + xy[j] + ", checking for " + chk);
    							if (xy[j].equals(chk))
    							{
    								z = j;
    								break;
    							}
    						}
    					}
    					if (z == 99)
    					{
    						System.out.println("Connection error");
    						System.exit(1);
    					}
    					roomArr[origin].setDir(direction, z);
    					roomArr[z].setDir(opp(direction), origin);
    					placeAtt = true;
    				}
    				
    				// If there are three unsuccessful attempts to create a room, relocate origin
    				if (placeAtt && !placed)
    				{
    					// Resets origin
        				origin = 1;
        				pastDir = 0;
        				x = 0;
        				y = 0;
        				placeAtt = false;
    				}
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
    
    /**
     * Returns opposite direction
     * @param x		Direction to be made opposite
     * @return Opposite direction based on x
     */
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
    		default:
    			System.out.println("Opp error");
    			return 99;
    	}
    	// Shouldn't be possible, but Eclipse screams if this isn't here
    }

    /**
     * Returns room to relocate origin to, also changes x/y based on origin
     * @param dir		Direction origin is being moved
     * @param origin	Location of current origin
     * @return Room # the origin is being relocated to
     */
    public int newOrigin(int dir, int origin)
    {
    	switch (dir)
    	{
    		case 1:
    			y += 1;
    			return roomArr[origin].N;
    		case 2:
    			x += 1;
    			return roomArr[origin].E;
    		case 3:
    			y -= 1;
    			return roomArr[origin].S;
    		case 4:
    			x -= 1;
    			return roomArr[origin].W;
    	}
    	// Shouldn't be possible, but Eclipse screams if this isn't here
    	return 99;
    }
    
    /**
     * Prints all rooms & connections for debugging
     * @param g		Game being mapped
     */
    public static void debugConnections(Game g)
    {
    	for (int i = 1; i <= g.numOfRooms; i++)
    	{
    		System.out.println("ID: " + g.roomArr[i].ID + " | N: " + g.roomArr[i].N + " E: " + g.roomArr[i].E
    				+ " S: " + g.roomArr[i].S + " W: " + g.roomArr[i].W);
    	}
    	System.out.println(); // For neatness
    }
    
    /**
     * Prints rooms as map using X/Y coordinates, moving row by row
     * generating rooms from left to right
     * @param g		Game being mapped
     */
    public static void printMap(Game g)
    {
    	int yMax = g.maxY();	// Max y value
    	int yMin = g.minY();	// Min y value
    	int xMax = g.maxX();	// Max x value
    	int xMin = g.minX();	// Min x value
    	String map = "";		// Variable to store map
    	
    	for (int i = yMax; i >= yMin; i--) 		// Increments y value
    	{
    		for (int j = xMin; j <= xMax; j++) 	// Increments x value
    		{
    			int k = 1;
    			boolean placed = false;
    			do
    			{
    				int xval = j;
    				int yval = i;
    				// Checks if room exists at current x, y values
    				if (g.roomArr[k].x == xval && g.roomArr[k].y == yval)
    				{
    					map += "[  ]";
    					placed = true;
    				}
    				k++;
    			} while(k <= g.numOfRooms && !placed);
    			if (!placed) // Inserts blank if no room exists at given x, y
    				map += "    ";
    		}
    		map += "\n"; // Adds new line character at end of each row
    	}
    	System.out.print(map + "\n");
    }
    
    /**
     * Gets the maximum y value from map
     * @return Max y value
     */
    public int maxY()
    {
    	int max = roomArr[1].y;
    	for (int i = 2; i <= numOfRooms; i++)
    	{
    		if (roomArr[i].y > max)
    			max = roomArr[i].y;
    	}
    	return max;
    }
    
    /**
     * Gets the maximum x value from map
     * @return Max x value
     */
    public int maxX()
    {
    	int max = roomArr[1].x;
    	for (int i = 2; i <= numOfRooms; i++)
    	{
    		if (roomArr[i].x > max)
    			max = roomArr[i].x;
    	}
    	return max;
    }
    
    /**
     * Gets the minimum y value from map
     * @return Min y value
     */
    public int minY()
    {
    	int min = roomArr[1].y;
    	for (int i = 2; i <= numOfRooms; i++)
    	{
    		if (roomArr[i].y < min)
    			min = roomArr[i].y;
    	}
    	return min;
    }
    
    /**
     * Gets the minimum x value from map
     * @return Min x value
     */
    public int minX()
    {
    	int min = roomArr[1].x;
    	for (int i = 2; i <= numOfRooms; i++)
    	{
    		if (roomArr[i].x < min)
    			min = roomArr[i].x;
    	}
    	return min;
    }
    
    /**
     * Stores the coordinates of newly created room in xy array
     * @param z			roomID of new room
     * @param dir		direction room was added
     * @param orig		originating room
     */
    public void xyArr(int z, int dir, int orig)
    {
    	int ax = roomArr[orig].x;
    	int ay = roomArr[orig].y;
    	switch (dir)
    	{
    		case 1:
    			ay += 1;
    			break;
    		case 2:
    			ax += 1;
    			break;
    		case 3:
    			ay -= 1;
    			break;
    		case 4:
    			ax -= 1;
    			break;
    	}
    	xy[z] = ax + " " + ay;
    	if (tracing) System.out.println(xy[z] + "\n");
    }
    
    /**
     * Returns x, y values to be checked against array for point in space
     * @param d		Direction room is being added (N-W)
     * @param g		Game containing rooms
     * @return The x, y string for comparison
     */
    public String dirCheck(int d, Game g, int orig)
	{
		int xv = 0, yv = 0;
		String ans;
		switch(d)
		{
			case 1:
				xv = g.roomArr[orig].x;
				yv = g.roomArr[orig].y + 1;
				break;
			case 2:
				xv = g.roomArr[orig].x + 1;
				yv = g.roomArr[orig].y;
				break;
			case 3:
				xv = g.roomArr[orig].x;
				yv = g.roomArr[orig].y - 1;
				break;
			case 4:
				xv = g.roomArr[orig].x - 1;
				yv = g.roomArr[orig].y;
				break;
			default:
				System.out.println("Err: dirCheck");
				System.exit(1);
		}
		ans = xv + " " + yv;
		return ans;
	}
    
    /**
     * Prints x & y values of all rooms
     * @param g		Game contatining rooms
     */
    public static void printXY(Game g)
    {
    	for (int i = 1; i <= g.numOfRooms; i++)
    	{
    		System.out.print("Room " + i + ": ");
    		System.out.println(g.roomArr[i].x + ", " + g.roomArr[i].y);
    	}
    	System.out.println();
    }
 }
