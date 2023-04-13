import java.util.ArrayList;

public class Scheepsbouwer {
    private static ArrayList<Scheepsbouwer> allScheepsbouwers = new ArrayList<Scheepsbouwer>(); //Arraylist om alle scheepsbouwers bekend in het systeem bij te houden
    private String voornaam;
    private String achternaam;
    private String email;
    private ArrayList<Offerte> offertes;

    public Scheepsbouwer(String voornaam, String achternaam, String email) {
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.email = email;
        offertes = new ArrayList<Offerte>();
        allScheepsbouwers.add(this);
    }

    public static ArrayList<Scheepsbouwer> getAllScheepsbouwers() {
        return allScheepsbouwers;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public String getEmail() {
        return email;
    }

    public ArrayList<Offerte> getOffertes() {
        return offertes;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setOffertes(ArrayList<Offerte> offertes) {
        this.offertes = offertes;
    }


}
