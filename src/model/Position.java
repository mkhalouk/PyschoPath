package model;

import java.util.*;
import model.Player.Dir;

public class Position {

	
	
    //========================================
	//=== ATTRIBUTES
	
	private ArrayList<Item> items = new ArrayList<>();
        private ArrayList<Item> droppedItems = new ArrayList<>();

	private Room room;
	private String description;
        private LayoutPosition layout;

    public LayoutPosition getLayout() {
        return layout;
    }

    public void setLayout(LayoutPosition layout) {
        this.layout = layout;
    }
	
	HashMap<Dir, Position> neighbors = new HashMap<> ();
		
	//========================================
	//=== CONSTRUCTORS	
	
	public Position(Room r) {
		neighbors.put(Dir.E, null);
		neighbors.put(Dir.W, null);
		neighbors.put(Dir.N, null);
		neighbors.put(Dir.S, null);
		
		this.room = r;
	}
		
	public Position() {
		this(null);
	}	
	
	//========================================
	//=== GETTERS AND SETTERS
        
	public void setNeighbor(Dir dir, Position pos) {
		this.neighbors.put(dir, pos);
	}
	
    public Position getNeighbor(Dir d) {
		return neighbors.get(d);
	}

	public void setRoom(Room room) {
    	this.room = room;
	}
        
    public Room getRoom() {
		return this.room;
	}
        
    public void setItems(ArrayList<Item> items) {
		this.items = items;
	}
    
	public ArrayList<Item> getItems() {
		return items;
	}
	
	public String getDescription() {
            if(droppedItems.isEmpty()) {
		return description;
            } else {
                String addon = ""; 
                for(Item item : droppedItems) {
                    addon = addon + " - " + item.getItemName() + "\n";
                }
                return description + "\nItems you can pick up again : " + addon;
            }
	}

	public void setDescription(String description) {
            this.description = description;
	}
        
        public ArrayList<Item> getDroppedItems() {
            return droppedItems;
        }

		
	//========================================
	//=== METHODS
        
        public void addItem(Item item) {
		items.add(item);
	}
	
	/**
	 * Returns the neighboring position
	 * according to the direction.
	 * @param d
	 * @return
	 */
	public Position go(Dir d) {
		return neighbors.get(d);
	}
        
        public void addDroppedItem(Item item) {
            droppedItems.add(item);
        }
        
        public void removeItem(Item item) {
            items.remove(item);
            if(droppedItems.contains(item)) {
                droppedItems.remove(item);
            }
        }
}
