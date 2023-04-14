import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;


public class DBConnection {
    private static ArrayList<Onderdeel> lijstVanOnderdelen;
    private static ArrayList<Bedrijf> lijstVanBedrijven;
    private static ArrayList<Overheid> lijstVanOverheid;
    private static ArrayList<Particulier> lijstvanParticulieren;
    private static ArrayList<Boot> lijstvanBoten;


    public static ArrayList<Onderdeel> getOnderdelen(){
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        maakOnderdelenLijst();

        try {
            // Establish connection to MySQL database
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://projectxsql.mysql.database.azure.com/projectx", "shipflex", "Ikspopdepl4");
   
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
            con = DriverManager.getConnection("jdbc:mysql://projectxsql.mysql.database.azure.com/projectx", "shipflex", "Ikspopdepl4");
   
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
           con = DriverManager.getConnection("jdbc:mysql://projectxsql.mysql.database.azure.com/projectx", "shipflex", "Ikspopdepl4");
  
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

    public static ArrayList<Onderdeel> zoekInOnderdelen(String optie) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        maakOnderdelenLijst();

        
        try {
         // Establish connection to MySQL database
         Class.forName("com.mysql.jdbc.Driver");
         con = DriverManager.getConnection("jdbc:mysql://projectxsql.mysql.database.azure.com/projectx", "shipflex", "Ikspopdepl4");
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
       con = DriverManager.getConnection("jdbc:mysql://projectxsql.mysql.database.azure.com", "root", "Ikspopdepl4");
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

 //-------------------------------------------------------------------------------------------------------------------------------------

 public static ArrayList<Bedrijf> getBedrijven(){
   Connection con = null;
   Statement stmt = null;
   ResultSet rs = null;
   maakOnderdelenLijst();

   try {
       // Establish connection to MySQL database
       Class.forName("com.mysql.jdbc.Driver");
       con = DriverManager.getConnection("jdbc:mysql://projectxsql.mysql.database.azure.com/projectx", "shipflex", "Ikspopdepl4");

       // Query database for boat parts
       stmt = con.createStatement();
       rs = stmt.executeQuery("SELECT Bnaam, telefoon, email FROM bedrijf");

       while (rs.next()) {
          // Fetch data from result set
          String naam = rs.getString("Bnaam");
          int telefoon = rs.getInt("telefoon");
          String email = rs.getString("email");

          Bedrijf bedrijf = new Bedrijf(email, telefoon, naam);
          lijstVanBedrijven.add(bedrijf);
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


   return lijstVanBedrijven;

  
}

public static void bedrijfToevoegen(Bedrijf bedrijf) {
   Connection con = null;
   PreparedStatement stmt = null;
   
   try {
       // Establish connection to MySQL database
       Class.forName("com.mysql.jdbc.Driver");
       con = DriverManager.getConnection("jdbc:mysql://projectxsql.mysql.database.azure.com/projectx", "shipflex", "Ikspopdepl4");

       // Insert new boat part into database
       String query = "INSERT INTO bedrijf (Bnaam, telefoon, email) VALUES (?, ?, ?)";
       stmt = con.prepareStatement(query);
       stmt.setString(1, bedrijf.getBedrijfNaam());
       stmt.setInt(2, bedrijf.getTelefoonnummer());
       stmt.setString(3, bedrijf.getEmail());
       stmt.executeUpdate();

       System.out.println("Bedrijf is toegevoegd.");

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

 public static ArrayList<Bedrijf> zoekInBedrijven(String optie) {
   Connection con = null;
   PreparedStatement stmt = null;
   ResultSet rs = null;
   maakOnderdelenLijst();

   
   try {
    // Establish connection to MySQL database
    Class.forName("com.mysql.jdbc.Driver");
    con = DriverManager.getConnection("jdbc:mysql://projectxsql.mysql.database.azure.com/projectx", "shipflex", "Ikspopdepl4");
    String query = "SELECT Bnaam, telefoon, email FROM bedrijf WHERE Bnaam LIKE ?";
    stmt = con.prepareStatement(query);
    stmt.setString(1, "%" + optie + "%");
    rs = stmt.executeQuery();

    while (rs.next()) {
       // Create a new BoatPart object with the values retrieved from the database
       String Bnaam = rs.getString("Bnaam");
       int telefoon = rs.getInt("telefoon");
       String email = rs.getString("email");
       Bedrijf bedrijf = new Bedrijf(email, telefoon, Bnaam);
       lijstVanBedrijven.add(bedrijf);
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
 return lijstVanBedrijven;
}


//------------------------------------------------------------------------------------------------------------------------------------------



public static ArrayList<Overheid> getOverheid(){
   Connection con = null;
   Statement stmt = null;
   ResultSet rs = null;
   maakOnderdelenLijst();

   try {
       // Establish connection to MySQL database
       Class.forName("com.mysql.jdbc.Driver");
       con = DriverManager.getConnection("jdbc:mysql://projectxsql.mysql.database.azure.com/projectx", "shipflex", "Ikspopdepl4");

       // Query database for boat parts
       stmt = con.createStatement();
       rs = stmt.executeQuery("SELECT instantie, telefoon, email FROM overheid");

       while (rs.next()) {
          // Fetch data from result set
          String instantie = rs.getString("instantie");
          int telefoon = rs.getInt("telefoon");
          String email = rs.getString("email");

          Overheid overheid = new Overheid(email, telefoon, instantie);
          lijstVanOverheid.add(overheid);
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


   return lijstVanOverheid;

  
}

public static void instantieToevoegen(Overheid overheid) {
   Connection con = null;
   PreparedStatement stmt = null;
   
   try {
       // Establish connection to MySQL database
       Class.forName("com.mysql.jdbc.Driver");
       con = DriverManager.getConnection("jdbc:mysql://projectxsql.mysql.database.azure.com/projectx", "shipflex", "Ikspopdepl4");

       // Insert new boat part into database
       String query = "INSERT INTO overheid (instantie, telefoon, email) VALUES (?, ?, ?)";
       stmt = con.prepareStatement(query);
       stmt.setString(1, overheid.getInstantie());
       stmt.setInt(2, overheid.getTelefoonnummer());
       stmt.setString(3, overheid.getEmail());
       stmt.executeUpdate();

       System.out.println("Instantie is toegevoegd.");

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

 public static ArrayList<Overheid> zoekInOverheid(String optie) {
   Connection con = null;
   PreparedStatement stmt = null;
   ResultSet rs = null;
   maakOnderdelenLijst();

   
   try {
    // Establish connection to MySQL database
    Class.forName("com.mysql.jdbc.Driver");
    con = DriverManager.getConnection("jdbc:mysql://projectxsql.mysql.database.azure.com/projectx", "shipflex", "Ikspopdepl4");
    String query = "SELECT instantie, telefoon, email FROM overheid WHERE instantie LIKE ?";
    stmt = con.prepareStatement(query);
    stmt.setString(1, "%" + optie + "%");
    rs = stmt.executeQuery();

    while (rs.next()) {
       // Create a new BoatPart object with the values retrieved from the database
       String instantie = rs.getString("instantie");
       int telefoon = rs.getInt("telefoon");
       String email = rs.getString("email");
       Overheid overheid = new Overheid(email, telefoon, instantie);
       lijstVanOverheid.add(overheid);
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
 return lijstVanOverheid;
}

//-------------------------------------------------------------------------------------------------------------------------------------------

public static ArrayList<Particulier> getParticulier(){
   Connection con = null;
   Statement stmt = null;
   ResultSet rs = null;
   maakOnderdelenLijst();

   try {
       // Establish connection to MySQL database
       Class.forName("com.mysql.jdbc.Driver");
       con = DriverManager.getConnection("jdbc:mysql://projectxsql.mysql.database.azure.com/projectx", "shipflex", "Ikspopdepl4");

       // Query database for boat parts
       stmt = con.createStatement();
       rs = stmt.executeQuery("SELECT voornaam, achternaam, telefoon, email FROM particulier");

       while (rs.next()) {
          // Fetch data from result set
          String voornaam = rs.getString("voornaam");
          String achternaam = rs.getString("voornaam");
          int telefoon = rs.getInt("telefoon");
          String email = rs.getString("email");

          Particulier particulier = new Particulier(email, telefoon, voornaam, achternaam);
          lijstvanParticulieren.add(particulier);
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


   return lijstvanParticulieren;

  
}

public static void particulierToevoegen(Particulier particulier) {
   Connection con = null;
   PreparedStatement stmt = null;
   
   try {
       // Establish connection to MySQL database
       Class.forName("com.mysql.jdbc.Driver");
       con = DriverManager.getConnection("jdbc:mysql://projectxsql.mysql.database.azure.com/projectx", "shipflex", "Ikspopdepl4");

       // Insert new boat part into database
       String query = "INSERT INTO particulier (voornaam, achternaam, telefoon, email) VALUES (?, ?, ?, ?)";
       stmt = con.prepareStatement(query);
       stmt.setString(1, particulier.getVoornaam());
       stmt.setString(2, particulier.getAchternaam());
       stmt.setInt(3, particulier.getTelefoonnummer());
       stmt.setString(4, particulier.getEmail());
       stmt.executeUpdate();

       System.out.println("particulier is toegevoegd.");

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

 public static ArrayList<Particulier> zoekInParticulieren(String optie) {
   Connection con = null;
   PreparedStatement stmt = null;
   ResultSet rs = null;
   maakOnderdelenLijst();

   
   try {
    // Establish connection to MySQL database
    Class.forName("com.mysql.jdbc.Driver");
    con = DriverManager.getConnection("jdbc:mysql://projectxsql.mysql.database.azure.com/projectx", "shipflex", "Ikspopdepl4");
    String query = "SELECT voornaam, achternaam, telefoon, email FROM particulier WHERE achternaam LIKE ?";
    stmt = con.prepareStatement(query);
    stmt.setString(1, "%" + optie + "%");
    rs = stmt.executeQuery();

    while (rs.next()) {
       // Create a new BoatPart object with the values retrieved from the database
       String voornaam = rs.getString("voornaam");
       String achternaam = rs.getString("achternaam");
       int telefoon = rs.getInt("telefoon");
       String email = rs.getString("email");
       Particulier particulier = new Particulier(email, telefoon, voornaam, achternaam);
       lijstvanParticulieren.add(particulier);
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
 return lijstvanParticulieren;
}

//--------------------------------------------------------------------------------------------------------------------------------

public static ArrayList<Boot> getBoot(){
   Connection con = null;
   Statement stmt = null;
   ResultSet rs = null;
   maakOnderdelenLijst();

   try {
       // Establish connection to MySQL database
       Class.forName("com.mysql.jdbc.Driver");
       con = DriverManager.getConnection("jdbc:mysql://projectxsql.mysql.database.azure.com/projectx", "shipflex", "Ikspopdepl4");

       // Query database for boat parts
       stmt = con.createStatement();
       rs = stmt.executeQuery("SELECT bootnaam, grootte, prijs FROM boot");

       while (rs.next()) {
          // Fetch data from result set
          int bootID = rs.getInt("bootID");
          double grootte = rs.getDouble("grootte");
          double prijs = rs.getDouble("prijs");

          Boot boot = new Boot(bootID, grootte, prijs);
          lijstvanBoten.add(boot);
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


   return lijstvanBoten;

  
}

public static void bootToevoegen(Boot boot) {
   Connection con = null;
   PreparedStatement stmt = null;
   
   try {
       // Establish connection to MySQL database
       Class.forName("com.mysql.jdbc.Driver");
       con = DriverManager.getConnection("jdbc:mysql://projectxsql.mysql.database.azure.com/projectx", "shipflex", "Ikspopdepl4");

       // Insert new boat part into database
       String query = "INSERT INTO boot (bootID, grootte, prijs) VALUES (?, ?, ?)";
       stmt = con.prepareStatement(query);
       stmt.setInt(1, boot.getBootID());
       stmt.setDouble(2, boot.getGrootte());
       stmt.setDouble(3, boot.getPrijsM2());
       stmt.executeUpdate();

       System.out.println("Boot is toegevoegd.");

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

 public static ArrayList<Boot> zoekInBoten(String optie) {
   Connection con = null;
   PreparedStatement stmt = null;
   ResultSet rs = null;
   maakOnderdelenLijst();

   
   try {
    // Establish connection to MySQL database
    Class.forName("com.mysql.jdbc.Driver");
    con = DriverManager.getConnection("jdbc:mysql://projectxsql.mysql.database.azure.com/projectx", "shipflex", "Ikspopdepl4");
    String query = "SELECT bootnaam, grootte, prijs FROM boot WHERE bootnaam LIKE ?";
    stmt = con.prepareStatement(query);
    stmt.setString(1, "%" + optie + "%");
    rs = stmt.executeQuery();

    while (rs.next()) {
       // Create a new BoatPart object with the values retrieved from the database
       double grootte = rs.getDouble("grootte");
       double prijs = rs.getDouble("prijs");
       Boot boot = new Boot(grootte, prijs);
       lijstvanBoten.add(boot);
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
 return lijstvanBoten;
}







}
