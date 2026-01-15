package th.rosenheim.oop.interfaces;

/**
 * Schnittstelle für jede planbare Trainingseinheit.
 * Jede Einheit besitzt eine Gesamtdauer
 */
public interface Schedulable {

    /**
     * Gibt die Gesamtdauer der Trainingseinheit zurück
     * @return Dauer in Minuten
     */
    int getTotalDuration();
}
