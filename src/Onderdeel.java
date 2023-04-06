public class Onderdeel {
    private String naam;
    private double prijs;
    private boolean millieuvriendelijk;

    Onderdeel(String naam, double prijs, boolean millieuvriendelijk) {
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

    public void setMillieuvriendelijk(boolean millieuvriendelijk) {
        this.millieuvriendelijk = millieuvriendelijk;
    }

    public String getNaam() {
        return naam;
    }

    public double getPrijs() {
        return prijs;
    }

    public boolean getMillieuvriendelijk() {
        return millieuvriendelijk;
    }
}
