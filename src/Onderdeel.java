import java.util.ArrayList;

public class Onderdeel {
    private String naam;
    private double prijs;
    private int millieuvriendelijk;
    private String categorie;
    private static ArrayList<Onderdeel> allOnderdelen = new ArrayList<Onderdeel>();
    private static String[] categorieën = new String[]{"navigatie", "motor", "roer", "tank", "overig"};

    Onderdeel(String naam, double prijs, int millieuvriendelijk, String categorie) {
        this.naam = naam;
        this.prijs = prijs;
        this.millieuvriendelijk = millieuvriendelijk;
        this.categorie = categorie;

        allOnderdelen.add(this);
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setMillieuvriendelijk(int millieuvriendelijk) {
        this.millieuvriendelijk = millieuvriendelijk;
    }

    public static ArrayList<Onderdeel> getAllOnderdelen() {
        return allOnderdelen;
    }

    public static ArrayList<Onderdeel> getAllOnderdelenCategorie(String categorie) {
        ArrayList<Onderdeel> onderdelenCat = new ArrayList<Onderdeel>();
        for(Onderdeel onderdeel : allOnderdelen) {
            if(onderdeel.categorie.equals(categorie)) {
                onderdelenCat.add(onderdeel);
            }
        }
        return onderdelenCat;
    }

    public  String getNaam() {
        return naam;
    }

    public String getCategorie() {
        return categorie;
    }

    public double getPrijs() {
        return prijs;
    }

    public int getMillieuvriendelijk() {
        return millieuvriendelijk;
    }

    public static String[] getCategorieën() {
        return categorieën;
    }

    public String printOnderdeel() {
        String prijsInDouble = String.format("%.2f", getPrijs());
        String s = "Onderdeel " + getNaam() + " kost: " + prijsInDouble + " euro en hierop zit " + getMillieuvriendelijk()
                + "% korting.\n";
        
        return s;
    }

    public boolean isMilieuVriendelijk() {
        return millieuvriendelijk != 0;
    }

    public double berekenPrijs() {
        if(isMilieuVriendelijk()) {
            return (prijs * (1 - ((double) millieuvriendelijk / 100)));
        }
        return prijs;
    }
}