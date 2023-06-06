package controller;

import model.LayoutPosition;
import model.LayoutPlayer;
import static controller.GeneralController.bindImg;
import static controller.GeneralController.getClass;
import static controller.GeneralController.getItem;
import static controller.GeneralController.goDir;
import static controller.GeneralController.itemMsg;
import static controller.GeneralController.mySetText;
import static controller.GeneralController.pickAllItems;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Item;
import model.Player;

/**
 *
 * @author Lisa, Mustapha, Khalouk
 */
public class Room23Controller implements Initializable{
    /* GeneralController instance that will be used to call the class' methods */
    GeneralController gc = new GeneralController();
    
    @FXML
    private SplitPane splitPane;
        
    @FXML
    private ImageView door;

    @FXML
    private ImageView bed;

    @FXML
    private ImageView commode;

    @FXML
    private ImageView desk;

    @FXML
    private ImageView nightstand;

    @FXML
    private ImageView closet;
    
    @FXML
    private ImageView window;
    
    @FXML
    private ImageView player;
    
    @FXML
    private GridPane grid;
    
    @FXML
    private Button map;

    @FXML
    private Button quit;

    @FXML
    private Button help;
    
    @FXML
    private ImageView background;
    
    @FXML
    private Button upBtn;

    @FXML
    private Button leftBtn;

    @FXML
    private Button downBtn;

    @FXML
    private Button rightBtn;
    
    @FXML
    private Button pickupBtn;
    
    @FXML
    private TextArea textArea;
    
    @FXML
    private Button inventory;
    
    @FXML
    private FlowPane inventoryPane;
    
    private LayoutPlayer layoutPlayer;
    
         
    /**
     * Add shadow effect when we hover over an image
     * 
     * @param event mouse entered
     */
    @FXML
    void addShadow(MouseEvent event) {
        ImageView source = (ImageView) event.getSource();
        source.setEffect(new DropShadow());
    }
                  
    /**
     * Remove shadow effect on an image when mouse exited
     * 
     * @param event mouse exited
     */
    @FXML
    void removeShadow(MouseEvent event) {
        ImageView source = (ImageView) event.getSource();
        source.setEffect(null);
    }
           
    /**
     * Pick up all the pickable items if they are pickable with a right click, 
     * otherwise shows their description.
     * 
     * @param event right or left click
     */
    @FXML
    void objectAction(MouseEvent event) {
        ImageView source = (ImageView) event.getSource();
        Item[] items = gc.roomItems.get(source);
        
        if(event.getButton().equals(MouseButton.SECONDARY)) {
            boolean picked = pickAllItems(layoutPlayer, items);
            if(!picked) {
                if(itemMsg.equals("")) {
                    mySetText(textArea, "There are no pickable items in here.", true);
                } else {
                    mySetText(textArea, itemMsg, true);
                }
            } else {
                mySetText(textArea, itemMsg, true);
            }
        } else {
            mySetText(textArea, gc.showDescription(items), true);
        }
        
        itemMsg = "";
    }

    /* Calls the general method pickupDropped of the GeneralController class */
    @FXML
    void pickupDropped(ActionEvent event) {
        GeneralController.pickupDropped(layoutPlayer, textArea);
    }
    
    /**
     * Show the inventory pane (which is a flowpane)
     * by changing its visibility
     * 
     * @param event associated to a button
     */
    @FXML
    void showInventory(ActionEvent event) {
        if(inventoryPane.isVisible()){
            inventoryPane.toBack();
            inventoryPane.setVisible(false);
        } else {
            inventoryPane.setVisible(true);
            inventoryPane.toFront();
            GeneralController.inventoryBuilder(layoutPlayer, inventoryPane, textArea, grid);
        }
    }
    
    /* Loads the map scene (method defined in GeneralController class) */
    @FXML
    void showMap(ActionEvent event) throws IOException {
        gc.showMap();
    }
    
    /* Loads the map scene (method defined in GeneralController class) */
    @FXML
    void showHelp(ActionEvent event) {
        gc.showHelp();
    }
    
    /* Load the corridor scene if the door is clicked */
    @FXML
    void changeScene(MouseEvent event) throws IOException {
        gc.changeScene(layoutPlayer, "door23", "/view/corridor.fxml", "Corridor", textArea, event);
    }
    
    /**
     * The four methods move the player to a direction
     * 
     * @param event associated to the arrow buttons
     */
    @FXML
    void moveRight(ActionEvent event) {
        goDir(textArea, player, layoutPlayer, Player.Dir.W);
    }
    
    @FXML
    void moveLeft(ActionEvent event) {
        goDir(textArea, player, layoutPlayer, Player.Dir.E);
    }
    
    @FXML
    void moveUp(ActionEvent event) {
        goDir(textArea, player, layoutPlayer, Player.Dir.N);
    }
    
    @FXML
    void moveDown(ActionEvent event) {
        goDir(textArea, player, layoutPlayer, Player.Dir.S);
    }
          
    /**
     * Quit and close the game's window
     * 
     * @param event button click
     */
    @FXML
    void quitGame(ActionEvent event) {
        // Get the current stage and clsoe the window
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
       
    /**
     * When the gridpane is clicked, it gets te focus so we can use the keys
     * to move the player
     * 
     * @param event associated to the gridpane (grid)
     */
    @FXML
    void setFocus(MouseEvent event) {
        grid.requestFocus();
    }
                
    /**
     * Key bindings to move the player
     * @param e the pressed key
     */
    @FXML
    public void moveWithKeys(KeyEvent e) {
        GeneralController.moveWithKeys(e, textArea, player, layoutPlayer);
    }


    //========================================================================
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /* Get the splitpane and the class (It will be used to get the 
         * current scene and change to the final scene when uv pen and note will be used)
         */
        GeneralController.splitPane = splitPane;
        getClass = getClass();
        
        /* Get the player and its properties */
        Player p = GeneralController.staticP;
        LayoutPosition playerLayout = p.getPos().getLayout();
        layoutPlayer = new LayoutPlayer(p, playerLayout, 3, 3);

        /* Set initial player position on the gridpane when move to the corridor */
        GridPane.setRowIndex(player, 3);
        GridPane.setColumnIndex(player, 3);
        
        /* Get the position's description */
        mySetText(textArea, layoutPlayer.getGamePlayer().getPos().getDescription(), false);
        
        /* Initialize the grid buttons and set their images */
        GeneralController.initializeControlBtns(grid);

        upBtn.setGraphic(GeneralController.upImg);
        downBtn.setGraphic(GeneralController.downImg);
        leftBtn.setGraphic(GeneralController.leftImg);
        rightBtn.setGraphic(GeneralController.rightImg);

        quit.setGraphic(GeneralController.quitImg);
        map.setGraphic(GeneralController.mapImg);
        help.setGraphic(GeneralController.helpImg);
        inventory.setGraphic(GeneralController.inventoryImg);

        /* Make the gridpane focusable, and deactivate the focus on other nodes
         * with the arrows */
        grid.setFocusTraversable(true);
        Node[] nodes = {inventory, textArea, upBtn, downBtn, leftBtn, rightBtn, map, help, quit, pickupBtn};
        GeneralController.setOutOfFocus(nodes, false);

        ImageView[] imgs01 = {nightstand, commode, desk};
        ImageView[] imgs02 = {door, window};
        ImageView[] imgs03 = {desk};
        ImageView[] imgs05 = {bed, closet};
        bindImg(imgs01, grid, 0.1);
        bindImg(imgs02, grid, 0.2);
        bindImg(imgs03, grid, 0.3);
        bindImg(imgs05, grid, 0.5);
        
        /* Bind the background properties to the gridpane for the resizable window */
        background.fitHeightProperty().bind(grid.heightProperty());
        background.fitWidthProperty().bind(grid.widthProperty());
        
        // Initializing room objects in their ImageViews
        HashMap<ImageView, Item[]> roomItems = gc.roomItems;
            
        /* If an item is already in our inventory (so it is no longer in the room), it won't be added to the
         * array.
         * Each array contains at least a not pickable item and other pickable items that are the "content" 
         * of the not pickable item.
         * All the array items are associated to an image of the corresponding FXML file and then put on an
         * HashMap that represents the room items to be easily manipulated.
         */
        Item[] bedItems = {getItem(layoutPlayer, "bed")};
        Item[] nightstandItems = {getItem(layoutPlayer, "nightstand")};
        Item[] closetItems = {getItem(layoutPlayer, "closet")};
        Item[] deskItems = {getItem(layoutPlayer, "desk")};
        Item[] commodeItems = {getItem(layoutPlayer, "commode")};

        Item[] windowItems;
        if(getItem(layoutPlayer, "note") != null) {
            windowItems = new Item[]{getItem(layoutPlayer, "window"), getItem(layoutPlayer, "note")};
        } else {
            windowItems = new Item[]{getItem(layoutPlayer, "window")};
        }
        
        
        
        roomItems.put(bed, bedItems);
        roomItems.put(nightstand, nightstandItems);
        roomItems.put(closet, closetItems);
        roomItems.put(desk, deskItems);
        roomItems.put(window, windowItems);
        roomItems.put(commode, commodeItems);
    }
}
