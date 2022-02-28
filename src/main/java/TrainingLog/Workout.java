package traininglog;

import java.util.ArrayList;
import java.util.Collection;

public class Workout {

    private String name;
    private Collection<Exercise> exercises = new ArrayList<>();
    private int time;
    private String date;
    
    public Workout(String name, String date) {
        if(validDate(date)) {
            this.name = name;
            this.date = date;
        }
        else throw new IllegalArgumentException("Date must be writen as 'dd.mm.yyyy'.");
    }

    private boolean validDate(String date) {
        String day = date.substring(0,2);
        if (Integer.parseInt(day) < 1 || Integer.parseInt(day) > 31) {
            return false;
        }
        String month = date.substring(2,4);
        if (Integer.parseInt(day) < 1 || Integer.parseInt(day) > 12) {
            return false;
        }
        String year = date.substring(4,8);
        if (Integer.parseInt(year) < 0 || Integer.parseInt(day) > 2022) {
            return false;
        }
        String dateTest = day + "." + month + "." + year;
        if (!dateTest.equals(date)) {
            return false;
        }
        return true;
    }

    public String getName() {
        return this.name;
    }

    public String getDate() {
        return this.date;
    }

    public void addExercise(Exercise exercise) {
        exercises.add(exercise);
    }

    public void removeExercise(Exercise exercise) {
        if (exercises.contains(exercise)) {
            exercises.remove(exercise);
        }
        else throw new IllegalArgumentException("The exercise doesn't exist");
    }

    public int getTime() {
        for (Exercise exercise : exercises) {
            this.time += exercise.getTime();
        }
        return this.time;
    }

    @Override
    public String toString() {
        return this.name + ", Date: " + this.date;
    }
}
