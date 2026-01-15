package th.rosenheim.oop.model;

import th.rosenheim.oop.enums.TrainingType;
import th.rosenheim.oop.interfaces.Schedulable;

import java.util.ArrayList;
import java.util.List;

/**
 * Repräsentiert einen Trainingstag.
 * Pro Tag ist entweder nur Kardio oder Krafttraining erlaubt.
 */
public class TrainingDay implements Schedulable, Comparable<TrainingDay> {

    private String day;
    private TrainingType trainingType;
    private List<Workout> workouts;

    /**
     * Erstellt einen neuen Trainingstag mit dem angegebenen Namen.
     * Zu Beginn enthält der Trainingstag noch keine Workouts.
     * @param day Bezeichnung des Trainingstags (z.B: Montag, Dienstag, etc.)
     */
    public TrainingDay(String day) {

        this.day = day;
        this.workouts = new ArrayList<>();
    }

    /**
     * Gibt den Namen des Trainingstags zurück.
     * @return Name des Trainingstags
     */
    public String getDay() {
        return day;
    }


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
     * @return Gesamtdauer des Trainingstags in Minuten
     */
    @Override
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

    /**
     * Prüft, ob es sich um einen "Rest Day" handelt.
     * @return true, wenn keine Workouts vorhanden sind
     */
    public boolean isRestDay() {
        return workouts.isEmpty();
    }

    /**
     * Gibt die Art des Trainingstags zurück.
     * @return Trainingsart als String
     */
    public String getTrainingTypeLabel() {

        if (workouts.isEmpty()) {
            return "Rest Day";
        }

        if (trainingType == TrainingType.STRENGTH) {
            return "Krafttraining";
        }

        if (trainingType == TrainingType.CARDIO) {
            return "Cardiotraining";
        }

        return "";
    }

    /**
     * Vergleicht zwei Trainingstage anhand ihrer Gesamtdauer.
     * Trainingstage mit kürzerer Dauer gelten als "kleiner".
     */
    @Override
    public int compareTo(TrainingDay other) {
        return Integer.compare(
                this.getTotalDuration(),
                //anderer Trainingstag mit dem verglichen wird
                other.getTotalDuration()
        );
    }

}
