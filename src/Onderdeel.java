public class Onderdeel {
    private String naam;
    private double prijs;
    private int millieuvriendelijk;

    Onderdeel(String naam, double prijs, int millieuvriendelijk) {
        this.naam = naam;
        this.prijs = prijs;
        this.millieuvriendelijk = millieuvriendelijk;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }

    public void setMillieuvriendelijk(int millieuvriendelijk) {
        this.millieuvriendelijk = millieuvriendelijk;
    }

    public String getNaam() {
        return naam;
    }

    public double getPrijs() {
        return prijs;
    }

    public int getMillieuvriendelijk() {
        return millieuvriendelijk;
    }

    public String printOnderdeel() {
        String prijsInDouble = String.format("%.2f", getPrijs());
        String s = "Onderdeel " + getNaam() + " kost €" + prijsInDouble + " en hierop zit " + getMillieuvriendelijk()
                + "% korting.\n";
        
        return s;
    }
}