package controller;

import static controller.GeneralController.bindImg;
import model.LayoutPosition;
import model.LayoutPlayer;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.Item;
import model.Player;

/**
 *
 * @author Lisa, Mustapha, Nesrine
 */
public class Room19Controller implements Initializable{
    /* GeneralController instance that will be used to call the class' methods */
    GeneralController gc = new GeneralController();
    
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
    private ImageView safe;
    
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
    private BorderPane unlockPane;

    @FXML
    private StackPane unlockStack;

    @FXML
    private TextField tfCode;
    
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
     * otherwise shows their description. In the case of a safe, we show the 
     * unlock interface that contains a 3d image of the safe and the text field
     * to insert the code
     * 
     * @param event right or left click
     */
    @FXML
    void objectAction(MouseEvent event) {
        /* We load the chest image and set the id with the item name */
        ImageView safe3d = new ImageView("view/images/objects/safe.png");
        safe3d.setId("safe");
        
        /* Detect that the chest has been clicked */
        ImageView source = (ImageView) event.getSource();
        
        /* Get the chest and its content*/
        Item[] items = gc.roomItems.get(source);
        
        /* If right click, we pick up all pickable items (if the not pickable is
         * unlocked (this is managed by the pickAllItems method) */
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
          /* If left click two times, we show the unlock interface to enter the code */    
        } else if(event.getClickCount() == 2 && source.getId().equals("safe")) {
            unlockPane.setVisible(true);
            unlockStack.getChildren().add(safe3d);
            safe3d.setPreserveRatio(true);
            ImageView[] img04 = {safe3d};
            bindImg(img04, grid, 0.4);            
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
    
    /* Shows the info alert (method defined in GeneralController class) */
    @FXML
    void showHelp(ActionEvent event) {
        gc.showHelp();
    }
        
    /**
     * Close the unlock interface (BorderPane)
     * 
     * @param event button click
     */
    @FXML
    void closeUnlockPane(ActionEvent event) {
        unlockPane.setVisible(false);
    }
    
    /**
     * Get the digit typed in the textfield and compre it with the code to
     * unlock the safe
     * 
     * @param event button click (Validate)
     */
    @FXML
    void validateCode(ActionEvent event) {
        int code = Integer.parseInt(tfCode.getText());
        layoutPlayer.getGamePlayer().unlockSafe(code, "safe");
        
        mySetText(textArea, itemMsg, true);
    }
    
    /* Load the corridor scene if the door is clicked */
    @FXML
    void changeScene(MouseEvent event) throws IOException {
        gc.changeScene(layoutPlayer, "door19", "/view/corridor.fxml", "Corridor", textArea, event);
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

        /* Add every item's imageview to an array to bind their ratio */
        ImageView[] imgs01 = {nightstand, commode, desk, safe};
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
        Item[] windowItems = {getItem(layoutPlayer, "red_curtains")};
        Item[] commodeItems = {getItem(layoutPlayer, "commode")};
        Item[] deskItems = {getItem(layoutPlayer, "desk"), getItem(layoutPlayer, "papers")};

        Item[] safeItems;
        if(getItem(layoutPlayer, "paper19") != null) {
            safeItems = new Item[]{getItem(layoutPlayer, "safe"), getItem(layoutPlayer, "paper19")};
        } else {
            safeItems = new Item[]{getItem(layoutPlayer, "safe")};
        }
        
        
        
        roomItems.put(bed, bedItems);
        roomItems.put(nightstand, nightstandItems);
        roomItems.put(closet, closetItems);
        roomItems.put(desk, deskItems);
        roomItems.put(window, windowItems);
        roomItems.put(commode, commodeItems);
        roomItems.put(safe, safeItems);
    }
}
