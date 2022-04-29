package traininglog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
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
    private Button addWorkoutButton, removeWorkoutButton, addExerciseButton, closeButton, saveButton, newWorkoutButton,
            newWorkoutButton2, exitChartButton, intensityCoachButton;
    @FXML
    private Text totalTime, workoutInfo, errorText, errorTextNewWorkout, intensityAdvice, distanceText, distanceCount;
    @FXML
    private TextField title, date, exercise, intensity, time, distanceInput;
    @FXML
    private AnchorPane newWorkoutBackground, logBackground, intensityData;
    @FXML
    private RadioButton dateButton, intensityButton, runningWorkoutButton, otherWorkoutButton;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private LineChart<String, Number> intensityChart;

    private Log log;
    private Workout workout;
    private boolean dateSort;
    private boolean workoutType = true;

    @FXML
    private void initialize() {
        log = new Log();
        workoutInfo.setText("Add a workout or load a training log to view info.");
        newWorkoutBackground.setVisible(false);
        intensityData.setVisible(false);
        removeWorkoutButton.setDisable(true);
        totalTime.setText("0h:0m");
        workoutList.setStyle("-fx-font-family: 'monospaced'");
        dateSort = true;
        runningWorkoutButton.setSelected(false);
        otherWorkoutButton.setSelected(true);
        try {
            log.fileToLog();
        } catch (IOException e) {
            errorText.setText("Error loading file.");
        }
        updateView();
    }

    private void updateView() {
        try {
            totalTime.setText(log.getTotalTime());
            distanceCount.setText(Double.toString(log.getDistanceCount()));
            log.logToFile();
            workoutList.getItems().setAll(log.getWorkoutList(dateSort));
        } catch (NullPointerException e) {
            workoutList.getItems().setAll(new ArrayList<>());
            totalTime.setText("0h:0m");
            distanceCount.setText("0");
        } catch (IOException e) {
            errorText.setText("Error reading file");
        }

        workout = null;
        removeWorkoutButton.setDisable(true);
        workoutInfo.setText("Click on a workout to view it's info.");
        errorText.setStyle("#ec1818");
        errorText.setText(null);
        errorTextNewWorkout.setText(null);
    }

    @FXML
    private void handleAddWorkout() {
        newWorkoutBackground.setVisible(true);
        updateEnabledFields(true);
        title.clear();
        date.clear();
        distanceInput.clear();
    }

    private void updateEnabledFields(boolean b) {
        exercise.setDisable(b);
        intensity.setDisable(b);
        time.setDisable(b);
        addExerciseButton.setDisable(b);
        date.setDisable(!b);
        title.setDisable(!b);
        newWorkoutButton.setDisable(!b);
        newWorkoutButton2.setDisable(!b);
        distanceInput.setDisable(!b);
        runningWorkoutButton.setDisable(!b);
        otherWorkoutButton.setDisable(!b);
    }

    @FXML
    private void handleCloseButton() {
        updateView();
        newWorkoutBackground.setVisible(false);
    }

    @FXML
    private void handleSaveWorkout() {
        try {
            log.addWorkout(workout);
            log.logToFile();
            updateView();
            newWorkoutBackground.setVisible(false);
        } catch (IllegalStateException e) {
            errorTextNewWorkout.setText(e.getMessage());
        } catch (IOException e) {
            errorTextNewWorkout.setText("Error with autosaving file");
        }
    }

    private void resetExerciseInput() {
        time.clear();
        exercise.clear();
        intensity.clear();
    }

    @FXML
    private void handleWorkoutTypeButton() {
        if (otherWorkoutButton.isSelected()) {
            workoutType = true;
            distanceText.setVisible(false);
            distanceInput.setVisible(false);
            distanceInput.clear();
            newWorkoutButton2.setVisible(false);
            newWorkoutButton.setVisible(true);
        } else {
            workoutType = false;
            distanceText.setVisible(true);
            distanceInput.setVisible(true);
            newWorkoutButton.setVisible(false);
            newWorkoutButton2.setVisible(true);
        }
    }

    @FXML
    private void initializeWorkout() {
        try {
            if (workoutType) {
                workout = new OtherWorkout(title.getText(), date.getText());
            } else {
                workout = new RunningWorkout(title.getText(), date.getText(),
                        Double.parseDouble(distanceInput.getText()));
            }
            updateEnabledFields(false);
            errorTextNewWorkout.setText(null);
        } catch (NullPointerException | NumberFormatException e) {
            handleAddWorkout();
            errorTextNewWorkout.setText("Distance must be a number from 1-50.");
        } catch (Exception e) {
            handleAddWorkout();
            errorTextNewWorkout.setText(e.getMessage());
        }
    }

    @FXML
    private void handleAddExercise() {
        try {
            Exercise ex = new Exercise(exercise.getText(), Integer.parseInt(time.getText()),
                    Integer.parseInt(intensity.getText()));
            workout.addExercise(ex);
            resetExerciseInput();
            errorTextNewWorkout.setText(null);
        } catch (NumberFormatException e) {
            errorTextNewWorkout.setText("Please add valid exercise parameters");
        } catch (Exception e) {
            errorTextNewWorkout.setText(e.getMessage());
        }
    }

    @FXML
    private void handleWorkoutIsPressed() {
        ShowWorkoutInfo();
        removeWorkoutButton.setDisable(false);
    }

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
    private void handleWorkoutSort() {
        if (!dateButton.isSelected()) {
            dateSort = false;
        } else
            dateSort = true;
        updateView();
    }

    @FXML
    private void exitIntensityData() {
        intensityData.setVisible(false);
    }

    @FXML
    private void showIntensityData() {
        intensityChart.getData().clear();
        intensityData.setVisible(true);
        intensityAdvice.setText(log.getIntensityAdvice());

        xAxis.setLabel("Date");
        yAxis.setLabel("Intensity (1-10)");

        XYChart.Series<String, Number> intensitySeries = new XYChart.Series<String, Number>();
        intensitySeries.setName("Intensity");

        log.getWorkoutList(true).stream().forEach(w -> {
            intensitySeries.getData().add(new XYChart.Data<String, Number>(w.getDate(), w.getAvgIntensity()));
        });

        intensityChart.getData().add(intensitySeries);
    }
}
