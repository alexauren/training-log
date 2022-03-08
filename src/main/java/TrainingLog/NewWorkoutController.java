package traininglog;


import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class NewWorkoutController {
    
    @FXML 
    private TextField title;
    @FXML 
    private TextField date;
    @FXML 
    private TextField exercise;
    @FXML 
    private TextField intensity;
    @FXML 
    private TextField time;
    @FXML 
    private Button addExercise, closeButton, saveButton;

    private Workout workout;
    private TrainingLogController trainingLogController = new TrainingLogController();
    private Log log;
    
    private Stage stage;
    private Scene scene;
    private Parent root;

    //vil begrense synligheten til denne
    void setLog(Log log) {
        this.log = log;
    }

    public void setTrainingLogController(TrainingLogController trainingLogController) {
        this.trainingLogController = trainingLogController;
    }

    public void handleCloseButton(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("LogGUI.FXML"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    } 

    @FXML
    public void handleSaveWorkout(ActionEvent event) throws IOException {
        initializeWorkout();
        //får nullpointexception på log på linja under.
        log.addWorkout(workout);
        trainingLogController.updateView();
        ActionEvent event2 = new ActionEvent();
        handleCloseButton(event2);

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
        initializeWorkout();
        Exercise ex = new Exercise(exercise.getText(), Integer.parseInt(time.getText()), Integer.parseInt(intensity.getText()));
        workout.addExercise(ex);
        resetExerciseInput(); 
    }   

}
