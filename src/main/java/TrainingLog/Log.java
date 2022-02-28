package traininglog;

import java.util.ArrayList;
import java.util.Collection;

public class Log {
    
    private Collection<Workout> workouts = new ArrayList<>();
    private int totalTime;
    
    public void addWorkout(Workout workout) {
        this.workouts.add(workout);
    }

    public void removeWorkout(Workout workout) {
        if (workouts.contains(workout)) {
            workouts.remove(workout);
        }
    }

    public String getTotalTime() {
        for (Workout workout : workouts) {
            totalTime += workout.getTime();
        }
        int hours = totalTime / 60;
        int minutes = totalTime % 60;
        return Integer.toString(hours) + ":" + Integer.toString(minutes); 
    }

}
