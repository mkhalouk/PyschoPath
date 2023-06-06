package controller;

import java.util.ArrayList;
import model.Chest;
import model.Door;
import model.Item;
import model.Key;

import model.Player.Dir;
import model.Position;
import model.Room;
import model.Safe;
import model.UVPen;

//=== Class for starting the game, initialize the world ...

public class Game {	
    
    // Method to set the positions' neighbours
    public static void setNeighbours(Position p1, Position p2, Dir d1, Dir d2) {
        p1.setNeighbor(d1, p2);
        p2.setNeighbor(d2, p1);
    }
	
	public static Room createGame() {	
		
		//=== ATTRIBUTES
		
		//=== Create and initialize the items
		ArrayList<Item> livingRoomItems = new ArrayList<>();
		ArrayList<Item> corridorItems = new ArrayList<>();
		ArrayList<Item> diningRoomItems = new ArrayList<>();
		ArrayList<Item> room17Items = new ArrayList<>();
		ArrayList<Item> room19Items = new ArrayList<>();
		ArrayList<Item> room21Items = new ArrayList<>();
		ArrayList<Item> room18Items = new ArrayList<>();
		ArrayList<Item> room22Items = new ArrayList<>();
		ArrayList<Item> room23Items = new ArrayList<>();

		
		//=== Create the rooms ===//
		Room livingRoom = new Room(4, livingRoomItems, "Living Room");
		Room corridor = new Room(10, corridorItems, "Corridor");
		Room diningRoom = new Room(5, diningRoomItems, "Dining Room");
		Room room17 = new Room(1, room17Items, "Room 17 [Crime Scene n.1]");
		Room room19 = new Room(1, room19Items, "Room 19 [Crime Scene n.2]");
		Room room21 = new Room(1, room21Items, "Room 21 [Crime Scene n.3]");
		Room room18 = new Room(1, room18Items, "Room 18 [Crime Scene n.4]");
		Room room22 = new Room(1, room22Items, "Room 22 [Crime Scene n.5]");
		Room room23 = new Room(1, room23Items, "Room 23 [Crime Scene n.6 - Final Scene]");
		
		//=== Create the doors ===//
		
		//=== Create the door (d1) that connects Living Room and Corridor
		Door d1 = new Door("doorlr", livingRoom, corridor, false);
		livingRoom.addDoor(d1);
		corridor.addDoor(d1);
		d1.setDoorPosition(0, livingRoom.getPosition(3));
		d1.setRooms(0, livingRoom);
		d1.setDoorPosition(1, corridor.getPosition(2));
		d1.setRooms(1, corridor);
		
		//=== Creating the door (d2) that connects Dining Room and Corridor
		Door d2 = new Door("doordr", diningRoom, corridor, false);
		diningRoom.addDoor(d2);
		corridor.addDoor(d2);
		d2.setDoorPosition(0, diningRoom.getPosition(0));
		d2.setRooms(0, diningRoom);
		d2.setDoorPosition(1, corridor.getPosition(3));
		d2.setRooms(1, corridor);
		
		//=== Creating the door (d3) that connects Room 17 and Corridor
		Door d3 = new Door("door17", room17, corridor, false);
		room17.addDoor(d3);
		corridor.addDoor(d3);
		d3.setDoorPosition(0, room17.getPosition(0));
		d3.setRooms(0, room17);
		d3.setDoorPosition(1, corridor.getPosition(0));
		d3.setRooms(1, corridor);
		
		//=== Creating the door (d4) that connects Room 19 and Corridor
		Door d4 = new Door("door19", room19, corridor, true);
		room19.addDoor(d4);
		corridor.addDoor(d4);
		d4.setDoorPosition(0, room19.getPosition(0));
		d4.setRooms(0, room19);
		d4.setDoorPosition(1, corridor.getPosition(4));
		d4.setRooms(1, corridor);

		//=== Creating the door (d5) that connects Room 21 and Corridor
		Door d5 = new Door("door21", room21, corridor, true);
		room21.addDoor(d5);
		corridor.addDoor(d5);
		d5.setDoorPosition(0, room21.getPosition(0));
		d5.setRooms(0, room21);
		d5.setDoorPosition(1, corridor.getPosition(6));
		d5.setRooms(1, corridor);
		
		//=== Creating the door (d6) that connects Room 18 and Corridor
		Door d6 = new Door("door18", room18, corridor, true);
		room18.addDoor(d6);
		corridor.addDoor(d6);
		d6.setDoorPosition(0, room18.getPosition(0));
		d6.setRooms(0, room18);
		d6.setDoorPosition(1, corridor.getPosition(1));
		d6.setRooms(1, corridor);
		
		//=== Creating the door (d7) that connects Room 22 and Corridor
		Door d7 = new Door("door22", room22, corridor, true);
		room22.addDoor(d7);
		corridor.addDoor(d7);
		d7.setDoorPosition(0, room22.getPosition(0));
		d7.setRooms(0, room22);
		d7.setDoorPosition(1, corridor.getPosition(7));
		d7.setRooms(1, corridor);
		
		//=== Creating the door (d8) that connects Room 23 and Corridor
		Door d8 = new Door("door23", room23, corridor, true);
		room23.addDoor(d8);
		corridor.addDoor(d8);
		d8.setDoorPosition(0, room23.getPosition(0));
		d8.setRooms(0, room23);
		d8.setDoorPosition(1, corridor.getPosition(8));
		d8.setRooms(1, corridor);
		
		//==============================================================================================//
		//																								//
		//  Set the rooms positions with item creation and adding them to their corresponding rooms 	//
		//																								//
		//==============================================================================================//
		
		//=========================================================================//
		//=== Room 1 : Living Room
		//=== Set positions in the room and their neighbors
                setNeighbours(livingRoom.getPosition(0), livingRoom.getPosition(3), Dir.S, Dir.N);
                setNeighbours(livingRoom.getPosition(2), livingRoom.getPosition(3), Dir.S, Dir.E);
                setNeighbours(livingRoom.getPosition(1), livingRoom.getPosition(3), Dir.S, Dir.W);
                setNeighbours(livingRoom.getPosition(0), livingRoom.getPosition(2), Dir.E, Dir.W);
                setNeighbours(livingRoom.getPosition(0), livingRoom.getPosition(1), Dir.W, Dir.E);
                                
                PositionsInit.initializePositions(livingRoom.getPositions(), livingRoom.getNameRoom());

		//=== Create and set items positions
		Item couchP1 = new Item("white_couch", "It’s just an old white couch, nothing to see here.", "", livingRoom, livingRoom.getPosition(1), false, null);
		Item tvStand = new Item("tv", "That’s a pretty large TV.\n", "Someone left a notebook on the stand.", livingRoom, livingRoom.getPosition(1), false, null);
		Item notebook = new Item("notebook", "Nothing special in this notebook.", "", livingRoom, livingRoom.getPosition(1), true, null);
		Item chair1 = new Item("chair1", "It’s just an old chair...", "Oh wait, there's a pen sticking out of the cushions.", livingRoom, livingRoom.getPosition(2), false, null);
		Item chair2 = new Item("chair2", "Just a regular chair, nothing to see here.", "", livingRoom, livingRoom.getPosition(2), false, null);
		Item uvpen = new UVPen("pen", "It looks like a normal pen. But it has a UV light.", "", livingRoom, livingRoom.getPosition(2), true, null);
		Item coffeTable = new Item("coffe_table", "There are some magazines and yesterday’s newspapers on it.", "", livingRoom, livingRoom.getPosition(2), false, null);
		Item bookshelf = new Item("bookshelf", "Nothing interesting here… There a few old books.", "", livingRoom, livingRoom.getPosition(3), false, null);
		Item miniChair = new Item("miniChair", "Do you want to have a seat? No time for that, there's a quest that must be solved.", "", livingRoom, livingRoom.getPosition(2), false, null);
		
		//=== Add items to the room
		livingRoom.addItem(couchP1);
		livingRoom.addItem(chair1);
		livingRoom.addItem(chair2);
		livingRoom.addItem(uvpen);
		livingRoom.addItem(tvStand);
		livingRoom.addItem(notebook);
		livingRoom.addItem(coffeTable);
		livingRoom.addItem(bookshelf);
		livingRoom.addItem(miniChair);
		
		//=== Description depending on the position
		livingRoom.getPosition(0).setDescription(
				"You are in the living room. Everything is quiet.\n"  
				+ "On your left side is a white couch facing a big TV stand.\n"
				+ "On your right side you can see what looks like a small\n"
				+ "and cozy tea corner, facing two large walls of fully stacked\n"
				+ "bookshelves.\n"
				+ "The double doors of this well-lit room seem to be leading\n"
				+ "somewhere darker."
				);
		
		livingRoom.getPosition(1).setDescription(
				"You are on the TV side there is a white couch facing a big TV stand.\n"
				+ "On the other side you can see what looks like a small\n"
				+ "and cozy tea corner, facing two large walls of fully stacked\n"
				+ "bookshelves."
				);

		livingRoom.getPosition(2).setDescription(
				"You are in the tea area facing two large walls of fully stacked\n"
				+ "bookshelves. Far on your left side you can see a red couch facing\n"
				+ "a big TV stand. At your feet is a small coffee table."
				);
		
		livingRoom.getPosition(3).setDescription(
				"You are now standing in front of the bookshelves.\n"
				+ "The books look old and uninteresting.\n"
				+ "Behind you is the tea corner.\n"
				+ "The door to the corridors is just beside you.\n"
				+ "That must be the way to the rooms."
				);
		
		//=========================================================================//
		//=== Room 2 : Corridor
		//=== Set positions in the corridor and their neighbors
                
                setNeighbours(corridor.getPosition(0), corridor.getPosition(1), Dir.S, Dir.N);
                setNeighbours(corridor.getPosition(0), corridor.getPosition(2), Dir.W, Dir.E);
                
                setNeighbours(corridor.getPosition(1), corridor.getPosition(3), Dir.W, Dir.E);
                
                setNeighbours(corridor.getPosition(2), corridor.getPosition(3), Dir.S, Dir.N);
                setNeighbours(corridor.getPosition(2), corridor.getPosition(4), Dir.W, Dir.E);
                
                setNeighbours(corridor.getPosition(3), corridor.getPosition(5), Dir.W, Dir.E);
                
                setNeighbours(corridor.getPosition(4), corridor.getPosition(5), Dir.S, Dir.N);
                setNeighbours(corridor.getPosition(4), corridor.getPosition(6), Dir.W, Dir.E);
                
                setNeighbours(corridor.getPosition(5), corridor.getPosition(7), Dir.W, Dir.E);
                
                setNeighbours(corridor.getPosition(6), corridor.getPosition(7), Dir.S, Dir.N);
                setNeighbours(corridor.getPosition(6), corridor.getPosition(8), Dir.W, Dir.E);
                
                setNeighbours(corridor.getPosition(7), corridor.getPosition(9), Dir.W, Dir.E);

                setNeighbours(corridor.getPosition(8), corridor.getPosition(9), Dir.S, Dir.N);
                
                PositionsInit.initializePositions(corridor.getPositions(), corridor.getNameRoom());
                
		
		//=== Description depending on the position
		corridor.getPosition(0).setDescription(
					"You are near Room 17."
				);
		
		corridor.getPosition(1).setDescription(
				"You are near Room 18."
				);

		corridor.getPosition(2).setDescription(
				"You are in the corridor.\n"
				+ "The walls are a bit old and dilapidated.\n"
				+ "The monotony of this place is deranged only\n" 
				+ "by the large dining room door, facing the\n"
				+ "living room, the only source of light for this\n"
				+ "dark corridor.\n"
				+ "Everywhere else along the way are exactly\n"
				+ "looking doors from either side.\n"
				+ "You are near the Living Room"
				);
		
		corridor.getPosition(3).setDescription(
				"You are near the Dining Room."
				);
		
		corridor.getPosition(4).setDescription(
				"You are near Room 19."
				);
		
		corridor.getPosition(5).setDescription(
				"You are near Room 20."
				+ "(This is just an unoccupied room.\nNot interesting...)"
				);
		
		corridor.getPosition(6).setDescription(
				"You are near Room 21."
				);
		
		corridor.getPosition(7).setDescription(
				"You are near Room 22."
				);
		
		corridor.getPosition(8).setDescription(
				"You are near Room 23."
				);
		
		corridor.getPosition(9).setDescription(
				"You are near Room 24.\n"
				+ "(This is just an unoccupied room.\nNot interesting...)"
				);
		
		//=========================================================================//
		//=== Room 3 : Dining Room
		//=== Set positions in the room and their neighbors
                setNeighbours(diningRoom.getPosition(0), diningRoom.getPosition(3), Dir.S, Dir.N);
                setNeighbours(diningRoom.getPosition(0), diningRoom.getPosition(2), Dir.E, Dir.W);
                setNeighbours(diningRoom.getPosition(0), diningRoom.getPosition(1), Dir.W, Dir.E);
                
                setNeighbours(diningRoom.getPosition(3), diningRoom.getPosition(2), Dir.E, Dir.W);
                
                PositionsInit.initializePositions(diningRoom.getPositions(), diningRoom.getNameRoom());
		
		//=== Create and set items positions
		Item chestDiningRoom = new Item("chest","Just a bunch of papers... ", "Oh wait, there's a post-it ", diningRoom, diningRoom.getPosition(2), false, null);
		Item postit = new Item("post-it", "It is empty. What could it be for? ", "Chests aren't just for pirate treasures, let the key show you why...", diningRoom, diningRoom.getPosition(2), true, null);
		Item cupboard = new Item("cupboard", "Just a kitchen cupboard... ", "There's a key.\nWhat's it doing here between the forks and knives?", diningRoom, diningRoom.getPosition(2), false, null);
		Item keyDr = new Key("keydr", "I wonder what is this key for... ", "", diningRoom, diningRoom.getPosition(2), true, null);
		Item diningTable = new Item("table", "Just some leftovers. They should clean this up. ", "", diningRoom, diningRoom.getPosition(3), false, null);
		Item fireplace = new Item("fireplace", "It seems they didn’t light it up for a while. ", "But there's some wood here. ", diningRoom, diningRoom.getPosition(4), false, null);
		Item sink = new Item("sink", "The sink looks pretty clean. \nJust some freshly washed tea cups.", "", diningRoom, diningRoom.getPosition(4), false, null);
		Item chair = new Item("chair", "Looks comfortable... ", "", diningRoom, diningRoom.getPosition(1), false, null);
		
		//=== Add items to the room
		diningRoom.addItem(chestDiningRoom);
		diningRoom.addItem(postit);
		diningRoom.addItem(cupboard);
		diningRoom.addItem(keyDr);
		diningRoom.addItem(diningTable);
		diningRoom.addItem(fireplace);
		diningRoom.addItem(sink);
		diningRoom.addItem(chair);
		
		//=== Description depending on the position
		diningRoom.getPosition(0).setDescription(
				"You are in the dining room, a big space.\n"
				+ "It's elegant and minimalistic, "
				+ "yet looks a little depressing compared to the beautiful " 
				+ "weather outside the manor, maybe it's because of "
				+ "the murder atmosphere...\n"
				+ "In front of you, at the center, there is a "
				+ "big dining table. "
				+ "On your left stands a large cupboard "
				+ "that contains all the dishes.\n"
				+ "You can see a chest full of papers right beside it. "
				+ "Maybe we have a writer here...\n"
				+ "On your left is a large fireplace.\n"
				+ "The door to the corridor is right behind you."
				);
		
		diningRoom.getPosition(1).setDescription(
                        "Just a fireplace"
				);
		
		diningRoom.getPosition(2).setDescription(
				"You are in front of a cupboard... " +
				"There's a chest full of papers"
				);
		
		diningRoom.getPosition(3).setDescription(
				"You can see the big table in here!"
				);
		
		
		//=========================================================================//
		//=== Room 4 : Room 17 (Crime Scene n.1)
		//=== Create and set items positions
		Item bed17 = new Item("bed", "Having the windows open was clearly not enough\nto fight this unpleasant odour of death and old sweat.", "", room17, room17.getPosition(0), false, null);
		Item nightstand17 = new Item("nightstand", "Just a normal nightstand.", "", room17, room17.getPosition(0), false, null);
		Item closet17 = new Item("closet", "It’s full of clothes.", "\nNothing interesting to see...", room17, room17.getPosition(0), false, null);
		Item chest17 = new Chest("chest", "That's a pretty big chest. ", " It's a paper that contains the following message:\nNumbers are my best friends.\nWith them, I discovered Nature's Secret Code\n8 13 .. 55\n", room17, room17.getPosition(0), false, (Key)keyDr, null);
		Item paper17 = new Item("paper17", "Numbers are my best friends.\nWith them, I discovered Nature's Secret Code.\n", "8 13 .. 55\n", room17, room17.getPosition(0), true, d4);
		Item desk17 = new Item("desk", "A pretty neat desk, just some newspapers and two pen holders.", "", room17, room17.getPosition(0), false, null);
		Item newspapers17 = new Item("newspapers", "This newspaper is outdated, it's from a month ago", "", room17, room17.getPosition(0), false, null);
		Item penholder17 = new Item("pen_holder", "Just some random pens, nothing interesting here.", "", room17, room17.getPosition(0), true, null);
		Item window17 = new Item("window", "This window is pretty large.\nWhat an amazing view.", "", room17, room17.getPosition(0), false, null);
		Item commode17 = new Item("commode", "Just a simple dresser, nothing to see here.", "", room17, room17.getPosition(0), false, null);
		
		//=== Add items to the room
		room17.addItem(bed17);
		room17.addItem(nightstand17);
		room17.addItem(closet17);
		room17.addItem(chest17);
		room17.addItem(desk17);
		room17.addItem(paper17);
		room17.addItem(newspapers17);
		room17.addItem(penholder17);
		room17.addItem(window17);
		room17.addItem(commode17);
		
		//=== Description
		room17.getPosition(0).setDescription(
				"You are on your crime scene and you can start investigating.\n"
				+ "The sun is pouring through the large window.\n" 
				+ "The bed was left undone like you asked the staff to leave it.\n" 
				+ "Beside it is a nightstand.\n"
				+ "There’s a brown closet on the other side of the bed.\n"
				+ "On your left side, facing the bed, is a small and cluttered desk."
				);
                
                PositionsInit.initializePositions(room17.getPositions(), room17.getNameRoom());
                
		//=========================================================================//
		//=== Room 5 : Room 19 (Crime Scene n.2)
		
		//=== Secret code for the safe
		int secretCode = 2134;
		//=== Create and set items positions
		Item bed19 = new Item("bed", "This looks very comfortable.", "", room19, room19.getPosition(0), false, null);
		Item nightstand19 = new Item("nightstand", "Nothing special, just a glass of water and some painkillers.", "", room19, room19.getPosition(0), false, null);
		Item closet19 = new Item("closet", "It’s full of clothes.", "", room19, room19.getPosition(0), false, null);
		Item safe19 = new Safe("safe", "This safe looks good for hiding things. ", "It's a paper that contains the following message:\nHe that would catch fish, must venture his bait.\n\nBenjamin Franklin\n", room19, room19.getPosition(0), false, secretCode, null);
		Item paper19 = new Item("paper19", "\"He that would catch fish, must venture his bait.\"\nBenjamin Franklin\n", "", room19, room19.getPosition(0), true, d5);
		Item desk19 = new Item("desk", "This is a pretty clean desk. ", "There are two stacks of white paper with a couple of pencils.", room19, room19.getPosition(0), false, null);
		Item pencils19 = new Item("pencils", "Just regular pencils.", "", room19, room19.getPosition(0), false, null);
		Item stackpapers19 = new Item("papers", "That's a lot of papers... ", "", room19, room19.getPosition(0), false, null);
		Item curtains19 = new Item("red_curtains", "Just some red curtains.", "", room19, room19.getPosition(0), false, null);
                Item commode19 = new Item("commode", "Just a simple dresser, nothing to see here.", "", room19, room19.getPosition(0), false, null);
		
		//=== Add items to the room
		room19.addItem(bed19);
		room19.addItem(nightstand19);
		room19.addItem(closet19);
		room19.addItem(safe19);
		room19.addItem(desk19);
		room19.addItem(paper19);
		room19.addItem(pencils19);
		room19.addItem(stackpapers19);
		room19.addItem(curtains19);
		room19.addItem(commode19);
		
		//=== Description
		room19.getPosition(0).setDescription(
				"You are in the second crime scene and you can start investigating.\n"
				+ "The room is dark because of the heavy red curtains.\n"
				+ "There's a large bed with its matching nightstand.\n" 
				+ "There’s also a brown closet on the other side of the bed.\n"
				+ "On the left side, facing the bed, is a large desk with\n"
				+ "two neatly stacked piles of paper. Everything is overall tidy,\n"
				+ "except the bed where the victim was found."
				);
                
                PositionsInit.initializePositions(room19.getPositions(), room19.getNameRoom());
		
		//=========================================================================//
		//=== Room 6 : Room 21 (Crime Scene n.3)
		//=== Create and set items positions
		Item bed21 = new Item("bed", "There is a trace of blood.", "", room21, room21.getPosition(0), false, null);
		Item nightstand21 = new Item("nightstand", "A glass of water and some painkillers", "\nThere's a fishbowl.", room21, room21.getPosition(0), false, null);
		Item closet21 = new Item("closet", "Nothing special here.", "", room21, room21.getPosition(0), false, null);
		Item fishbowl21 = new Item("fishbowl", "There's a fishbowl on the nightstand. But there are no fish inside, that's weird...\n", "Oh, there's a key!\n", room21, room21.getPosition(0), false, null);
		Item key21 = new Key("key21", "I wonder what is this key for...", "", room21, room21.getPosition(0), true, null);
		Item journal21 = new Chest("journal", "", "You measure my life in hours and I serve you by expiring.\nI’m quick when I’m thin and slow when I’m fat.\nThe wind is my enemy.\nYour next clue is right above me.", room21, room21.getPosition(0), true, (Key)key21, d6);
		Item desk21 = new Item("desk", "There are some pencils ", "and a journal, a key is needed to see what's inside.", room21, room21.getPosition(0), false, null);
		Item window21 = new Item("window", "This window is pretty large. The view looks nice.", "", room21, room21.getPosition(0), false, null);
                Item commode21 = new Item("commode", "Just a simple dresser, nothing to see here.", "", room21, room21.getPosition(0), false, null);
		
		//=== Add items to the room
		room21.addItem(bed21);
		room21.addItem(nightstand21);
		room21.addItem(closet21);
		room21.addItem(fishbowl21);
		room21.addItem(key21);
		room21.addItem(desk21);
		room21.addItem(journal21);
		room21.addItem(window21);
		room21.addItem(commode21);

		
		//=== Description
		room21.getPosition(0).setDescription(
				"You are in the third crime scene and you can start investigating.\n"
				+ "At first sight there is nothing abnormal in the room.\n"
				+ "We see a large window overlooking the garden.\n"
				+ "There's a bed where the victim was found.\n"
				+ "Beside it is a small fishbowl on a nightstand.\n"
				+ "There’s also a brown closet on the other side of the bed.\n"
				+ "On the left side, facing the bed, is a clean desk.\n"
				);
                
                PositionsInit.initializePositions(room21.getPositions(), room21.getNameRoom());

		//=========================================================================//
		//=== Room 7 : Room 18 (Crime Scene n.4)
		//=== Create and set items positions
		Item bed18 = new Item("bed", "It's just a bed. ", "Nothing to see here.", room18, room18.getPosition(0), false, null);
		Item nightstand18 = new Item("nightstand", "Nothing special, just a glass of water and some painkillers", "", room18, room18.getPosition(0), false, null);
		Item closet18 = new Item("closet", "That's a pretty large closet. ", "Quite boring though...", room18, room18.getPosition(0), false, null);
		Item safe18 = new Safe("safe", "This safe looks heavy. ", "", room18, room18.getPosition(0), false, 9999, null);
		Item candle18 = new Item("candle", "Just a normal candle. ", "", room18, room18.getPosition(0), true, null);
		Item desk18 = new Item("desk", "It’s quite empty except a couple of magazines. ", "", room18, room18.getPosition(0), false, null);
		Item bookshelf1_18 = new Item("bookshelf", "Just some bookshelves.\n", "What's that? There's a candle on the lower shelf.\nWho still uses candles in this century anyway?\nThere's only one single history book and nothing else, on the top shelf.\nAnd bove it we can see a Caesar painting. That’s weird ...", room18, room18.getPosition(0), false, null);
		Item bookshelf2_18 = new Item("bookshelf2", "Just some boring looking books.", "\nNothing to see here...", room18, room18.getPosition(0), false, null);
		Item book18 = new Item("book", "Hmm.. The title says \"Caesar, Life of a Colossul\"\n\n", "There's a marked page that says:\n\"Twice two is four is not life, gentlemen,\nbut the beginning of death...\"\n\nFyodor Dostoevsky\n", room18, room18.getPosition(0), true, d7);
		Item magazines18 = new Item("magazines", "Just a couple of magazines.", "", room18, room18.getPosition(0), false, null);
                Item commode18 = new Item("commode", "Just a simple dresser, nothing to see here.", "", room18, room18.getPosition(0), false, null);
                Item window18 = new Item("window", "This window is pretty large. Nice view.", "", room18, room18.getPosition(0), false, null);
		
		//=== Add items to the room
		room18.addItem(bed18);
		room18.addItem(nightstand18);
		room18.addItem(closet18);
		room18.addItem(safe18);
		room18.addItem(candle18);
		room18.addItem(desk18);
		room18.addItem(book18);
		room18.addItem(bookshelf1_18);
		room18.addItem(bookshelf2_18);
		room18.addItem(magazines18);
		room18.addItem(commode18);
		room18.addItem(window18);
		
		//=== Description
		room18.getPosition(0).setDescription(
				"You are in the fourth crime scene and you can start investigating.\n"
				+ "This room is a little wider than the others.\n"
				+ "On your left is a large bed with two nightstands.\n"
				+ "There’s also a brown closet on the side.\n"
				+ "On your right side, facing the bed, is a large desk beside\n"
				+ "the window. Right in the corner, there are two rows of bookshelves."
				);
                
                PositionsInit.initializePositions(room18.getPositions(), room18.getNameRoom());
		
		//=========================================================================//
		//=== Room 8 : Room 22 (Crime Scene n.5)
		//=== Create and set items positions
		Item bed22 = new Item("bed", "It's just a bed.\n", "Nothing to see here.", room22, room22.getPosition(0), false, null);
		Item nightstand22 = new Item("nightstand", "Nothing special, just a glass of water and some painkillers", "", room22, room22.getPosition(0), false, null);
		Item closet22 = new Item("closet", "Hmm... I can see something. ", "Oh, it's a safe", room22, room22.getPosition(0), false, null);
		Item safe22 = new Safe("safe", "Something is in this safe.", "", room22, room22.getPosition(0), false, 9999, null);
		Item paper22 = new Item("paper", "Something is written on it\n", "It says:\n\"I’m in vssq xairxc xlvii, come and pay me a visit.\nOh and I might not be able to talk to you directly so try looking out the window.\nDon’t forget the sunscreen, UV light can be harmful...\"", room22, room22.getPosition(0), true, null);
		Item desk22 = new Item("desk", "The desk is completely empty. ", "Oh wait, someone has left a paper...\nand a key.\n", room22, room22.getPosition(0), false, null);
		Item window22 = new Item("window", "The sun is pouring through the window.", "", room22, room22.getPosition(0), false, null);
                Item commode22 = new Item("commode", "Just a simple dresser, nothing to see here.", "", room22, room22.getPosition(0), false, null);
                //=== This is key is situated in Room 22 Crime Scene n.5, it will be used to unlock Room 23
		Key key22 = new Key("key22", "I wonder what is this key for...", "", room22, room22.getPosition(0), true, null);
		
                d8.setKey(key22);
                
		//=== Add items to the room
		room22.addItem(bed22);
		room22.addItem(nightstand22);
		room22.addItem(closet22);
		room22.addItem(safe22);
		room22.addItem(paper22);
		room22.addItem(desk22);
		room22.addItem(key22);
		room22.addItem(window22);
		room22.addItem(commode22);
		
		//=== Description
		room22.getPosition(0).setDescription(
				"You are in the fifth crime scene and you can start investigating.\n"
				+ "This room is quite luminous. On your left is a small bed with a nightstand.\n"
				+ "There’s also a brown closet on the side.\n"
				+ "On your right side, facing the bed, is a large desk beside the window.\n"
				+ "Above the desk are two rows of bookshelves."
				);
		
                PositionsInit.initializePositions(room22.getPositions(), room22.getNameRoom());
		
		//=========================================================================//
		//=== Room 9 : Room 23 (Crime Scene n.6 FINAL ONE)
		//=== Create and set items positions
		Item bed23 = new Item("bed", "Dead body alert, better not get near it...", ".", room23, room23.getPosition(0), false, null);
		Item nightstand23 = new Item("nightstand", "Nothing special, just a glass of water and some painkillers", "", room23, room23.getPosition(0), false, null);
		Item closet23 = new Item("closet", "It's locked, not interesting", "", room23, room23.getPosition(0), false, null);
		Item note23 = new Item("note", "It seems empty but not unused ", "Congratulations!\nYou did it! You followed all the clues and have finally found me.\nForgive me for not being able to converse with you\nbut I’m sure you’ll understand my position, dead people are not in the habit of talking...\n"
				+ "This little game might seem useless to you but trust me\nI had so much fun putting it up.\nIn fact it was the only time I really felt alive for the first time in a while.\n"
				+ "I am truly sorry for what you are about to read and\nsorry that I have to break it to you in this way.\n"
				+ "You don’t know me but I know you all too well.\nI know you because you are my son and I went away and left you.\nI left because I was a coward and I have lived all my life regretting it.\nI have lived in this nursing home for years now and I’ve had enough of it.\n"
				+ "I have always kept an eye on your work since you became a famous detective,\nand I wanted to put up this little challenge to pass time.\nI thought it would be good to have something to bond over with my son before I left for good.\nDon’t worry though, all the people I killed were as desperate to die as I was,\nthey just didn’t want to act upon it.\nI did them all a big favor.\n"
				+ "Thank you for your time, and again, sorry for everything...", room23, room23.getPosition(0), true, null);
		Item desk23 = new Item("desk", "The desk is completely empty.", "", room23, room23.getPosition(0), false, null);
		Item window23 = new Item("window", "The blinds are drawn. ", "An empty note is sticking out of the border...", room23, room23.getPosition(0), false, null);
                Item commode23 = new Item("commode", "Just a simple dresser, nothing to see here.", "", room23, room23.getPosition(0), false, null);
		
		//=== Add items to the room
		room23.addItem(bed23);
		room23.addItem(nightstand23);
		room23.addItem(closet23);
		room23.addItem(note23);
		room23.addItem(desk23);
		room23.addItem(window23);
		room23.addItem(commode23);
		
		//=== Description
		room23.getPosition(0).setDescription(
				"You are in the killer’s bedroom.\n"
				+ "Woow there’s a dead person on the bed, this is clearly the killer himself...\n"
				+ "you should call for help.\n"
				+ "But first you decide to follow the last instructions he has given you.\n\n"
				+ "This room is dark, the blinds are drawn.\n"
				+ "The bed is on your right, There’s also a large closet on the side.\n"
				+ "Facing the bed, is a large desk beside the window.\n"
				+ "Above the desk are two rows of bookshelves."
				);
                
                PositionsInit.initializePositions(room23.getPositions(), room23.getNameRoom());
		
		return livingRoom;
	}
}
