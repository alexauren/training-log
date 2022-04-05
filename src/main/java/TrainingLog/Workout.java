package traininglog;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Workout {

    private String name;
    private Collection<Exercise> exercises = new ArrayList<>();
    private String date;
    
    public Workout(String name, String date) {
        if(!validDate(date)) {
            throw new IllegalArgumentException("Please enter a valid date.");
        }

        else if(name.length() < 1 || name.length() > 20) {
            throw new IllegalArgumentException("Please enter a valid workout name (1-20 chars).");
        }
        else {
            this.name = name;
            this.date = date;
        }
    }

    private boolean validDate(String date) {
        if(date.length() < 10) {
            return false;
        }
        String day = date.substring(0,2);
        if (Integer.parseInt(day) < 1 || Integer.parseInt(day) > 31) {
            return false;
        }
        String month = date.substring(3,5);
        if (Integer.parseInt(day) < 1 || Integer.parseInt(day) > 12) {
            return false;
        }
        String year = date.substring(6,10);
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
        return name;
    }

    public String getDate() {
        return date;
    }

    public void addExercise(Exercise exercise) {
        exercises.add(exercise);
    }


    public int getTime() {
        return this.getExercises().stream().mapToInt(e -> e.getTime()).sum();
    }

    public double getAvgIntensity() {
        return this.getExercises().stream()
            .mapToDouble(e -> e.getIntensity()).sum() 
            / this.getExercises().size();
    }

    public Collection<Exercise> getExercises() {
        return new ArrayList<Exercise>(exercises);
    }

    @Override
    public String toString() {
        return name + ", Date: " + date;
    }

    public String extendedToString() {

        String tmpExercises = new String();
        for (Exercise exercise : exercises) {
            tmpExercises += exercise.toString() + "\n";
        }
        String tmpString = new String(
        "Name: " + name + ", " +
        "Date: " + date + 
        "\n\n" + tmpExercises + "\n" +
        "Total time spent: " + this.getTime()
        );
        
        return tmpString;
    }

    public String workoutToFile() {
        String tmpExercises = this.getExercises().stream().map(s -> s.exerciseTofile()).collect(Collectors.joining());
        return this.getName() + "," + this.getDate() + "," + tmpExercises + "\n";
    }

    private int getYear() {
        return Integer.parseInt(this.getDate().substring(6));
    }
    private int getMonth() {
        return Integer.parseInt(this.getDate().substring(3,5));
    }
    private int getDay() {
        return Integer.parseInt(this.getDate().substring(0,2));
    }

    public static Comparator<Workout> workoutComparatorDate = (o1, o2) -> {
        if (o1.getYear() != o2.getYear()){
            return o1.getYear() - o2.getYear();
        }
        else if (o1.getMonth() != o2.getMonth()){
            return o1.getMonth() - o2.getMonth();
        }
        else {
            return o1.getDay() - o2.getDay();
        }
    };

    public static Comparator<Workout> workoutComparatorTime = (o1, o2) -> (int) o1.getTime() - o2.getTime();
    
}
