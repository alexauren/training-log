package traininglog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class TrainingLogController {

    @FXML 
    private ListView<Workout> workoutList;
    @FXML
    private Button addWorkoutButton, removeWorkoutButton, saveLogButton, loadLogButton, addExerciseButton, closeButton, saveButton, newWorkoutButton, exitChartButton, intensityCoachButton;
    @FXML
    private Text totalTime, workoutInfo, errorText, errorTextNewWorkout, intensityAdvice;
    @FXML 
    private TextField title, date, exercise, intensity, time;
    @FXML 
    private AnchorPane newWorkoutBackground, logBackground, intensityData;
    @FXML 
    private RadioButton dateButton, timeButton;
    /* @FXML
    private LineChart<> intensityChart; */

    private Log log;
    private Workout workout;
    private List<Workout> emptyList = new ArrayList<>();
    private boolean dateSort;
    private FileHandler fileHandler = new FileHandler();
    
    @FXML 
    public void initialize() {
        log = new Log();
        workoutInfo.setText("Add a workout or load a training log to view info.");
        newWorkoutBackground.setVisible(false);
        intensityData.setVisible(false);
        removeWorkoutButton.setDisable(true);
        totalTime.setText("0h:0m");
    }

    @FXML
    private void updateView() {
        try {
            totalTime.setText(log.getTotalTime());
            workoutList.getItems().setAll(log.getWorkoutList(dateSort));
        }
        catch (NullPointerException e) {
            workoutList.getItems().setAll(emptyList);
            totalTime.setText("0h:0m");
        }
        
        workout = null;
        removeWorkoutButton.setDisable(true);
        workoutInfo.setText("Click on a workout to view it's info.");
        errorText.setStyle("#ec1818");
        errorText.setText(null);
        errorTextNewWorkout.setText(null);   
    }

    @FXML
    public void handleAddWorkout() {
        newWorkoutBackground.setVisible(true);
        updateEnabledFields(true);
        title.setText("");
        date.setText("");
    }

    @FXML
    private void updateEnabledFields(boolean b) {
        exercise.setDisable(b);
        intensity.setDisable(b);
        time.setDisable(b);
        addExerciseButton.setDisable(b);
        date.setDisable(!b);
        title.setDisable(!b);
        newWorkoutButton.setDisable(!b);
        }

    @FXML
    public void handleCloseButton() {
        updateView();
        newWorkoutBackground.setVisible(false);    
    }

    @FXML
    public void handleSaveWorkout() {
        try {
            log.addWorkout(workout);
            updateView();
            newWorkoutBackground.setVisible(false);
        }
        catch(IllegalStateException e) {
            errorTextNewWorkout.setText(e.getMessage());
        }
    }

    @FXML 
    private void resetExerciseInput() {
        time.setText("");
        exercise.setText("");
        intensity.setText("");
    }

    @FXML
    public void initializeWorkout() {
        try {
        workout = new Workout(title.getText(), date.getText());
        updateEnabledFields(false);
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
        catch (Exception e) {
            errorTextNewWorkout.setText(e.getMessage());
        }
    }   

    @FXML
    public void handleWorkoutIsPressed() {
        ShowWorkoutInfo();
        removeWorkoutButton.setDisable(false);
    }
    
    @FXML 
    private void ShowWorkoutInfo() {
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

    @FXML
    public void handleWorkoutSort() {
        if (!dateButton.isSelected()) {
            dateSort = false;
        }
        else dateSort = false;
        updateView();
    }

    @FXML
    public void exitIntensityData() {
        intensityData.setVisible(false);
    }

    @FXML 
    public void showIntensityData() {
        intensityData.setVisible(true);
        intensityAdvice.setText(log.getIntensityAdvice());
    }

    @FXML
    public void saveLog() {
        try {
            fileHandler.writeToFile(log.getWorkoutList(true));
            errorText.setStyle("-fx-fill:#03cc00");
            errorText.setText("File saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void loadLog() {
        try {
            log.setWorkouts(fileHandler.readFromFile());;
            updateView();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
