package controller;

import model.LayoutPosition;
import java.util.ArrayList;
import model.Position;

/**
 *
 * @author Lisa, Mustapha, Nesrine
 */
public class PositionsInit {
    /* Connect all of the game positions with the interface positions */
    public static void initializePositions(ArrayList<Position> positions, String roomName) {
        switch(roomName) {
            case "Living Room":
                LayoutPosition lrLp0 = new LayoutPosition(positions.get(0), 1, 3);
                LayoutPosition lrLp1 = new LayoutPosition(positions.get(1), 3, 5);
                LayoutPosition lrLp2 = new LayoutPosition(positions.get(2), 2, 1);
                LayoutPosition lrLp3 = new LayoutPosition(positions.get(3), 4, 2);
                
            break;
            
            case "Dining Room":
                LayoutPosition drLp0 = new LayoutPosition(positions.get(0), 1, 3);
                LayoutPosition drLp1 = new LayoutPosition(positions.get(1), 1, 5);
                LayoutPosition drLp2 = new LayoutPosition(positions.get(2), 3, 0);
                LayoutPosition drLp3 = new LayoutPosition(positions.get(3), 3, 2);
                
            break;
            
            case "Corridor" :
                LayoutPosition corLp0 = new LayoutPosition(positions.get(0), 1, 0);
                LayoutPosition corLp1 = new LayoutPosition(positions.get(1), 4, 0);
                LayoutPosition corLp2 = new LayoutPosition(positions.get(2), 1, 2);
                LayoutPosition corLp3 = new LayoutPosition(positions.get(3), 4, 2);
                LayoutPosition corLp4 = new LayoutPosition(positions.get(4), 1, 3);
                LayoutPosition corLp5 = new LayoutPosition(positions.get(5), 4, 3);
                LayoutPosition corLp6 = new LayoutPosition(positions.get(6), 1, 4);
                LayoutPosition corLp7 = new LayoutPosition(positions.get(7), 4, 4);
                LayoutPosition corLp8 = new LayoutPosition(positions.get(8), 1, 5);
                LayoutPosition corLp9 = new LayoutPosition(positions.get(9), 4, 5);
            break;
            
            case "Room 17 [Crime Scene n.1]" :
                LayoutPosition room17Lp0 = new LayoutPosition(positions.get(0), 3, 3);
            break;
            
            case "Room 19 [Crime Scene n.2]" :
                LayoutPosition room19Lp0 = new LayoutPosition(positions.get(0), 3, 3);
            break;
            
            case "Room 21 [Crime Scene n.3]" :
                LayoutPosition room21Lp0 = new LayoutPosition(positions.get(0), 3, 3);
            break;
            
            case "Room 18 [Crime Scene n.4]" :
                LayoutPosition room18Lp0 = new LayoutPosition(positions.get(0), 3, 3);
            break;
            
            case "Room 22 [Crime Scene n.5]" :
                LayoutPosition room22Lp0 = new LayoutPosition(positions.get(0), 3, 3);
            break;
            
            case "Room 23 [Crime Scene n.6 - Final Scene]" :
                LayoutPosition room23Lp0 = new LayoutPosition(positions.get(0), 3, 3);
            break;
            
        }   
    }
}
