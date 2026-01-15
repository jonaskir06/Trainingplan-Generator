package th.rosenheim.oop;

import org.junit.jupiter.api.Test;
import th.rosenheim.oop.logic.PlanGenerator;
import th.rosenheim.oop.logic.ExerciseCatalog;
import th.rosenheim.oop.model.*;
import th.rosenheim.oop.enums.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testet, dass der PlanGenerator einen vollständigen
 * Wochen-Trainingsplan erzeugt.
 */
class TrainingPlanGenerationTest {

    @Test
    void testWeeklyPlanHasSevenDays() {

        User user = new User(
                "Andi Arbeit",
                ExperienceLevel.EXPERT,
                TrainingGoal.MUSCLE_GAIN
        );

        //Lädt alle verfügbaren Übungen aus ExerciseCatalog
        List<Exercise> exercises =
                ExerciseCatalog.getAllExercises();

        //Erzeugung des Trainingsplans
        PlanGenerator generator = new PlanGenerator();
        TrainingPlan plan =
                generator.generateWeeklyPlan(user, exercises);

        //Sicherheitscheck, ob der Plan erzeugt wurde
        assertNotNull(plan, "Trainingsplan darf nicht null sein");
        //Sicherheitscheck, dass ein Wochenplan aus 7 Tagen besteht
        assertEquals(7, plan.getTrainingDays().size(),
                "Ein Wochenplan muss genau 7 Tage enthalten");
    }
}
