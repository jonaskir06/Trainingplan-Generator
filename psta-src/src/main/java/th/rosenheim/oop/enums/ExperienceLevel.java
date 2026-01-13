package th.rosenheim.oop.enums;

/**
 * Beschreibt das Trainings- bzw. Erfahrungsniveau eines Users.
 * Die Erfahrungsstufe beeinflusst unter anderem die Trainingsintensität,
 * die Anzahl der Trainingstage sowie die Komplexität der Übungen.
 */
public enum ExperienceLevel {

    /** Kompletter Neueinsteiger ohne Trainingserfahrung */
    BEGINNER,

    /** Fortgeschrittener Nutzer mit erster Trainingserfahrung */
    INTERMEDIATE,

    /** Sehr erfahrener Nutzer mit regelmäßigem Training */
    ADVANCED,

    /** Profi bzw. Experte mit hoher Trainingsbelastung */
    EXPERT
}

