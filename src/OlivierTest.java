public class OlivierTest {
    public static void main(String[] args) {
        LijstOlivierAlexander lijst = new LijstOlivierAlexander();
        lijst.maakOnderdelenLijst();

        Onderdeel onderdeel1 = new Onderdeel("Onderdeel 1", 12.50, true);
        Onderdeel onderdeel2 = new Onderdeel("Onderdeel 2", 20.50, false);
        Onderdeel onderdeel3 = new Onderdeel("Onderdeel 3", 18.50, true);

        lijst.onderdeelToevoegen(onderdeel1);
        lijst.onderdeelToevoegen(onderdeel2);
        lijst.onderdeelToevoegen(onderdeel3);

        lijst.onderdeelVerwijderen(onderdeel2);

        lijst.getLijstVanOnderdelen();

        lijst.zoekInLijst("Onderdeel 1");
    }
}
