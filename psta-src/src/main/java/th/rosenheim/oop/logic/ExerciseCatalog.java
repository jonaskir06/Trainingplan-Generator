package th.rosenheim.oop.logic;

import th.rosenheim.oop.enums.DifficultyLevel;
import th.rosenheim.oop.enums.MuscleGroup;
import th.rosenheim.oop.model.Exercise;

import java.util.ArrayList;
import java.util.List;

public class ExerciseCatalog {

    /**
     * Liefert alle verfügbaren Übungen.
     *
     * @return Liste aller Übungen
     */
    public static List<Exercise> getAllExercises() {

        List<Exercise> exercises = new ArrayList<>();

        //Brust
        exercises.add(new Exercise("Liegestütze", MuscleGroup.CHEST, DifficultyLevel.EASY, 3, 12, 0));
        exercises.add(new Exercise("Schrägbankdrücken", MuscleGroup.CHEST, DifficultyLevel.MEDIUM, 4, 8, 0));
        exercises.add(new Exercise("Dips", MuscleGroup.CHEST, DifficultyLevel.HARD, 4, 6, 0));

        //Rücken
        exercises.add(new Exercise("Rudern Maschine", MuscleGroup.BACK, DifficultyLevel.EASY, 3, 12, 0));
        exercises.add(new Exercise("Latziehen", MuscleGroup.BACK, DifficultyLevel.MEDIUM, 3, 10, 0));
        exercises.add(new Exercise("Klimmzüge", MuscleGroup.BACK, DifficultyLevel.HARD, 3, 6, 0));

        //Beine
        exercises.add(new Exercise("Beinpresse", MuscleGroup.LEGS, DifficultyLevel.EASY, 3, 12, 0));
        exercises.add(new Exercise("Kniebeugen", MuscleGroup.LEGS, DifficultyLevel.MEDIUM, 4, 8, 0));
        exercises.add(new Exercise("Bulgarian Split Squats", MuscleGroup.LEGS, DifficultyLevel.HARD, 4, 6, 0));

        //Arme
        exercises.add(new Exercise("Bizepscurls", MuscleGroup.ARMS, DifficultyLevel.EASY, 3, 12, 0));
        exercises.add(new Exercise("Trizepsdrücken Kabel", MuscleGroup.ARMS, DifficultyLevel.MEDIUM, 3, 10, 0));
        exercises.add(new Exercise("Enges Bankdrücken", MuscleGroup.ARMS, DifficultyLevel.HARD, 4, 6, 0));

        //Schultern
        exercises.add(new Exercise("Seitheben", MuscleGroup.SHOULDERS, DifficultyLevel.EASY, 3, 12, 0));
        exercises.add(new Exercise("Schulterdrücken", MuscleGroup.SHOULDERS, DifficultyLevel.MEDIUM, 3, 10, 0));
        exercises.add(new Exercise("Arnold Press", MuscleGroup.SHOULDERS, DifficultyLevel.HARD, 4, 6, 0));

        //Bauch
        exercises.add(new Exercise("Sit-ups", MuscleGroup.CORE, DifficultyLevel.EASY, 3, 30, 0));
        exercises.add(new Exercise("Crunches", MuscleGroup.CORE, DifficultyLevel.MEDIUM, 3, 20, 0));
        exercises.add(new Exercise("Hanging Leg Raises", MuscleGroup.CORE, DifficultyLevel.HARD, 4, 12, 0));

        return exercises;
    }
}
