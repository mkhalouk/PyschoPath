package controller;

import model.LayoutPosition;
import model.LayoutPlayer;
import static controller.GeneralController.bindImg;
import static controller.GeneralController.goDir;
import static controller.GeneralController.itemImageViews;
import static controller.GeneralController.mySetText;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.Item;
import model.Key;
import model.Player;

/**
 *
 * @author Lisa, Mustapha, Nesrine
 */
public class CorridorController implements Initializable{
    @FXML
    private ImageView player;
    
    @FXML
    private GridPane grid;
    
    @FXML
    private ImageView door17;

    @FXML
    private ImageView doorlr;

    @FXML
    private ImageView door18;

    @FXML
    private ImageView door19;

    @FXML
    private ImageView door21;

    @FXML
    private ImageView door23;

    @FXML
    private ImageView doordr;

    @FXML
    private ImageView door20;

    @FXML
    private ImageView door22;

    @FXML
    private ImageView door24;
    
    @FXML
    private Button map;

    @FXML
    private Button quit;

    @FXML
    private Button help;
    
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
    private FlowPane inventoryPane;
    
    @FXML
    private BorderPane unlockPane;
    
    @FXML
    private HBox unlockHBox;

    @FXML
    private StackPane unlockStack;
    
    @FXML
    private Button inventory;
    
    private LayoutPlayer layoutPlayer;
    
    @FXML
    void closeUnlockPane(ActionEvent event) {
        unlockPane.setVisible(false);
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
    
    /* Calls the general method pickupDropped of the GeneralController class */
    @FXML
    void pickupDropped(ActionEvent event) {
        GeneralController.pickupDropped(layoutPlayer, textArea);
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
     * When the gridpane is clicked, it gets te focus so we can use the keys
     * to move the player
     * 
     * @param event associated to the gridpane (grid)
     */
    @FXML
    void setFocus(MouseEvent event) {
        grid.requestFocus();
    }
    
    /* GeneralController instance that will be used to call the class' methods */
    GeneralController gc = new GeneralController();
    
    /**
     * Change and load the corresponding scene if left click on a door.
     * Otherwise will right click and open an image of the door and the key
     * below it that it will be used to open the door with a drag and drop
     * 
     * @param event
     * @throws IOException 
     */
    @FXML
    void doorAction(MouseEvent event) throws IOException {
        /* We load the door image */
        ImageView door3d = new ImageView("view/images/objects/door.png");
        
        /* Detect which door has been clicked and assign its name to image of
         * the door in grand format */
        ImageView doorImg = (ImageView) event.getSource();
        String doorName = doorImg.getId();
        door3d.setId(doorName);
        
        if(event.getButton().equals(MouseButton.SECONDARY)) {
            unlockPane.setVisible(true);
            unlockStack.getChildren().add(door3d);
            door3d.setPreserveRatio(true);
            ImageView[] imgs04 = {door3d};
            bindImg(imgs04, grid, 0.4);
            
            /* Set the drag over on the door's image */
            GeneralController.unlockWithKey_3d(door3d);
            
            /* Show all the keys that can be used  */
            Item[] inventoryTable = layoutPlayer.getGamePlayer().getInventory();
            
            /* Build the inventory for the image */
            GeneralController.inventoryBuilder(layoutPlayer, inventoryPane, textArea, grid);
            
            for(Item item : inventoryTable) {
                if((item != null) && (item instanceof Key)) {
                    
                    /* When building the new image, give it the key's name as Id */
                    ImageView img = itemImageViews.get(item.getItemName());
                    img.setId(item.getItemName());
                    
                    /* Add the key to the HBox below the door's image with all the binds, ratio etc...*/
                    img.setPreserveRatio(true);
                    if(!unlockHBox.getChildren().contains(img)) {
                        unlockHBox.getChildren().add(img);
                        img.setPreserveRatio(true);
                        ImageView[] imgs01 = {img};
                        bindImg(imgs01, grid, 0.1);
                    }
                }
            }
           
        } else {
            /* Else just test the door's name and load the new scene */
            switch(doorName) {
                
                case "doorlr" : 
                    gc.changeScene(layoutPlayer, "doorlr", "/view/livingroom.fxml", "Living Room", textArea, event);
                break;
                
                case "doordr" : 
                    gc.changeScene(layoutPlayer, "doordr", "/view/diningroom.fxml", "Dining Room", textArea, event);
                break;
                
                case "door17" : 
                    gc.changeScene(layoutPlayer, "door17", "/view/room17.fxml", "Room 17 [Crime Scene n.1]", textArea, event);
                break;
                
                case "door19" : 
                    gc.changeScene(layoutPlayer, "door19", "/view/room19.fxml", "Room 19 [Crime Scene n.2]", textArea, event);
                break;
                
                case "door21" : 
                    gc.changeScene(layoutPlayer, "door21", "/view/room21.fxml", "Room 21 [Crime Scene n.3]", textArea, event);
                break;
                
                case "door18" : 
                    gc.changeScene(layoutPlayer, "door18", "/view/room18.fxml", "Room 18 [Crime Scene n.4]", textArea, event);
                break;
                
                case "door22" : 
                    gc.changeScene(layoutPlayer, "door22", "/view/room22.fxml", "Room 22 [Crime Scene n.5]", textArea, event);
                break;
                
                case "door23" : 
                    gc.changeScene(layoutPlayer, "door23", "/view/room23.fxml", "Room 23 [Crime Scene n.6 - Final Scene]", textArea, event);
                break;
                
                case "door24" : 
                    mySetText(textArea, "This room is unoccupied.", true);
                break;
                
                case "door20" : 
                    mySetText(textArea, "This room is unoccupied.", true);
                break;
                
            }
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
    
    @FXML
    public void moveWithKeys(KeyEvent e) {
        GeneralController.moveWithKeys(e, textArea, player, layoutPlayer);
    }
    
    /**
     * Quit and close the game's window
     * 
     * @param event button click
     */
    @FXML
    void quitGame(ActionEvent event) {
        /* Get the current stage and close the window */
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /* Get the player and its properties */
        Player p = GeneralController.staticP;
        LayoutPosition playerLayout = p.getPos().getLayout();
        layoutPlayer = new LayoutPlayer(p, playerLayout, playerLayout.getX(), playerLayout.getY());
        
        /* Set initial player position on the gridpane when move to the corridor */
        GridPane.setRowIndex(player, playerLayout.getX());
        GridPane.setColumnIndex(player, playerLayout.getY());
        
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

        ImageView[] imgs015 = {door17, door18, door19, door20, door21, door22, door23, door24, doorlr, doordr};
        bindImg(imgs015, grid, 0.15);
    }
    
    
}
