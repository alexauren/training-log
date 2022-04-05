package traininglog;

import java.io.IOException;
import java.util.List;

public interface FileHandlerInterface {
    
    public List<Workout> readFromFile() throws IOException;

    public void writeToFile(List<Workout> workouts) throws IOException;

}