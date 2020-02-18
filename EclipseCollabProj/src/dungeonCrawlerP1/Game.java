/**
 * @author - Michael
 * Class description here
 */

import java.util.Random;
package dungeonCrawlerP1;

public class Game {
    int numOfRooms;
    RoomNode[] roomArr;
    int from;
    Random ran;

    /**
     * Default Constructor, creates medium room
     */
    public Game() {
        numOfRooms = 0;
        roomArr = new RoomNode[30];
        from = 0;
        ran = new Random();
    }

    /**
     * Constructor that allows for map size modification
     * @param size
     */
    public Game(int size) {
        numOfRooms = 0;
        roomArr = new RoomNode[size];
        from = 0;
        ran = new Random();
    }

    /**
     * Dev Constructor for testing
     */
    public Game(int numOfRooms, RoomNode[] roomArr, int from) {
        numOfRooms = this.numOfRooms;
        roomArr = this.roomArr;
        from = this.from;
        ran = new Random();
    }

    /**
     * Generate Map based on size
     * @param size
     */
    public void generateMap(int size) {
        int dir = 0;
        int plc = 0;
        for (int i = 1; i <= size; i++) {
            plc = 1;
            roomArr[i] = new RoomNode(i,ran.nextInt(20) + 1,ran.nextInt(20) + 1);
            while (!roomPlaced(roomArr[i])) {
                dir = ran.nextInt(4) + 1;
                if (roomDirAvl(roomArr[plc],dir)) {
                    setRoomDir(roomArr[plc],dir,i);
                    setRoomDir(roomArr[i],opp(dir),i);
                } else {
                    switch (dir) {
                        case 1:
                            plc = roomArr[plc].N;
                            break;
                        case 2:
                            plc = roomArr[plc].E;
                            break;
                        case 3:
                            plc = roomArr[plc].S;
                            break;
                        case 4:
                            plc = roomArr[plc].W;
                            break;
                    {
                }
            }
        }
    }

    /**
     * Check to see if the room has been placed, check if n-w are empty
     * @param x
     * @return
     */
    private boolean roomPlaced(RoomNode x) {
        boolean res = false;
        if (x.N != 0 || x.E !=0 || x.S != 0 || x.W !=0) {
            res = true;
        }
        return res;
    }

    /**
     * checks to see if a rooms n-w is available
     * @param x
     * @param dir
     * @return
     */
    private boolean roomDirAvl(RoomNode x, int dir) {
        boolean res = false;
        switch (dir) {
            case 1:
                if (x.N == 0) res = true;
                break;
            case 2:
                if (x.E == 0) res = true;
                break;
            case 3:
                if (x.S == 0) res = true;
                break;
            case 4:
                if (x.W == 0) res = true;
                break;
        }
    }

    /**
     * Set rooms n-w to diffrent rooms index
     * @param x
     * @param dir
     * @param y
     */
    private void setRoomDir(RoomNode x,int dir, int y) {
        switch (dir) {
            case 1:
                x.N = y;
                break;
            case 2:
                x.E = y;
                break;
            case 3:
                x.S = y;
                break;
            case 4:
                x.W = y;
                break;
         }
     }

     /**
      * Change n-w direction to opposite direction
      * @param dir
      * @return
      */
    private int opp(int dir){
        if (dir == 1) return 3;
        if (dir == 2) return 4;
        if (dir == 3) return 1;
        return 2;
     }
 }
