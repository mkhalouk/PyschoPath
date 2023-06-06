package controller;

import static controller.GeneralController.bindImg;
import static controller.GeneralController.mySetText;
import static controller.GeneralController.pickAllItems;
import static controller.GeneralController.itemMsg;
import static controller.GeneralController.getItem;
import static controller.GeneralController.goDir;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
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
import model.LayoutPlayer;
import model.LayoutPosition;
import model.Player;
import model.Player.Dir;

/**
 *
 * @author Lisa, Mustapha, Nesrine
 */
public class DiningRoomController implements Initializable {
    /* GeneralController instance that will be used to call the class' methods */
    GeneralController gc = new GeneralController();
    
    @FXML
    private ImageView player;
    
    @FXML
    private GridPane grid;
    
    @FXML
    private ImageView fireplace;

    @FXML
    private ImageView chair;

    @FXML
    private ImageView table;

    @FXML
    private ImageView sink;
    
    @FXML
    private ImageView chest;

    @FXML
    private ImageView cupboard;
    
    @FXML
    private ImageView doordr;
    
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
    private TextArea textArea;
    
    @FXML
    private Button pickupBtn;
    
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
     * otherwise shows their description
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
    
    /* Shows the info alert (method defined in GeneralController class) */
    @FXML
    void showHelp(ActionEvent event) {
        gc.showHelp();
    }
    
    /* Load the corridor scene if the door is clicked */
    @FXML
    void doorAction(MouseEvent event) throws IOException {    
        if(event.getButton().equals(MouseButton.SECONDARY)) {
            mySetText(textArea, "This door is already unlocked, left click to use it. \n", false);
           
        } else {
            gc.changeScene(layoutPlayer, "doordr", "/view/corridor.fxml", "corridor", textArea, event);
        }
    }
    
    /**
     * The four methods move the player to a direction
     * 
     * @param event associated to the arrow buttons
     */
    @FXML
    void moveRight(ActionEvent event) {
        goDir(textArea, player, layoutPlayer, Dir.W);
    }
    
    @FXML
    void moveLeft(ActionEvent event) {
        goDir(textArea, player, layoutPlayer, Dir.E);
    }
    
    @FXML
    void moveUp(ActionEvent event) {
        goDir(textArea, player, layoutPlayer, Dir.N);
    }
    
    @FXML
    void moveDown(ActionEvent event) {
        goDir(textArea, player, layoutPlayer, Dir.S);
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
        try {
            /* Get the player and its properties */
            Player p = GeneralController.staticP;
            GridPane.setRowIndex(player, 1);
            GridPane.setColumnIndex(player, 3);
            LayoutPosition playerLayout = p.getPos().getLayout();
            layoutPlayer = new LayoutPlayer(p, playerLayout, 1, 3);

            mySetText(textArea, layoutPlayer.getGamePlayer().getPos().getDescription(), false);

            /* Bind the background properties to the gridpane for the resizable window */
            background.fitHeightProperty().bind(grid.heightProperty());
            background.fitWidthProperty().bind(grid.widthProperty());

            /* Add every item's imageview to an array to bind their ratio */
            ImageView[] imgs01 = {chest};
            ImageView[] imgs015 = {fireplace, chair, doordr};
            ImageView[] imgs03 = {sink};
            ImageView[] imgs04 = {table, cupboard};
        
            bindImg(imgs01, grid, 0.1);
            bindImg(imgs015, grid, 0.15);
            bindImg(imgs03, grid, 0.3);
            bindImg(imgs04, grid, 0.4);
       
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
            
            /* Scroll the text area always at the bottom if new text is added */
            textArea.textProperty().addListener((ObservableValue<?> observable, Object oldValue, Object newValue) -> {
                textArea.setScrollTop(Double.MAX_VALUE);
            });
        
        } catch (Exception ex) {
            Logger.getLogger(DiningRoomController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /* Connect the interface items with their game description */
        HashMap<ImageView, Item[]> roomItems = gc.roomItems;
        
        /* If an item is already in our inventory (so it is no longer in the room), it won't be added to the
         * array.
         * Each array contains at least a not pickable item and other pickable items that are the "content" 
         * of the not pickable item.
         * All the array items are associated to an image of the corresponding FXML file and then put on an
         * HashMap that represents the room items to be easily manipulated.
         */
        Item[] cupboardItems;
        if(getItem(layoutPlayer, "keydr") != null) {
            cupboardItems = new Item[]{getItem(layoutPlayer, "cupboard"), getItem(layoutPlayer, "keydr")};
        } else {
            cupboardItems = new Item[]{getItem(layoutPlayer, "cupboard")};
        }
        
        Item[] chestItems;
        if(getItem(layoutPlayer, "post-it") != null) {
            chestItems = new Item[]{getItem(layoutPlayer, "chest"), getItem(layoutPlayer, "post-it")};
        } else {
            chestItems = new Item[]{getItem(layoutPlayer, "chest")};
        }
                
        Item[] tableItems = {getItem(layoutPlayer, "table")};
        Item[] fireplaceItems = {getItem(layoutPlayer, "fireplace")};
        Item[] sinkItems = {getItem(layoutPlayer, "sink")};
        Item[] chairItems = {getItem(layoutPlayer, "chair")};
        
        roomItems.put(table, tableItems);
        roomItems.put(fireplace, fireplaceItems);
        roomItems.put(cupboard, cupboardItems);
        roomItems.put(sink, sinkItems);
        roomItems.put(chair, chairItems);
        roomItems.put(chest, chestItems);
       
    }
    
}
