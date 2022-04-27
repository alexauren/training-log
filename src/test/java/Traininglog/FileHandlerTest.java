package Traininglog;

import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import traininglog.Exercise;
import traininglog.FileHandler;
import traininglog.Log;
import traininglog.OtherWorkout;
import traininglog.RunningWorkout;
import traininglog.Workout;

public class FileHandlerTest {

    private Exercise e1, e2, e3, e4;
    private Workout w1, w2, w3;
    private Log expectedLog = new Log();
    private static Log oldLog = new Log();
    private static FileHandler fileHandler = new FileHandler();
    
        
    @DisplayName("This method saves the pre-saved file so it can be restored after the tests.")
    @BeforeAll    
    public static void saveOldLog() throws IOException {
        oldLog.setWorkouts(fileHandler.readFromFile());
    }

    @BeforeEach
    public void Setup() {
        e1 = new Exercise("Run", 60, 7);
        e2 = new Exercise("Squat", 15, 8);
        e3 = new Exercise("Deadlift", 20, 6);
        e4 = new Exercise("Bench", 10, 6); 

        w1 = new RunningWorkout("Long run", "03.03.2022", 12);
        w2 = new OtherWorkout("Strength", "05.03.2022");
        w3 = new OtherWorkout("Strength", "03.04.2022");

        w1.addExercise(e1);
        w2.addExercise(e2);
        w2.addExercise(e3);
        w3.addExercise(e4);

        expectedLog.addWorkout(w1);
        expectedLog.addWorkout(w2);
        expectedLog.addWorkout(w3);
    }

    @Test
    @DisplayName("This method tests that the FileHandler loads the same object it saved.")
    public void testFileHandler() throws IOException {
        Log actualLog = new Log();
        expectedLog.logToFile();
        actualLog.fileToLog();
       
        //Hvorfor funker ikke denne?
        //Assertions.assertEquals(expectedLog.getWorkoutList(true), actualLog.getWorkoutList(true));
        

        for (int i = 0; i < expectedLog.getWorkoutList(true).size(); i++) {
            Assertions.assertEquals(
                expectedLog.getWorkoutList(true).get(i).toString(), 
                actualLog.getWorkoutList(true).get(i).toString(),
                "Expected workouts must match actual workouts.");
            
            for (int j = 0; j < expectedLog.getWorkoutList(true).get(i).getExercises().size(); j++) {
                Assertions.assertEquals(
                expectedLog.getWorkoutList(true).get(i).getExercises().get(j).toString(), 
                actualLog.getWorkoutList(true).get(i).getExercises().get(j).toString(),
                "Expected exercises must match the actual exercises.");
            }
        }
    }
    
    @AfterAll
    @DisplayName("This method restores the old content on the .txt-file.")
    public static void restoreOldFile() throws IOException {
        fileHandler.writeToFile(oldLog.getWorkoutList(true));
    }

}
