package th.rosenheim.oop.logic;

import th.rosenheim.oop.enums.DifficultyLevel;
import th.rosenheim.oop.enums.ExperienceLevel;
import th.rosenheim.oop.enums.MuscleGroup;
import th.rosenheim.oop.model.CardioWorkout;
import th.rosenheim.oop.model.Exercise;
import th.rosenheim.oop.model.StrengthWorkout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Erzeugt Workouts basierend auf Erfahrungslevel und Trainingslogik.
 */
public class PlanGenerator {

    /**
     * Erstellt ein Krafttraining mit mehreren Übungen pro Muskelgruppe,
     * abhängig vom Erfahrungslevel des Benutzers.
     *
     * @param allExercises alle verfügbaren Übungen
     * @param level Erfahrungslevel des Benutzers
     * @return StrengthWorkout
     */
    public StrengthWorkout createStrengthWorkout(List<Exercise> allExercises,
                                                 ExperienceLevel level) {

        // Gruppierung: Muskelgruppe -> Liste von Übungen
        Map<MuscleGroup, List<Exercise>> groupedExercises = new HashMap<>();

        for (Exercise exercise : allExercises) {
            if (isAllowedDifficulty(level, exercise.getDifficulty())) {

                groupedExercises
                        .computeIfAbsent(exercise.getMuscleGroup(),
                                g -> new ArrayList<>())
                        .add(exercise);
            }
        }

        // Alle Übungen wieder zu einer Liste zusammenführen
        List<Exercise> selectedExercises = new ArrayList<>();

        for (List<Exercise> groupList : groupedExercises.values()) {
            selectedExercises.addAll(groupList);
        }

        if (selectedExercises.isEmpty()) {
            throw new IllegalStateException(
                    "Keine passenden Kraftübungen für dieses Erfahrungslevel gefunden."
            );
        }

        return new StrengthWorkout(selectedExercises);
    }

    /**
     * Erstellt ein Cardio-Workout mit genau einer Übung.
     *
     * @param cardioExercise Cardio-Übung (z. B. Schwimmen)
     * @param level Erfahrungslevel des Benutzers
     * @return CardioWorkout
     */
    public CardioWorkout createCardioWorkout(Exercise cardioExercise,
                                             ExperienceLevel level) {
        return new CardioWorkout(List.of(cardioExercise), level);
    }

    /**
     * Prüft, ob eine Übung für das gegebene Erfahrungslevel erlaubt ist.
     *
     * @param level Erfahrungslevel
     * @param difficulty Schwierigkeitsgrad der Übung
     * @return true, wenn erlaubt
     */
    private boolean isAllowedDifficulty(ExperienceLevel level,
                                        DifficultyLevel difficulty) {

        switch (level) {
            case BEGINNER:
                return difficulty == DifficultyLevel.EASY;

            case INTERMEDIATE:
                return difficulty == DifficultyLevel.EASY
                        || difficulty == DifficultyLevel.MEDIUM;

            case ADVANCED:
                return difficulty == DifficultyLevel.MEDIUM;

            case EXPERT:
                return difficulty == DifficultyLevel.MEDIUM
                        || difficulty == DifficultyLevel.HARD;

            default:
                return false;
        }
    }
}
