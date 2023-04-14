import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BootTest {
    @Test
    void berekenTotaalPrijs() {
        Onderdeel navigatie = new Onderdeel("Navigatie", 250.0);
        Onderdeel motor = new Onderdeel("Motor", 1500.0);
        Onderdeel roer = new Onderdeel("Roer", 300.0);
        Onderdeel tank = new Onderdeel("Tank", 1000.0);

        Boot boot = new Boot(50.0, 100.0, navigatie, motor, roer, tank);
        boot.extraOptieToevoegen(new Onderdeel("Zwemtrap", 75.0));
        boot.extraOptieToevoegen(new Onderdeel("Zonnedek", 150.0));

        double expectedTotaalPrijs = 50.0 * 100.0 + 250.0 + 1500.0 + 300.0 + 1000.0 + 75.0 + 150.0;
        double actualTotaalPrijs = boot.berekenTotaalPrijs();

        assertEquals(expectedTotaalPrijs, actualTotaalPrijs, 0.001);
    }

}