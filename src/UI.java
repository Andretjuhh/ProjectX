import java.util.Scanner;

public class UI {
    static private Scanner input = new Scanner(System.in);
    static private Scheepsbouwer actieveScheepsbouwer;
    static private Klant actieveKlant;

    // Methode om het programma te starten
    static private void startUp() {
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

            default: buitenBereik(); startUp();
        }
    }

    static private void inloggen() {
        System.out.println("Als u al een account heeft voer uw email in om in te loggen");
        System.out.print("email: ");
        String emailInlog = input.next();

        // Check door alle scheepsbouwers of de email bekend is.
        for(Scheepsbouwer inlog : Scheepsbouwer.getAllScheepsbouwers()) {
            if(inlog.getEmail().equals(emailInlog)) {
                actieveScheepsbouwer = inlog;
                clearConsole();
                scheepsbouwerUI();
            }
        }

        // Check door alle klanten of de email bekend is.
        for(Klant inlog : Klant.getAllKlanten()) {
            if(inlog.getEmail().equals(emailInlog)) {
                actieveKlant = inlog;
                clearConsole();
                klantUI();
            }
        }

        clearConsole();
        System.out.println("Het emailadres is niet gevonden in ons systeem. Probeer opnieuw");
        inloggen();

    }

    static private void aanmelden() {
        System.out.println("Leuk dat u gebruik gaat maken van ShipFlex!");
        System.out.println("Bent u een scheepsbouwer toets 1, voor klanten toets 2.");
        int keuze = inputInt();
        if(keuze == 1) {
            clearConsole();
            nieuwScheepsbouwer();
        } else if(keuze == 2) {
            clearConsole();
            nieuwKlant();
        } else {
            buitenBereik();
            aanmelden();
        }
    }

    static private void nieuwScheepsbouwer() {
        System.out.println("Voer de onderstaande informatie in om een nieuw account aan te maken:");
        System.out.print("Voornaam: ");
        String voornaam = input.next();
        System.out.print("Achternaam: ");
        String achternaam = input.next();
        System.out.print("E-mail: ");
        String email = input.next();
        
        Scheepsbouwer nieuw = new Scheepsbouwer(voornaam, achternaam, email);
        actieveScheepsbouwer = nieuw;
        
        clearConsole();
        scheepsbouwerUI();
    }

    static private void nieuwKlant() {
        System.out.println("Voer de onderstaande informatie in om een nieuw account te maken");
        System.out.print("email: ");
        String email = input.next();
        System.out.print("telefoonnummer: ");
        int telefoonnummer = inputInt();

        System.out.println("\nVoor particuliere klanten toets 1, Bent u een bedrijf toets 2, Voor overheid toets 3");
        int keuze = inputInt();
        switch(keuze) {
            case 1 : nieuwParticulier(email, telefoonnummer);
            break;
            
            case 2 : nieuwBedrijf(email, telefoonnummer);
            break;

            case 3 : nieuwOverheid(email, telefoonnummer);
            break;
            
            default : buitenBereik(); nieuwKlant();
        }
    }

    static private void nieuwParticulier(String email, int telefoonnummer) {
        System.out.println("Voer verder de onderstaande informatie nog in om uw account compleet te maken");
        System.out.print("Voornaam: ");
        String voornaam = input.next();
        System.out.print("Achternaam: ");
        String achternaam = input.next();

        Particulier nieuw = new Particulier(email, telefoonnummer, voornaam, achternaam);
        actieveKlant = nieuw;

        clearConsole();
        klantUI();
    }

    static private void nieuwBedrijf(String email, int telefoonnummer) {
        System.out.println("Voer verder de onderstaande informatie nog in om uw account compleet te maken");
        System.out.print("Bedrijfsnaam: ");
        String bedrijfsNaam = input.next();

        Bedrijf nieuw = new Bedrijf(email, telefoonnummer, bedrijfsNaam);
        actieveKlant = nieuw;

        clearConsole();
        klantUI();
    }

    static private void nieuwOverheid(String email, int telefoonnummer) {
        System.out.println("Voer verder de onderstaande informatie nog in om uw account compleet te maken");
        System.out.print("Instantie: ");
        String instantie = input.next();

        Overheid nieuw = new Overheid(email, telefoonnummer, instantie);
        actieveKlant = nieuw;

        clearConsole();
        klantUI();
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
        System.out.println("Uw account heeft de volgende offertes:");
        
        for(Offerte offerte : actieveKlant.getOffertes()) {
            System.out.println(offerte);
        }

        System.out.println("Om een offerte te openen kunt u het offertenummer nu intoetsen:");
        System.out.print("Offertenummer: ");
        int offerteNummer = inputInt();

        for(Offerte offerte : actieveKlant.getOffertes()) {
            // if(offerte.getOfferteNummer)
        }
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
        System.out.println("U bent ingelogd als " + actieveKlant.getEmail() + ", kies uit de volgende opties:");
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
        System.out.println("U bent ingelogd als " + actieveScheepsbouwer.getEmail() + ", kies uit de volgende opties:");
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
        Scheepsbouwer nieuwBouwer = new Scheepsbouwer("Reno", "Welleman", "rwell@mail");
        Klant nieuwKlant = new Particulier("rwelleman@mail", 061120, "Reno", "Welleman");
        startUp();
    }
}
