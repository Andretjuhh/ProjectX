import java.util.Scanner;

public class UI {
    static private Scanner input = new Scanner(System.in);

    // Methode om het programma te starten
    static private void start() {
        clearConsole();
        System.out.println("Welkom bij ShipFlex, om van ons systeem gebruik te maken vragen wij u in te loggen.");
        System.out.println("Nog geen klant? Maak dan een account aan.");
        System.out.println("1: Inloggen");
        System.out.println("2: Aanmelden");
        
        int keuze = inputInt();
        
        switch(keuze) {
            case 1: clearConsole(); inloggen();
            break;

            case 2: clearConsole(); aanmelden();
            break;

            default: buitenBereik(); start();
        }
    }

    static private void inloggen() {
        System.out.println("TBI inlog platform");
        System.out.println("1 voor scheepsbouwer, 2 voor klant");
        int keuze = inputInt();
        if(keuze == 1) {clearConsole(); scheepsbouwerUI();}
        else if(keuze == 2) {clearConsole(); klantUI();}
        else {buitenBereik(); inloggen();}
    }

    static private void aanmelden() {
        System.out.println("TBI: aanmeld platform");
    }

    // Methode voor een scheepsbouwer om een nieuwe offerte op te stellen.
    static private void scheepsbouwerNieuweOfferte() {
        System.out.println("TBI");
    }

    // Methode voor een scheepsbouwer om een lijst van al zijn offertes te zien.
    static private void scheepsbouwerOfferteLijst() {
        System.out.println("TBI");
    }

    // Methode voor een scheepsbouwer om een nieuw type klant toe te voegen.
    static private void scheepsbouwerKlantType() {
        System.out.println("TBI");
    }

    // Methode voor een scheepsbouwer om een nieuwe optie toe te voegen.
    static private void scheepsbouwerOptie() {
        System.out.println("TBI");
    }

    // Methode voor een klant om een nieuw project op te stellen.
    static private void klantNieuwProject() {
        System.out.println("TBI");
    }

    // Methode voor een klant om al zijn offertes in te zien.
    static private void klantOfferteLijst() {
        System.out.println("TBI");
    }

    // Methode om de console leeg te maken.
    static private void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /* Methode om te kijken of een input een int is.
     * Zo ja return de ingevulde int, zo niet return -1 als standaard.
     */
    static private int inputInt() {
        if(input.hasNextInt()) {
            return input.nextInt();
        } return -1;
    }

    // Methode om door te communiceren dat een gevraagd getal buiten het gevraagde bereik zit
    static private void buitenBereik() {
        clearConsole();
        System.out.println("Uw keuze zat buiten het gevraagde bereik, vul een juiste keuze in.\n");
    }

    // Methode om het programma veilig af te sluiten met een standaard procedure
    static private void shutdown() {
        System.out.println("Het programma is afgesloten");
        System.out.println("Bedankt voor het gebruik maken van ShipFlex!");
        input.close();
        System.exit(1);
    }
    
    // Keuze UI voor klanten.
    static private void klantUI() {
        System.out.println("U bent ingelogd als klant, kies uit de volgende opties:");
        System.out.println("1: Nieuw project aanmaken");
        System.out.println("2: Mijn offertes");
        System.out.println("0: Programma afsluiten");

        int keuze = inputInt();
        switch(keuze) {
            case 1: clearConsole(); klantNieuwProject();
            break;

            case 2: clearConsole(); klantOfferteLijst();
            break;

            case 0: clearConsole(); shutdown();
            break;

            default: buitenBereik(); klantUI();
        }
    }

    // Keuze menu voor scheepsbouwers.
    static private void scheepsbouwerUI() {
        System.out.println("U bent ingelogd als scheepsbouwer, kies uit de volgende opties:");
        System.out.println("1: Nieuwe offerte opstellen");
        System.out.println("2: Lijst van offertes tonen");
        System.out.println("3: Nieuwe klanttype toevoegen");
        System.out.println("4: Nieuwe optie toevoegen ");
        System.out.println("0: Programma afsluiten");

        int keuze = inputInt();
        switch(keuze) {
            case 1: clearConsole(); scheepsbouwerNieuweOfferte();
            break;

            case 2: clearConsole(); scheepsbouwerOfferteLijst();
            break;

            case 3: clearConsole(); scheepsbouwerKlantType();
            break;

            case 4: clearConsole(); scheepsbouwerOptie();
            break;

            case 0: clearConsole(); shutdown();
            break;

            default: buitenBereik(); scheepsbouwerUI();
        }
    }


    public static void main(String[] args) {
        start();
    }
}
