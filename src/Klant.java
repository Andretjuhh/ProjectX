import java.util.ArrayList;

public abstract class Klant {
    private static ArrayList<Klant> allKlanten = new ArrayList<Klant>(); // ArrayList om alle klanten in het systeem bij te houden
    private String email;
    private int telefoonnummer;
    private ArrayList<Offerte> offertes;

    // constructor
    public Klant(String email, int telefoonnummer) {
        this.email = email;
        this.telefoonnummer = telefoonnummer;
        this.offertes = new ArrayList<Offerte>();
        allKlanten.add(this);
    }

    // getters
    public String getEmail() {
        return email;
    }

    public int getTelefoonnummer() {
        return telefoonnummer;
    }

    public ArrayList<Offerte> getOffertes() {
        return offertes;
    }

    public static ArrayList<Klant> getAllKlanten() {
        return allKlanten;
    }

    // setters
    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefoonnummer(int telefoonnummer) {
        this.telefoonnummer = telefoonnummer;
    }

    public void setOffertes(ArrayList<Offerte> offertes) {
        this.offertes = offertes;
    }

    // Offerte methodes TBI
    
    // Korting functie
    static public double berekenKorting(int korting) {
        return (double)korting / 100;
    }

    public abstract double berekenKorting();
}
