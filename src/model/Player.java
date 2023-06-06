package model;

import static controller.GeneralController.itemMsg;

public class Player {
	
	//========================================
	//=== ATTRIBUTES
	
	private String name;
	private Room myLoc;
	private Position myPos;
	private Item[] inventory = new Item[50];
	private int itemCount = 0;
	public enum Dir {E, W, N, S} 

	//========================================
	//=== CONSTRUCTOR
	
	public Player(String name, Room r, Position pos) {
		this.name = name;
		this.myLoc = r;
		this.myPos = pos;
	}
	
	public Player() {
		this(null, null, null);
	}
	
	//========================================
	//=== GETTERS AND SETTERS
	
	public String getNamePlayer() {
		return name;
	}

	public void setNamePlayer(String name) {
		this.name = name;
	}

	public void setLoc(Room r) {
		myLoc = r;
	}
	
	public Room getLoc() {
		return myLoc;
	}
	
	public void setPos(Position p) {
		myPos = p;
	}
	
	public Position getPos() {
		return myPos;
	}
	
	public void setInventory(Item[] inventory) {
		this.inventory = inventory;
	}

	public Item[] getInventory() {
		return this.inventory;
	}
	
	public Item getInventory(int ind) {
		return this.inventory[ind];
	}

	public int getItemCount() {
		return itemCount;
	}
	
	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}

	//========================================
	//=== METHODS


	public String inspect() {
		return myPos.getDescription();
	}

	
	/**
	 * The player picks an item if it's
	 * in the same room as him, and we
	 * remove the item from the room.
	 * @param itemName
         * @return 
	 */
	public boolean pickup(String itemName) {
            
            boolean picked = false;
            for(Item i : myLoc.getItems()) {
                    if(itemName.equals(i.getItemName())) {
                            if(i.isPickable() && i.getPosition() == this.getPos()) {
                                    //== We add it at the end
                                    // and we increase the item's
                                    // number
                                    inventory[itemCount] = i;
                                    itemCount++;

                                    //=== If we have the clue item
                                    // we unlock the corresponding door
                                    if(i.getClueFor() != null) {
                                            i.getClueFor().levelPassed(this, i);
                                    }

                                    //=== We remove the item from the room
                                    myLoc.removeItem(i);
                                    myPos.removeItem(i);
//					System.out.println(itemName + " taken.");
                                    itemMsg = itemMsg + " \n- " + itemName + " taken.";
                                    picked = true;
                            } else if(i.isPickable() && i.getPosition() != this.getPos()) {
                                itemMsg = "You are too far!";
                            }
                            break;
                    }
            }
            return picked;
	}
	
	/**
	 * We drop the item from the inventory
	 * with the index.
	 * @param itemInd
	 */
	public void drop(int itemInd) {
		
            //=== We drop the item and add it to the room
            if(itemInd > 0 && itemInd <= itemCount) {
                myLoc.addItem(inventory[itemInd-1]);
                myPos.addItem(inventory[itemInd-1]);
                inventory[itemInd-1].setRoom(myLoc);
                inventory[itemInd-1].setPosition(myPos);

                //=== We update the position's description
                myPos.addDroppedItem(inventory[itemInd-1]);

                //=== We look through our inventory if the item
                // is there and then we drop it, and its position
                // is replaced by the next item until we displace
                // the last item.
                for(int i = itemInd-1; i < itemCount; i++) {
                        if(i != itemCount - 1) {
                                inventory[i] = inventory[i+1];
                        } else {
                                //=== Last case will be empty
                                inventory[i] = null;					
                        }
                }
                //=== The item's number decreases
                itemCount--;
            }
	}
	
	public boolean inventoryEmpty() {
		//=== We test if the item's number
		// in the inventory equals 0
		return itemCount == 0;
	}
	
	/**
	 * Changes the player's position
	 * to the neighboring one according
	 * to the direction.
	 * @param d
	 */
	public void go(Dir d) {
		Position pos = this.getPos();
		Position neighbor = pos.go(d);
		if (neighbor != null)
			this.setPos(pos.go(d));
	}
	
	/**
	 * Tests if the door is within reach
	 * and calls the "useDoor" method of
	 * the Door Class.
	 * @param doorName
         * @return if door is locked or not
	 */
	public boolean useDoor(String doorName) {
            
		boolean found = false;
		boolean locked = false;
		for(Door d : myLoc.getDoors()) {
			if(doorName.equals(d.getName())) {
				if(this.myLoc == d.getRooms(0)) {
					if(this.myPos == d.getDoorPosition(0)) {
						locked = d.useDoor(this);
					}
					else {
                                            locked = false;
					}					
				}
				else {
					if(this.myPos == d.getDoorPosition(1)) {
						locked = d.useDoor(this);
					}
					else {
                                            locked = false;
					}
				}
				found = true;
				break;
			}
		}
		if(!found) {
			System.out.println("Door not found!");
		}
                
                return locked;
	}
	
	/**
	 * Tests if the two items are in the 
	 * inventory and calls the "useWithItem"
	 * method from the Item Class.
	 * @param itemName1
	 * @param itemName2
	 */
	public void useWithItem(String itemName1, String itemName2) {

                for(Item i1 : this.inventory) {
                        if(itemName1.equals(i1.getItemName())) {
                                for(Item i2 : this.inventory) {
                                        if(itemName2.equals(i2.getItemName())) {
                                                i1.useWithItem(i2);
                                                break;
                                        }
                                }
                                break;
                        }
                        else {
                                //=== We test if the other item is in the inventory (optional)
                                for(Item i2 : this.inventory) {
                                        if(itemName2.equals(i2.getItemName())) {
                                                break;
                                        }
                                }
                        }
                }

	}
	
	/**
	 * It's for unlocking a chest or the journal with a key.
	 * @param itemName1
	 * @param itemName2
	 */
	public void unlockChest(String itemName1, String itemName2) {
            for(Item i1 : this.inventory) {
                    if(itemName1.equals(i1.getItemName())) {
                            for(Item i2 : this.myLoc.getItems()) {
                                    if(itemName2.equals(i2.getItemName())) {
                                            ((Key)i1).useWithItem((Chest)i2);
                                            break;
                                    }
                            }
                            break;
                    }
                    else {
                            //=== We test if the other item is in the inventory (optional)
                            for(Item i2 : this.myLoc.getItems()) {
                                    if(itemName2.equals(i2.getItemName())) {
                                            break;
                                    }
                            }
                    }
            }
	}
	
	/**
	 * This method is for unlocking a safe with a code
	 * @param code
	 * @param itemName
	 */
	public void unlockSafe(int code, String itemName) {
		boolean found = false;
		for(Item i : this.myLoc.getItems()) {
			if(itemName.equals(i.getItemName())) {
				((Safe)i).unlockSafe(code);
				found = true;
				break;
			}
		}
		if(!found) {
			System.out.println(itemName + "not found!");
		}
	}
	
	/**
	 * This method is for unlocking a door with a key
	 * @param itemName
	 * @param doorName
	 */
	public void unlockDoor(String itemName, String doorName) {
            for(Item i : this.inventory) {
                    if(itemName.equals(i.getItemName())) {
                            for(Door d : this.myLoc.getDoors()) {
                                    if(doorName.equals(d.getName())) {
                                            d.unlockDoor((Key)i);
                                            break;
                                    }
                            }
                            break;
                    }
                    else {
                            //=== We test if the door is near
                            for(Door d : this.myLoc.getDoors()) {
                                    if(doorName.equals(d.getName())) {
                                            break;
                                    }
                            }
                    }
            }

	}
}

