package th.rosenheim.oop;

//Importiert die JUnit-Annotation @Test
import org.junit.jupiter.api.Test;

import th.rosenheim.oop.model.*;
import th.rosenheim.oop.enums.ExperienceLevel;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testet, dass ein TrainingDay nicht Kardio- und Krafttraining mischen darf.
 */
class TrainingDayTest {

    @Test
    void testMixedWorkoutTypesNotAllowed() {

        //Erzeugt einen leeren Trainingstag
        TrainingDay day = new TrainingDay("Montag");

        //Liste für Kraftübungen
        List<Exercise> strengthExercises = new ArrayList<>();

        strengthExercises.add(
                new Exercise("Liegestütze", null, null, 3, 10, 0)
        );

        StrengthWorkout strengthWorkout =
                new StrengthWorkout(strengthExercises);

        //Liste für Kardioübung
        List<Exercise> cardioExercises = new ArrayList<>();
        cardioExercises.add(
                new CardioExercise("Joggen", null, null)
        );

        CardioWorkout cardioWorkout =
                new CardioWorkout(
                        cardioExercises,
                        ExperienceLevel.BEGINNER
                );

        //Krafttraining als erster Eintrag, deshalb wird der Trainingstyp des Tages auf Krafttraining festgelegt
        day.addWorkout(strengthWorkout);

        //Erwartet: Exception bei Mischen der zwei Trainingstypen
        assertThrows(IllegalStateException.class,
                () -> day.addWorkout(cardioWorkout));
    }
}
