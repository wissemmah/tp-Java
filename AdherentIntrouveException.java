// exception lancee quand on cherche un adherent avec un id qui n existe pas
public class AdherentIntrouveException extends RuntimeException {

    public AdherentIntrouveException(int id) {
        super("Aucun adherent trouve avec l id : " + id);
    }
}
