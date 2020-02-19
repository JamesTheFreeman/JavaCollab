/**
 * @author - Michael
 * Class description here
 */

package dungeonCrawlerP1;
import java.util.Random;

public class Game {
    int numOfRooms;
    RoomNode[] roomArr;
    int from;
    private Random random;

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
    	int direction;
    	int origin;

    	for (int i = 1; i <= numOfRooms; i++)
    	{
    		roomArr[i] = new RoomNode(i, random.nextInt(20) + 1, random.nextInt(20) + 1);
    	}

    	int pastDir = 0;
    	boolean placed;
    	for (int i = 2; i <= numOfRooms; i++)
    	{
    		origin = 1;
    		placed = false;
    		while (!placed)
    		{
				// Ensures generateMap doesn't move backwards
				do
				{
					direction = random.nextInt(4) + 1;
				} while (direction == opp(pastDir));

    			// Tells whether direction is available
    			if (roomArr[origin].avail(direction))
    			{
    				// Sets N-W value for room
    				roomArr[origin].setDir(direction, i);
    				// Sets opposite value for connected
    				roomArr[i].setDir(opp(direction), origin);
    				placed = true;
    			}

    			// Moves to new room to generate from
    			else
    			{
    				origin = newOrigin(direction, origin);
    				if (origin == 0)
    				{
    					System.out.println("Err: cannot generate from room 0");
    					System.exit(1);
    				}
    				pastDir = direction;
    			}
    		}
    	}
    }

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
    	return 0;
    }

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
    	return 0;
    }
    
    public static void debugConnections(Game g)
    {
    	for (int i = 1; i <= g.numOfRooms; i++)
    	{
    		System.out.println("ID: " + g.roomArr[i].ID + " | N: " + g.roomArr[i].N + " E: " + g.roomArr[i].E
    				+ " S: " + g.roomArr[i].S + " W: " + g.roomArr[i].W);
    	}
    	System.out.println();
    }
 }
