package model;

import static controller.GeneralController.itemMsg;

public class Safe extends Item {
	
	//========================================
	//=== ATTRIBUTES ET CONSTANTS
	
	private final boolean DEFAULT_STATUS = true;
	
	private int secretCode;
	private boolean locked = DEFAULT_STATUS;
	
	//========================================
	//=== CONSTRUCTOR
	
	public Safe(String name, String desc, String content, Room r, Position pos, boolean pick, int code, Door clue) {
		super(name, desc, content, r, pos, pick, clue);
		this.locked = DEFAULT_STATUS; //=== locked.
		this.secretCode = code;
	}
	
	public Safe() {
		this("unnamedsafe", "no desc", "no content", null, null, false, 0, null);
	}

	//========================================
	//=== GETTERS AND SETTERS
	
	public void setSecretCode(int code) {
		this.secretCode = code;
	}
	
	public int getSecretCode() {
		return this.secretCode;
	}
	
	public boolean isSafeLocked() {
		return this.locked;
	}

	//========================================
	//=== METHODS
	
	public void lockSafe() {
		if(this.isSafeLocked() == false)
			this.locked = true;
	}
	
	public void unlockSafe(int code) {
		if(code == this.getSecretCode()) {
                    this.locked = false;
                    itemMsg = "Safe unlocked!";
                }
		else
			itemMsg = "Incorrect code!";
	}
}
