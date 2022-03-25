package traininglog;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileEditor implements FileEditorInterface {

    private List<Workout> workouts = new ArrayList<>();

    public void readFile(String file) {
        try {
            URL classUrl = getClass().getResource("flerkamp.txt");
            Path path = Paths.get(classUrl.toURI());

            this.workouts = Files.lines(Paths.get(getClass().getResource(file).toURI()))
            .map(w -> w.split("\t"))
            .map(e -> e.split(","))
            .toList();
            
            //må fikse at den ser forskjell på data til workouts og exercises
        }
        catch(Exception e) {
            
        }
    }

    @Override
    public void writeFile(String file) {
        // TODO Auto-generated method stub
        
    }

}
