public class Onderdeel {
    private  String naam;
    private double prijs;
    private int millieuvriendelijk;
    private String categorie;

    Onderdeel(String naam, double prijs, int millieuvriendelijk, String categorie) {
        this.naam = naam;
        this.prijs = prijs;
        this.millieuvriendelijk = millieuvriendelijk;
        this.categorie = categorie;
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

    public  String getNaam() {
        return naam;
    }

    public double getPrijs() {
        return prijs;
    }

    public int getMillieuvriendelijk() {
        return millieuvriendelijk;
    }

    public String getCategorie(){
        return categorie;
    }

    public void setCategorie(String categorie){
        this.categorie = categorie;
    }
}
