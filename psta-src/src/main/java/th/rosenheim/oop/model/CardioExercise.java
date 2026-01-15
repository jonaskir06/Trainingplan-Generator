package th.rosenheim.oop.model;

import th.rosenheim.oop.enums.CardioType;
import th.rosenheim.oop.enums.DifficultyLevel;

/**
 * Repräsentiert eine Kardio-Übung.
 */
public class CardioExercise extends Exercise {

    private CardioType cardioType;

    public CardioExercise(String name,
                          CardioType cardioType,
                          DifficultyLevel difficulty) {

        // Sets/Reps spielen bei Cardio keine Rolle → 0
        super(name, null, difficulty, 0, 0, 0);
        this.cardioType = cardioType;
    }

    public CardioType getCardioType() {
        return cardioType;
    }
}
