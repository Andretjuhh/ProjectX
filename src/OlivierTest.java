import java.util.Scanner;

public class OlivierTest {
    LijstOlivierAlexander lijst = new LijstOlivierAlexander();
    static Scanner scanner = new Scanner(System.in);

    static Onderdeel onderdeel1 = new Onderdeel("Onderdeel 1", 12.50, 10);
    static Onderdeel onderdeel2 = new Onderdeel("Onderdeel 2", 20.50, 14);
    static Onderdeel onderdeel3 = new Onderdeel("Onderdeel 3", 18.50, 20);

    public static void main(String[] args) {

        System.out.println("Kies hier een onderdeel:");
        System.out.println("[1] " + onderdeel1.getNaam());
        System.out.println("[2] " + onderdeel2.getNaam());
        System.out.println("[3] " + onderdeel3.getNaam());

        int antwoord1 = scanner.nextInt();

        if (antwoord1 == 1) {
            System.out.println("U koos " + onderdeel1.getNaam() + ".");
        } else if (antwoord1 == 2) {
            System.out.println("U koos " + onderdeel2.getNaam() + ".");
        } else if (antwoord1 == 3) {
            System.out.println("U koos " + onderdeel3.getNaam() + ".");
        }

        onderdeel1.printOnderdeel();

        scanner.close();
    }
}
