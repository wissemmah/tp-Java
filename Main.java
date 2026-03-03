import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // --- 1. creation des prestations ---
        Prestation sauna     = new Prestation("SAUNA",     "Acces sauna",        5.00);
        Prestation coach     = new Prestation("COACH",     "Seance coach perso", 25.00);
        Prestation serviette = new Prestation("SERVIETTE", "Location serviette",  2.00);

        System.out.println("=== Prestations ===");
        System.out.println(sauna);
        System.out.println(coach);
        System.out.println(serviette);

        // --- 2. creation des seances ---
        Seance s1 = new Seance(1, "BodyPump", LocalDateTime.now().plusDays(3),  20); // future
        Seance s2 = new Seance(2, "Yoga",     LocalDateTime.now().plusDays(7),  15); // future
        Seance s3 = new Seance(3, "CrossFit", LocalDateTime.now().minusDays(2), 12); // passee

        // --- 3. creation des abonnements et des adherents ---
        AbonnementBasic    abBasic   = new AbonnementBasic("BASIC-001", LocalDate.now(), 6, 29.90);
        AbonnementPremium  abPremium = new AbonnementPremium("PREM-001", LocalDate.now(), 12, 49.90, 4);
        AbonnementEtudiant abEtu     = new AbonnementEtudiant("ETU-001", LocalDate.now(), 6, 29.90, 20);

        Adherent alice = new Adherent(1, "Alice Martin", abBasic);
        Adherent bob   = new Adherent(2, "Bob Dupont",   abPremium);
        Adherent clara = new Adherent(3, "Clara Lebrun", abEtu);

        SalleDeSport salle = new SalleDeSport();
        salle.ajouterAdherent(alice);
        salle.ajouterAdherent(bob);
        salle.ajouterAdherent(clara);
        salle.ajouterSeance(s1);
        salle.ajouterSeance(s2);
        salle.ajouterSeance(s3);

        // --- 4. reservations ---
        Reservation r1 = alice.reserver(s1);
        r1.ajouterPrestation(serviette);

        Reservation r2 = alice.reserver(s2);
        r2.ajouterPrestation(sauna);
        r2.ajouterPrestation(coach);

        Reservation r3 = alice.reserver(s3); // seance passee
        r3.ajouterPrestation(serviette);

        Reservation r4 = bob.reserver(s1);
        r4.ajouterPrestation(coach);
        r4.annuler(); // annulee, ne compte pas dans les depenses

        Reservation r5 = bob.reserver(s2);
        r5.ajouterPrestation(sauna);

        // --- 5. affichages ---

        System.out.println("");
        System.out.println("=== Adherents et abonnements ===");
        for (Adherent a : salle.getAdherents()) {
            System.out.println(a);
        }

        System.out.println("");
        System.out.println("=== Cout total des abonnements ===");
        System.out.println("Alice  : " + alice.getAbonnement().coutTotal() + " euros");
        System.out.println("Bob    : " + bob.getAbonnement().coutTotal() + " euros");
        System.out.println("Clara  : " + clara.getAbonnement().coutTotal() + " euros (apres reduction 20%)");

        System.out.println("");
        System.out.println("=== Reservations futures d Alice ===");
        List<Reservation> futures = alice.reservationsFutures();
        if (futures.isEmpty()) {
            System.out.println("aucune");
        } else {
            for (Reservation r : futures) {
                System.out.println(r);
            }
        }

        System.out.println("");
        System.out.println("=== Adherents avec acces sauna ===");
        for (Adherent a : salle.adherentsAvecSauna()) {
            System.out.println(a.getNom());
        }

        System.out.println("");
        System.out.println("=== Depenses prestations ===");
        System.out.println("Alice : " + alice.depensesTotales() + " euros");
        System.out.println("Bob   : " + bob.depensesTotales() + " euros");
        System.out.println("Clara : " + clara.depensesTotales() + " euros");

        System.out.println("");
        System.out.println("=== Chiffre d affaires : " + salle.chiffreAffairesPrestations() + " euros ===");

        // test de l exception
        System.out.println("");
        System.out.println("=== Test exception ===");
        try {
            salle.trouverAdherent(99);
        } catch (AdherentIntrouveException e) {
            System.out.println("Erreur : " + e.getMessage());
        }

        Adherent trouve = salle.trouverAdherent(2);
        System.out.println("Adherent trouve : " + trouve.getNom());
    }
}
