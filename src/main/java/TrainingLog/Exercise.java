package TrainingLog;

public class Exercise {
    
    private String name;
    private char type; 
    private int time;
    private int intensity;
    
    public Exercise(String name, char type, int time, int intensity) {
        if (this.isValidExercise()) {
            this.name = name;
            this.type = type;
            this.time = time;
            this.intensity = intensity;
        }
        else throw new IllegalArgumentException("Invalid exercse parameters.");     
    }

    private boolean isValidExercise() {
        if (type != 'E' || type != 'S') {
           return false; 
        }
        else if (time <= 0 && time > 180) {
            return false;
        }
        else if (intensity <= 0 && intensity > 10) {
        return false;
        }
        return true;
    }

    
    public String getName() {
        return this.name;
    }
    
    public char getType() {
        return this.type;
    }

    public int getTime() {
        return this.time;
    }

    
    public int getIntensity() {
        return this.intensity;
    }

}
