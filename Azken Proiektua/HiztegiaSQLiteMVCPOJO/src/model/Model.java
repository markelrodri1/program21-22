package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static model.Hizkuntza.ES;
import static model.Hizkuntza.EU;

public class Model {

    private final String DB = "db/Hiztegia.db";

    public Connection konektatu() {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:" + DB;
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            //  System.out.println("Connection to SQLite has been established.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return conn;

    }

    public void terminoakInprimatu() {
        String taula = "Terminoak";
        String sql = "SELECT * FROM " + taula;

        try (Connection conn = konektatu(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            //Txostenaren izenburua
            String izenburua = DB + " datu-baseko " + taula + " taulako datuak:";
            System.out.println(izenburua);
            for (int i = 0; i < izenburua.length(); i++) {
                System.out.print("=");
            }
            System.out.println("");

            int kont = 0;
            while (rs.next()) {
                Terminoa t = new Terminoa(rs.getInt("id"), rs.getString("euskaraz"), rs.getString("gazteleraz"));
                System.out.println(t);
                kont++;
            }
            for (int i = 0; i < izenburua.length(); i++) {
                System.out.print("-");
            }
            System.out.println("");
            System.out.println("GUZTIRA: " + kont + " termino");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

//    public static ArrayList<Terminoa> tBilatu(String hitza) {
//        String taula = "Terminoak";
//        String sqlInsert = "SELECT * FROM " + taula + " WHERE euskaraz= ? OR gazteleraz = ?";
//
//        ArrayList<Terminoa> alt = new ArrayList<>();
//        try ( Connection conn = connect();  PreparedStatement pstmt = conn.prepareStatement(sqlInsert)) {
//
//        } catch (Exception ex) {
//
//        }
//        return alt;
//    }
//    public static int tGehitu(Terminoa t) {
//
//        String sqlInsert = "INSERT INTO Terminoak(id,euskaraz,gazteleraz) VALUES(?,?,?)";
//
//        try ( Connection conn = konektatu();  PreparedStatement pstmt = conn.prepareStatement(sqlInsert)) {
//            pstmt.setInt(1, t.getId());
//            pstmt.setString(2, t.getEuskaraz());
//            pstmt.setString(3, t.getGazteleraz());
//            return pstmt.executeUpdate();
//
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//            return 0;
//        }
//
//    }
    public void terminoakInprimatuObjektuGabe() {
        String taula = "Terminoak";
        String sql = "SELECT * FROM " + taula;

        try (Connection conn = konektatu(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            //Txostenaren izenburua
            String izenburua = DB + " datu-baseko " + taula + " taulako datuak:";
            System.out.println(izenburua);
            for (int i = 0; i < izenburua.length(); i++) {
                System.out.print("=");
            }
            System.out.println("");

            int kont = 0;
            while (rs.next()) {
                System.out.printf("%3d  %-30s%-30s\n", rs.getInt("id"), rs.getString("euskaraz"), rs.getString("gazteleraz"));
                kont++;
            }
            for (int i = 0; i < izenburua.length(); i++) {
                System.out.print("-");
            }
            System.out.println("");
            System.out.println("GUZTIRA: " + kont + " termino");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void itzulpenaInprimatu(String euskarazkoa) {
        String taula = "Terminoak";
        String sql = "SELECT * FROM " + taula + " WHERE euskaraz= ?";

        try (Connection conn = konektatu(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, euskarazkoa);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Terminoa t = new Terminoa(rs.getInt("id"), rs.getString("euskaraz"), rs.getString("gazteleraz"));
                System.out.println(t);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public int terminoaGehitu(Terminoa t) {

        String sqlSelect = "SELECT * FROM Terminoak WHERE euskaraz =? AND gazteleraz = ?";
        try (Connection conn = konektatu(); PreparedStatement pstmt = conn.prepareStatement(sqlSelect)) {

            pstmt.setString(1, t.getEuskaraz());
            pstmt.setString(2, t.getGazteleraz());
            ResultSet rs = pstmt.executeQuery();
            int kopurua = 0;
            while (rs.next()) {
                kopurua++;

            }
            if (kopurua > 0) {

                return 0;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }

        String sqlInsert = "INSERT INTO Terminoak(euskaraz,gazteleraz) VALUES(?,?)";

        try (Connection conn = konektatu(); PreparedStatement pstmt = conn.prepareStatement(sqlInsert)) {

            pstmt.setString(1, t.getEuskaraz());
            pstmt.setString(2, t.getGazteleraz());

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -2;
        }
    }

    public int terminoaGehituObjektuGabe(int id, String euskaraz, String gazteleraz) {

        String sql = "INSERT INTO Terminoak(id,euskaraz,gazteleraz) VALUES(?,?,?)";

        try (Connection conn = konektatu(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, 1790);
            pstmt.setString(2, euskaraz);
            pstmt.setString(3, gazteleraz);
            return pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }

    }

    public int terminoaEzabatu(int id) {

        String sql = "DELETE FROM Terminoak WHERE id = ?";

        try (Connection conn = konektatu(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }

    }

    /**
     * Kontuz: lechuga');DELETE FROM Terminoak;--
     */
    public void metodoBat(String eu, String es) {

//        Scanner in = new Scanner(System.in);
//        System.out.print("Termino berria: ");
//        String strEu = in.nextLine();
//        String strEs = terminoa.split(" ");
        //String sqlInsert = "SELECT id,euskaraz,gazteleraz FROM Terminoak WHERE euskaraz = '"+ strEuskaraz+"'" ;
        String sql = "INSERT INTO Terminoak(euskaraz,gazteleraz) VALUES('" + eu + "','" + es + "')";

        try (Connection conn = konektatu(); Statement stmt = conn.createStatement()) {
            int n = stmt.executeUpdate(sql);

            //     System.out.println(strEuskaraz +" => " +rs.getString("gazteleraz"));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ArrayList<Terminoa> terminoakArrayListera() {
        ArrayList<Terminoa> terminoak = new ArrayList<>();
        String sql = "SELECT * FROM Terminoak";
        try (Connection conn = konektatu(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Terminoa t = new Terminoa(rs.getInt("id"), rs.getString("euskaraz"), rs.getString("gazteleraz"));
                terminoak.add(t);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return terminoak;
    }

    public Terminoa[] terminoakArrayra() {
        int size = 0;

        String sqlCount = "Select COUNT(id) AS zenbat FROM Terminoak";
        try (Connection conn = konektatu(); PreparedStatement pstmt = conn.prepareStatement(sqlCount)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                size = rs.getInt("zenbat");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        int i = 0;
        Terminoa[] terminoak = new Terminoa[size];
        String sql = "SELECT * FROM Terminoak";
        try (Connection conn = konektatu(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {

                terminoak[i] = new Terminoa(rs.getInt("id"), rs.getString("euskaraz"), rs.getString("gazteleraz"));
                i++;

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return terminoak;
    }

    public String euskaratu(String gazteleraz) {
        int count = 0;
        String itzulpena = "";
        String sql = "SELECT euskaraz, gazteleraz FROM Terminoak WHERE gazteleraz = ? ";
        try (Connection conn = konektatu(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, gazteleraz);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {

                count++;
                if (count == 1) {
                    itzulpena = rs.getString("euskaraz");
                } else {
                    itzulpena = itzulpena + "," + rs.getString("euskaraz");
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return itzulpena;
    }

    public String itzuli(Hizkuntza hizkuntza, String hitza) {
        String itzulpena = "";
        int count = 0;
        if (hizkuntza == ES) {

            String sql = "SELECT euskaraz, gazteleraz FROM Terminoak WHERE gazteleraz = ? ";
            try (Connection conn = konektatu(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, hitza);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {

                    count++;
                    if (count == 1) {
                        itzulpena = rs.getString("euskaraz");
                    } else {
                        itzulpena = itzulpena + "," + rs.getString("euskaraz");
                    }
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        } else if (hizkuntza == EU) {

            String sql = "SELECT euskaraz, gazteleraz FROM Terminoak WHERE euskaraz = ? ";
            try (Connection conn = konektatu(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, hitza);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {

                    count++;
                    if (count == 1) {
                        itzulpena = rs.getString("gazteleraz");
                    } else {
                        itzulpena = itzulpena + "," + rs.getString("gazteleraz");
                    }
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        }
        return itzulpena;
    }

    public Terminoa hitzaBilatu(String x) {
        String sql = "SELECT * FROM Terminoak WHERE euskaraz =? OR gazteleraz = ?";
        Terminoa t = new Terminoa();
        try (Connection conn = konektatu(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, x);
            pstmt.setString(2, x);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                t = new Terminoa(rs.getInt("id"), rs.getString("euskaraz"), rs.getString("gazteleraz"));
                System.out.println(t);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return t;
    }

    /**
     * alfabetuko hizki guztiekin
     */
    public void alfabetikokiInprimatuHizkiGuztiak() {
        String sql = "SELECT * FROM Terminoak ORDER BY euskaraz";
        ArrayList<Character> abc = new ArrayList<>();
        for (char letra = 'a'; letra <= 'z'; letra++) {
            abc.add(letra);
        }
        try (Connection conn = konektatu(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            
            
                ArrayList<Terminoa> terminoak = new ArrayList<>();
                if(rs.next()){
                for (int i = 0; i < abc.size(); i++) {
                    
                    System.out.println(abc.get(i));
                    System.out.println("____");
                
                    if (rs.getString("euskaraz").charAt(0) == abc.get(i)) {
                        
                        Terminoa t = new Terminoa(rs.getInt("id"), rs.getString("euskaraz"), rs.getString("gazteleraz"));
                        terminoak.add(t);
                        
                        System.out.println(terminoak);

                    } else {
                        System.out.println("Ez dago halakorik");

                    }
                
                }
                
                }      
        

    }   catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }

    /**
     * bakarrik hiztegian dauzkagun hitzekin
     */
    public void alfabetikokiInprimatu() {
        String sql = "SELECT * FROM Terminoak ORDER BY euskaraz";
        char unekoHizkia = ' ';

        try (Connection conn = konektatu(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                if (rs.getString("euskaraz").charAt(0) != unekoHizkia) {//hizki aldaketa
                    unekoHizkia = rs.getString("euskaraz").charAt(0);
                    System.out.println(unekoHizkia);
                    System.out.println("____");
                    Terminoa t = new Terminoa(rs.getInt("id"), rs.getString("euskaraz"), rs.getString("gazteleraz"));
                    System.out.println(t);
                } else {
                    Terminoa t = new Terminoa(rs.getInt("id"), rs.getString("euskaraz"), rs.getString("gazteleraz"));
                    System.out.println(t);
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

}
