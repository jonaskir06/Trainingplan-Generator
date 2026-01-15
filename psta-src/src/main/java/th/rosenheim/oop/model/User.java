package th.rosenheim.oop.model;

import th.rosenheim.oop.enums.ExperienceLevel;
import th.rosenheim.oop.enums.TrainingGoal;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Repräsentiert einen User der Trainings-App.
 * Jeder User besitzt einen Namen, ein Erfahrungslevel,
 * ein Trainingsziel und mehrere Trainingstage.
 */
public class User {

    private String name;
    private ExperienceLevel experienceLevel;
    private TrainingGoal trainingGoal;
    private List<TrainingDay> trainingDays;

    /**
     * Erstellt einen neuen Benutzer.
     * @param name Name des Benutzers
     * @param experienceLevel Erfahrungslevel des Benutzers
     * @param trainingGoal Trainingsziel des Benutzers
     */
    public User(String name,
                ExperienceLevel experienceLevel,
                TrainingGoal trainingGoal) {

        this.name = name;
        this.experienceLevel = experienceLevel;
        this.trainingGoal = trainingGoal;
        //trainingDays leer damit später Trainingstage hinzugefügt werden können
        this.trainingDays = new ArrayList<>();
    }

    /**
     * Fügt dem Benutzer einen Trainingstag hinzu.
     * @param trainingDay Trainingstag
     */
    public void addTrainingDay(TrainingDay trainingDay) {
        trainingDays.add(trainingDay);
    }

    /**
     * Gibt alle Trainingstage des Benutzers zurück.
     * @return Liste der Trainingstage
     */
    public List<TrainingDay> getTrainingDays() {
        return trainingDays;
    }

    /**
     * Gibt das Erfahrungslevel des Benutzers zurück.
     * @return Erfahrungslevel
     */
    public ExperienceLevel getExperienceLevel() {
        return experienceLevel;
    }

    /**
     * Gibt das Trainingsziel des Benutzers zurück.
     * @return Trainingsziel
     */
    public TrainingGoal getTrainingGoal() {
        return trainingGoal;
    }

    /**
     * Berechnet die gesamte Trainingsdauer über alle Trainingstage.
     * @return Gesamtdauer in Minuten
     */
    public int getTotalTrainingDuration() {
        int total = 0;
        //für jeden Trainingstag wird die summierte Trainingseinheitsdauer ausgegeben
        for (TrainingDay day : trainingDays) {
            total += day.getTotalDuration();
        }
        return total;
    }

    /**
     * Gibt den Namen des Benutzers zurück.
     * @return Name
     */
    public String getName() {
        return name;
    }

    /**
     * Textuelle Darstellung des Benutzers.
     * @return Benutzer als String
     */
    @Override
    public String toString() {
        //Konsolenausgabe
        return "User{" +
                "name='" + name + '\'' +
                ", experienceLevel=" + experienceLevel +
                ", trainingGoal=" + trainingGoal +
                ", trainingDays=" + trainingDays.size() +
                '}';
    }

    /**
     * Vergleicht zwei Benutzer anhand ihres Namens
     * @param o Vergleichsobjekt
     * @return true, wenn die Benutzer gleich sind
     */
    @Override
    //Ein Benutzer ist eindeutig durch seinen Namen definiert (hier genutzt um equals() einzubauen)
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(name, user.name);
    }

    /**
     * Berechnet den Hashcode des Benutzers.
     * @return Hashcode
     */
    @Override
    //Ohne hashCode() ist equals() zumindest formal unvollständig
    public int hashCode() {
        return Objects.hash(name);
    }
}
