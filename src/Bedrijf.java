public class Bedrijf extends Klant {
    private String bedrijfNaam;
    static private int korting = 21;

    public Bedrijf(String email, int telefoonnummer, String bedrijfNaam) {
        super(email, telefoonnummer);
        this.bedrijfNaam = bedrijfNaam;
    }

    // getter
    public String getBedrijfNaam() {
        return bedrijfNaam;
    }
    
    // setter
    public void setBedrijfNaam(String bedrijfNaam) {
        this.bedrijfNaam = bedrijfNaam;
    }

    // Korting functie
    public double berekenKorting() {
        return Klant.berekenKorting(korting);
    }
}
