package Traininglog;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import traininglog.Exercise;
import traininglog.Log;
import traininglog.Workout;

public class LogTest {

    private Log log, emptyLog;
    private Workout w1, w2, w3, w4;
    private Exercise e1, e2, e3;
    private List<Workout> workoutList;


    @BeforeEach
    public void setup() {
        log = new Log();
        emptyLog = new Log();

        e1 = new Exercise("Squat", 10, 5);
        e2 = new Exercise("Deadlift", 15, 8);
        e3 = new Exercise("Bench press", 20, 9);

        w1 = new Workout("Strength", "11.04.2021");
        w2 = new Workout("Back", "12.04.2021");
        w3 = new Workout("Legs", "10.05.2022");
        w4 = new Workout("Empty workout", "10.10.2022");

        w1.addExercise(e1);
        w2.addExercise(e2);
        w3.addExercise(e1);
        w3.addExercise(e2);

        log.addWorkout(w1);
        log.addWorkout(w2);
        log.addWorkout(w3);

        workoutList = new ArrayList<Workout>();
    }

    @DisplayName("Test addWorkout and getWorkoutList(datesort).")
    @Test
    public void testAddWorkout() {
        Assertions.assertThrows(IllegalStateException.class, () -> log.addWorkout(w4));

        workoutList.add(w1);
        emptyLog.addWorkout(w1);
        Assertions.assertEquals(emptyLog.getWorkoutList(true), workoutList);

        workoutList.add(w2);
        workoutList.add(w3);
        workoutList.sort(Workout.workoutComparatorDate);
        Assertions.assertEquals(log.getWorkoutList(true), workoutList);

        workoutList.sort(Workout.workoutComparatorTime);
        Assertions.assertEquals(log.getWorkoutList(false), workoutList);   
    }

    @DisplayName("Test removeWorkout")
    @Test
    public void testRemoveWorkout() {
        workoutList.add(w1);
        workoutList.add(w2);
        workoutList.sort(Workout.workoutComparatorDate);
        Assertions.assertNotEquals(workoutList, log.getWorkoutList(true));
        log.removeWorkout(w3);
        Assertions.assertEquals(workoutList, log.getWorkoutList(true));

        Assertions.assertThrows(IllegalStateException.class, () -> log.removeWorkout(w3));
    }

    @DisplayName("Test getTotalTime")
    @Test
    public void testGetTotalTime() {
        int expectedHours = (w1.getTime() + w2.getTime() + w3.getTime()) / 60;
        int expectedMins = (w1.getTime() + w2.getTime() + w3.getTime()) % 60;
        String expectedTime = expectedHours + "h:" + expectedMins + "m";

        Assertions.assertEquals(expectedTime, log.getTotalTime());

        Log emptyLog = new Log();
        Assertions.assertEquals(emptyLog.getTotalTime(), "0h:0m");
    }

    private String getExpectedIntensityAdvice(Log log) {
        return String.format("%.2f", log.getWorkoutList(true).stream()
                .mapToDouble(w -> w.getAvgIntensity()).sum() 
                / log.getWorkoutList(true).size());
    }

    @DisplayName("Test getIntensityAdvice")
    @Test
    public void testIntensityAdvice() {
        Log normalIntensity = log;
        Assertions.assertEquals(normalIntensity.getIntensityAdvice(), 
        "Your average training intensity is " + getExpectedIntensityAdvice(normalIntensity) + ", which is within the optimal range.");

        Log highIntensity = new Log();
        w4.addExercise(e3);
        highIntensity.addWorkout(w4);
        Assertions.assertEquals(highIntensity.getIntensityAdvice(),
        "Your average training intensity is " + getExpectedIntensityAdvice(highIntensity) + ". You should consider training less hard to avoid overtraining.");
    
        Log lowIntensity = new Log();
        lowIntensity.addWorkout(w1);
        Assertions.assertEquals(lowIntensity.getIntensityAdvice(),
        "Your average training intensity is " + getExpectedIntensityAdvice(lowIntensity) + ". You should consider training harder if you want more gains.");
    }

}
