import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private String naam;
    private double prijs;
    private double korting;

    public Main(String naam, double prijs, double korting) {
        this.naam = naam;
        this.prijs = prijs;
        this.korting = korting;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public double getPrijs() {
        return prijs;
    }

    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }

    public double getKorting() {
        return korting;
    }

    public void setKorting(double korting) {
        this.korting = korting;
    }

    public static void main(String[] args) {
        List<Main> onderdelen = new ArrayList<>();
        List<Main> gekozenOnderdelen = new ArrayList<>();
        double totaalPrijs = 0.0;
        Scanner scanner = new Scanner(System.in);

        // Voeg onderdelen toe aan de lijst
        onderdelen.add(new Main("Motor 1", 30000.0, 0.0));
        onderdelen.add(new Main("Motor 2", 25000.0, 0.0));
        onderdelen.add(new Main("BioMotor", 45000.0, 0.20));

        System.out.println("Kies een onderdeel:");
        System.out.println(onderdelen.get(0).naam + " Prijs: " + onderdelen.get(0).prijs + " milleukorting: " + onderdelen.get(0).korting* 100 + "%");
        System.out.println(onderdelen.get(1).naam + " Prijs: " + onderdelen.get(1).prijs + " milleukorting: " + onderdelen.get(1).korting* 100 + "%");
        System.out.println(onderdelen.get(2).naam + " Prijs: " + onderdelen.get(2).prijs + " milleukorting: " + onderdelen.get(2).korting* 100 + "%");

        int keuze = scanner.nextInt();
        Main gekozenOnderdeel = null;

        // Zoek het gekozen onderdeel in de lijst en voeg het toe aan de gekozenOnderdelen lijst
        for (Main onderdeel : onderdelen) {
            if (onderdeel.getNaam().equals("Motor 1") && keuze == 1) {
                gekozenOnderdeel = onderdeel;
                break;
            } else if (onderdeel.getNaam().equals("Motor 2") && keuze == 2) {
                gekozenOnderdeel = onderdeel;
                break;
            } else if (onderdeel.getNaam().equals("BioMotor") && keuze == 3) {
                gekozenOnderdeel = onderdeel;
                break;
            }
        }

        // Bereken de totaalprijs van het gekozen onderdeel en voeg het toe aan de gekozenOnderdelen lijst
        if (gekozenOnderdeel != null) {
            double prijsMetKorting = gekozenOnderdeel.getPrijs() * (1 - gekozenOnderdeel.getKorting());
            gekozenOnderdeel.setPrijs(prijsMetKorting);
            gekozenOnderdelen.add(gekozenOnderdeel);
            totaalPrijs += prijsMetKorting;
            System.out.println("Gekozen onderdeel: " + gekozenOnderdeel.getNaam());
            System.out.println("Totaalprijs: " + totaalPrijs);
        } else {
            System.out.println("Ongeldige keuze.");
        }
    }
}