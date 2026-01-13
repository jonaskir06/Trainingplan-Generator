package th.rosenheim.oop.model;

import java.util.List;

/**
 * Repräsentiert ein Krafttraining-Workout.
 * Die Dauer berechnet sich aus der Zeit pro Satz
 * sowie den standardisierten Pausen zwischen den Sätzen.
 */
public class StrengthWorkout extends Workout {

    /** Dauer eines Satzes in Minuten */
    private static final int MINUTES_PER_SET = 1;

    /** Pause zwischen zwei Sätzen in Minuten */
    private static final int BREAK_BETWEEN_SETS = 2;

    /**
     * Erstellt ein Krafttraining mit den angegebenen Kraftübungen.
     * @param exercises Liste der Kraftübungen
     */
    public StrengthWorkout(List<Exercise> exercises) {

        //super(exercises) ruft den Konstruktor der Oberklasse Workout auf
        //damit die gemeinsamen Attribute korrekt initialisiert werden
        super(exercises);
    }

    /**
     * Berechnet die Gesamtdauer des Krafttrainings.
     * Für jeden Satz gibt es eine festgelegte pauschale Ausführungszeit
     * sowie eine zeitlich immer gleiche Pause.
     * @return Gesamtdauer in Minuten
     */
    @Override //damit die Methode aus der Oberklasse überschrieben werden kann
    public int calculateDuration() {
        int totalDuration = 0;

        //Berechnung der Dauer für jede Übung im Workout
        for (Exercise exercise : exercises) {

            //Ruft Anzahl der Sätze der jeweiligen Übung ab
            int sets = exercise.getSets();

            // Zeit für alle Sätze
            totalDuration += sets * MINUTES_PER_SET;

            // Pausen zwischen den Sätzen (keine Pause nach letztem Satz)
            totalDuration += (sets - 1) * BREAK_BETWEEN_SETS;
        }

        return totalDuration;
    }
}
