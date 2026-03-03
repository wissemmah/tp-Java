import java.util.ArrayList;
import java.util.List;

public class SalleDeSport {

    private List<Adherent> adherents;
    private List<Seance> seances;

    public SalleDeSport() {
        this.adherents = new ArrayList<>();
        this.seances = new ArrayList<>();
    }

    public void ajouterAdherent(Adherent a) { adherents.add(a); }
    public void ajouterSeance(Seance s) { seances.add(s); }

    public List<Seance> seancesDisponibles() {
        return new ArrayList<>(seances);
    }

    // garde uniquement les adherents dont l abonnement permet le sauna
    public List<Adherent> adherentsAvecSauna() {
        List<Adherent> resultat = new ArrayList<>();
        for (Adherent a : adherents) {
            if (a.getAbonnement().permetAccesSauna()) {
                resultat.add(a);
            }
        }
        return resultat;
    }

    // somme des depenses de tous les adherents
    public double chiffreAffairesPrestations() {
        double total = 0;
        for (Adherent a : adherents) {
            total = total + a.depensesTotales();
        }
        return total;
    }

    // cherche un adherent par id, lance une exception si introuvable
    public Adherent trouverAdherent(int id) {
        for (Adherent a : adherents) {
            if (a.getId() == id) {
                return a;
            }
        }
        throw new AdherentIntrouveException(id);
    }

    public List<Adherent> getAdherents() { return adherents; }
    public List<Seance> getSeances() { return seances; }
}
