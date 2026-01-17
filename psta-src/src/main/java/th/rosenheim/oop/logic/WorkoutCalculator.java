package th.rosenheim.oop.logic;

import th.rosenheim.oop.model.Workout;

import java.util.List;

/**
 * Hilfsklasse zur Berechnung der Gesamtdauer mehrerer Workouts.
 */
public class WorkoutCalculator {

    /**
     * Berechnet die Gesamtdauer mehrerer Workouts.
     * @param workouts Liste der Workouts
     * @return Gesamtdauer in Minuten
     */
    public int calculateTotalDuration(List<Workout> workouts) {
        int total = 0;

        for (Workout workout : workouts) {
            total += workout.calculateDuration();
        }
        return total;
    }
}
