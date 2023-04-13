public class Offerte {
    private static String[] statussen = new String[]{"Aangevraagd","Compleet"};

    private int offerteNummer;
    private String datum;
    private Klant klant;
    private Scheepsbouwer scheepsbouwer;
    private Boot boot;
    private String status;

    public Offerte(String datum, Klant klant, Scheepsbouwer scheepsbouwer, Boot boot) {
        this.datum = datum;
        this.klant = klant;
        this.scheepsbouwer = scheepsbouwer;
        this.boot = boot;
        status = statussen[0];
    }

    public int getOfferteNummer() {
        return offerteNummer;
    }
    public String getDatum() {
        return datum;
    }
    public Klant getKlant() {
        return klant;
    }
    public Scheepsbouwer getScheepsbouwer() {
        return scheepsbouwer;
    }
    public Boot getBoot() {
        return boot;
    }
    public String getStatus() {
        return status;
    }

    public void setOfferteNummer(int offerteNummer) {
        this.offerteNummer = offerteNummer;
    }
    public void setDatum(String datum) {
        this.datum = datum;
    }
    public void setKlant(Klant klant) {
        this.klant = klant;
    }
    public void setScheepsbouwer(Scheepsbouwer scheepsbouwer) {
        this.scheepsbouwer = scheepsbouwer;
    }
    public void setBoot(Boot boot) {
        this.boot = boot;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String printHead() {
        return "Offerte " + offerteNummer + "Datum: " + datum;
    }

    public String print() {
        String result = printHead() + "\n";
        result += "Status: " + status + "\n";
        result += "Klant: " + klant.getEmail() + "\n";
        result += "Scheepsbouwer: " + scheepsbouwer.getVoornaam() + " " + scheepsbouwer.getAchternaam() + "\n";
        result += boot.print();

        return result;
    }

    public double totaalPrijs() {
        return klant.berekenKorting() * boot.berekenTotaalPrijs();
    }
}
