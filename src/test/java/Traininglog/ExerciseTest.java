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
		});
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Exercise("Long run", 10, 0);
		});
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Exercise("Long run", 181, 8);
		});
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Exercise("Long run", 10, 11);
		});
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Exercise("1234567890123456", 0, 8);
		});
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Exercise("", 0, 8);
		});
    }

    @Test
    @DisplayName("Test toString and exerciseToFile")
    public void testToStringAndToFile() {
        Assertions.assertEquals("Exercise: Squat, Time: 15, Intensity: 8", e1.toString());
        Assertions.assertEquals("Squat,15,8,", e1.exerciseTofile());
    }
}

