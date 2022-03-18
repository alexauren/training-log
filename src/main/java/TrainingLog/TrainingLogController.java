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
    private Button addWorkoutButton, removeWorkoutButton, saveTrainingLog, loadTrainingLog, addExercise, closeButton, saveButton;
    @FXML
    private Text totalTime, workoutInfo, errorText;
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
        catch (Exception e) {
            workoutList.getItems().setAll(emptyList);
        }
        
        workout = null;
        removeWorkoutButton.setDisable(true);
        workoutInfo.setText(null);
    }

    @FXML
    public void handleAddWorkout() {
        title.setText(null);
        date.setText(null);
        newWorkoutBackground.setVisible(true);
        
    }

    @FXML
    public void handleCloseButton() {
        workout = null;
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
        if (Objects.isNull(workout)) {
            initializeWorkout();
        }
        Exercise ex = new Exercise(exercise.getText(), Integer.parseInt(time.getText()), Integer.parseInt(intensity.getText()));
        workout.addExercise(ex);
        resetExerciseInput(); 
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
