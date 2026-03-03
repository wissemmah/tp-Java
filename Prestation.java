public class Prestation {

    private String code;    // ex: "SAUNA"
    private String libelle; // ex: "Acces sauna"
    private double prix;    // ex: 5.0

    public Prestation(String code, String libelle, double prix) {
        this.code = code;
        this.libelle = libelle;
        this.prix = prix;
    }

    public String getCode() { return code; }
    public String getLibelle() { return libelle; }
    public double getPrix() { return prix; }

    @Override
    public String toString() {
        return "Prestation{code='" + code + "', libelle='" + libelle + "', prix=" + prix + "}";
    }
}
