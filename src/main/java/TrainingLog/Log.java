package traininglog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class Log {

    private Collection<Workout> workouts = new ArrayList<Workout>();
    private int totalTime;
    private FileHandler fileHandler = new FileHandler();

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
        else throw new IllegalStateException("Cannot remove a workout that doesn't exist.");
    }

    public String getTotalTime() {
        totalTime = this.getWorkoutList(true).stream().mapToInt(w -> w.getTime()).sum();
        int hours = totalTime / 60;
        int minutes = totalTime % 60;
        return Integer.toString(hours) + "h:" + Integer.toString(minutes) + "m"; 
    }

    public List<Workout> getWorkoutList(boolean dateSort) {
        List<Workout> workoutsCopy = new ArrayList<Workout>(workouts);
        if (dateSort) {
            workoutsCopy.sort(Workout.workoutComparatorDate);
        }
        else {
            workoutsCopy.sort(Workout.workoutComparatorIntensity);
        }
        return workoutsCopy;
    }

    public String getIntensityAdvice() {
        double avg = workouts.stream()
            .mapToDouble(w -> w.getAvgIntensity()).sum() 
            / workouts.size();
        String avgString = String.format("%.2f", avg);
        String advice;
        if (avg > 8) {
            advice =  "Your average training intensity is " + avgString + ". You should consider training less hard to avoid overtraining.";
        }
        else if (avg < 6) {
            advice = "Your average training intensity is " + avgString + ". You should consider training harder if you want more gains.";
        } 
        else {
            advice = "Your average training intensity is " + avgString + ", which is within the optimal range.";
        }
        return advice;
    }

    public double getDistanceCount() {
         return workouts.stream().filter(w -> w instanceof RunningWorkout).mapToDouble(w -> ((RunningWorkout) w).getDistance()).sum();
    }

    public void logToFile() throws IOException {
        fileHandler.writeToFile(this.getWorkoutList(true));
    }

    public void fileToLog() throws IOException {
        this.setWorkouts(fileHandler.readFromFile());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((workouts == null) ? 0 : workouts.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Log other = (Log) obj;
        if (workouts == null) {
            if (other.workouts != null)
                return false;
        } else if (!workouts.equals(other.workouts))
            return false;
        return true;
    }

    

}
