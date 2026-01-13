package th.rosenheim.oop.model;

import th.rosenheim.oop.enums.TrainingType;

import java.util.ArrayList;
import java.util.List;

/**
 * Repräsentiert einen Trainingstag.
 * Pro Tag ist entweder nur Kardio oder Krafttraining erlaubt.
 */
public class TrainingDay {

    private String name;
    private TrainingType trainingType;
    private List<Workout> workouts;

    /**
     * Erstellt einen neuen Trainingstag mit dem angegebenen Namen.
     * Zu Beginn enthält der Trainingstag noch keine Workouts.
     * @param name Bezeichnung des Trainingstags (z.B: Montag, Dienstag, etc.)
     */
    public TrainingDay(String name) {

        this.name = name;
        this.workouts = new ArrayList<>();
    }

    /**
     /**
     * Fügt ein Workout zum Trainingstag hinzu.
     * Hier wird geprüft, dass ein Trainingstag entweder ausschließlich
     * Kardio oder ausschließlich Krafttraining enthält.
     *
     * @param workout Das hinzuzufügende Workout
     * @throws IllegalStateException wenn versucht wird,
     *         Cardio- und Krafttraining an einem Tag zu mischen
     */
    public void addWorkout(Workout workout) {

        TrainingType newType =
                workout instanceof CardioWorkout
                        ? TrainingType.CARDIO
                        : TrainingType.STRENGTH;

        if (trainingType == null) {
            trainingType = newType;
        } else if (trainingType != newType) {
            throw new IllegalStateException(
                    "Ein TrainingDay darf entweder nur Kardio oder Krafttraining enthalten!"
            );
        }

        workouts.add(workout);
    }

    /**
     * Die Gesamtdauer eines Trainingstags wird berechnet,
     * indem für jedes enthaltene Workout die Methode calculateDuration aufgerufen wird.
     * Durch Polymorphie wird dabei automatisch die passende Implementierung
     * der jeweiligen Workout-Art verwendet.
     *
     * @return Gesamtdauer des Trainingstags in Minuten
     */
    public int getTotalDuration() {
        int total = 0;
        for (Workout workout : workouts) {
            total += workout.calculateDuration();
        }
        return total;
    }

    /**
     * Gibt alle Workouts des Trainingstags zurück.
     * @return Liste der Workouts
     */
    public List<Workout> getWorkouts() {
        return workouts;
    }
}
