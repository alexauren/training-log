package Traininglog;

import java.util.Collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import traininglog.Exercise;
import traininglog.OtherWorkout;
import traininglog.Workout;

public class WorkoutTest {

    /* Tester alle metoder som er felles for RunningWorkout og OtherWorkout 
    (altå de som er definert i den abstrakte Workout-klassen). 
    Har brukt OtherWorkout gjennom testen for enkelhets skyld, men siden det er felles metoder som testes, 
    ville det gitt samme resultet som å bruke RunningWorkout. */
    
    private Workout w1, w2, w3, w4, w5;
    private Exercise e1, e2;
    Collection<Exercise> exercises;

    @BeforeEach
    public void setup() {
        w1 = new OtherWorkout("Strength", "11.04.2021");
        w2 = new OtherWorkout("Cardio", "10.05.2021");
        w3 = new OtherWorkout("Cross-fit", "09.04.2022");
        w4 = new OtherWorkout("Strength", "11.05.2021");
        w5 = new OtherWorkout("Cardio", "11.04.2021");

        e1 = new Exercise("Squat", 10 ,10);
        e2 = new Exercise("Deadlift", 15, 8);
        
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
			new OtherWorkout("", "11.04.2022");
		});
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new OtherWorkout("1234567890123456", "11.04.2022");
		});
    }

    @Test
    @DisplayName("Test date validation")
    public void testDate() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new OtherWorkout("Strength", "00.04.2022");
		});
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new OtherWorkout("Strentgh", "32.04.2022");
		});
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new OtherWorkout("Strength", "11.13.2022");
		});
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new OtherWorkout("Strength", "11.00.2022");
		});
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new OtherWorkout("Strength", "11.04.2020");
		});
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new OtherWorkout("Strength", "11.04.2023");
		});
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new OtherWorkout("Strength", "11/04/2022");
		});
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new OtherWorkout("Strength", "11042022");
		});
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new OtherWorkout("Strength", "11 04 2022");
		});
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new OtherWorkout("Strength", "11.04.22");
		});
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
        avgIntensity = e1.getIntensity();
        Assertions.assertEquals(w3.getAvgIntensity(), avgIntensity);

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
        Assertions.assertTrue(Workout.workoutComparatorIntensity.compare(w1, w2) < 0);
        Assertions.assertTrue(Workout.workoutComparatorIntensity.compare(w2, w3) > 0);
        Assertions.assertTrue(Workout.workoutComparatorIntensity.compare(w3, w4) < 0);
        Assertions.assertTrue(Workout.workoutComparatorIntensity.compare(w5, w2) == 0);
        Assertions.assertTrue(Workout.workoutComparatorIntensity.compare(w1, w1) == 0);  
    }

}
