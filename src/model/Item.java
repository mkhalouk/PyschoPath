package model;

import controller.GeneralController;

public class Item {
	
	//========================================
	//=== ATTRIBUTES
	
	public String description;
	public String name;
	public String content;
	private Room room;
	private Position position;
	private boolean pickable;
	private Door clueFor;
	
	//========================================
	//=== CONSTRUCTOR
	
	public Item(String name, String desc, String content, Room r, Position pos, boolean pick, Door clue) {
		this.name = name;
		this.description = desc;
		this.content = content;
		this.room = r;
		this.position = pos;
		this.pickable = pick;
		this.clueFor = clue;
	}
	
	public Item() {
		this("unnamed", null, null, null, null, false, null);
	}
	
	//========================================
	//=== GETTERS AND SETTERS
	
	public Door getClueFor() {
		return clueFor;
	}

	public void setClueFor(Door clueFor) {
		this.clueFor = clueFor;
	}

	public void setItemName(String name) {
		this.name = name;
	}
	
	public String getItemName() {
		return this.name;
	}
	
	public void setItemContent(String cont) {
		this.content = cont;
	}
	
	public String getItemContent() {
		return this.content;
	}
	
	public void setItemDesc(String desc) {
		this.description = desc;
	}
	
	/**
	 * Returns the item's description combined with its content
	 * (If it's not a locked chest or safe).
	 * @return String
	 */
	public String getItemDesc() {
		String contentItem = "";
        if(this.getItemName().equals("post-it") || this.getItemName().equals("note")) {
            contentItem = "";
        } 
        else {
        	if(this instanceof Chest) {
        		if(!((Chest)this).isLocked()) {
        			contentItem = getItemContent();        		
        		}
        		else {
        			contentItem = "This is locked!\n";
        		}
        	}
        	else if(this instanceof Safe) {
        		if(!((Safe)this).isSafeLocked()) {
        			contentItem = getItemContent();
        		}
        		else {
        			contentItem = "This safe is locked!\n";
        		}
        	}
        	else {
        		contentItem = getItemContent();
        	}
        }
        return this.description + contentItem;
    }
	
	public void setRoom (Room room) {
		this.room = room;
	}
	
	public void setPosition (Position position) {
		this.position = position;
	}
	
	public Room getRoom () {
		return this.room;
	}
	
	public Position getPosition () {
		return this.position;
	}

	public void setPickable(boolean pickable) {
		this.pickable = pickable;
	}

	public boolean isPickable() {
		return pickable;
	}
	
	//========================================
	//=== METHODS
	
	/**
	 * If the method is called by a pen it can show the content of
	 * a post-it or a note.
	 * If it's called by a chest or a key, we unlock the chest with
	 * the key if it matches.
	 * @param item
	 */
	public void useWithItem(Item item) {
		if(this.name.equals("pen")) {			
			if((item.name).equals("post-it") || (item.name).equals("note")) {
                            GeneralController.itemMsg = item.content + ". ";
			}
			else {
                            GeneralController.itemMsg = "You cannot use " + this.name + " with " + item.name + ". ";
			}
		}
		else {
			if(item instanceof Chest && this instanceof Key) {
                            ((Chest)item).unlockChest((Key)this);
			}
			else if(item instanceof Key && this instanceof Chest) {
                            ((Chest)this).unlockChest((Key)item);
			}
			else {
                            GeneralController.itemMsg = "You cannot use " + this.name + ". ";
			}
		}
	}
}
