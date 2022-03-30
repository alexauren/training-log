package traininglog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileEditor implements FileEditorInterface {

    private List<Workout> workouts = new ArrayList<>();
    private Workout workout;

    public void readFile(String file) {
        try {
            
            //må fikse at den ser forskjell på data til workouts og exercises
        }
        catch(Exception e) {
            
        }
    }

    @Override
    public void writeFile(String filename) {
        // TODO Auto-generated method stub
        File file = new File(filename);
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));
            reader.readLine();
            String s;
            while ((s = reader.readLine()) != null) {
               
            }

        }
    }

    public List<Workout> getLog() {
        return workouts;        
    }

}
