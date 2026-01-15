package th.rosenheim.oop.model;

import th.rosenheim.oop.enums.ExperienceLevel;
import th.rosenheim.oop.enums.TrainingGoal;

import java.util.List;

/**
 * Die Klasse TrainingPlan repräsentiert einen vollständigen
 * Trainingsplan für ein bestimmtes Erfahrungslevel.
 * Ein Trainingsplan besteht aus mehreren Trainingstagen
 */
public class TrainingPlan {

    private ExperienceLevel level;
    private TrainingGoal trainingGoal;
    private List<TrainingDay> trainingDays;

    /**
     * Erstellt einen neuen Trainingsplan.
     *
     * @param level        Erfahrungslevel für den Trainingsplan
     * @param trainingGoal Trainingszeil des Users
     * @param trainingDays Liste der Trainingstage
     */
    public TrainingPlan(ExperienceLevel level,
                        TrainingGoal trainingGoal, List<TrainingDay> trainingDays) {
        this.level = level;
        this.trainingGoal = trainingGoal;
        this.trainingDays = trainingDays;
    }

    /**
     * Gibt das Erfahrungslevel des Users zurück.
     * @return Erfahrungslevel
     */
    public ExperienceLevel getLevel() {
        return level;
    }

    /**
     * Gibt das Trainingsziel des Users zurück.
     * @return Trainingsziel
     */
    public TrainingGoal getTrainingGoal() {
        return trainingGoal;
    }


    /**
     * Gibt alle Trainingstage des Trainingsplans zurück.
     * @return Liste der Trainingstage
     */
    public List<TrainingDay> getTrainingDays() {
        return trainingDays;
    }

    /**
     * Darstellung des Trainingsplans.
     * @return Trainingsplan als String
     */
    @Override
    public String toString() {
        return "TrainingPlan (" + level + ", Tage: " + trainingDays.size() + ")";
    }
}
