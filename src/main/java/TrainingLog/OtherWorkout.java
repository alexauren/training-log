package traininglog;

import java.util.stream.Collectors;

public class OtherWorkout extends Workout {

    public OtherWorkout(String name, String date) {
        super(name, date);
    }

    @Override
    public void addExercise(Exercise exercise) {
        exercises.add(exercise);        
    }

    @Override
    public String toString() {
        return this.getDate() + ":  " + this.getName();
    }

    @Override
    public String workoutToFile() {
        String tmpExercises = this.getExercises().stream().map(s -> s.exerciseTofile()).collect(Collectors.joining());
        return this.getName() + "," + this.getDate() + "," + tmpExercises + "\n";
    }
}
