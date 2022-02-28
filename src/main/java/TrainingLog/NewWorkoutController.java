package traininglog;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class NewWorkoutController {
    
    @FXML TextField title;
    @FXML TextField date;
    @FXML TextField exercise;
    @FXML TextField intensity;
    @FXML TextField time;

    @FXML RadioButton strength;
    @FXML RadioButton endurance;

    @FXML Button addExercise;

    char type;
    Workout workout;
    Log log;

    @FXML void updateGUI() {
        time.setText(null);
        exercise.setText(null);
        intensity.setText(null);
    }

    @FXML void handleTitle() {
        
    }

    @FXML void handleDate() {

    }

    @FXML void handleExercise() {

    }

    @FXML boolean handleStrength() {
        return true;
    }

    @FXML boolean handleEndurance() {
        return true;
    }

    @FXML void handleIntensity() {

    }

    @FXML void handleTime() {

    }

    @FXML void handleAddExercise() {
        
        if(handleStrength()) {
            this.type = 'S';
        }
        else this.type = 'E';
        // todo:
        Exercise ex = new Exercise(exercise.getText(), this.type,  Integer.parseInt(time.getText()), Integer.parseInt(intensity.getText()));
        workout.addExercise(ex);
        updateGUI();
        
    }   

    @FXML void handleCloseButton() {
        //return to Log
    }

    @FXML void handleSaveWorkout() {
        log.addWorkout(workout);
        //return to log
    }

}
