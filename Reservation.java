import java.util.ArrayList;
import java.util.List;

public class Reservation {

    private Seance seance;
    private List<Prestation> prestations; // liste des options choisies
    private StatutReservation statut;

    public Reservation(Seance seance) {
        this.seance = seance;
        this.prestations = new ArrayList<>();
        this.statut = StatutReservation.CONFIRMEE; // confirmee par defaut
    }

    public Seance getSeance() { return seance; }
    public List<Prestation> getPrestations() { return prestations; }
    public StatutReservation getStatut() { return statut; }

    public void ajouterPrestation(Prestation p) {
        prestations.add(p);
    }

    // additionne le prix de chaque prestation
    public double coutPrestations() {
        double total = 0;
        for (Prestation p : prestations) {
            total = total + p.getPrix();
        }
        return total;
    }

    public void annuler() {
        this.statut = StatutReservation.ANNULEE;
    }

    @Override
    public String toString() {
        return "Reservation{seance='" + seance.getNom() + "', statut=" + statut + ", cout=" + coutPrestations() + "}";
    }
}
