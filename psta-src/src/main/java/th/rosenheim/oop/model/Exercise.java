package th.rosenheim.oop.model;

import th.rosenheim.oop.enums.DifficultyLevel;
import th.rosenheim.oop.enums.MuscleGroup;

import java.util.Objects;

/**
 * Repräsentiert eine einzelne Trainingsübung und enthällt
 * alle relevanten Informationen wie Muskelgruppe, Schwierigkeit
 * und sonstige Trainingsparameter.
 */
public class Exercise {

    private String name;
    private MuscleGroup muscleGroup;
    private DifficultyLevel difficulty;
    private int sets;
    private int reps;
    private int durationInMinutes;

    /**
     * Erstellt eine neue Übung mit allen notwendigen Trainingsparametern.
     *
     * @param name Name der Übung
     * @param muscleGroup Trainierte Muskelgruppe
     * @param difficulty Schwierigkeitsgrad der Übung
     * @param sets Anzahl der Sätze
     * @param reps Anzahl der Wiederholungen pro Satz
     * @param durationInMinutes Dauer der Übung in Minuten
     */
    public Exercise(String name,
                    MuscleGroup muscleGroup,
                    DifficultyLevel difficulty,
                    int sets,
                    int reps,
                    int durationInMinutes) {

        this.name = name;
        this.muscleGroup = muscleGroup;
        this.difficulty = difficulty;
        this.sets = sets;
        this.reps = reps;
        this.durationInMinutes = durationInMinutes;
    }

    /**
     * Gibt den Namen der Übung zurück.
     * @return Name der Übung
     */
    public String getName() {
        return name;
    }

    /**
     * Gibt die Muskelgruppe der Übung zurück.
     * @return Muskelgruppe
     */
    public MuscleGroup getMuscleGroup() {
        return muscleGroup;
    }

    /**
     * Gibt den Schwierigkeitsgrad der Übung zurück.
     * @return Schwierigkeitsgrad
     */
    public DifficultyLevel getDifficulty() {
        return difficulty;
    }

    /**
     * Gibt die Anzahl der Sätze zurück.
     * @return Anzahl der Sätze
     */
    public int getSets() {
        return sets;
    }

    /**
     * Gibt die Anzahl der Wiederholungen pro Satz zurück.
     * @return Wiederholungen pro Satz
     */
    public int getReps() {
        return reps;
    }

    /**
     * Gibt die Dauer der Übung in Minuten zurück.
     * @return Dauer in Minuten
     */
    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    /**
     * Textuelle Darstellung der Übung.
     * @return Übung als String
     */
    @Override   //Override nutzen da diese Methode die Methode aus der Parentclass Object überschreibt
    public String toString() {
        return name + " (" + muscleGroup + ", " + difficulty + ")";
    }

    /**
     * Vergleicht zwei Übungen und prüft,
     * ob sie inhaltlich gleich sind.
     * Die Gleichheit wird anhand von Name, Muskelgruppe
     * und Schwierigkeit bestimmt.
     */
    @Override
    public boolean equals(Object o) {

        //Prüft ob beide Referenzen auf dasselbe Objekt zeigen
        //Wenn ja, sind sie automatisch gleich
        if (this == o) return true;

        //Prüft ob das übergebene Objekt überhaupt eine Exercise ist
        //Falls nicht können sie nicht gleich sein
        if (!(o instanceof Exercise)) return false;

        //Später können dann Duplikate verhindert werden
        Exercise e = (Exercise) o;
        return Objects.equals(name, e.name)
                && muscleGroup == e.muscleGroup
                && difficulty == e.difficulty;
    }

    /**
     * Berechnet den Hashcode der jeweiligen Übung.
     * @return Hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, muscleGroup, difficulty);
    }
}
