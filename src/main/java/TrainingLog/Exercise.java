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
            return false;
        }
        else if (intensity <= 0 || intensity > 10) {
        return false;
        }
        if (name == null) {
            return false;
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
