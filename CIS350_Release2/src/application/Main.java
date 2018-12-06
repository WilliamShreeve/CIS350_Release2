package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

/***********************************************************************
 * This is the driver class of the Student Database application. This 
 * class will take in the FXML file 'TableView.fxml' and start our 
 * application.
 * 
 * @author William Shreeve, Hai Duong, and Trung-Vuong Pham
 **********************************************************************/
public class Main extends Application {
    /*******************************************************************
     * Sets up the application boundaries for it to hold the scenes and 
     * tables needed by our application.
     *
     * @param primaryStage The main container that holds a scene
     ******************************************************************/
	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = FXMLLoader.load(getClass().getResource("TableView.fxml"));
			Scene scene = new Scene(root,850,680);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Student Database");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/*******************************************************************
	 * Launches the main application.
	 *
	 * @param args The argument ran through
	 ******************************************************************/
	public static void main(String[] args) {
		launch(args);
	}
}