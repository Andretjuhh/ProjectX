import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
            List<Onderdeel> alleOnderdelen = DBConnection.getOnderdelen();
            for(Onderdeel onderdeel : alleOnderdelen) {
                System.out.println(onderdeel.getNaam());
            }
        }
    }

