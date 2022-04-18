package Traininglog;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import traininglog.Exercise;
import traininglog.Workout;

public class WorkoutTest {
    
    private Workout w1, w2, w3, w4, w5;
    private Exercise e1, e2;
    Collection<Exercise> exercises;

    @BeforeEach
    public void setup() {
        w1 = new Workout("Strength", "11.04.2021");
        w2 = new Workout("Cardio", "10.05.2021");
        w3 = new Workout("Cross-fit", "09.04.2022");
        w4 = new Workout("Strength", "11.05.2021");
        w5 = new Workout("Cardio", "11.04.2021");

        e1 = new Exercise("Squat", 10 ,10);
        e2 = new Exercise("Deadlift", 15, 8);
        exercises  = new ArrayList<>();
        w2.addExercise(e1);
        w2.addExercise(e2);
        w3.addExercise(e1);
        w4.addExercise(e2);
        w5.addExercise(e1);
        w5.addExercise(e2);
        
    }

    @Test
    @DisplayName("Test name validation")
    public void testName() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Workout("", "11.04.2022");
		});
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Workout("1234567890123456", "11.04.2022");
		});
    }

    @Test
    @DisplayName("Test date validation")
    public void testDate() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Workout("Strength", "00.04.2022");
		});
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Workout("Strentgh", "32.04.2022");
		});
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Workout("Strength", "11.13.2022");
		});
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Workout("Strength", "11.00.2022");
		});
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Workout("Strength", "11.04.2020");
		});
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Workout("Strength", "11.04.2023");
		});
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Workout("Strength", "11/04/2022");
		});
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Workout("Strength", "11.04.22");
		});
    }
    
    @Test
    @DisplayName("Test addExercise")
    public void testAddExercises() {
        w1.addExercise(e1);
        exercises.add(e1);
        Assertions.assertEquals(w1.getExercises(), exercises);
        w1.addExercise(e2);
        exercises.add(e2);
        Assertions.assertEquals(w1.getExercises(), exercises);
    }    

    @Test 
    @DisplayName("Test getTime og getAvgIntensity") 
    public void testGetTimeGetAvgIntensity() {
        int time = e1.getTime() + e2.getTime();
        w1.addExercise(e1);
        w1.addExercise(e2);
        Assertions.assertEquals(w1.getTime(), time);

        double avgIntensity = (e1.getIntensity() + e2.getIntensity()) / 2;
        Assertions.assertEquals(w1.getAvgIntensity(), avgIntensity);
    }

    @Test
    @DisplayName("Test various toString-methods (toString(), extendedToString() and workoutToFile()).")
    public void testToString() {
        Assertions.assertEquals(w1.toString(), "11.04.2021:  Strength");

        Assertions.assertEquals(w2.extendedToString(), 
        "Name: Cardio, Date: 10.05.2021\n\nExercise: Squat, Time: 10, Intensity: 10\nExercise: Deadlift, Time: 15, Intensity: 8\n\nTotal time spent: 25");
        Assertions.assertEquals(w1.extendedToString(),
        "Name: Strength, Date: 11.04.2021\n\n\nTotal time spent: 0");

        Assertions.assertEquals(w2.workoutToFile(), 
        "Cardio,10.05.2021,Squat,10,10,Deadlift,15,8,\n");
        Assertions.assertEquals(w1.workoutToFile(),
        "Strength,11.04.2021,\n");
    }

    @Test
    @DisplayName("Test date comparator")
    public void testDateComparator() {
        Assertions.assertTrue(Workout.workoutComparatorDate.compare(w1, w2) < 0);
        Assertions.assertTrue(Workout.workoutComparatorDate.compare(w1, w3) < 0);
        Assertions.assertTrue(Workout.workoutComparatorDate.compare(w2, w3) < 0);
        Assertions.assertTrue(Workout.workoutComparatorDate.compare(w4, w2) > 0);
        Assertions.assertTrue(Workout.workoutComparatorDate.compare(w1, w5) == 0);
    }

    @Test
    @DisplayName("Test time comparator")
    public void testTimeComparator() {
        Assertions.assertTrue(Workout.workoutComparatorTime.compare(w1, w2) < 0);
        Assertions.assertTrue(Workout.workoutComparatorTime.compare(w2, w3) > 0);
        Assertions.assertTrue(Workout.workoutComparatorTime.compare(w3, w4) < 0);
        Assertions.assertTrue(Workout.workoutComparatorTime.compare(w5, w2) == 0);
        Assertions.assertTrue(Workout.workoutComparatorTime.compare(w1, w1) == 0);  
    }

}
