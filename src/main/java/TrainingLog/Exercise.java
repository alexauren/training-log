package traininglog;

public class Exercise {

    private String name;
    private int time;
    private int intensity;

    public Exercise(String name, int time, int intensity) {
        if (isValidExercise(name, time, intensity)) {
            this.name = name;
            this.time = time;
            this.intensity = intensity;
        } else
            throw new IllegalArgumentException("Invalid exercise parameters.");
    }

    private boolean isValidExercise(String name, int time, int intensity) {
        if (time <= 0 || time > 180) {
            throw new IllegalArgumentException("Please enter a time between 1 and 180 minutes.");
        } else if (intensity <= 0 || intensity > 10) {
            throw new IllegalArgumentException("Please enter an intensity between 1 and 10.");
        } else if (name == null || name.length() > 15) {
            throw new IllegalArgumentException("Please enter a valid exercise name (1-15 chars).");
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

    public String exerciseTofile() {
        return name + "," + time + "," + intensity + ",";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Exercise other = (Exercise) obj;
        if (intensity != other.intensity)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (time != other.time)
            return false;
        return true;
    }
}