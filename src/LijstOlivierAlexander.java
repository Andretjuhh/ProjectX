import java.util.ArrayList;
import java.util.Collections;

public class LijstOlivierAlexander {
    private ArrayList<Onderdeel> lijstVanOnderdelen;

    public void maakOnderdelenLijst() {
        lijstVanOnderdelen = new ArrayList<>();
    }

    public void onderdeelToevoegen(Onderdeel onderdeel) {
        lijstVanOnderdelen.add(onderdeel);
    }

    public void onderdeelVerwijderen(Onderdeel onderdeel) {
        lijstVanOnderdelen.remove(onderdeel);
    }

    public void lijstSorteren() {
        ArrayList<String> lijstVanOnderdelenGesorteerd = new ArrayList<>();
        for (Onderdeel onderdeel : lijstVanOnderdelen) {
            lijstVanOnderdelenGesorteerd.add(onderdeel.getNaam());
            Collections.sort(lijstVanOnderdelenGesorteerd);
        }
    }

    public ArrayList<Onderdeel> getLijstVanOnderdelen() {
        System.out.println(lijstVanOnderdelen);
        return lijstVanOnderdelen;
    }

    public ArrayList<Onderdeel> zoekInLijst(String optie) {
        ArrayList<Onderdeel> olivier = new ArrayList<>();
        for (Onderdeel onderdeel : lijstVanOnderdelen) {
            if (onderdeel.getNaam().equals(optie)) {
                olivier.add(onderdeel);
            }
        }
        System.out.println(olivier);
        return olivier;
    }
}
