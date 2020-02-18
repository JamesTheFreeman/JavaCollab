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
    public void generateMap() {
        int direction = 0;
        int generatorRoom = 0;  // Room thats n-w is being check to see in a room can be generated next to it
        int pastDirection = 0;
        // Create number of room based on numOfRooms
        for (int i = 1; i <= numOfRooms; i++) {
            generatorRoom = 1;
            roomArr[i] = new RoomNode(i,random.nextInt(20) + 1,random.nextInt(20) + 1);
            // Repeat until room[i] has a n-w value / is placed
            while (!roomPlaced(roomArr[i])) {
                // Prevent program to go back to previous generatorRoom
                do {
                    direction = random.nextInt(4) + 1;
                } while (direction == opp(pastDirection));
                // Check if generatorRooms n-w is available
                if (roomDirAvl(roomArr[generatorRoom],direction)) {
                    setRoomDir(roomArr[generatorRoom],direction,i);
                    setRoomDir(roomArr[i],opp(direction),i);
                } else {
                    // else go to next generator room based on n-w
                    switch (direction) {
                        case 1:
                            generatorRoom = roomArr[generatorRoom].N;
                            break;
                        case 2:
                            generatorRoom = roomArr[generatorRoom].E;
                            break;
                        case 3:
                            generatorRoom = roomArr[generatorRoom].S;
                            break;
                        case 4:
                            generatorRoom = roomArr[generatorRoom].W;
                            break;
                    }
                    pastDirection = direction;
                }
            }
        }
    }

    /**
     * Check to see if the room has been placed, check if n-w are empty
     * @param roomNode
     * @return
     */
    private boolean roomPlaced(RoomNode roomNode) {
        if (roomNode.N != 0 || roomNode.E !=0 || roomNode.S != 0 || roomNode.W !=0) {
            return true;
        }
        return false;
    }

    /**
     * checks to see if a rooms n-w is available
     * @param roomNode
     * @param direction
     * @return
     */
    private boolean roomDirAvl(RoomNode roomNode, int direction) {
        switch (direction) {
            case 1:
                if (roomNode.N == 0) return true;
            case 2:
                if (roomNode.E == 0) return true;
            case 3:
                if (roomNode.S == 0) return true;
            case 4:
                if (roomNode.W == 0) return true;
            default:
                return false;
        }
    }

    /**
     * Set rooms n-w to diffrent rooms index
     * @param roomNode
     * @param direction
     * @param input
     */
    private void setRoomDir(RoomNode roomNode,int direction, int input) {
        switch (direction) {
            case 1:
                roomNode.N = input;
                break;
            case 2:
                roomNode.E = input;
                break;
            case 3:
                roomNode.S = input;
                break;
            case 4:
                roomNode.W = input;
                break;
         }
     }

     /**
      * Change n-w direction to opposite direction, If error throw -1
      * @param direction
      * @return
      */
    private int opp(int direction){
        switch (direction) {
            case 1:
                return 3;
            case 2:
                return 4;
            case 3: 
                return 1;
            case 4: 
                return 2;
            default:
                return -1;
        }
     }

     public String testRoomList() {
        String retur = "Error";
        for (int i = 0; i <= roomArr.length; i++) {
            retur += "ID: " + roomArr[i].ID + " N: " + roomArr[i].N + " E: " + roomArr[i].E + " S: " + roomArr[i].S + " W: " + roomArr[i].W + "\n";
        }
        return retur;
     }
 }
