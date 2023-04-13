public class Particulier extends Klant {
    private String voornaam;
    private String achternaam;
    static private int korting = 5;

    // constructor
    public Particulier(String email, int telefoonnummer, String voornaam, String achternaam) {
        super(email, telefoonnummer);
        this.voornaam = voornaam;
        this.achternaam = achternaam;
    }

    // getters
    public String getVoornaam() {
        return voornaam;
    }

    public String getAchternaam() {
        return achternaam;
    }

    // settters
    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    // Korting functie
    @Override
    public double berekenKorting() {
        return Klant.berekenKorting(korting);
    }
}
