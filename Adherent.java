import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Adherent {

    private int id;
    private String nom;
    private Abonnement abonnement; // peut etre Basic, Premium ou Etudiant
    private List<Reservation> reservations;

    public Adherent(int id, String nom, Abonnement abonnement) {
        this.id = id;
        this.nom = nom;
        this.abonnement = abonnement;
        this.reservations = new ArrayList<>();
    }

    public int getId() { return id; }
    public String getNom() { return nom; }
    public Abonnement getAbonnement() { return abonnement; }
    public List<Reservation> getReservations() { return reservations; }

    // cree une reservation et l ajoute a la liste
    public Reservation reserver(Seance s) {
        Reservation r = new Reservation(s);
        reservations.add(r);
        return r;
    }

    // somme des prestations, seulement pour les reservations confirmees
    public double depensesTotales() {
        double total = 0;
        for (Reservation r : reservations) {
            if (r.getStatut() == StatutReservation.CONFIRMEE) {
                total = total + r.coutPrestations();
            }
        }
        return total;
    }

    // retourne les reservations dont la seance est apres maintenant
    public List<Reservation> reservationsFutures() {
        List<Reservation> futures = new ArrayList<>();
        LocalDateTime maintenant = LocalDateTime.now();
        for (Reservation r : reservations) {
            if (r.getStatut() == StatutReservation.CONFIRMEE && r.getSeance().getDateHeure().isAfter(maintenant)) {
                futures.add(r);
            }
        }
        return futures;
    }

    @Override
    public String toString() {
        return "Adherent{id=" + id + ", nom='" + nom + "', abonnement=" + abonnement + ", reservations=" + reservations.size() + "}";
    }
}
