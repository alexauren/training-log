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
        for (Workout workout : workouts) {
            totalTime += workout.getTime();
        }
        int hours = totalTime / 60;
        int minutes = totalTime % 60;
        return Integer.toString(hours) + ":" + Integer.toString(minutes); 
    }
    
    public Collection<Workout> getWorkoutList() {
        return workouts;
    }
    public static void main(String[] args) {
        Workout w = new Workout("man", "02.02.2001");
        Workout s = new Workout("tir", "01.02.2001");
        Log l = new Log();
        l.addWorkout(w);
        l.addWorkout(s);
        System.out.println(l.getWorkoutList());
    }
}
