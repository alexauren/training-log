package traininglog;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class TrainingLogController {

    @FXML ListView<Workout> workoutList;
    @FXML TextFlow workoutInfo;
    @FXML Button addWorkout, removeWorkout, saveTrainingLog, loadTrainingLog;
    @FXML Text totalTime;

    @FXML void handleAddWorkout() throws IOException {
        //diriger til newworkout
        FXML loader = (FXML) new FXMLLoader(getClass().getResource("NewWorkout.fxml"));
        Parent root = ((FXMLLoader) loader).load();

        NewWorkoutController newWorkoutController = ((FXMLLoader) loader).getController();
        
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Add workout");
        stage.show();
 
    }
    @FXML void handleRemoveWorkout() {
        //trenger vi egentlig denne?
    }

}
