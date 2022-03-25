package traininglog;

import java.util.ArrayList;
import java.util.Collection;

public class Log {

    private Collection<Workout> workouts = new ArrayList<Workout>();
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
        totalTime = workouts.stream().mapToInt(w -> w.getTime()).sum();
        int hours = totalTime / 60;
        int minutes = totalTime % 60;
        return Integer.toString(hours) + "h:" + Integer.toString(minutes) + "m"; 
    }

    public Collection<Workout> getWorkoutList() {
        return workouts;
    }

}
