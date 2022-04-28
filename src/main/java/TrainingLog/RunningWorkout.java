package traininglog;

import java.util.stream.Collectors;

public class RunningWorkout extends Workout {

    private double distance;

    public RunningWorkout(String name, String date, double distance) {
        super(name, date);
        if (distance > 0 && distance < 50) {
        this.distance = distance;
        }
        else throw new IllegalArgumentException("Distance must be a number from 1-50");
    } 

    @Override 
    public void addExercise(Exercise exercise) {
        if (this.getExercises().size() > 0) {
            throw new IllegalStateException("You can only have one exercise in running workouts.");
        }
        else exercises.add(exercise);
    }

    public double getDistance() {
        return distance;
    }

    @Override
    public String toString() {
        return this.getDate() + ": " + this.getName() + ", " + this.getDistance() + "km";
    }

    @Override
    public String workoutToFile() {
        String tmpExercises = this.getExercises().stream().map(s -> s.exerciseTofile()).collect(Collectors.joining());
        return "Running" + "," + this.getName() + "," + this.getDate() + "," + this.getDistance() + "," +  tmpExercises + "\n";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(distance);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj))
            return false;
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RunningWorkout other = (RunningWorkout) obj;
        if (Double.doubleToLongBits(distance) != Double.doubleToLongBits(other.distance))
            return false;
        return true;
    }  

    
}
