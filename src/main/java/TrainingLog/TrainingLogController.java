package traininglog;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class TrainingLogController {

    @FXML
    private ListView<Workout> workoutList;
    @FXML
    private TextFlow workoutInfo;
    @FXML
    private Button addWorkout, removeWorkout, saveTrainingLog, loadTrainingLog, addExercise, closeButton, saveButton;
    @FXML
    private Text totalTime;
    @FXML 
    private TextField title, date, exercise, intensity, time;
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;
    @FXML 
    private AnchorPane newWorkkoutBackground, logBackground;

    private Log log = new Log();
    private Workout workout;


    @FXML
    public void updateView() {
        totalTime.setText(log.getTotalTime());
        workoutList.getItems().setAll(log.getWorkoutList());
    }

    @FXML
    public void handleAddWorkout(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("NewWorkout.FXML"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void handleCloseButton(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("LogGUI.FXML"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    } 

    @FXML
    public void handleSaveWorkout(ActionEvent event) throws IOException {
        log.addWorkout(workout);
        root = FXMLLoader.load(getClass().getResource("LogGUI.FXML"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        updateView();
    }
    @FXML 
    private void resetExerciseInput() {
        time.setText(null);
        exercise.setText(null);
        intensity.setText(null);
    }

    @FXML
    private void initializeWorkout() {
        workout = new Workout(title.getText(), date.getText());
    }

    @FXML 
    public void handleAddExercise() {
        if (workout == null) {
            initializeWorkout();
        }
        Exercise ex = new Exercise(exercise.getText(), Integer.parseInt(time.getText()), Integer.parseInt(intensity.getText()));
        workout.addExercise(ex);
        resetExerciseInput(); 
    }   

    @FXML 
    public void handleRemoveWorkout() {
        //trenger vi egentlig denne?
    }
}
