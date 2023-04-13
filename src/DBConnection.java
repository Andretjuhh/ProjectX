import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;


public class DBConnection {
    private static ArrayList<Onderdeel> lijstVanOnderdelen;

    public static ArrayList<Onderdeel> getOnderdelen(){
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        maakOnderdelenLijst();

        try {
            // Establish connection to MySQL database
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/projectX", "root", "Ikspopdepl4");
   
            // Query database for boat parts
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT Onaam, prijs, milieu, categorie FROM onderdelen");
   
            while (rs.next()) {
               // Fetch data from result set
               String naam = rs.getString("Onaam");
               double prijs = rs.getDouble("prijs");
               int milieu = rs.getInt("milieu");
               String categorie = rs.getString("categorie");

               Onderdeel onderdeel = new Onderdeel(naam, prijs, milieu, categorie);
               lijstVanOnderdelen.add(onderdeel);
            }
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
         } finally {
            try {
               if (rs != null) rs.close();
               if (stmt != null) stmt.close();
               if (con != null) con.close();
            } catch (SQLException e) {
               e.printStackTrace();
            }
        }


        return lijstVanOnderdelen;

       
    }

    public static void maakOnderdelenLijst() {
        lijstVanOnderdelen = new ArrayList<>();
    }

    public static void onderdeelToevoegen(Onderdeel onderdeel) {
        Connection con = null;
        PreparedStatement stmt = null;
        
        try {
            // Establish connection to MySQL database
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/projectX", "root", "Ikspopdepl4");
   
            // Insert new boat part into database
            String query = "INSERT INTO onderdelen (Onaam, prijs, milieu, categorie) VALUES (?, ?, ?, ?)";
            stmt = con.prepareStatement(query);
            stmt.setString(1, onderdeel.getNaam());
            stmt.setDouble(2, onderdeel.getPrijs());
            stmt.setInt(3, onderdeel.getMillieuvriendelijk());
            stmt.setString(4, onderdeel.getCategorie());
            stmt.executeUpdate();
   
            System.out.println("Onderdeel is toegevoegd.");
   
         } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
         } finally {
            try {
               if (stmt != null) stmt.close();
               if (con != null) con.close();
            } catch (SQLException e) {
               e.printStackTrace();
            }
         }
      }

    

    public static void onderdeelVerwijderen(Onderdeel onderdeel) {
        Connection con = null;
        PreparedStatement stmt = null;
  
        try {
           // Establish connection to MySQL database
           Class.forName("com.mysql.jdbc.Driver");
           con = DriverManager.getConnection("jdbc:mysql://localhost/projectX", "root", "Ikspopdepl4");
  
           // Remove boat part from database
           String query = "DELETE FROM onderdelen WHERE Onaam = ?";
           stmt = con.prepareStatement(query);
           stmt.setString(1, onderdeel.getNaam());
           int rowsDeleted = stmt.executeUpdate();
  
           if (rowsDeleted > 0) {
              System.out.println("Onderdeel verwijderd.");
           } else {
              System.out.println("Onderdeel niet gevonden.");
           }
  
        } catch (ClassNotFoundException | SQLException e) {
           e.printStackTrace();
        } finally {
           try {
              if (stmt != null) stmt.close();
              if (con != null) con.close();
           } catch (SQLException e) {
              e.printStackTrace();
           }
        }    }

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

    public static ArrayList<Onderdeel> zoekInLijst(String optie) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        maakOnderdelenLijst();

        
        try {
         // Establish connection to MySQL database
         Class.forName("com.mysql.jdbc.Driver");
         con = DriverManager.getConnection("jdbc:mysql://localhost/projectX", "root", "Ikspopdepl4");
         String query = "SELECT Onaam, prijs, milieu, categorie FROM onderdelen WHERE Onaam LIKE ?";
         stmt = con.prepareStatement(query);
         stmt.setString(1, "%" + optie + "%");
         rs = stmt.executeQuery();

         while (rs.next()) {
            // Create a new BoatPart object with the values retrieved from the database
            String Onaam = rs.getString("Onaam");
            double prijs = rs.getDouble("prijs");
            int millieuvriendelijk = rs.getInt("milieu");
            String categorie = rs.getString("categorie");
            Onderdeel onderdeel = new Onderdeel(Onaam, prijs, millieuvriendelijk, categorie);
            lijstVanOnderdelen.add(onderdeel);
         }
      }
      catch (ClassNotFoundException | SQLException e) {
         e.printStackTrace();
      } finally {
         try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (con != null) con.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      return lijstVanOnderdelen;
   }

   public static ArrayList<Onderdeel> SorteerCategorie(String optie) {
      Connection con = null;
      PreparedStatement stmt = null;
      ResultSet rs = null;
      maakOnderdelenLijst();

      
      try {
       // Establish connection to MySQL database
       Class.forName("com.mysql.jdbc.Driver");
       con = DriverManager.getConnection("jdbc:mysql://localhost/projectX", "root", "Ikspopdepl4");
       String query = "SELECT Onaam, prijs, milieu, categorie FROM onderdelen WHERE categorie = ?";
       stmt = con.prepareStatement(query);
       stmt.setString(1, optie );
       rs = stmt.executeQuery();

       while (rs.next()) {
          // Create a new BoatPart object with the values retrieved from the database
          String Onaam = rs.getString("Onaam");
          double prijs = rs.getDouble("prijs");
          int millieuvriendelijk = rs.getInt("milieu");
          String categorie = rs.getString("categorie");
          Onderdeel onderdeel = new Onderdeel(Onaam, prijs, millieuvriendelijk, categorie);
          lijstVanOnderdelen.add(onderdeel);
       }
    }
    catch (ClassNotFoundException | SQLException e) {
       e.printStackTrace();
    } finally {
       try {
          if (rs != null) rs.close();
          if (stmt != null) stmt.close();
          if (con != null) con.close();
       } catch (SQLException e) {
          e.printStackTrace();
       }
    }
    return lijstVanOnderdelen;
 }
}
