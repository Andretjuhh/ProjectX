import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BootTest {
    @Test
    void berekenTotaalPrijs() {
        Onderdeel navigatie = new Onderdeel("Navigatie", 250.0, 0, "navigatie");
        Onderdeel motor = new Onderdeel("Motor", 1500.0, 0, "motor");
        Onderdeel roer = new Onderdeel("Roer", 300.0, 0, "roer");
        Onderdeel tank = new Onderdeel("Tank", 1000.0, 0, "tank");

        Boot boot = new Boot(50.0, 100.0, navigatie, motor, roer, tank);
        boot.extraOptieToevoegen(new Onderdeel("Zwemtrap", 75.0, 0, "overig"));
        boot.extraOptieToevoegen(new Onderdeel("Zonnedek", 150.0, 0, "overig"));

        double expectedTotaalPrijs = 50.0 * 100.0 + 250.0 + 1500.0 + 300.0 + 1000.0 + 75.0 + 150.0;
        double actualTotaalPrijs = boot.berekenTotaalPrijs();

        assertEquals(expectedTotaalPrijs, actualTotaalPrijs, 0.001);
    }

}