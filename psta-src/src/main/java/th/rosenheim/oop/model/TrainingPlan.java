package th.rosenheim.oop.model;

import th.rosenheim.oop.enums.ExperienceLevel;
import th.rosenheim.oop.enums.MuscleGroup;

import java.util.List;
import java.util.Map;

/**
 * Die Klasse TrainingPlan repräsentiert einen vollständigen
 * Trainingsplan für ein bestimmtes Erfahrungslevel.
 * Pro Muskelgruppe können mehrere Übungen enthalten sein.
 */
public class TrainingPlan {

    private ExperienceLevel level;
    private Map<MuscleGroup, List<Exercise>> exercises;

    /**
     * Erstellt einen neuen Trainingsplan.
     * @param level Erfahrungslevel für den Trainingsplan
     * @param exercises Zuordnung von Muskelgruppen zu ihren Übungen
     */
    public TrainingPlan(ExperienceLevel level,
                        Map<MuscleGroup, List<Exercise>> exercises) {
        this.level = level;
        this.exercises = exercises;
    }

    /**
     * Gibt das Erfahrungslevel des Trainingsplans zurück.
     * @return Erfahrungslevel
     */
    public ExperienceLevel getLevel() {
        return level;
    }

    /**
     * Gibt alle Übungen des Trainingsplans zurück.
     * Jede Muskelgruppe ist dabe ieiner Liste von Übungen zugeordnet
     * @return Map aus Muskelgruppe und Liste der Übungen
     */
    public Map<MuscleGroup, List<Exercise>> getExercises() {
        return exercises;
    }

    /**
     * Textuelle Darstellung des Trainingsplans.
     * @return Trainingsplan als String
     */
    @Override
    public String toString() {
        return "TrainingsPlan (" + level + "): " + exercises;
    }
}
