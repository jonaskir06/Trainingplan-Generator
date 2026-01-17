package th.rosenheim.oop;

import th.rosenheim.oop.enums.ExperienceLevel;
import th.rosenheim.oop.enums.TrainingGoal;
import th.rosenheim.oop.logic.ExerciseCatalog;
import th.rosenheim.oop.logic.PlanGenerator;
import th.rosenheim.oop.model.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Einstiegspunkt der Trainings-App.
 * Die App dient ausschließlich zur Demonstration der Trainingsplangenerierung.
 */
public class App {

    /**
     * Startpunkt des Programms.
     * @param args Kommandozeilenargumente (nicht verwendet)
     */
    public static void main(String[] args) {
        init();
    }

    /**
     * Initialisiert Beispiel-Daten und erzeugt
     * einen Trainingsplan für einen Benutzer.
     */
    public static void init() {

        //Benutzer erstellen (
        User user = new User(
                "Hannes Hantelheber",
                ExperienceLevel.INTERMEDIATE,
                TrainingGoal.MUSCLE_GAIN
        );

        //Alle verfügbaren Übungen aus dem Katalog laden
        List<Exercise> allExercises = ExerciseCatalog.getAllExercises();

        //Trainingsplan generieren
        PlanGenerator generator = new PlanGenerator();

        TrainingPlan plan =
                generator.generateWeeklyPlan(user, allExercises);

        // Trainingstage nach Gesamtdauer sortieren (Comparable-Demo)
        List<TrainingDay> sortedDays =
                new ArrayList<>(plan.getTrainingDays());

        Collections.sort(sortedDays);

        //Trainingsplan ausgeben
        printTrainingPlan(user, plan, sortedDays);
    }

    /**
     * Wandelt das Erfahrungslevel in eine deutsche Bezeichnung um.
     *  @param level Erfahrungslevel
     *  @return deutsche Bezeichnung des Erfahrungslevels
     */
    private static String formatExperienceLevel(ExperienceLevel level) {
        switch (level) {
            case BEGINNER:
                return "Anfänger";
            case INTERMEDIATE:
                return "Fortgeschritten";
            case ADVANCED:
                return "Sehr fortgeschritten";
            case EXPERT:
                return "Experte";
            default:
                return "";
        }
    }

    /**
     * Wandelt das Trainingsziel in eine lesbare deutsche Bezeichnung um.
     * @param goal Trainingsziel
     * @return deutsche Bezeichnung des Trainingsziels
     */
    private static String formatTrainingGoal(TrainingGoal goal) {
        switch (goal) {
            case MUSCLE_GAIN:
                return "Muskelaufbau";
            case FAT_LOSS:
                return "Fettabbau";
            default:
                return "";
        }
    }

    /**
     * Gibt einen übersichtlichen Trainingsplan aus.
     * @param user Benutzer, für den der Trainingsplan erstellt wurde
     * @param plan generierter Trainingsplan
     * @param sortedDays Trainingstage sortiert nach Gesamtdauer
     */
    private static void printTrainingPlan(User user, TrainingPlan plan, List<TrainingDay> sortedDays) {

        System.out.println("==============================================");
        System.out.println("               TRAININGSPLAN");
        System.out.println("==============================================");
        System.out.println("Nutzer          : " + user.getName());
        System.out.println("Erfahrungslevel : " + formatExperienceLevel(plan.getLevel()));
        System.out.println("Trainingsziel   : " + formatTrainingGoal(plan.getTrainingGoal()));
        System.out.println();
        System.out.println("[ⓘ Nutzer-Hinweis: Nach jedem Satz im Kraft-\n" +
                "training sind 2 Minuten Pause eingeplant]");
        System.out.println();

        for (TrainingDay day : plan.getTrainingDays()) {

            System.out.println("==============================================");
            System.out.println(day.getDay() + " - " + day.getTrainingTypeLabel());
            System.out.println("==============================================");

            for (Workout workout : day.getWorkouts()) {

                //Krafttraining
                if (workout instanceof StrengthWorkout) {

                    System.out.printf(
                            "%-25s %-10s %-15s%n",
                            "Übung",
                            "Sätze",
                            "Reps"
                    );
                    System.out.println("----------------------------------------------");

                    for (Exercise exercise : workout.getExercises()) {
                        System.out.printf(
                                "%-25s %-10d %-15d%n",
                                exercise.getName(),
                                exercise.getSets(),
                                exercise.getReps()
                        );
                    }

                    System.out.println("----------------------------------------------");
                    System.out.println("Dauer: "
                            + workout.calculateDuration() + " Minuten");
                    System.out.println();
                }

                //Kardiotraining
                else if (workout instanceof CardioWorkout) {

                    System.out.printf(
                            "%-25s %-15s%n",
                            "Aktivität",
                            "Dauer (in min)"
                    );
                    System.out.println("----------------------------------------------");

                    for (Exercise exercise : workout.getExercises()) {
                        System.out.printf(
                                "%-25s %-15d%n",
                                exercise.getName(),
                                workout.calculateDuration()
                        );
                    }

                    System.out.println("----------------------------------------------");
                    System.out.println();
                }
            }
        }

        //Um die Comparable Funktion darzustellen
        System.out.println("TRAININGSTAGE SORTIERT NACH BELASTUNG (in min)");

        for (TrainingDay day : sortedDays) {
            System.out.println(
                    day.getDay() + " → " + day.getTotalDuration() + " Minuten"
            );
        }
    }
}