import java.util.ArrayList;

class Boot {
    private int bootID;
    private double grootte;
    private double prijsM2;
    private Onderdeel navigatie;
    private Onderdeel motor;
    private Onderdeel roer;
    private Onderdeel tank;
    private static ArrayList<Boot> bootIDs = new ArrayList<Boot>();
    private ArrayList<Onderdeel> extraOpties = new ArrayList<Onderdeel>();

    public Boot(double grootte, double prijsM2) {
        this.grootte = grootte;
        this.prijsM2 = prijsM2;

        this.bootID = ((int) (Math.random() * 100000));

        for (Boot b : bootIDs) {
            if (b.getBootID() == bootID) {
                this.bootID = ((int) (Math.random() * 100000));
            }
        }

        bootIDs.add(this);
    }

    public Boot(int bootID, double grootte, double prijsM2) {
        this.bootID = bootID;
        this.grootte = grootte;
        this.prijsM2 = prijsM2;
    }

    public Boot(double grootte, double prijsM2, Onderdeel navigatie, Onderdeel motor, Onderdeel roer, Onderdeel tank) {
        this.grootte = grootte;
        this.prijsM2 = prijsM2;
        this.navigatie = navigatie;
        this.motor = motor;
        this.roer = roer;
        this.tank = tank;

        this.bootID = ((int) (Math.random() * 100000));

        for (Boot b : bootIDs) {
            if (b.getBootID() == bootID) {
                this.bootID = ((int) (Math.random() * 100000));
            }
        }
    }

    public int getBootID() {
        return bootID;
    }

    public double getGrootte() {
        return grootte;
    }

    public double getPrijsM2() {
        return prijsM2;
    }

    public Onderdeel getNavigatie() {
        return navigatie;
    }

    public Onderdeel getMotor() {
        return motor;
    }

    public Onderdeel getRoer() {
        return roer;
    }

    public Onderdeel getTank() {
        return tank;
    }

    public ArrayList<Onderdeel> getExtraOpties() {
        return extraOpties;
    }
    public static ArrayList<Boot> getBootIDs() {
        return bootIDs;
    }

    public void setBootID(int bootID) {
        this.bootID = bootID;
    }

    public void setGrootte(double grootte) {
        this.grootte = grootte;
    }

    public void setPrijsM2(double prijsM2) {
        this.prijsM2 = prijsM2;
    }

    public void setNavigatie(Onderdeel navigatie) {
        this.navigatie = navigatie;
    }

    public void setMotor(Onderdeel motor) {
        this.motor = motor;
    }

    public void setRoer(Onderdeel roer) {
        this.roer = roer;
    }

    public void setTank(Onderdeel tank) {
        this.tank = tank;
    }

    public void setExtraOpties(ArrayList<Onderdeel> extraOpties) {
        this.extraOpties = extraOpties;
    }

    public void extraOptieToevoegen(Onderdeel onderdeel) {
        getExtraOpties().add(onderdeel);
    }

    public void extraOptieVerwijderen(Onderdeel onderdeel) {
        getExtraOpties().remove(onderdeel);
    }

    public double berekenTotaalPrijs() {
        double totaalPrijs = getGrootte() * getPrijsM2();
        totaalPrijs += getNavigatie().berekenPrijs();
        totaalPrijs += getMotor().berekenPrijs();
        totaalPrijs += getRoer().berekenPrijs();
        totaalPrijs += getTank().berekenPrijs();
        for (Onderdeel o : getExtraOpties()) {
            totaalPrijs += o.berekenPrijs();
        }
        return totaalPrijs;
    }

    public String printBoot() {
        String result = "BootID: " + bootID + "\n";
        result += "Grootte in M²: " + grootte + " prijs per M²: " + prijsM2 + "\n";
        result += "Prijs van de boot: " + grootte * prijsM2 + "\n";

        return result;
    }

    public String print() {
        String result = printBoot();

        result += "\n" + "Onderdelen:\n";
        result += navigatie.printOnderdeel();
        result += motor.printOnderdeel();
        result += roer.printOnderdeel();
        result += tank.printOnderdeel();

        for(Onderdeel onderdeel : extraOpties) {result += onderdeel.printOnderdeel();}

        return result;
    }

    // Hier komt later nog een uitgewerkte methode

    public ArrayList<Onderdeel> filterOnderdeel(String categorie, ArrayList<Onderdeel> lijst) {
        ArrayList<Onderdeel> filterLijst = new ArrayList<>();
        return filterLijst;
    }
}