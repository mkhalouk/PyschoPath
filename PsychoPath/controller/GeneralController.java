package controller;

import model.LayoutPosition;
import model.LayoutPlayer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.Chest;
import model.Item;
import model.Player;
import model.Position;
import model.Room;
import model.Safe;

/**
 *
 * @authors Lisa, Mustapha, Nesrine
 */

/** Controller class that contains all the utils for our classes **/
public class GeneralController {
    
    /* Test for the help button */
    public static String helpText = "             --- HELP ---             \n\n"
                + " You have to follow the clues that will lead you to the criminal."
                + " Move around and explore the house.\n"
                + " Here are some tips on how to interact with your world.\n\n"
                + " -> Move :\n"
                + "  - Use keyboard arrow keys.\n"
                + "  - Use zqsd keyboard keys.\n"
                + "  - Click on arrow buttons in the control panels.\n\n"

                + " -> Show inventory :\n"
                + "   Click on the backpack button in the control panel.\n\n"

                + " -> Show map : \n"
                + "   If you want to know where you are, just click on the map pin"
                + " in the control panel to show a map of the world.\n\n"

                + " -> Show object description : \n"
                + "   Left Click with your mouse on any object, in the world or in the inventory.\n\n"

                + " -> Pickup objects :\n"
                + "   Right Click on the container object to pick up all the objects it contains.\n\n"

                + " -> Drop an object :\n"
                + "Right click on in inside the inventory.\n\n"

                + " -> Pick up a dropped item : \n" 
                + "   Go to the position where you have dropped it and click on the \"pick up dropped items\" "
                + "button in the control panel.\n\n"

                + " -> Use a door :\n"
                + "  - If it’s unlocked : Simply click on it.\n"
                + "  - If it’s locked :\n"
                + "         - Right Click if it’s locked to show the unlock-panel.\n"
                + "         - If you have keys in your inventory, they will be shown in the button of the pane.\n"
                + "         - Choose the right key, drag and drop it on the door.\n"
                + "         - Close the panel and click on the door to use it.\n\n"

                + " -> Unlock a chest :\n"
                + "   - Double Click on it to show the unlock-panel.\n"
                + "   - If you have keys in your inventory, they will be shown in the button of the pane.\n"
                + "   - Choose the right key, drag and drop it on the chest.\n"
                + "   - Close the panel and right click on the chest to take what's inside.\n\n"

                + " -> Unlock a safe :\n"
                + "   - Right Click if it’s locked to show the unlock-panel.\n"
                + "   - Write the corresponding passcode into the text field and validate.\n"
                + "   - Close the panel and click on the door to use it.\n\n\n"
            ;
    
    /* Images for the control buttons */
    public static ImageView upImg = new ImageView("view/images/controls/up.png");
    public static ImageView downImg = new ImageView("view/images/controls/down.png");
    public static ImageView leftImg = new ImageView("view/images/controls/left.png");
    public static ImageView rightImg = new ImageView("view/images/controls/right.png");
    
    public static ImageView quitImg = new ImageView("view/images/controls/quit.png");
    public static ImageView mapImg = new ImageView("view/images/controls/map.png");
    public static ImageView inventoryImg = new ImageView("view/images/controls/knapsack.png");
    public static ImageView helpImg = new ImageView("view/images/controls/info.png");
    
    /* Create buttons for controls */
    public static Button upBtn = new Button();
    public static Button downBtn = new Button();
    public static Button leftBtn = new Button();
    public static Button rightBtn = new Button();
    
    public static Button quit = new Button();
    public static Button map = new Button();
    public static Button help = new Button();
    public static Button inventory = new Button();
    
    /* Inventory item images */
    static HashMap<String, ImageView> itemImageViews = new HashMap<>();
    
    public static ImageView pen = new ImageView("view/images/objects/pen.png");
    public static ImageView pen_holder = new ImageView("view/images/objects/pen_holder.png");
    public static ImageView notebook = new ImageView("view/images/objects/notebook.png");
    public static ImageView journal = new ImageView("view/images/objects/journal.png");
    public static ImageView book = new ImageView("view/images/objects/book.png");
    public static ImageView post_it = new ImageView("view/images/objects/post_it.png");
    public static ImageView paper17 = new ImageView("view/images/objects/paper17.png");
    public static ImageView paper19 = new ImageView("view/images/objects/paper19.png");
    public static ImageView note = new ImageView("view/images/objects/note.png");
    public static ImageView chestKey = new ImageView("view/images/objects/chestKey.png");
    public static ImageView journalKey = new ImageView("view/images/objects/journalKey.png");
    public static ImageView doorKey = new ImageView("view/images/objects/doorKey.png");
    public static ImageView paper22 = new ImageView("view/images/objects/paper22.png");
    public static ImageView candle = new ImageView("view/images/objects/candle.png");
       
    /** Global Player variable **/
    public static Player staticP = null;
    public static boolean gameStarted = false; 
    
    /** Room item images **/
    public HashMap<ImageView, Item[]> roomItems = new HashMap<>();
    
    public static String itemMsg;
    
    public static SplitPane splitPane;
    
    public static Class getClass;
    
    /**
     * Returns an item in the room
     * 
     * @param layoutPlayer  the player's layout
     * @param itemName      item's name that we are searching for
     * @return the item object or null if not found
     */
    public static Item getItem(LayoutPlayer layoutPlayer, String itemName) {
        return layoutPlayer.getGamePlayer().getLoc().getItem(itemName);
    }
    
    /**
     * Get all the not pickable item's descriptions
     * 
     * @param items the item's array
     * @return the description of each item in the array
     */
    public String showDescription(Item[] items) {
        String desc = "";
        for(Item item : items) {
            if(!item.isPickable()) {
                desc = item.getItemDesc();
            }
        }
        return desc;
    }
    
    /**
     * Picks up all the item that are pickable that are in the same player's 
     * position. With this method, the player will pick up items from an array
     * that we will pass in argument which represents the items that are in this
     * room/position.
     * 
     * @param layoutPlayer  player's layout
     * @param items         item's array to pick up from
     * @return boolean to know if we picked up at least an item
     */
    public static boolean pickAllItems(LayoutPlayer layoutPlayer, Item[] items) {
        boolean picked = false;
        boolean locked = false;
        boolean isPickable = false;
        Item notPickable = new Item();
        
        for(Item item : items) {
            /* We test if an item is a chest/safe and we will lock it to not pick it up */
            if((item instanceof Chest && ((Chest) item).isLocked()) || (item instanceof Safe && ((Safe) item).isSafeLocked())) {
                if(!item.isPickable()) {
                    locked = true;
                } else {
                    /* This case is for items that are locked but that we can pick
                     * like the journal that needs a key to be unlocked */
                    locked = true;
                    isPickable = true;
                }
            } 
        }

        /* If an item is not locked, we pick it up ;
         * if it is locked, that we test if it's pickable. */
        if(!locked || isPickable) {
            itemMsg = "";
            for(Item item : items) {
                if(item.isPickable()) {
                    layoutPlayer.getGamePlayer().pickup(item.getItemName());
                    picked = true;
                } else {
                    notPickable = item;
                }
            }
        } else {
            itemMsg = "It's locked! You need to find the key.";
        }
        
        /* When we pick up an item we delete the content of its container item */
        if(picked) {
            notPickable.setItemContent("");
        }
        
        return picked;
    }
    
    /**
     * Pick up all the items dropped on the floor.
     * @param lp        player's layout
     * @param textArea  contains the message of the picked up items.
     */
    public static void pickupDropped(LayoutPlayer lp, TextArea textArea) {
        ArrayList<Item> droppedItems = lp.getGamePlayer().getPos().getDroppedItems();
        if(droppedItems.isEmpty()) {
            mySetText(textArea, "There are no pickable items in here.", true);
        } else {
            pickAllItems(lp, droppedItems.toArray(new Item[droppedItems.size()]));            
            mySetText(textArea, itemMsg, true);

        }
    }
    
    /**
     * Associate all of the in-game pickable items with their interface images
     * 
     * @param grid that contains the items' positions.
     */
    public static void initializeItemImages(GridPane grid) {
        itemImageViews.put("pen", pen);
        itemImageViews.put("pen_holder", pen_holder);
        itemImageViews.put("notebook", notebook);
        itemImageViews.put("book", book);
        itemImageViews.put("journal", journal);
        itemImageViews.put("paper17", paper17);
        itemImageViews.put("paper19", paper19);
        itemImageViews.put("note", note);
        itemImageViews.put("post-it", post_it);
        itemImageViews.put("keydr", chestKey);
        itemImageViews.put("key21", journalKey);
        itemImageViews.put("key22", doorKey);
        itemImageViews.put("paper", paper22);
        itemImageViews.put("candle", candle);
        // Add all other items
        ImageView[] itemImageViewsTab = {pen, pen_holder, notebook, book, journal, paper17, paper19, note, post_it, chestKey, journalKey, doorKey, candle, paper22};
        bindImg(itemImageViewsTab, grid, 0.2);
    }
    
    /**
     * Builds the player's graphical inventory
     * 
     * @param lp        player's layout
     * @param fp        flowpane thet contains the items
     * @param textArea  shows the item's description
     * @param grid      pane that contains the initialized items images
     */
    public static void inventoryBuilder(LayoutPlayer lp, FlowPane fp, TextArea textArea, GridPane grid) {
        initializeItemImages(grid);
        ImageView img;
        
        /* If player's inventory not empty */
        if(lp.getGamePlayer().getItemCount() != 0) {
            Item[] playerInventory = lp.getGamePlayer().getInventory();
            for(Item item : playerInventory) {
                if(item != null) {
                    /* We get the item image from the hashmap */
                    img = itemImageViews.get(item.getItemName());
                    img.setId(item.getItemName());
                    img.setPreserveRatio(true);
                    
                    /* If the flowpane (graphical inventory) doesn't contain 
                     * this item image, then we add it */
                    if(!fp.getChildren().contains(img)) {
                        fp.getChildren().add(img);
                        
                        /* We set actions and properties on the added item. */
                        inventoryObjectAction(img, fp, textArea);
                        useItemWith(img, lp, textArea);
                    }
                }
            }
        } else {
            mySetText(textArea, "Your inventory is empty, pick up items to display them here.", true);
        }
        
    }
    
    /**
     * Sets some actions on the inventory items
     * 
     * @param img       Item's image
     * @param fp        Flowpane that contains the items' images
     * @param textArea  shows the items description
     */
    public static void inventoryObjectAction(ImageView img, FlowPane  fp, TextArea textArea) {
        img.setOnMouseClicked((MouseEvent event) -> {
            
            /* Get the source's index in the flowpane, because it is the same 
             * as our in-game inventory index. */
            ImageView source = (ImageView) event.getSource();
            int itemIndex = fp.getChildren().indexOf(source);
            
            /* If right click, then we drop the item from inventory and the image
             * from the flowpane */
            if(event.getButton().equals(MouseButton.SECONDARY)) {
                if(itemIndex >= 0) {
                    String dropped = staticP.getInventory()[itemIndex].getItemName() + " dropped.";
                    staticP.drop(itemIndex + 1);
                    fp.getChildren().remove(source);
                    mySetText(textArea, dropped, false);
                }
            } else {
                /* if left click then we just show the item's description */
                Item[] inv = staticP.getInventory();
                if(itemIndex >= 0)
                    mySetText(textArea, inv[itemIndex].getItemDesc(), false);
            }
            event.consume();
        });
    }
    
    /* This is a static attribute for the following methods, it represents
     * the name of the item that we drop on it another item to use them */
    public static String destItem = "";
    
    /**
     * Returns an item in the room
     * 
     * @param lp            the player's layout
     * @param itemName      item's name that we are searching for
     * @return the item object or null if not found
     */
    public static Item findItem(LayoutPlayer lp, String itemName) {
        Item item;
        
        /** We search in the room **/
        item = getItem(lp, itemName);
        
        /** If item not found, then we search in the player's inventory **/
        if(item == null) {
            Item[] inv = lp.getGamePlayer().getInventory();
            int cpt = lp.getGamePlayer().getItemCount();
            for(int i = 0; i < cpt; i++) {
                if(inv[i].getItemName().equals(itemName)) {
                    item = inv[i];
                    break;
                }
            }
        }
        
        return item;
    }
   
    /**
     * Use an item with another item to unlock or have a special content by 
     * dragging the item image and drop it on another item
     * 
     * @param img       item image
     * @param lp        player's layout
     * @param textArea  shows item's message (content, description etc...)
     */
    public static void useItemWith(ImageView img, LayoutPlayer lp, TextArea textArea) {

        img.setOnDragDetected((MouseEvent event) -> {
            Dragboard db = img.startDragAndDrop(TransferMode.ANY);
            
            ClipboardContent content = new ClipboardContent();
            content.putImage(img.getImage());
            db.setContent(content);
            event.consume();
        });

        /* The first item will be dropped over this item (destItem). 
         * destItem will contain the id of its image, which represents
         * its name */
        img.setOnDragOver((DragEvent event) -> {
            if (event.getGestureSource() != img && event.getDragboard().hasImage()) {
                destItem = img.getId();
                event.acceptTransferModes(TransferMode.ANY);  
            }
            
            event.consume();
        });

        /* By using the method findItem we will make sure that the destination item
         * is in the room or in the inventory, and we will call the use method 
         * depending on its type. If we don't find the item in the room or inventory,
         * it means that it is a door, so we will call the unlock door method.
         * Last, if the destination item is "note", we will just redirect to the
         * last scene, because it represents the last step for our game */
        img.setOnDragDone((DragEvent event) -> {
            Dragboard db = event.getDragboard();
            
            Item item = findItem(lp, destItem);
            
            if(item != null) {
                
                if(item instanceof Chest) {
                    if(item.getItemName().equals("journal"))
                        staticP.useWithItem(img.getId(), destItem);
                    
                    staticP.unlockChest(img.getId(), destItem);
                } else {
                    staticP.useWithItem(img.getId(), destItem);
                    if(destItem.equals("note")){
                        
                        try {
                            changeToEnd();
                        } catch (IOException ex) {
                            Logger.getLogger(GeneralController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            } else { /** It's a door **/
                staticP.unlockDoor(img.getId(), destItem);
            }
            
            /* Shows on the text area the taken items */
            mySetText(textArea, itemMsg, true);
            itemMsg = "";
            
            event.consume();
        });
        
    }
    
    /**
     * Sets the image of the destination item to accept the "drag over"
     * @param img image of the destination item
     */
    public static void unlockWithKey_3d(ImageView img) {
        img.setOnDragOver((DragEvent event) -> {
            if (event.getGestureSource() != img && event.getDragboard().hasImage()) {
                destItem = img.getId();
                event.acceptTransferModes(TransferMode.ANY);  
            }
            event.consume();
        });
    }
    
    /**
     * Bind the control buttons with the grid size to fit in it properly
     * @param grid gridpane
     */
    public static void initializeControlBtns(GridPane grid) {
        ImageView[] imgs004 = {quitImg, mapImg, helpImg, inventoryImg, upImg, downImg, leftImg, rightImg};
        bindImg(imgs004, grid, 0.04);
    }
    
    /**
     * Move the player by changing its layout position in the interface 
     * and in the game
     * 
     * @param layoutPlayer  player's layout
     * @param d             direction that the player will move to
     * @return the layout position of the player
     */
    public static LayoutPosition move(LayoutPlayer layoutPlayer, Player.Dir d) {
        layoutPlayer.getGamePlayer().go(d);
       
        return layoutPlayer.getGamePlayer().getPos().getLayout();
    }
    
    /**
     * Change position of the player by calling the move method
     * If the layout position of the player changer, it means that the player
     * moved in-game, so the interface position has to be changed too
     * 
     * @param textArea      shows the position description or if the player didn't move
     * @param player        the player's image
     * @param layoutPlayer  player's layout
     * @param d             direction that the player will go to
     */
    public static void goDir(TextArea textArea, ImageView player, LayoutPlayer layoutPlayer, Player.Dir d) {
        Position initialGamePos = layoutPlayer.getGamePlayer().getPos();
        
        /* Make the game player move */
        LayoutPosition newPos = move(layoutPlayer, d);
        
        Position newGamePos = layoutPlayer.getGamePlayer().getPos();
        
        if(initialGamePos != newGamePos) {
            /* Make the interface player move */
            GridPane.setRowIndex(player, newPos.getX());
            GridPane.setColumnIndex(player, newPos.getY());
            
            mySetText(textArea, layoutPlayer.getGamePlayer().getPos().getDescription(), false);
        } else {
            mySetText(textArea, "Try with another direction.", true);
        }   
    }
    
    /**
     * Set a text on the text area field and make it always scroll at the bottom
     * when new text is added.
     * 
     * @param textArea  field
     * @param text      content to put in the text area
     * @param keep      to keep the previous text + new text or replace it
     */
    public static void mySetText(TextArea textArea, String text, Boolean keep) {
        if(keep) {
            textArea.setText(textArea.getText() + "\n" + text + "\n");
            textArea.appendText("");
        } else {
            textArea.setText(text + "\n");
            textArea.appendText("");
        }
    }
    
    /**
     * Move the player with keys (up, down, left, right or z, s, q, d)
     * 
     * @param e             key event
     * @param textArea      to show the position's description
     * @param player        player's image to move
     * @param layoutPlayer  player's layout
     */
    public static void moveWithKeys(KeyEvent e, TextArea textArea, ImageView player, LayoutPlayer layoutPlayer) {
        if(e.getCode() != null) {
            switch(e.getCode()) {
                case Q      :
                case LEFT   : 
                    goDir(textArea, player, layoutPlayer, Player.Dir.E); break;
                case D      :
                case RIGHT  : 
                    goDir(textArea, player, layoutPlayer, Player.Dir.W); break;
                case Z :    
                case UP  : 
                    goDir(textArea, player, layoutPlayer, Player.Dir.N); break;
                case S :
                case DOWN  : 
                    goDir(textArea, player, layoutPlayer, Player.Dir.S); break;
                default : break;
            }
        }
    }
    
    /**
     * Create the world and initialize all the rooms, objects, doors in-game
     * 
     * @param textArea  first text that the game will show
     * @return the player, that will be affected to a static variable
     * @throws Exception 
     */
    public static Player launchGame(TextArea textArea) throws Exception {

        Room myLocation = Game.createGame();
        Player p = new Player("Popoo", myLocation, myLocation.getPosition(0));

        textArea.setText("You just arrived at the nursing home...\n\n" + p.getPos().getDescription());		
        staticP = p;
        gameStarted = true;
        
        return p;
    }
    
    /**
     * Set nodes out of focus in order not to have conflict with the move keys
     * to select other nodes
     * 
     * @param components
     * @param b 
     */
    public static void setOutOfFocus(Node[] components, Boolean b) {
        for(Node n : components) {
            n.setFocusTraversable(b);
        }
    }
    
    /**
     * Method to bind all images' width and height
     * with the pane's width and height properties
     * 
     * @param imgArr    images' array
     * @param pane      pane to bind with
     * @param ratio     the ratio for the images
     */
    public static void bindImg(ImageView[] imgArr, Pane pane, double ratio ) {
        for(ImageView img : imgArr) {
            img.fitWidthProperty().bind(pane.widthProperty().multiply(ratio));
            img.fitHeightProperty().bind(pane.heightProperty().multiply(ratio));
        }
    }
    
    /**
     * It will change scene when we will use a door by loading a new FXML file 
     * and testing if the door is in the same position of the player and if it
     * is not locked. If the in-game player changes position, then the interface
     * image will also change it position (so the scene)
     * 
     * @param layoutPlayer  player's layout
     * @param doorName      door that we will use with the useDoor method
     * @param fxmlPath      path of the FXML file to be loaded
     * @param title         title of the new scene
     * @param textArea      description of the new position (or message if door locked or 
     *                      out of reach)
     * @param event         mouse event (right click to show the door image or left click
     *                      to use the door)
     * @throws IOException 
     */
    public void changeScene(LayoutPlayer layoutPlayer, String doorName, String fxmlPath, String title, TextArea textArea, MouseEvent event) throws IOException {
        Position initialGamePos = layoutPlayer.getGamePlayer().getPos();
        boolean locked = layoutPlayer.getGamePlayer().useDoor(doorName);
        Position newGamePos = layoutPlayer.getGamePlayer().getPos();
        
        if(initialGamePos != newGamePos) {
            Parent room = FXMLLoader.load(getClass().getResource(fxmlPath));
            Scene scene = new Scene(room, 1000, 800);

            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setTitle(title);
            stage.setScene(scene);
            stage.show();
        } else if(locked) {
            mySetText(textArea, "This door is locked! You need more clues to open it.", true);
        } else {
            mySetText(textArea, "This door is out of reach!", true);
        }
    }
    
    /**
     * This method is called once (case of use uv pen with the final note) to 
     * change to the final scene that shows the final message.
     * 
     * @throws IOException 
     */
    public static void changeToEnd() throws IOException {
        Parent room = FXMLLoader.load(getClass.getResource("/view/end.fxml"));
        Scene scene = new Scene(room, 1000, 800);

        Stage stage = (Stage) splitPane.getScene().getWindow();
        stage.setTitle("THE END");
        stage.setScene(scene);
        stage.show();
    }
    
    /**
     * Loads the map scene (action on the map button)
     * @throws IOException 
     */
    public void showMap() throws IOException {
        Parent room = FXMLLoader.load(getClass().getResource("/view/map.fxml"));
        Scene scene = new Scene(room, 980, 600);

        Stage stage = new Stage();
        stage.setTitle("Map");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    
    /**
     * Shows the help alert (type info.. action on the help/info button)
     */
    public void showHelp() {
        TextArea helpContent = new TextArea(helpText);
        helpContent.setEditable(false);
        
        StackPane sp = new StackPane();
        sp.getChildren().add(helpContent);
        
        Scene scene = new Scene(sp, 800, 600);

        Stage stage = new Stage();
        stage.setTitle("Help");
        stage.setScene(scene);
        stage.showAndWait();
    }
   
}
