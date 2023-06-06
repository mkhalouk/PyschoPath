package model;

public class Key extends Item {
	
	//========================================
	//=== CONSTRUCTOR
	
	public Key(String name, String desc, String content, Room r, Position pos, boolean pick, Door clue) {
		super(name, desc, content, r, pos, pick, clue);
		
	}
	
	public Key() {
		this("unnamedkey", "no desc", "no content", null, null, true, null);
	}
}
