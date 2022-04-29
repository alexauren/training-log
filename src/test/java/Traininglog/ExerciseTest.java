package Traininglog;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import traininglog.Exercise;

public class ExerciseTest {

    private Exercise e1;

    @BeforeEach
    public void setup() {
        e1 = new Exercise("Squat", 15, 8);
    }

    @Test
    @DisplayName("Test constructor")
    public void testConstructor() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Exercise("Long run", 0, 8);
		}, "Time must be 1-180");
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Exercise("Long run", 10, 0);
		}, "intensity must be 1-10");
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Exercise("Long run", 181, 8);
		}, "Time must be 1-180");
        
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Exercise("Long run", 10, 11);
		}, "Intensity must be 1-10");
        
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Exercise("1234567890123456", 10, 8);
		}, "Name cannot be 1-15 chars");
        
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Exercise("", 0, 8);
		}, "Name must be 1-15 chars");
        
        e1 = new Exercise("Squat", 180, 10);
        e1 = new Exercise("Squat", 1, 1);
    }

    @Test
    @DisplayName("Test toString and exerciseToFile")
    public void testToStringAndToFile() {
        Assertions.assertEquals("Exercise: Squat, Time: 15, Intensity: 8", e1.toString());
        Assertions.assertEquals("Squat,15,8,", e1.exerciseTofile());
    }

    @Test
    @DisplayName("Test equals")
    public void testEquals() {
        Assertions.assertEquals(e1, new Exercise("Squat", 15, 8));
        
        Assertions.assertFalse(e1.equals(new Exercise("Squa", 15, 8)));
        Assertions.assertFalse(e1.equals(new Exercise("Squat", 16, 8)));
        Assertions.assertFalse(e1.equals(new Exercise("Squat", 15, 9)));
    }
}

