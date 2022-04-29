package Traininglog;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import traininglog.Exercise;
import traininglog.OtherWorkout;
import traininglog.RunningWorkout;
import traininglog.Workout;

public class RunningWorkoutTest {

    private Workout w1, w2, w3;
    private Exercise e1, e2;
    private Collection<Exercise> exercises = new ArrayList<>();

    @BeforeEach
    public void setup() {
        w1 = new RunningWorkout("Long Run", "11.04.2021", 10);
        w2 = new RunningWorkout("Intervals", "10.05.2021", 8);
        w3 = new RunningWorkout("Intervals", "10.05.2021", 8);

        e1 = new Exercise("Running", 50 ,7);
        e2 = new Exercise("More running", 30, 8);

        w2.addExercise(e1);
    }

    @Test
    @DisplayName("Test distance validation in constructor.")
    public void testDsistanceValidation() {
        Assertions.assertThrows(IllegalArgumentException.class, 
        () -> w1 = new RunningWorkout("Long Run", "03.03.2022", 0));
        Assertions.assertThrows(IllegalArgumentException.class, 
        () -> w1 = new RunningWorkout("Long Run", "03.03.2022", 51));
        Assertions.assertThrows(IllegalArgumentException.class, 
        () -> w1 = new RunningWorkout("Long Run", "03.03.2022", 50.0001));
        Assertions.assertDoesNotThrow( 
        () -> w1 = new RunningWorkout("Long Run", "03.03.2022", 49.999));
        Assertions.assertDoesNotThrow( 
            () -> w1 = new RunningWorkout("Long Run", "03.03.2022", 0.00001));
    }

    @Test
    @DisplayName("Test addExercise")
    public void testAddExercises() {
        Assertions.assertEquals(w1.getExercises(), new ArrayList<Exercise>());
        w1.addExercise(e1);
        exercises.add(e1);
        Assertions.assertEquals(w1.getExercises(), exercises);
        
        Assertions.assertThrows(IllegalStateException.class, () -> w1.addExercise(e1), 
        "RunningWorkouts can only contain one exercise.");        
        Assertions.assertThrows(IllegalStateException.class, () -> w1.addExercise(e2), 
        "RunningWorkouts can only contain one exercise.");        

    }    
    
    @Test
    @DisplayName("Test various toString-methods (toString, extendedToString and workoutToFile).")
    public void testToString() {
        Assertions.assertEquals(w1.toString(), "11.04.2021: Long Run, 10.0km");

        Assertions.assertEquals(w2.extendedToString(), 
        "10.05.2021: Intervals, 8.0km\n\nExercise: Running, Time: 50, Intensity: 7\n\nTotal time spent: 50 minutes.");
        Assertions.assertEquals(w1.extendedToString(),
        "11.04.2021: Long Run, 10.0km\n\n\nTotal time spent: 0 minutes.");

        Assertions.assertEquals(w2.workoutToFile(), 
        "Running,Intervals,10.05.2021,8.0,Running,50,7,\n");
        Assertions.assertEquals(w1.workoutToFile(),
        "Running,Long Run,11.04.2021,10.0,\n");
    }

    @Test
    @DisplayName("Test equals method")
    public void testEquals() {
        w3.addExercise(e1);
        Assertions.assertEquals(w2,w3);

        Workout w4 = new OtherWorkout("Intervals", "10.05.2021");
        w4.addExercise(e1);
        Assertions.assertFalse(w4.equals(w2), 
        "A RunningWorkout cannot be equal to an OtherWorkout");

        w3 = new RunningWorkout("Interval", "10.05.2021", 8);
        w3.addExercise(e1);
        Assertions.assertFalse(w3.equals(w2));

        w3 = new RunningWorkout("Intervals", "11.05.2021", 8);
        w3.addExercise(e1);
        Assertions.assertFalse(w3.equals(w2));

        w3 = new RunningWorkout("Intervals", "10.05.2021", 7);
        w3.addExercise(e1);
        Assertions.assertFalse(w3.equals(w2));
    }
}
