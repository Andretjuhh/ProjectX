public class Overheid extends Klant {
    private String instantie;
    static private int korting = 12;

    public Overheid(String email, int telefoonnummer, String instantie) {
        super(email, telefoonnummer);
        this.instantie = instantie;
    }

    // getter
    public String getInstantie() {
        return instantie;
    }

    // setter
    public void setInstantie(String instantie) {
        this.instantie = instantie;
    }

    // Korting functie
    @Override
    public double berekenKorting() {
        return Klant.berekenKorting(korting);
    }
}
