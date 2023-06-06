package model;

//import java.util.*;

import controller.GeneralController;


public class Chest extends Item implements Lockable {

	//========================================
	//=== ATTRIBUTES
	
	private final boolean DEFAULT_STATUS = true;
	
	private Key key;
	private boolean locked;
	
	//========================================
	//=== GETTERS AND SETTERS
	
	public void setKey(Key k) {
		key = k;
	}
	
	public Key getKey() {
		return this.key;
	}
	
	//========================================
	//=== CONSTRUCTORS
	
	public Chest(String name, String desc, String content, Room r, Position pos, boolean pick, Key k, Door clue) {
		super(name, desc, content, r, pos, pick, clue);
		this.locked = DEFAULT_STATUS;
		this.key = k;
	}
	
	public Chest() {
		this("unnamed", "no desc", "no content", null, null, false, null, null);
	}

	
	//========================================
	//=== METHODS
	
	@Override
	public boolean isLocked() {
		return this.locked;
	}
	
	@Override
	public void lock() {
		if(this.locked == false)
			this.locked = true;
		else
			System.out.println("It is already locked!");
	}
	
	public void unlockChest(Key k) {
		if(k == this.getKey()) {
                    this.locked = false;
                    GeneralController.itemMsg = this.getItemName() + " unlocked!";
                }
                else {
                    GeneralController.itemMsg = "The key doesn't match!";
                }
	}
	
}
