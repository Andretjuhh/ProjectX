import static org.junit.jupiter.api.Assertions.*;

class OnderdeelTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void printOnderdeel() {
        Onderdeel onderdeel = new Onderdeel("Test onderdeel", 10.0, 20, "overig");
        String expectedOutput = "Onderdeel Test onderdeel kost: 10,00 euro en hierop zit 20% korting.\n";
        String actualOutput = onderdeel.printOnderdeel();
        assertEquals(expectedOutput, actualOutput);
    }

    @org.junit.jupiter.api.Test
    void isMilieuVriendelijk() {
        Onderdeel o1 = new Onderdeel("Onderdeel 1", 10.0, 20, "motor");
        Onderdeel o2 = new Onderdeel("Onderdeel 2", 20.0, 0, "navigatie");

        assertTrue(o1.isMilieuVriendelijk());
        assertFalse(o2.isMilieuVriendelijk());
    }

    @org.junit.jupiter.api.Test
    public void testBerekenPrijsWithDiscount() {
        Onderdeel onderdeel = new Onderdeel("test", 100.0, 10, "navigatie");
        double expectedPrice = 90.0; // 10% discount
        double actualPrice = onderdeel.berekenPrijs();
        assertEquals(expectedPrice, actualPrice, 0.001); // delta is the maximum delta between expected and actual for which both numbers are still considered equal
    }
}