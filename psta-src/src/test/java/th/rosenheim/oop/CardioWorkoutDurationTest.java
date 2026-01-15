package th.rosenheim.oop;

import org.junit.jupiter.api.Test;
import th.rosenheim.oop.model.*;
import th.rosenheim.oop.enums.ExperienceLevel;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testet, dass die Dauer eines CardioWorkouts
 * vom Erfahrungslevel des Benutzers abhängt.
 */
class CardioWorkoutDurationTest {

    @Test
    void testDurationDependsOnExperienceLevel() {

        //Kardioübung
        CardioExercise jogging =
                new CardioExercise("Joggen", null, null);

        //Liste mit genau einer Kardioübung, sonst scheitert der Test
        List<Exercise> cardioExercises = new ArrayList<>();
        cardioExercises.add(jogging);

        //Beginner-Workout
        CardioWorkout beginnerWorkout =
                new CardioWorkout(cardioExercises, ExperienceLevel.BEGINNER);

        //Experten-Workout
        CardioWorkout expertWorkout =
                new CardioWorkout(cardioExercises, ExperienceLevel.EXPERT);

        //Erwartet Dauer basierend auf dem Erfahrungslevel
        assertEquals(20, beginnerWorkout.calculateDuration());
        assertEquals(60, expertWorkout.calculateDuration());
    }
}
