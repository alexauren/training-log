package traininglog;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileHandler implements FileHandlerInterface {

    @Override
    public void writeToFile(List<Workout> workouts) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("src\\main\\java\\traininglog\\TrainingLog.txt"));
        workouts.stream().forEach(workout -> {
            try {
                writer.write(workout.workoutToFile());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        writer.close();
    }

    @Override
    public ArrayList<Workout> readFromFile() throws IOException {
        ArrayList<Workout> workouts = new ArrayList<>();
        File file = new File("src\\main\\java\\traininglog\\TrainingLog.txt");
        BufferedReader reader;
        reader = new BufferedReader(new FileReader(file));
        String s;
        while ((s = reader.readLine()) != null) {
            Workout w = stringToWorkout(s);
            workouts.add(w);
        }
        reader.close();
        return workouts;
    }

    private Workout stringToWorkout(String s) {
        List<String> strings = new ArrayList<String>(Arrays.asList(s.split(",")));
        Workout workout = new Workout((strings).get(0), strings.get(1));
        for (int i = 2; i < strings.size(); i += 3) {
            Exercise exercise = new Exercise(strings.get(i), Integer.parseInt(strings.get(i + 1)),
                    Integer.parseInt(strings.get(i + 2)));
            workout.addExercise(exercise);
        }
        return workout;
    }

}
