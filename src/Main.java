import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Particulier particulier = new Particulier("mail", 0, null, null);
        Bedrijf bedrijf = new Bedrijf("bedrijfMail", 0, null);
        Overheid overheid = new Overheid("overheidMail", 0, null);
        Scheepsbouwer sb = new Scheepsbouwer("Reno", "Welleman", "rpjwell@gmail.com");
        Boot boot = new Boot(10, 100);
        Onderdeel motor = new Onderdeel("motor 1", 1, 0, "motor");
        Onderdeel nav = new Onderdeel("nav 1", 10, 12, "navigatie");
        Onderdeel tank = new Onderdeel("tank 1", 1, 0, "tank");
        Onderdeel roer = new Onderdeel("roer 1", 1, 0, "roer");
        Onderdeel extra1 = new Onderdeel("koffie 1", 1, 0, "overig");
        Boot nBoot = new Boot(boot.getGrootte(), boot.getPrijsM2(), nav, motor, roer, tank);
        Offerte nOfferte = new Offerte("10-4", overheid, sb, nBoot);

        UI.startUp();
        }
    }

