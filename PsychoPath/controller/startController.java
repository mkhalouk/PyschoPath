package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;


/**
 *
 * @author Lisa, Mustapha, Nesrine
 */
public class startController implements Initializable {
    /* This controller class shows the first interface that the user will
     * see and a wolcome and presentation text, and finally a "Start" button
     * that will launch the game
     */

    @FXML
    private TextArea text;
    
    @FXML
    void changeScene(ActionEvent event) throws IOException {
        Parent room = FXMLLoader.load(getClass().getResource("/view/livingroom.fxml"));
        Scene scene = new Scene(room, 1000, 800);

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Living Room");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        text.setText(
            "An Interactive Fiction game created by:\n"
            + "Lisa BILEK\nMustapha KHALOUK\nNesrine BENSEGHIR\n\n"
            + "You are Attwell Frank, private investigator and you receive an anonymous "
            + "e-mail telling you that there was a murder in a nursing home not far from "
            + "where you live, a small town where everyone is kind to eachother. "
            + "In the mail there was the following: "
            + "\n"
            + "\n"
            + "\"Let’s play a little game... \n"
            + "Today, by the time this message would have reached you, you would have heard in "
            + "the local news that, in the peaceful nursing home of Deantown, an old man "
            + "would have peacefully died in his sleep after a long, peaceful life. "
            + "What you wouldn’t hear is what actually happened, and the fact that I’m the "
            + "one who did it. "
            + "I want you to go there and follow the clues. One by one until you find me. "
            + "Are you up for the challenge? Don’t waste any time, every minute counts, "
            + "you'll see that when you get there...\"\n\n "            
            + "Who could that monster be? And why would he summon you to this crooked game?!\n "
        );
    }

}