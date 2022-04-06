package traininglog;

import java.util.ArrayList;


public class Log {

    private ArrayList<Workout> workouts = new ArrayList<Workout>();
    private int totalTime;

    public void addWorkout(Workout workout) {
        if (workout.getExercises().size() > 0) {
            workouts.add(workout);
        }
        else throw new IllegalStateException("Please add at least one exercise before saving your workout.");
    }

    public void setWorkouts(ArrayList<Workout> workoutList) {
        this.workouts = workoutList;
    }

    public void removeWorkout(Workout workout) {
        if (workouts.contains(workout)) {
            workouts.remove(workout);
        }
    }

    public String getTotalTime() {
        totalTime = this.getWorkoutList(true).stream().mapToInt(w -> w.getTime()).sum();
        int hours = totalTime / 60;
        int minutes = totalTime % 60;
        return Integer.toString(hours) + "h:" + Integer.toString(minutes) + "m"; 
    }

    public ArrayList<Workout> getWorkoutList(boolean dateSort) {
        ArrayList<Workout> workoutsCopy = new ArrayList<Workout>(workouts);
        if (dateSort) {
            workoutsCopy.sort(Workout.workoutComparatorDate);
        }
        else {
            workoutsCopy.sort(Workout.workoutComparatorTime);
        }

        return workoutsCopy;
    }

    public String getIntensityAdvice() {
        double avg = (workouts.stream()
            .mapToDouble(w -> w.getAvgIntensity())).sum() 
            / workouts.size();
        String avgString = String.format("%.2f", avg);
        String advice;
        if (avg > 8) {
            advice =  "Your average training intensity is " + avgString + ". You should consider training less hard to avoid overtraining.";
        }
        else advice = "Your average training intensity is " + avgString + ". You should consider training harder if you want more gains.";
        return advice;
    }

}
