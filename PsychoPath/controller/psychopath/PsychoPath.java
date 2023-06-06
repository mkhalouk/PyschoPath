package controller.psychopath;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Lisa, Mustapha, Nesrine
 */
public class PsychoPath extends Application {
    
    /* Main application to load the first scene*/
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/start.fxml"));
        
        Scene scene = new Scene(root, 1000, 800);
        
        /* First screen : start game */
        stage.setTitle("Psychopath");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
