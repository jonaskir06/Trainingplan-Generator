package th.rosenheim.oop.model;

import java.util.List;

/**
 * Abstrakte Basisklasse für alle Arten von Workouts.
 * Ein Workout besteht aus einer Liste von Übungen
 * und besitzt eine festgelegte Dauer.
 */
public abstract class Workout { //abstract da von Workout kein Objekt erzeugt wird (immer nur StrengthWorkout bzw CardioWorkout)

    protected List<Exercise> exercises; //protected da Unterklassen direkt auf die Übungen zugreifen können, aber fremde Klassen nicht

    /**
     * Erstellt ein Workout anhand der hinterlegten Übungen.
     * @param exercises Liste der Übungen
     */
    public Workout(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    /**
     * Gibt die im Workout enthaltenen Übungen zurück.
     * @return Liste von Übungen
     */
    public List<Exercise> getExercises() {
        return exercises;
    }

    /**
     * Berechnet die Gesamtdauer des Workouts in Minuten.
     * @return Gesamtdauer in Minuten
     */
    public abstract int calculateDuration();
    //abstract da jede Art von Workout ihre Dauer unterschiedlich berechnet,
    //aber jedes Workout eine Dauer haben MUSS.
}
