import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Particulier particulier = new Particulier("mail", 0, null, null);
        Bedrijf bedrijf = new Bedrijf("bedrijfMail", 0, null);
        Overheid overheid = new Overheid("overheidMail", 0, null);
        Scheepsbouwer sb = new Scheepsbouwer("Reno", "Welleman", "rpjwell@gmail.com");
        Boot boot = new Boot(10, 100);
        ArrayList<Onderdeel> onderdelen = DBConnection.getOnderdelen();

        UI.startUp();
        }
    }

