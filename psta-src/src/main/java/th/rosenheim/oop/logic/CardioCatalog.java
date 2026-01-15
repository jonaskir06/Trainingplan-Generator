package th.rosenheim.oop.logic;

import th.rosenheim.oop.enums.CardioType;
import th.rosenheim.oop.enums.DifficultyLevel;
import th.rosenheim.oop.model.CardioExercise;

import java.util.ArrayList;
import java.util.List;

/**
 * Liefert verfügbare Cardio-Übungen.
 */
public class CardioCatalog {

    public static List<CardioExercise> getAllCardioExercises() {

        List<CardioExercise> exercises = new ArrayList<>();

        exercises.add(new CardioExercise(
                "Joggen",
                CardioType.RUNNING,
                DifficultyLevel.EASY
        ));

        exercises.add(new CardioExercise(
                "Radfahren",
                CardioType.CYCLING,
                DifficultyLevel.EASY
        ));

        exercises.add(new CardioExercise(
                "Rudern",
                CardioType.ROWING,
                DifficultyLevel.MEDIUM
        ));

        exercises.add(new CardioExercise(
                "Schwimmen",
                CardioType.SWIMMING,
                DifficultyLevel.MEDIUM
        ));

        return exercises;
    }
}
