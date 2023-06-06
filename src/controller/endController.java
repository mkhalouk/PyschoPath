package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;


/**
 *
 * @author Lisa, Mustapha, Nesrine
 */
public class endController implements Initializable {
    
    /* This controller class is just for showing a button "The End" and the last message
     * that the player will see
     */
    @FXML
    private TextArea text;

    @FXML
    void changeScene(ActionEvent event) throws IOException {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        text.setText(
            "Congratulations!\nYou did it! You followed all the clues and have finally found me.\nForgive me for not being able to converse with you\nbut I’m sure you’ll understand my position, dead people are not in the habit of talking...\n"
            + "This little game might seem useless to you but trust me\nI had so much fun putting it up.\nIn fact it was the only time I really felt alive for the first time in a while.\n"
            + "I am truly sorry for what you are about to read and\nsorry that I have to break it to you in this way.\n"
            + "You don’t know me but I know you all too well.\nI know you because you are my son and I went away and left you.\nI left because I was a coward and I have lived all my life regretting it.\nI have lived in this nursing home for years now and I’ve had enough of it.\n"
            + "I have always kept an eye on your work since you became a famous detective,\nand I wanted to put up this little challenge to pass time.\nI thought it would be good to have something to bond over with my son before I left for good.\nDon’t worry though, all the people I killed were as desperate to die as I was,\nthey just didn’t want to act upon it.\nI did them all a big favor.\n"
            + "Thank you for your time, and again, sorry for everything..."
        );
    }

}