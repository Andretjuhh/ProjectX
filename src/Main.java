import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        ArrayList<String> gemaakteKeuzes = new ArrayList<>();
        ArrayList<Double> gemaakteKeuzesPrijs = new ArrayList<Double>();
        ArrayList<String> materiaal = new ArrayList<>();
        materiaal.add("Ijzer");
        materiaal.add("Staal");
        materiaal.add("Koper");
        ArrayList<Double> materiaalPrijs = new ArrayList<Double>();
        materiaalPrijs.add(5600.00);
        materiaalPrijs.add(9800.00);
        materiaalPrijs.add(4600.00);
        ArrayList<String> motor = new ArrayList<>();
        motor.add("motor1");
        motor.add("motor2");
        motor.add("motor3");
        ArrayList<Double> motorPrijs = new ArrayList<Double>();
        motorPrijs.add(2000.00);
        motorPrijs.add(3000.00);
        motorPrijs.add(4500.00);
        int i;
        i = 0;
        while (i < materiaal.size()) {
            System.out.println(i + 1 + ": " + materiaal.get(i) + " Prijs: " + materiaalPrijs.get(i));
            i++;
        }
        Scanner scanner = new Scanner(System.in);
        int keuze;
        double z = 0;
        keuze = scanner.nextInt();
        System.out.println("je kiest: "+ materiaal.get(keuze - 1));
        gemaakteKeuzes.add(materiaal.get(keuze - 1));
        gemaakteKeuzesPrijs.add(materiaalPrijs.get(keuze - 1));
        z = z + materiaalPrijs.get(keuze - 1);
        System.out.print("Huidige prijs: "+ z);
        System.out.println("");
        i = 0;
        while (i < motor.size()){
            System.out.println(i + 1 + ": " + motor.get(i) + " Prijs: " + motorPrijs.get(i));
            i++;
        }
        keuze = scanner.nextInt();
        System.out.println("je kiest: "+ motor.get(keuze - 1));
        gemaakteKeuzes.add(motor.get(keuze - 1));
        gemaakteKeuzesPrijs.add(motorPrijs.get(keuze - 1));
        z = z + materiaalPrijs.get(keuze - 1);
        System.out.print("Huidige prijs: "+ z);
        System.out.println("");
        System.out.println("De door jou geselecteerde onderdelen: " + gemaakteKeuzes);
        i = 0;
        double x = 0;
        while ( i < gemaakteKeuzesPrijs.size()){
            x = x + gemaakteKeuzesPrijs.get(i);
            i++;
        }
        System.out.print("De totale kosten zijn: " + x);

    }
}