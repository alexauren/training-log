package traininglog;

import java.util.ArrayList;

public class Log {

    private ArrayList<Workout> workouts = new ArrayList<Workout>();
    private int totalTime;

    public void addWorkout(Workout workout) {
        workouts.add(workout);
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

}
