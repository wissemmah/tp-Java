import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Seance {

    private int id;
    private String nom;
    private LocalDateTime dateHeure;
    private int capaciteMax;

    public Seance(int id, String nom, LocalDateTime dateHeure, int capaciteMax) {
        this.id = id;
        this.nom = nom;
        this.dateHeure = dateHeure;
        this.capaciteMax = capaciteMax;
    }

    public int getId() { return id; }
    public String getNom() { return nom; }
    public LocalDateTime getDateHeure() { return dateHeure; }
    public int getCapaciteMax() { return capaciteMax; }

    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return "Seance{id=" + id + ", nom='" + nom + "', date=" + dateHeure.format(format) + ", capaciteMax=" + capaciteMax + "}";
    }
}
