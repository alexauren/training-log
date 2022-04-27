package Traininglog;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import traininglog.Exercise;
import traininglog.OtherWorkout;
import traininglog.Workout;

public class OtherWorkoutTest {

    private Workout w1, w2;
    private Exercise e1, e2;
    private Collection<Exercise> exercises = new ArrayList<>();

    @BeforeEach
    public void setup() {
        w1 = new OtherWorkout("Strength", "11.04.2021");
        w2 = new OtherWorkout("Cardio", "10.05.2021");

        e1 = new Exercise("Squat", 10 ,10);
        e2 = new Exercise("Deadlift", 15, 8);

        w2.addExercise(e1);
        w2.addExercise(e2);
    }

    @Test
    @DisplayName("Test addExercise")
    public void testAddExercises() {
        Assertions.assertEquals(w1.getExercises(), exercises);
        
        w1.addExercise(e1);
        exercises.add(e1);
        Assertions.assertEquals(w1.getExercises(), exercises);
        
        w1.addExercise(e2);
        exercises.add(e2);
        Assertions.assertEquals(w1.getExercises(), exercises);
    }    
    
    @Test
    @DisplayName("Test various toString-methods (toString(), extendedToString() and workoutToFile()).")
    public void testToString() {
        Assertions.assertEquals(w1.toString(), "11.04.2021: Strength");

        Assertions.assertEquals(w2.extendedToString(), 
        "10.05.2021: Cardio\n\nExercise: Squat, Time: 10, Intensity: 10\nExercise: Deadlift, Time: 15, Intensity: 8\n\nTotal time spent: 25");
        Assertions.assertEquals(w1.extendedToString(),
        "11.04.2021: Strength\n\n\nTotal time spent: 0");

        Assertions.assertEquals(w2.workoutToFile(), 
        "Cardio,10.05.2021,Squat,10,10,Deadlift,15,8,\n");
        Assertions.assertEquals(w1.workoutToFile(),
        "Strength,11.04.2021,\n");
    }
}
