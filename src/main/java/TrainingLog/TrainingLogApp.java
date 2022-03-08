package traininglog;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TrainingLogApp extends Application {

    public static void main(final String[] args) {
		Application.launch(args);
	}
    
    @Override
	public void start(final Stage primaryStage) throws IOException {
		primaryStage.setTitle("Training log"); 
		Parent parent = FXMLLoader.load(getClass().getResource("LogGUI.fxml"));
		primaryStage.setScene(new Scene(parent));
		primaryStage.show();
	}
	
}
