package model;

import static controller.GeneralController.itemMsg;

public class Door implements Lockable {
	
	//========================================
	//=== ATTRIBUTES
	
	private final boolean DEFAULT_STATUS = true;
	
	private boolean locked = DEFAULT_STATUS;
	private String name;
	
	private Room[] rooms = new Room[2];
	public Key key;
	private Position[] positions = new Position[2];
	
	//========================================
	//=== CONSTRUCTORS
	
	public Door(String name, Room r1, Room r2, boolean locked) {
		this.name = name;
		this.rooms[0] = r1;
		this.rooms[1] = r2;
		this.locked = locked;
	}
	
	public Door(String name, Room r1, Room r2, boolean locked, Key k) {
		this.name = name;
		this.rooms[0] = r1;
		this.rooms[1] = r2;
		this.locked = locked;
		this.key = k;
	}
	
	public Door() {
		this("unnameddoor", null, null, true, null);
	}


	//========================================
	//=== GETTERS AND SETTERS
	
	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public Room getRooms(int roomIndex) {
		return rooms[roomIndex];
	}

	public void setRooms(int roomIndex, Room room) {
		//=== Either roomIndex equals 1 or 2
		this.rooms[roomIndex] = room;
		room.addDoor(this);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Position getDoorPosition(int posIndex) {
		return positions[posIndex];
	}
	
	//=== Either posIndex equals 1 or 2
	public void setDoorPosition(int posIndex, Position pos) {
		this.positions[posIndex] = pos;
	}

	//========================================
	//=== METHODS
	
	/**
	 * It changes the player's position
	 * to the neighboring room.
	 * @param p
         * @return if door is locked or not
	 */
	public boolean useDoor(Player p) {
            boolean lockedDoor;
            
		if(!isLocked()) {
			if(p.getLoc() == rooms[0]) {
				p.setLoc(this.rooms[1]);
				p.setPos(this.positions[1]);
			} 
			else {
				if (p.getLoc() == rooms[1]) {
					p.setLoc(this.rooms[0]);
					p.setPos(this.positions[0]);
				} 
			}
                        lockedDoor = false;
		}
		else {
                    lockedDoor = true;
		}
                
                return lockedDoor;
	}
	
	@Override
	public boolean isLocked() {
		return locked;
	}
	
	@Override
	public void lock() {
		if(!isLocked())
			locked = true;
		else
			itemMsg = "The door is already locked.";
	}
	
	public void unlockDoor() {
		if(!isLocked())
			itemMsg = "The door is already unlocked.";
		else
			this.locked = false;
	}
	
	public void unlockDoor(Key k) {
		if(!isLocked())
			itemMsg = "The door is already unlocked.";
		else if(this.key == k)
		{
			this.locked = false;
                        itemMsg = "Door successfully unlocked! ";
		}
	}
	
	/**
	 * As soon as the player has the right clues
	 * the next level is unlocked and another
	 * room is opened. (Except for Room 23,
	 * which requires a key).
	 * @param p
	 * @param item
	 */
	public void levelPassed(Player p, Item item) {
		
		for(Item i : p.getInventory()) {
			if(item == i) {
				this.unlockDoor();
				if(!(this.getRooms(0).getNameRoom().equals("Room 23 [Crime Scene n.6 - Final Scene]"))
						|| !(this.getRooms(0).getNameRoom().equals("Room 23 [Crime Scene n.6 - Final Scene]")))
				{
					if(this.getRooms(0).getNameRoom().equals("corridor")) {
                                                itemMsg = "Another crime has been commited in: " + this.getRooms(1).getNameRoom();
						break;
					} 
					else {
						itemMsg = "Another crime has been commited in: " + this.getRooms(0).getNameRoom();
						break;					
					}
				}
			}
		}
	}

}
