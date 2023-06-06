package model;

import java.util.*;

public class Room {
	
	//========================================
	//=== ATTRIBUTS
	
	private ArrayList<Door> doors = new ArrayList<>();
	private String name;
	private ArrayList<Position> positions = new ArrayList<>();

    
	private ArrayList<Item> items = new ArrayList<>();
	
	//========================================
	//=== CONSTRUCTOR

	public Room(int n, ArrayList<Item> items, String name) {
		for(int i = 0; i < n; i++) {
			this.positions.add(new Position(this));
		}
		this.items = items;
		this.name = name;
	}
	
	public Room() {
		this(0, new ArrayList<Item>(), "Untitled");
	}
	
	//========================================
	//=== GETTERS AND SETTERS
	
	public String getNameRoom() {
		return name;
	}

	public void setNameRoom(String name) {
		this.name = name;
	}

	
	public int getNbDoors() {
		return doors.size();
	}
	
	public void setDoors(ArrayList<Door> doors) {
		this.doors = doors;
	}
        
        public ArrayList<Position> getPositions() {
            return positions;
        }
	
	public ArrayList<Door> getDoors() {
		return this.doors;
	}
	
	public Position getPosition(int posIndex) {
		return positions.get(posIndex);
	}
	
	public void setPosition(Position pos) {
		this.positions.add(pos);
	}
	
	public ArrayList<Item> getItems() {
		return items;
	}
	
	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}
        
        public Item getItem(String name) {
            Item foundItem = null;
            for(Item item : this.getItems()) {
                if(item.getItemName().equals(name)) {
                    foundItem = item;
                }
            } 
            return foundItem;
        }
	
	//========================================
	//=== METHODS
	
	public void addDoor(Door d) {
		doors.add(d);
	}

	public void removeDoor(Door d) {
		doors.remove(doors.indexOf(d));
	}

	public void addItem(Item item) {
		items.add(item);
	}
	
	public void removeItem(Item item) {
		items.remove(item);
	}
	
	public boolean isRoomEmpty() {
		return items.isEmpty();
	}

}
