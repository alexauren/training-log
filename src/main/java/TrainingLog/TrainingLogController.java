package traininglog;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class TrainingLogController {

    @FXML 
    private ListView<Workout> workoutList;
    @FXML
    private Button addWorkoutButton, removeWorkoutButton, saveTrainingLog, loadTrainingLog, addExerciseButton, closeButton, saveButton, newWorkoutButton;
    @FXML
    private Text totalTime, workoutInfo, errorText, errorTextNewWorkout;
    @FXML 
    private TextField title, date, exercise, intensity, time;
    @FXML 
    private AnchorPane newWorkoutBackground, logBackground;

    private Log log;
    private Workout workout;
    private List<Workout> emptyList = new ArrayList<Workout>();
    
    @FXML 
    public void initialize() {
        log = new Log();
        workoutInfo.setText("Click on a workout to see info.");
        newWorkoutBackground.setVisible(false);
        removeWorkoutButton.setDisable(true);
    }

    @FXML
    private void updateView() {
        try {
            totalTime.setText(log.getTotalTime());        
            workoutList.getItems().setAll(log.getWorkoutList());
        }
        catch (NullPointerException e) {
            workoutList.getItems().setAll(emptyList);
            time.setText("0h:0m");
        }
        
        workout = null;
        removeWorkoutButton.setDisable(true);
        workoutInfo.setText(null);
        errorTextNewWorkout.setText(null);   
    }

    @FXML
    public void handleAddWorkout() {
        title.setText(null);
        date.setText(null);
        newWorkoutBackground.setVisible(true);
        updateDisableNewExercise(true);
        title.setDisable(false);
        date.setDisable(false);
        newWorkoutButton.setDisable(false);
    }
    @FXML
    private void updateDisableNewExercise(boolean b) {
        exercise.setDisable(b);
        intensity.setDisable(b);
        time.setDisable(b);
        addExerciseButton.setDisable(b);
    }

    @FXML
    public void handleCloseButton() {
        updateView();
        newWorkoutBackground.setVisible(false);
        
    }

    @FXML
    public void handleSaveWorkout() {
        log.addWorkout(workout);
        updateView();
        newWorkoutBackground.setVisible(false);
        
    }

    @FXML 
    private void resetExerciseInput() {
        time.setText("");
        exercise.setText("");
        intensity.setText("");
    }

    @FXML
    private void initializeWorkout() {
        try {
            workout = new Workout(title.getText(), date.getText());
        newWorkoutButton.setDisable(true);
        title.setDisable(true);
        date.setDisable(true);
        updateDisableNewExercise(false);
        errorTextNewWorkout.setText(null);
        }
        catch (IllegalArgumentException e) {
            handleAddWorkout();
            errorTextNewWorkout.setText(e.getMessage());
        } 
    }

    @FXML 
    public void handleAddExercise() {
        try {
            Exercise ex = new Exercise(exercise.getText(), Integer.parseInt(time.getText()), Integer.parseInt(intensity.getText()));
            workout.addExercise(ex);
            resetExerciseInput(); 
            errorTextNewWorkout.setText(null);
        }
        catch (IllegalArgumentException e) {
            errorTextNewWorkout.setText("Please enter valid exercise parameters.");
        }
        finally {
            exercise = null;
        }
    }   

    @FXML
    public void handleWorkoutIsPressed() {
        handleShowWorkoutInfo();
        removeWorkoutButton.setDisable(false);
    }
    
    @FXML 
    private void handleShowWorkoutInfo() {
        Workout tmpWorkout = workoutList.getSelectionModel().getSelectedItem();
        workoutInfo.setText(tmpWorkout.extendedToString());
    }

    @FXML 
    private void handleRemoveWorkout() {
        if (!Objects.isNull(workoutList.getSelectionModel().getSelectedItem())) {
            log.removeWorkout(workoutList.getSelectionModel().getSelectedItem());
            updateView();
        }
    }
}
