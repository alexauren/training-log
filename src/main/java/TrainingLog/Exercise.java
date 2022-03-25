package traininglog;

import java.util.Objects;

public class Exercise {
    
    private String name;
    private int time;
    private int intensity;
    
    public Exercise(String name, int time, int intensity) {
        if (isValidExercise(name, time, intensity)) {
            this.name = name;
            this.time = time;
            this.intensity = intensity;
        }
        else throw new IllegalArgumentException("Invalid exercise parameters.");     
    }

    private boolean isValidExercise(String name, int time, int intensity) {
        if (time <= 0 || time > 180 || Objects.isNull(time)) {
            throw new IllegalArgumentException("Please enter a time between 0 and 180 minutes.");

        }
        else if (intensity <= 0 || intensity > 10) {
            throw new IllegalArgumentException("Please enter an intensity between 0 and 10 minutes.");
        }
        if (name == null || name.length() > 20) {
            throw new IllegalArgumentException("Please enter a valid exercise name (1-20 chars).");
        }
        return true;
    }

    
    public String getName() {
        return name;
    }

    public int getTime() {
        return time;
    }

    
    public int getIntensity() {
        return intensity;
    }

    @Override
    public String toString() {
        return "Exercise: " + name + ", Time: " + time + ", Intensity: " + intensity;
    }
}
