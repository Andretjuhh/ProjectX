import java.util.ArrayList;
import java.util.Scanner;

public class UI {
    static private Scanner input = new Scanner(System.in);
    static private Scheepsbouwer actieveScheepsbouwer;
    static private Klant actieveKlant;

    // Methode om het programma te starten
    static public void startUp() {
        loadDB();
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
            clearConsole();
            scheepsbouwerUI();
        } else if(keuze == 2) {
            clearConsole();
            nieuwKlant();
            clearConsole();
            klantUI();
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
        DBConnection.scheepsbouwerToevoegen();
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
        DBConnection.particulierToevoegen(nieuw);
    }

    static private void nieuwBedrijf(String email, int telefoonnummer) {
        System.out.println("Voer verder de onderstaande informatie nog in om uw account compleet te maken");
        System.out.print("Bedrijfsnaam: ");
        String bedrijfsNaam = input.next();

        Bedrijf nieuw = new Bedrijf(email, telefoonnummer, bedrijfsNaam);
        actieveKlant = nieuw;
        DBConnection.getBedrijven();
    }

    static private void nieuwOverheid(String email, int telefoonnummer) {
        System.out.println("Voer verder de onderstaande informatie nog in om uw account compleet te maken");
        System.out.print("Instantie: ");
        String instantie = input.next();

        Overheid nieuw = new Overheid(email, telefoonnummer, instantie);
        actieveKlant = nieuw;
        DBConnection.instantieToevoegen(nieuw);
    }

    // Methode voor een scheepsbouwer om een nieuwe offerte op te stellen.
    static private void scheepsbouwerNieuweOfferte() {
        System.out.println("Voor welke klant maakt u een offerte aan");
        for(Klant klant : Klant.getAllKlanten()) {
            System.out.println(Klant.getAllKlanten().indexOf(klant) + 1 + ": Klant: " + klant.getEmail());
        }
        System.out.println("Voer hier het nummer in van de klant voor wie u een offerte aanmaakt: (Voor een nieuwe klant toets 0)");
        
        // Probeer om de gekregen input te selecteren in de lijst, lukt dit niet dan wordt het doorgegeven en moet de scheepsbouwer opnieuw proberen
        int keuze = inputInt(0, Klant.getAllKlanten().size()) - 1;
        Klant klant = null;
        if(keuze == -1) {
            nieuwKlant();
            klant = actieveKlant;
        } else {
            try {
                klant = Klant.getAllKlanten().get(keuze);
            } catch (IndexOutOfBoundsException e) {
                buitenBereik(); scheepsbouwerNieuweOfferte();
            }
        }

        System.out.println("\nU kunt kiezen uit de volgende boten:");
        for(Boot boot : Boot.getBootIDs()) {
            System.out.println(Boot.getBootIDs().indexOf(boot) + 1 + ": " + boot.printBoot());
        }
        System.out.print("Voer hier het nummer van de boot in die u wilt: ");
        keuze = inputInt(1, Boot.getBootIDs().size()) - 1;
        Boot boot = null;
        try {
            boot = Boot.getBootIDs().get(keuze);
        } catch (IndexOutOfBoundsException e) {
            buitenBereik(); scheepsbouwerNieuweOfferte();
        }

        System.out.println("\nKies uw navigatie");
        for(Onderdeel onderdeel : Onderdeel.getAllOnderdelenCategorie("navigatie")) {
            System.out.println(
                Onderdeel.getAllOnderdelenCategorie("navigatie").indexOf(onderdeel) + 1 + ": " + onderdeel.printOnderdeel()
                );
        }
        System.out.print("Voer hier het nummer in van de navigatie die u wilt hebben: ");
        keuze = inputInt(1, Onderdeel.getAllOnderdelenCategorie("navigatie").size());
        Onderdeel navigatie = null;
        try {
            navigatie = Onderdeel.getAllOnderdelenCategorie("navigatie").get(keuze - 1);
        } catch (IndexOutOfBoundsException e) {
            buitenBereik(); scheepsbouwerNieuweOfferte();
        }

        System.out.println("\nKies uw motor:");
        for(Onderdeel onderdeel : Onderdeel.getAllOnderdelenCategorie("motor")) {
            System.out.println(
                Onderdeel.getAllOnderdelenCategorie("motor").indexOf(onderdeel) + 1 + ": " + onderdeel.printOnderdeel()
                );
        }
        System.out.print("Voer hier het nummer in van de motor die u wilt hebben: ");
        keuze = inputInt(1, Onderdeel.getAllOnderdelenCategorie("motor").size());
        Onderdeel motor = null;
        try {
            motor = Onderdeel.getAllOnderdelenCategorie("motor").get(keuze - 1);
        } catch (IndexOutOfBoundsException e) {
            buitenBereik(); scheepsbouwerNieuweOfferte();
        }
        
        System.out.println("\nKies uw roer:");
        for(Onderdeel onderdeel : Onderdeel.getAllOnderdelenCategorie("roer")) {
            System.out.println(
                Onderdeel.getAllOnderdelenCategorie("roer").indexOf(onderdeel) + 1 + ": " + onderdeel.printOnderdeel()
                );
        }
        System.out.print("Voer hier het nummer in van het roer die u wilt hebben: ");
        keuze = inputInt(1, Onderdeel.getAllOnderdelenCategorie("roer").size());
        Onderdeel roer = null;
        try {
            roer = Onderdeel.getAllOnderdelenCategorie("roer").get(keuze - 1);
        } catch (IndexOutOfBoundsException e) {
            buitenBereik(); scheepsbouwerNieuweOfferte();
        }

        System.out.println("\nKies uw tank:");
        for(Onderdeel onderdeel : Onderdeel.getAllOnderdelenCategorie("tank")) {
            System.out.println(
                Onderdeel.getAllOnderdelenCategorie("tank").indexOf(onderdeel) + 1 + ": " + onderdeel.printOnderdeel()
                );
        }
        System.out.print("Voer hier het nummer in van de tank die u wilt hebben: ");
        keuze = inputInt(1, Onderdeel.getAllOnderdelenCategorie("tank").size());
        Onderdeel tank = null;
        try {
            tank = Onderdeel.getAllOnderdelenCategorie("tank").get(keuze - 1);
        } catch(IndexOutOfBoundsException e) {
            buitenBereik(); scheepsbouwerNieuweOfferte();
        }

        ArrayList<Onderdeel> extra = new ArrayList<Onderdeel>();
        while(keuze != 0) {
            System.out.println("\nKies uw overige onderdelen:");
            for(Onderdeel onderdeel : Onderdeel.getAllOnderdelenCategorie("overig")) {
                System.out.println(
                    Onderdeel.getAllOnderdelenCategorie("overig").indexOf(onderdeel) + 1 + ": " + onderdeel.printOnderdeel()
                    );
            }

            System.out.println("Voer hier het nummer in van het onderdeel dat u wilt hebben, toets 0 in als u door wilt gaan: ");
            keuze = inputInt(0, Onderdeel.getAllOnderdelenCategorie("overig").size());
            if(keuze != -1 && keuze != 0) {
                try {
                    extra.add(Onderdeel.getAllOnderdelenCategorie("overig").get(keuze - 1));
                    System.out.println("Uw huidige extra opties zijn:");
                    for(Onderdeel onderdeel : extra) {
                        System.out.println(onderdeel.printOnderdeel());
                    }
                } catch (IndexOutOfBoundsException e) {
                    buitenBereik(); scheepsbouwerNieuweOfferte();
                }
            }
        }

        System.out.print("Voer de datum in: (dd-mm-yyyy)");
        String datum = input.next();

        Boot nieuweBoot = new Boot(boot.getGrootte(), boot.getPrijsM2(), navigatie, motor, roer, tank);
        nieuweBoot.setExtraOpties(extra);
        Offerte nieuweOfferte = new Offerte(datum, klant, actieveScheepsbouwer, nieuweBoot);
        System.out.print(nieuweOfferte.print() + "\nType 1 om door te gaan:");
        input.next();
    }

    // Methode voor een scheepsbouwer om een lijst van al zijn offertes te zien.
    static private void scheepsbouwerOfferteLijst() {
        if(actieveScheepsbouwer.getOffertes().isEmpty()) {
            System.out.println("U heeft nog geen offertes, toets 1 om teruggestuurd te worden");
            input.next();
            return;
        }

        System.out.println("U heeft de volgende offertes:");
        
        for(Offerte offerte : actieveScheepsbouwer.getOffertes()) {
            System.out.println(offerte.printHead());
        }


        System.out.println("Om een offerte te openen kunt u het offertenummer nu intoetsen: (toets daarna 1 om door te gaan)");
        System.out.print("Offertenummer: ");
        int offerteNummer = inputInt();

        for(Offerte offerte : actieveScheepsbouwer.getOffertes()) {
            if(offerte.getOfferteNummer() == offerteNummer) {
                System.out.print(offerte.print());
            }
        }

        input.next();
    }

    // Methode voor een scheepsbouwer om een nieuw type klant toe te voegen.
    static private void scheepsbouwerNieuweBoot() {
        System.out.println("Voer hieronder de details voor het nieuwe model boot in:");
        System.out.print("Grootte in m: ");
        double grootte = inputDouble();
        System.out.print("Prijs per m²: ");
        double prijsM2 = inputDouble();

        Boot nBoot = new Boot(grootte, prijsM2);
        DBConnection.bootToevoegen(nBoot);
    }

    // Methode voor een scheepsbouwer om een nieuwe onderdeel toe te voegen.
    static private void scheepsbouwerOnderdeel() {
        System.out.println("Voer hieronder de details voor een nieuw onderdeel in:");
        
        System.out.println("Wat is de categorie van het onderdeel?");
        System.out.println("1: Navigatie");
        System.out.println("2: Motor");
        System.out.println("3: Roer");
        System.out.println("4: Tank");
        System.out.println("5: Overig");

        String categorie = null;
        int categorieKeuze = inputInt();

        try {
            categorie = Onderdeel.getCategorieën()[categorieKeuze - 1];
        } catch (ArrayIndexOutOfBoundsException e) {
            buitenBereik(); scheepsbouwerOnderdeel(); return;
        }
        
        System.out.print("Naam: ");
        String naam = input.next();
        
        System.out.print("Prijs: ");
        double prijs = inputDouble();
        
        System.out.print("Milieu: ");
        int milieu = inputInt();

        Onderdeel nieuw = new Onderdeel(naam, prijs, milieu, categorie);
        DBConnection.onderdeelToevoegen(nieuw);

        System.out.println(nieuw.printOnderdeel() + "\nHet onderdeel is toegevoegd, toets 1 om door te gaan");
        input.next();
    }

    // Methode voor een klant om een nieuw project op te stellen.
    static private void klantNieuwProject() {
        System.out.println("Bij welke scheepsbouwer wilt u kijken?");
        for(Scheepsbouwer scheepsbouwer : Scheepsbouwer.getAllScheepsbouwers()) {
            System.out.println(
                Scheepsbouwer.getAllScheepsbouwers().indexOf(scheepsbouwer) + 1 + ": Scheepsbouwer: " + scheepsbouwer.getVoornaam()
                + " " + scheepsbouwer.getAchternaam());
        }
        System.out.println("Voer hier het nummer in van de scheepsbouwer bij wie u wilt kijken:");
        
        // Probeer om de gekregen input te selecteren in de lijst, lukt dit niet dan wordt het doorgegeven en moet de scheepsbouwer opnieuw proberen
        int keuze = inputInt(1, Scheepsbouwer.getAllScheepsbouwers().size()) - 1;
        Scheepsbouwer scheepsbouwer = null;
        try {
            scheepsbouwer = Scheepsbouwer.getAllScheepsbouwers().get(keuze);
        } catch (IndexOutOfBoundsException e) {
            buitenBereik(); scheepsbouwerNieuweOfferte();
        }

        System.out.println("\nU kunt kiezen uit de volgende boten:");
        for(Boot boot : Boot.getBootIDs()) {
            System.out.println(Boot.getBootIDs().indexOf(boot) + 1 + ": " + boot.printBoot());
        }
        System.out.print("Voer hier het nummer van de boot in die u wilt: ");
        keuze = inputInt(1, Boot.getBootIDs().size()) - 1;
        Boot boot = null;
        try {
            boot = Boot.getBootIDs().get(keuze);
        } catch (IndexOutOfBoundsException e) {
            buitenBereik(); scheepsbouwerNieuweOfferte();
        }

        System.out.println("\nKies uw navigatie");
        for(Onderdeel onderdeel : Onderdeel.getAllOnderdelenCategorie("navigatie")) {
            System.out.println(
                Onderdeel.getAllOnderdelenCategorie("navigatie").indexOf(onderdeel) + 1 + ": " + onderdeel.printOnderdeel()
                );
        }
        System.out.print("Voer hier het nummer in van de navigatie die u wilt hebben: ");
        keuze = inputInt(1, Onderdeel.getAllOnderdelenCategorie("navigatie").size());
        Onderdeel navigatie = null;
        try {
            navigatie = Onderdeel.getAllOnderdelenCategorie("navigatie").get(keuze - 1);
        } catch (IndexOutOfBoundsException e) {
            buitenBereik(); scheepsbouwerNieuweOfferte();
        }

        System.out.println("\nKies uw motor:");
        for(Onderdeel onderdeel : Onderdeel.getAllOnderdelenCategorie("motor")) {
            System.out.println(
                Onderdeel.getAllOnderdelenCategorie("motor").indexOf(onderdeel) + 1 + ": " + onderdeel.printOnderdeel()
                );
        }
        System.out.print("Voer hier het nummer in van de motor die u wilt hebben: ");
        keuze = inputInt(1, Onderdeel.getAllOnderdelenCategorie("motor").size());
        Onderdeel motor = null;
        try {
            motor = Onderdeel.getAllOnderdelenCategorie("motor").get(keuze - 1);
        } catch (IndexOutOfBoundsException e) {
            buitenBereik(); scheepsbouwerNieuweOfferte();
        }
        
        System.out.println("\nKies uw roer:");
        for(Onderdeel onderdeel : Onderdeel.getAllOnderdelenCategorie("roer")) {
            System.out.println(
                Onderdeel.getAllOnderdelenCategorie("roer").indexOf(onderdeel) + 1 + ": " + onderdeel.printOnderdeel()
                );
        }
        System.out.print("Voer hier het nummer in van het roer die u wilt hebben: ");
        keuze = inputInt(1, Onderdeel.getAllOnderdelenCategorie("roer").size());
        Onderdeel roer = null;
        try {
            roer = Onderdeel.getAllOnderdelenCategorie("roer").get(keuze - 1);
        } catch (IndexOutOfBoundsException e) {
            buitenBereik(); scheepsbouwerNieuweOfferte();
        }

        System.out.println("\nKies uw tank:");
        for(Onderdeel onderdeel : Onderdeel.getAllOnderdelenCategorie("tank")) {
            System.out.println(
                Onderdeel.getAllOnderdelenCategorie("tank").indexOf(onderdeel) + 1 + ": " + onderdeel.printOnderdeel()
                );
        }
        System.out.print("Voer hier het nummer in van de tank die u wilt hebben: ");
        keuze = inputInt(1, Onderdeel.getAllOnderdelenCategorie("tank").size());
        Onderdeel tank = null;
        try {
            tank = Onderdeel.getAllOnderdelenCategorie("tank").get(keuze - 1);
        } catch(IndexOutOfBoundsException e) {
            buitenBereik(); scheepsbouwerNieuweOfferte();
        }

        ArrayList<Onderdeel> extra = new ArrayList<Onderdeel>();
        while(keuze != 0) {
            System.out.println("\nKies uw overige onderdelen:");
            for(Onderdeel onderdeel : Onderdeel.getAllOnderdelenCategorie("overig")) {
                System.out.println(
                    Onderdeel.getAllOnderdelenCategorie("overig").indexOf(onderdeel) + 1 + ": " + onderdeel.printOnderdeel()
                    );
            }

            System.out.println("Voer hier het nummer in van het onderdeel dat u wilt hebben, toets 0 in als u door wilt gaan: ");
            keuze = inputInt(0, Onderdeel.getAllOnderdelenCategorie("overig").size());
            if(keuze != -1 && keuze != 0) {
                try {
                    extra.add(Onderdeel.getAllOnderdelenCategorie("overig").get(keuze - 1));
                    System.out.println("Uw huidige extra opties zijn:");
                    for(Onderdeel onderdeel : extra) {
                        System.out.println(onderdeel.printOnderdeel());
                    }
                } catch (IndexOutOfBoundsException e) {
                    buitenBereik(); scheepsbouwerNieuweOfferte();
                }
            }
        }

        System.out.print("Voer de datum in: (dd-mm-yyyy)");
        String datum = input.next();

        Boot nieuweBoot = new Boot(boot.getGrootte(), boot.getPrijsM2(), navigatie, motor, roer, tank);
        nieuweBoot.setExtraOpties(extra);
        Offerte nieuweOfferte = new Offerte(datum, actieveKlant, scheepsbouwer, nieuweBoot);
        System.out.print(nieuweOfferte.print() + "\nType 1 om door te gaan:");
        input.next();
    }

    // Methode voor een klant om al zijn offertes in te zien.
    static private void klantOfferteLijst() {
        if(actieveKlant.getOffertes().isEmpty()) {
            System.out.println("U heeft nog geen offertes, toets 1 om teruggestuurd te worden");
            input.next();
            return;
        }

        System.out.println("U heeft de volgende offertes:");
        
        for(Offerte offerte : actieveKlant.getOffertes()) {
            System.out.println(offerte.printHead());
        }


        System.out.println("Om een offerte te openen kunt u het offertenummer nu intoetsen: (toets daarna 1 om door te gaan)");
        System.out.print("Offertenummer: ");
        int offerteNummer = inputInt();

        for(Offerte offerte : actieveKlant.getOffertes()) {
            if(offerte.getOfferteNummer() == offerteNummer) {
                System.out.print(offerte.print());
            }
        }

        input.next();
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

    static private int inputInt(int min, int max) {
        int i = inputInt();
        if(min <= i && i <= max) {
            return i;
        }
        return -1;
    }

    static private double inputDouble() {
        if(input.hasNextDouble()) {
            return input.nextDouble();
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

    static private void loadDB() {
        DBConnection.getOnderdelen();
        DBConnection.getBoot();
        DBConnection.getBedrijven();
        DBConnection.getOverheid();
        DBConnection.getParticulier();
    }
    
    // Keuze UI voor klanten.
    static private void klantUI() {
        int keuze = -1;
        while(keuze != 0) {
            System.out.println("U bent ingelogd als " + actieveKlant.getEmail() + ", kies uit de volgende opties:");
            System.out.println("1: Nieuw project aanmaken");
            System.out.println("2: Mijn offertes");
            System.out.println("0: Programma afsluiten");

            keuze = inputInt();

            switch(keuze) {
                case 1: clearConsole(); klantNieuwProject();
                break;
    
                case 2: clearConsole(); klantOfferteLijst();
                break;

                case 0: clearConsole(); shutdown();
                break;
    
                default: buitenBereik(); klantUI();
            }
            clearConsole();
        }
    }

    // Keuze menu voor scheepsbouwers.
    static private void scheepsbouwerUI() {
        int keuze = -1;
        
        while(keuze != 0) {
            System.out.println("U bent ingelogd als " + actieveScheepsbouwer.getEmail() + ", kies uit de volgende opties:");
            System.out.println("1: Nieuwe offerte opstellen");
            System.out.println("2: Lijst van offertes tonen");
            System.out.println("3: Nieuwe boot toevoegen");
            System.out.println("4: Nieuw onderdeel toevoegen ");
            System.out.println("0: Programma afsluiten");

            keuze = inputInt();
            switch(keuze) {
                case 1: clearConsole(); scheepsbouwerNieuweOfferte();
                break;
    
                case 2: clearConsole(); scheepsbouwerOfferteLijst();
                break;
    
                case 3: clearConsole(); scheepsbouwerNieuweBoot();
                break;
    
                case 4: clearConsole(); scheepsbouwerOnderdeel();
                break;
    
                case 0: clearConsole(); shutdown();
                break;

                default: buitenBereik(); scheepsbouwerUI();
            }
            clearConsole();
        }
    }
}
