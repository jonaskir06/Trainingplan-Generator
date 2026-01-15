package th.rosenheim.oop.logic;

import th.rosenheim.oop.enums.*;
import th.rosenheim.oop.model.*;

import java.util.*;
import java.util.stream.Collectors;


/**
 * Der PlanGenerator erzeugt Trainingspläne basierend auf Trainingsziel
 * und Erfahrungslevel.
 * Pro Trainingstag werden maximal zwei Muskelgruppen trainiert.
 */
public class PlanGenerator {

    //Wochentage
    private static final List<String> WEEK_DAYS = Arrays.asList(
            "Montag",
            "Dienstag",
            "Mittwoch",
            "Donnerstag",
            "Freitag",
            "Samstag",
            "Sonntag"
    );

    //Feste Rest Days
    private static final Set<String> REST_DAYS =
            new HashSet<>(Arrays.asList("Donnerstag", "Sonntag"));


    /**
     * Erzeugt einen Wochen-Trainingsplan für einen Benutzer.
     * Die Anzahl der Kraft- und Kardiotage hängt vom Trainingsziel ab.
     * @param user Benutzer, für den der Plan erzeugt wird
     * @param allExercises alle verfügbaren Übungen
     * @return Trainingsplan für eine Woche
     */
    public TrainingPlan generateWeeklyPlan(User user,
                                           List<Exercise> allExercises) {

        List<TrainingDay> generatedTrainingDays = new ArrayList<>();

        //Anzahl der Trainingsarten bestimmen
        int strengthDays;
        int cardioDays;

        if (user.getTrainingGoal() == TrainingGoal.MUSCLE_GAIN) {
            strengthDays = 4;
            cardioDays = 1;
        } else { // FAT_LOSS
            strengthDays = 2;
            cardioDays = 3;
        }

        //Maximal zwei Muskelgruppen pro Trainingstag
        List<List<MuscleGroup>> muscleGroupSplits = new ArrayList<>(Arrays.asList(
                Collections.singletonList(MuscleGroup.CHEST),
                Collections.singletonList(MuscleGroup.BACK),
                Collections.singletonList(MuscleGroup.LEGS),
                Collections.singletonList(MuscleGroup.SHOULDERS),
                Arrays.asList(MuscleGroup.ARMS, MuscleGroup.CORE)
        ));

        //Durchmischen für Abwechslung im Wochenplan
        Collections.shuffle(muscleGroupSplits);

        //Krafttrainingstage erzeugen
        for (int i = 0; i < strengthDays; i++) {

            List<MuscleGroup> trainedGroups = muscleGroupSplits.get(i);

            //Passende Übungen für die Muskelgruppen auswählen
            List<Exercise> selectedExercises =
                    selectExercisesForMuscleGroups(
                            allExercises,
                            trainedGroups,
                            user.getExperienceLevel()
                    );

            StrengthWorkout strengthWorkout =
                    new StrengthWorkout(selectedExercises);

            TrainingDay day = new TrainingDay("Krafttag " + (i + 1));
            day.addWorkout(strengthWorkout);

            generatedTrainingDays.add(day);
        }

        //Kardiotage erzeugen
        for (int i = 0; i < cardioDays; i++) {

            List<CardioExercise> cardioExercises = CardioCatalog.getAllCardioExercises();
            //Trainingstage mischen (Kraft und Cardio)
            Collections.shuffle(generatedTrainingDays);

            //Woche mit Rest Days erzeugen
            List<TrainingDay> week = new ArrayList<>();

            CardioExercise cardioExercise = cardioExercises.get(0);

            CardioWorkout cardioWorkout =
                    createCardioWorkout(cardioExercise, user.getExperienceLevel());

            TrainingDay day = new TrainingDay("Cardio " + (i + 1));
            day.addWorkout(cardioWorkout);

            generatedTrainingDays.add(day);
        }

        //Woche mit Rest Days erzeugen
        List<TrainingDay> week = new ArrayList<>();
        int trainingIndex = 0;

        for (String weekday : WEEK_DAYS) {

            if (REST_DAYS.contains(weekday)) {
                week.add(new TrainingDay(weekday));
                continue;
            }

            if (trainingIndex < generatedTrainingDays.size()) {

                TrainingDay trainingDay = generatedTrainingDays.get(trainingIndex);
                TrainingDay dayWithWeekName = new TrainingDay(weekday);

                for (Workout workout : trainingDay.getWorkouts()) {
                    dayWithWeekName.addWorkout(workout);
                }

                week.add(dayWithWeekName);
                trainingIndex++;

            } else {
                week.add(new TrainingDay(weekday));
            }
        }

        //Trainingsplan mit allen erzeugten Trainingstagen zurückgeben
        return new TrainingPlan(
                user.getExperienceLevel(),
                user.getTrainingGoal(),
                week
        );
    }

    /**
     * Wählt passende Übungen für die angegebenen Muskelgruppen aus.
     * Dabei werden sowohl Erfahrungslevel als auch Schwierigkeitsgrad berücksichtigt.
     * @param allExercises alle verfügbaren Übungen
     * @param muscleGroups Muskelgruppen des Trainingstags
     * @param level Erfahrungslevel des Benutzers
     * @return Liste der ausgewählten Übungen
     */
    private List<Exercise> selectExercisesForMuscleGroups(
            List<Exercise> allExercises,
            List<MuscleGroup> muscleGroups,
            ExperienceLevel level) {

        List<Exercise> selectedExercises = new ArrayList<>();

        // Maximale Anzahl an Übungen pro Muskelgruppe
        int maxExercisesPerGroup = getMaxExercisesPerMuscleGroup(level);

        for (MuscleGroup group : muscleGroups) {

            // Filtert Übungen nach Muskelgruppe und erlaubter Schwierigkeit
            List<Exercise> filteredExercises = allExercises.stream()
                    .filter(e -> e.getMuscleGroup() == group)
                    .filter(e -> isAllowedDifficulty(level, e.getDifficulty()))
                    .limit(maxExercisesPerGroup)
                    .collect(Collectors.toList());

            selectedExercises.addAll(filteredExercises);
        }

        return selectedExercises;
    }

    /**
     * Legt fest, wie viele Übungen pro Muskelgruppe
     * abhängig vom Erfahrungslevel erlaubt sind.
     * @param level Erfahrungslevel
     * @return maximale Anzahl an Übungen pro Muskelgruppe
     */
    private int getMaxExercisesPerMuscleGroup(ExperienceLevel level) {

        switch (level) {
            case BEGINNER:
                return 2;
            case INTERMEDIATE:
                return 3;
            case ADVANCED:
                return 4;
            case EXPERT:
                return 5;
            default:
                return 2;
        }
    }

    /**
     * Erstellt ein Cardio-Workout mit genau einer Übung.
     * @param cardioExercise Cardio-Übung (z. B. Joggen)
     * @param level Erfahrungslevel des Benutzers
     * @return CardioWorkout
     */
    public CardioWorkout createCardioWorkout(Exercise cardioExercise,
                                             ExperienceLevel level) {

        return new CardioWorkout(
                // Erzeugt eine Liste mit genau einem Element
                // (ein Cardio-Workout besteht aus genau einer Übung)
                Collections.singletonList(cardioExercise),
                // Übergabe des Erfahrungslevels
                level
        );
    }

    /**
     * Prüft, ob eine Übung für das gegebene Erfahrungslevel erlaubt ist.
     * @param level Erfahrungslevel
     * @param difficulty Schwierigkeitsgrad der Übung
     * @return true, wenn erlaubt
     */
    private boolean isAllowedDifficulty(ExperienceLevel level,
                                        DifficultyLevel difficulty) {

        switch (level) {
            case BEGINNER:
                return difficulty == DifficultyLevel.EASY;

            case INTERMEDIATE:
            case ADVANCED:
                return difficulty == DifficultyLevel.EASY
                        || difficulty == DifficultyLevel.MEDIUM;

            case EXPERT:
                return true; // alle Schwierigkeitsgrade erlaubt

            default:
                return false;
        }
    }
}
