package traininglog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

public abstract class Workout {

    private String name;
    protected List<Exercise> exercises = new ArrayList<>();
    private String date;

    public Workout(String name, String date) {
        if (!validDate(date)) {
            throw new IllegalArgumentException("Please enter a valid date from either this year or last year.");
        }

        else if (name.length() < 1 || name.length() > 15) {
            throw new IllegalArgumentException("Please enter a valid workout name (1-15 chars).");
        } else {
            this.name = name;
            this.date = date;
        }
    }

    private boolean validDate(String date) {
        if (date.length() < 10) {
            return false;
        }

        String day = date.substring(0, 2);
        if (Integer.parseInt(day) < 1 || Integer.parseInt(day) > 31) {
            return false;
        }

        String month = date.substring(3, 5);
        if (Integer.parseInt(month) < 1 || Integer.parseInt(month) > 12) {
            return false;
        }

        String year = date.substring(6, 10);
        if (Integer.parseInt(year) < Calendar.getInstance().get(Calendar.YEAR) - 1 
        || Integer.parseInt(year) > Calendar.getInstance().get(Calendar.YEAR)) {
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

    public abstract void addExercise(Exercise exercise);

    public int getTime() {
        return this.getExercises().stream().mapToInt(e -> e.getTime()).sum();
    }

    public double getAvgIntensity() {
        return this.getExercises().stream()
                .mapToDouble(e -> e.getIntensity()).sum()
                / this.getExercises().size();
    }

    public List<Exercise> getExercises() {
        return new ArrayList<Exercise>(exercises);
    }

    public String extendedToString() {

        String tmpExercises = new String();
        for (Exercise exercise : exercises) {
            tmpExercises += exercise.toString() + "\n";
        }
        String tmpString = new String(
            this.toString() +
            "\n\n" + tmpExercises + "\n" +
            "Total time spent: " + this.getTime() + " minutes.");

        return tmpString;
    }

    public abstract String workoutToFile();

    private int getYear() {
        return Integer.parseInt(this.getDate().substring(6));
    }

    private int getMonth() {
        return Integer.parseInt(this.getDate().substring(3, 5));
    }

    private int getDay() {
        return Integer.parseInt(this.getDate().substring(0, 2));
    }

    public static Comparator<Workout> workoutComparatorDate = (o1, o2) -> {
        if (o1.getYear() != o2.getYear()) {
            return o1.getYear() - o2.getYear();
        } 
        else if (o1.getMonth() != o2.getMonth()) {
            return o1.getMonth() - o2.getMonth();
        } 
        else {
            return o1.getDay() - o2.getDay();
        }   
    };

    public static Comparator<Workout> workoutComparatorIntensity = (o1, o2) 
            -> (int) o1.getAvgIntensity() - (int)o2.getAvgIntensity();

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + ((exercises == null) ? 0 : exercises.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        Workout other = (Workout) obj;
        if (date == null) {
            if (other.date != null)
                return false;
        } else if (!date.equals(other.date))
            return false;
        if (exercises == null) {
            if (other.exercises != null)
                return false;
        } else if (!exercises.equals(other.exercises))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    
}
