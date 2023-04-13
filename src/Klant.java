import java.util.ArrayList;

public abstract class Klant {
    private int ID;
    protected String email;
    protected int telefoonnummer;
    // protected ArrayList<Offerte> offertes;        //lijst van offertes voor de klant

    // constructor
    public Klant(String email, int telefoonnummer) {
        ID = (int)(Math.random() * 100000);
        this.email = email;
        this.telefoonnummer = telefoonnummer;
        // this.offertes = new ArrayList<Offerte>();
    }

    // getters
    public int getID() {
        return ID;
    }

    public String getEmail() {
        return email;
    }

    public int getTelefoonnummer() {
        return telefoonnummer;
    }

    // public ArrayList<Offerte> getOffertes() {
    //     return offertes;
    // }

    // setters
    public void setID(int ID) {
        this.ID = ID;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefoonnummer(int telefoonnummer) {
        this.telefoonnummer = telefoonnummer;
    }

    // public void setOffertes(ArrayList<Offerte> offertes) {
    //     this.offertes = offertes;
    // }

    // Offerte methodes TBI
    
    // Korting functie
    static public double berekenKorting(int korting) {
        return (double)korting / 100;
    }
}
