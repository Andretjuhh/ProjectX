public class Onderdeel {
    private  String naam;
    private double prijs;
    private double millieuvriendelijk;

    Onderdeel(String naam, double prijs, double millieuvriendelijk) {
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

    public void setMillieuvriendelijk(double millieuvriendelijk) {
        this.millieuvriendelijk = millieuvriendelijk;
    }

    public  String getNaam() {
        return naam;
    }

    public double getPrijs() {
        return prijs;
    }

    public double getMillieuvriendelijk() {
        return millieuvriendelijk;
    }
}
