package th.rosenheim.oop.model;

import th.rosenheim.oop.enums.ExperienceLevel;

import java.util.List;

/**
 * Repräsentiert ein Cardio-Workout.
 * Die Dauer des Workouts richtet sich nach dem Erfahrungslevel des Benutzers.
 * Ein Cardio-Workout besteht aus genau einer Übung.
 */
public class CardioWorkout extends Workout {

    //Speichert das Erfahrungslevel des Users
    private ExperienceLevel experienceLevel;

    /**
     * Erstellt ein Cardio-Workout für einen Benutzer.
     * @param exercises Liste der Cardio-Übungen (genau eine Übung)
     * @param experienceLevel Erfahrungslevel des Benutzers
     */
    public CardioWorkout(List<Exercise> exercises,
                         ExperienceLevel experienceLevel) {
        super(exercises);

        //Verhindert dass z.B Joggen und Rudern in einem Training sind
        //Nur EIN Kardio-Training pro Workout
        if (exercises.size() != 1) {
            throw new IllegalArgumentException(
                    "Ein CardioWorkout darf genau eine Übung enthalten."
            );
        }

        this.experienceLevel = experienceLevel;
    }

    /**
     * Berechnet die Dauer des Cardio-Workouts abhängig vom Erfahrungslevel
     * mit festgelegten Werten.
     * @return Dauer in Minuten
     */
    @Override
    public int calculateDuration() {
        switch (experienceLevel) {
            case BEGINNER:
                return 20;
            case INTERMEDIATE:
                return 30;
            case ADVANCED:
                return 45;
            case EXPERT:
                return 60;

            //Dadurch gibt es im Sicherheitsfall immer einen Rückgabewert
            default:
                return 20;
        }
    }
}
