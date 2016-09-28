package com.bvmc.rdf.h2;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Date;

import org.h2.tools.DeleteDbFiles;

import com.bvmc.rdf.dto.TranslationDTO;
import com.bvmc.rdf.geonames.Country;

public class H2Database {

    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:~/bvmcLocations";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";
    
    public static void deleteDatabase() throws SQLException {
        DeleteDbFiles.execute("~", "bvmcLocations", true);
    }
    
    public static void createBibliographicRecordTable() throws SQLException {
        Connection connection = getDBConnection();
        PreparedStatement createPreparedStatement = null;

        String CreateQuery = "CREATE TABLE BibliographicRecord("
                + "idManifestation varchar(255), "
                + "place varchar(255), "
                + "entity varchar(255), "
                + "date varchar(255), "
                + "publication varchar(255))";
        try {
            connection.setAutoCommit(false);
           
            createPreparedStatement = connection.prepareStatement(CreateQuery);
            createPreparedStatement.executeUpdate();
            createPreparedStatement.close();
           
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }
    
    public static void createGeonamesTable() throws SQLException {
        Connection connection = getDBConnection();
        PreparedStatement createPreparedStatement = null;

        String CreateQuery = "CREATE TABLE Geoname("
                + "idGeoname varchar(255), "
                + "country varchar(255), "
                + "name varchar(255), "
                + "original varchar(255))";
        try {
            connection.setAutoCommit(false);
           
            createPreparedStatement = connection.prepareStatement(CreateQuery);
            createPreparedStatement.executeUpdate();
            createPreparedStatement.close();
           
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }
    
    public static void createFKGeonamesTable() throws SQLException {
        Connection connection = getDBConnection();
        PreparedStatement createPreparedStatement = null;

        String CreateQuery = "CREATE TABLE BibliographicGeoname("
                + "idGeoname varchar(255), "
                + "idManifestation varchar(255))";
        try {
            connection.setAutoCommit(false);
           
            createPreparedStatement = connection.prepareStatement(CreateQuery);
            createPreparedStatement.executeUpdate();
            createPreparedStatement.close();
           
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }
    
    public static void createFKTranslationTable() throws SQLException {
        Connection connection = getDBConnection();
        PreparedStatement createPreparedStatement = null;

        String CreateQuery = "CREATE TABLE BibliographicTranslation("
                + "idTranslation varchar(255), "
                + "idManifestation varchar(255))";
        try {
            connection.setAutoCommit(false);
           
            createPreparedStatement = connection.prepareStatement(CreateQuery);
            createPreparedStatement.executeUpdate();
            createPreparedStatement.close();
           
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }
    
    public static void insertBibliographicTranslation(String idManifestation, String idTranslation) throws SQLException {
        Connection connection = getDBConnection();
        PreparedStatement insertPreparedStatement = null;
    
        String InsertQuery = "INSERT INTO BibliographicTranslation" + "(idManifestation, idTranslation) values" + "(?,?)";
        try {
            connection.setAutoCommit(false);
           
            insertPreparedStatement = connection.prepareStatement(InsertQuery);
            insertPreparedStatement.setString(1, idManifestation);
            insertPreparedStatement.setString(2, idTranslation);
            insertPreparedStatement.executeUpdate();
            insertPreparedStatement.close();
           
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }
    
    // H2 SQL Prepared Statement Example
    public static void insertBibliographicRecord(String idManifestation, String place, String entity, String date, String publication) throws SQLException {
        Connection connection = getDBConnection();
        PreparedStatement insertPreparedStatement = null;
    
        String InsertQuery = "INSERT INTO BibliographicRecord" + "(idManifestation, place, entity, date, publication) values" + "(?,?,?,?,?)";
        try {
            connection.setAutoCommit(false);
           
            insertPreparedStatement = connection.prepareStatement(InsertQuery);
            insertPreparedStatement.setString(1, idManifestation);
            insertPreparedStatement.setString(2, place);
            insertPreparedStatement.setString(3, entity);
            insertPreparedStatement.setString(4, date);
            insertPreparedStatement.setString(5, publication);
            insertPreparedStatement.executeUpdate();
            insertPreparedStatement.close();
           
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }
    
    public static void insertBibliographicGeoname(String idManifestation, String idgeoname) throws SQLException {
        Connection connection = getDBConnection();
        PreparedStatement insertPreparedStatement = null;
    
        String InsertQuery = "INSERT INTO BibliographicGeoname" + "(idManifestation, idgeoname) values" + "(?,?)";
        try {
            connection.setAutoCommit(false);
           
            insertPreparedStatement = connection.prepareStatement(InsertQuery);
            insertPreparedStatement.setString(1, idManifestation);
            insertPreparedStatement.setString(2, idgeoname);
            insertPreparedStatement.executeUpdate();
            insertPreparedStatement.close();
           
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }
    
    public static void insertGeoname(String idgeoname, String place, String country, String original) throws SQLException {
        Connection connection = getDBConnection();
        PreparedStatement insertPreparedStatement = null;
    
        String InsertQuery = "INSERT INTO Geoname" + "(idgeoname, name, country, original) values" + "(?,?,?,?)";
        try {
            connection.setAutoCommit(false);
           
            insertPreparedStatement = connection.prepareStatement(InsertQuery);
            insertPreparedStatement.setString(1, idgeoname);
            insertPreparedStatement.setString(2, place);
            insertPreparedStatement.setString(3, country);
            insertPreparedStatement.setString(4, original.trim());
            insertPreparedStatement.executeUpdate();
            insertPreparedStatement.close();
           
            connection.commit();
            System.out.println("Inserted " + place + " in Geonames table.");
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }
    
    public static String checkGeoname(String query) throws SQLException {
        Connection connection = getDBConnection();
        PreparedStatement selectPreparedStatement = null;
        String idGeoname = "";
        
        String SelectQuery = "select * from geoname where lower(original)=lower('"+ query +"') ";
        
        String countryCode = Country.getCode(query);
        if(!countryCode.isEmpty())
            SelectQuery += " and lower(country) = lower('" + Country.getCode(query) + "')";
        
        try {
            System.out.println("checkGeoname query: " + SelectQuery);
            selectPreparedStatement = connection.prepareStatement(SelectQuery);
            ResultSet rs = selectPreparedStatement.executeQuery();
            while (rs.next()) {
                System.out.println("checkGeoname found Id: "+rs.getString("idgeoname"));
                idGeoname = rs.getString("idgeoname");
                break;
            }
            selectPreparedStatement.close();
           
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        
        return idGeoname;
    }
    
    public static TranslationDTO checkTranslation(String query) throws SQLException {
        Connection connection = getDBConnection();
        PreparedStatement selectPreparedStatement = null;
        TranslationDTO translation = new TranslationDTO();
        
        String SelectQuery = "select * from translation where lower(latin)=lower('"+ query.trim() +"') ";
        
        try {
            System.out.println("checkTranslation query: " + SelectQuery);
            selectPreparedStatement = connection.prepareStatement(SelectQuery);
            ResultSet rs = selectPreparedStatement.executeQuery();
            while (rs.next()) {
                System.out.println("check translation "+rs.getString("translation"));
                translation.setText(rs.getString("translation"));
                translation.setId(rs.getInt("id"));
                break;
            }
            selectPreparedStatement.close();
           
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        
        return translation;
    }

    public static void showTable(String table, String orderBy) throws SQLException {
        Connection connection = getDBConnection();
        PreparedStatement selectPreparedStatement = null;
        String SelectQuery = "select * from " + table + " order by " + orderBy;
        
        PrintWriter pw = null;

        try {
        	File file = new File(System.getProperty("user.home") + "/" + table + "_" + new Date().toString() + ".txt");
            FileWriter fw = new FileWriter(file, true);
            pw = new PrintWriter(fw);
            
            ResultSet rst=connection.prepareStatement("SELECT COUNT(*) FROM " + table).executeQuery();
            if(rst.next()) System.out.println("Total:" + rst.getInt(1));
        
            System.out.println("select * from " + table);
            selectPreparedStatement = connection.prepareStatement(SelectQuery);
            ResultSet rs = selectPreparedStatement.executeQuery();
            
            while (rs.next()) {
            
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnCount = rsmd.getColumnCount();
    
                String rowString = "";
                // The column count starts from 1
                for (int i = 1; i <= columnCount; i++ ) {
                  String name = rsmd.getColumnName(i);
                  
                  if(rsmd.getColumnType(i) == Types.VARCHAR)
                      rowString += name + ": " + rs.getString(name) + " ";
                }
                System.out.println("rowString:" + rowString);
                pw.println(rowString);
            }
            selectPreparedStatement.close();
           
            connection.commit();
            System.out.println("Finish select * from " + table);
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
            
            if (pw != null) {
                pw.close();
            }
        }
    }
    
    public static void createTranslationTable() throws SQLException {
        Connection connection = getDBConnection();
        //DeleteDbFiles.execute("~", "TRANSLATION", true);
        
        Statement stmt = null;
        try {
            connection.setAutoCommit(false);
           
            stmt = connection.createStatement();
            stmt.execute("CREATE TABLE TRANSLATION(id int primary key, latin varchar(255), translation varchar(255), lpnUri varchar(255), countrycode varchar(255))");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(1, 'Matriti', 'Madrid (Spain)', 'http://rbms.info/lpn/m/madridii/', 'es')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(2, 'Murciae', 'Murcia', '', 'es')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(3, 'Tudelae', 'Tudela', '', 'es')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(4, 'Pampilonae', 'Pamplona', '', 'es')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(5, 'Pompelonae', 'Pamplona', '', 'es')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(6, 'Malacae', 'Malaga', '', 'es')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(7, 'Burgis', 'Burgos', '', 'es')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(8, 'Francofurti ad Oderam', 'Frankfurt an der Oder (Germany)', 'http://rbms.info/lpn/f/francofurti-ad-oderam/', 'de')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(9, 'Francofurti ad Moenum', 'Frankfurt am Main (Germany)', 'http://rbms.info/lpn/f/francofurti-ad-moenum/', 'de')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(10, 'Francofurti', 'Frankfurt am Main (Germany)', 'http://rbms.info/lpn/f/francofurti-ad-moenum/', 'de')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(11, 'Romae', 'Rome (Italy)', 'http://rbms.info/lpn/r/romae/', 'it')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(12, 'Geneuae', 'Geneva (Switzerland)', 'http://rbms.info/lpn/g/geneuae/', 'sz')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(13, 'Genevae', 'Geneva (Switzerland)', 'http://rbms.info/lpn/g/geneuae/', 'sz')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(14, 'Valentiae', 'Valence (Drôme, France)', 'http://rbms.info/lpn/v/valentiae/', 'fr')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(15, 'Valentiae Edetanorum', 'Valencia (Spain)', 'http://rbms.info/lpn/v/valentiae-edetanorum/', 'es')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(16, 'Veronae', 'Verona (Italy)', 'http://rbms.info/lpn/v/veronae/', 'it')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(17, 'Stutgardiae', 'Stuttgart (Germany)', 'http://rbms.info/lpn/s/stutgardiae/', 'de')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(18, 'Stuttgartiae', 'Stuttgart (Germany)', 'http://rbms.info/lpn/s/stutgardiae/', 'de')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(19, 'Coloniae Agrippinae', 'Cologne (Germany)', 'http://rbms.info/lpn/c/coloniae-agrippinae/', 'de')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(20, 'Coloniae', 'Cologne (Germany)', 'http://rbms.info/lpn/c/coloniae-agrippinae/', 'de')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(21, 'Florentiae', 'Florence (Italy)', 'http://rbms.info/lpn/f/florentiae/', 'it')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(22, 'Marpurgi', 'Marburg (Germany)', 'http://rbms.info/lpn/m/marpurgi/', 'de')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(23, 'Lipsiae', 'Leipzig (Germany)', 'http://rbms.info/lpn/l/lipsiae/', 'de')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(24, 'Lemgouiae', 'Lemgo (Germany)', 'http://rbms.info/lpn/l/lemgouiae/', 'de')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(25, 'Lemgoviae', 'Lemgo (Germany)', 'http://rbms.info/lpn/l/lemgouiae/', 'de')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(26, 'Lubecae', 'Lübeck (Germany)', 'http://rbms.info/lpn/l/lubecae/', 'de')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(27, 'Norimbergae', 'Nuremberg (Germany)', 'http://rbms.info/lpn/n/norimbergae/', 'de')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(28, 'Noribergae', 'Nuremberg (Germany)', 'http://rbms.info/lpn/n/norimbergae/', 'de')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(29, 'Parmae', 'Parma (Italy)', 'http://rbms.info/lpn/p/parmae/', 'it')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(30, 'Placentiae', 'Piacenza (Italy)', 'http://rbms.info/lpn/p/placentiae/', 'it')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(31, 'Lucae', 'Lucca (Italy)', 'http://rbms.info/lpn/l/lucae/', 'it')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(32, 'Basileae', 'Basel (Switzerland)', 'http://rbms.info/lpn/b/basileae/', 'sz')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(33, 'Coloniae Munatianae', 'Basel (Switzerland)', 'http://rbms.info/lpn/b/basileae/', 'sz')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(34, 'Ienae', 'Jena (Germany)', 'http://rbms.info/lpn/i/ienae/', 'de')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(35, 'Jenae', 'Jena (Germany)', 'http://rbms.info/lpn/i/ienae/', 'de')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(36, 'Bononiae', 'Bologna (Italy)', 'http://rbms.info/lpn/b/bononiae/', 'it')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(37, 'Ripae', 'Riva (Italy)', 'http://rbms.info/lpn/r/ripae/', 'it')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(38, 'Garnatae', 'Granada (Spain)', 'http://rbms.info/lpn/g/garnatae/', 'es')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(39, 'Granatae', 'Granada (Spain)', 'http://rbms.info/lpn/g/garnatae/', 'es')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(40, 'Brixiae', 'Brescia (Italy)', 'http://rbms.info/lpn/b/brixiae/', 'it')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(41, 'Ferrariae', 'Ferrara (Italy)', 'http://rbms.info/lpn/f/ferrariae/', 'it')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(42, 'Argentorati', 'Strassburg', 'http://rbms.info/lpn/a/argentorati/', 'fr')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(43, 'Argentinae', 'Strassburg', 'http://rbms.info/lpn/a/argentorati/', 'fr')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(44, 'Venetiis', 'Venice (Italy)', 'http://rbms.info/lpn/v/venetiis/', 'it')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(45, 'Venetijs', 'Venice (Italy)', 'http://rbms.info/lpn/v/venetiis/', 'it')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(46, 'Venetia', 'Venice (Italy)', 'http://rbms.info/lpn/v/venetiis/', 'it')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(47, 'Giessae', 'Giessen (Hesse, Germany)', 'http://rbms.info/lpn/g/giessae/', 'de')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(48, 'Gissae', 'Giessen (Hesse, Germany)', 'http://rbms.info/lpn/g/giessae/', 'de')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(49, 'Hannouerae', 'Hannover (Germany)', 'http://rbms.info/lpn/h/hannoverae/', 'de')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(50, 'Hannoverae', 'Hannover (Germany)', 'http://rbms.info/lpn/h/hannoverae/', 'de')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(51, 'Antuerpiae', 'Antwerp (Belgium)', 'http://rbms.info/lpn/a/antuerpiae/', 'be')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(52, 'Lutetiae Parisiorum', 'Paris (France)', 'http://rbms.info/lpn/l/lutetiae-parisiorum/', 'fr')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(53, 'Parrhisiis', 'Paris (France)', 'http://rbms.info/lpn/l/lutetiae-parisiorum/', 'fr')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(54, 'Lutetiae', 'Paris (France)', 'http://rbms.info/lpn/l/lutetiae-parisiorum/', 'fr')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(55, 'Parisiis', 'Paris (France)', 'http://rbms.info/lpn/l/lutetiae-parisiorum/', 'fr')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(56, 'Salmanticae', 'Salamanca (Spain)', 'http://rbms.info/lpn/s/salmanticae/', 'es')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(57, 'Salmâtice', 'Salamanca (Spain)', 'http://rbms.info/lpn/s/salmanticae/', 'es')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(58, 'Cremonae', 'Cremona (Italy)', 'http://rbms.info/lpn/c/cremonae/', 'it')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(59, 'Bruxellis', 'Brussels (Belgium)', 'http://rbms.info/lpn/b/bruxellis/', 'be')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(60, 'Bruxellae', 'Brussels (Belgium)', 'http://rbms.info/lpn/b/bruxellis/', 'be')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(61, 'Conchae', 'Cuenca (Spain)', 'http://rbms.info/lpn/c/conchae/', 'es')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(62, 'Hamburgi', 'Hamburg (Germany)', 'http://rbms.info/lpn/h/hamburgi/', 'de')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(63, 'Genuae', 'Genoa (Italy)', 'http://rbms.info/lpn/g/genuae/', 'it')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(64, 'Coloniae Allobrogum', 'Genoa (Italy)', 'http://rbms.info/lpn/g/genuae/', 'it')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(65, 'Tarracone', 'Tarragona (Spain)', 'http://rbms.info/lpn/t/tarracone/', 'es')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(66, 'Tarraconae', 'Tarragona (Spain)', 'http://rbms.info/lpn/t/tarracone/', 'es')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(67, 'Bergomi', 'Bergamo (Italy)', 'http://rbms.info/lpn/b/bergomi/', 'it')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(68, 'Lugduni', 'Lyon (France)', 'http://rbms.info/lpn/l/lugduni/', 'fr')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(69, 'Lugduni Batauorum', 'Leiden (Netherlands)', 'http://rbms.info/lpn/l/lugduni-batauorum/', 'nl')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(70, 'Amstelodami', 'Amsterdam (Netherlands)', 'http://rbms.info/lpn/a/amstelodami/', 'nl')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(71, 'Amstelaedami', 'Amsterdam (Netherlands)', 'http://rbms.info/lpn/a/amstelodami/', 'nl')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(72, 'Amsterdami', 'Amsterdam (Netherlands)', 'http://rbms.info/lpn/a/amstelodami/', 'nl')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(73, 'Eborae', 'Evora (Portugal)', 'http://rbms.info/lpn/e/eborae/', 'pt')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(74, 'Barcinonae', 'Barcelona (Spain)', 'http://rbms.info/lpn/b/barcinonae/', 'es')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(75, 'Barcinone', 'Barcelona (Spain)', 'http://rbms.info/lpn/b/barcinonae/', 'es')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(76, 'Barchinonae', 'Barcelona (Spain)', 'http://rbms.info/lpn/b/barcinonae/', 'es')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(77, 'Turiasonae', 'Tarazona de Aragón (Spain)', 'http://rbms.info/lpn/t/turiasonae/', 'es')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(78, 'Ingolstadii', 'Ingolstadt (Germany)', 'http://rbms.info/lpn/i/ingolstadii/', 'de')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(79, 'Cordubae', 'Córdoba (Spain)', 'http://rbms.info/lpn/c/cordubae/', 'es')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(80, 'Cordubae Tucumanorum', 'Córdoba (Spain)', 'http://rbms.info/lpn/c/cordubae/', 'es')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(81, 'Cordova', 'Córdoba (Spain)', 'http://rbms.info/lpn/c/cordubae/', 'es')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(82, 'Cordoua', 'Córdoba (Spain)', 'http://rbms.info/lpn/c/cordubae/', 'es')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(83, 'Thymnae a Campo', 'Medina del Campo (Spain)', 'http://rbms.info/lpn/m/methymnae-a-campo/', 'es')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(84, 'Veneunt Methinae Campi', 'Medina del Campo (Spain)', 'http://rbms.info/lpn/m/methymnae-a-campo/', 'es')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(85, 'Compluti', 'Alcalá de Henares (Spain)', 'http://rbms.info/lpn/c/compluti/', 'es')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(86, 'Côpluti', 'Alcalá de Henares (Spain)', 'http://rbms.info/lpn/c/compluti/', 'es')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(87, 'Palmae Balearis', 'Palma (Spain)', 'http://rbms.info/lpn/p/palmae-balearis/', 'es')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(88, 'Palmae', 'Palma (Spain)', 'http://rbms.info/lpn/p/palmae-balearis/', 'es')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(89, 'Pintiae', 'Valladolid (Spain)', 'http://rbms.info/lpn/p/pintiae/', 'es')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(90, 'Vallesoleti', 'Valladolid (Spain)', 'http://rbms.info/lpn/p/pintiae/', 'es')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(91, 'Vallis-Oleti', 'Valladolid (Spain)', 'http://rbms.info/lpn/p/pintiae/', 'es')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(92, 'Hispali', 'Seville (Spain)', 'http://rbms.info/lpn/h/hispali/', 'es')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(93, 'Seuilla', 'Seville (Spain)', 'http://rbms.info/lpn/h/hispali/', 'es')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(94, 'Caesaraugustae', 'Zaragoza (Spain)', 'http://rbms.info/lpn/c/caesaraugustae/', 'es')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(95, 'Londini', 'London (England)', 'http://rbms.info/lpn/l/londini/', 'enk')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(96, 'Toleti', 'Toledo (Spain)', 'http://rbms.info/lpn/t/toleti/', 'es')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(97, 'Louanii', 'Louvain (Belgium)', 'http://rbms.info/lpn/l/louanii/', 'be')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(98, 'Hagae-Comitis', 'Hague (Netherlands)', 'http://rbms.info/lpn/h/hagae-comitis/', 'nl')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(99, 'Tolosae Tectosagum', 'Toulouse (France)', 'http://rbms.info/lpn/t/tolosae-tectosagum/', 'fr')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(100, 'Vindobonae', 'Vienna (Austria)', 'http://rbms.info/lpn/v/vindobonae/', 'au')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(101, 'Vienae Austria', 'Vienna (Austria)', 'http://rbms.info/lpn/v/vindobonae/', 'au')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(102, 'Conimbricae', 'Coimbra (Portugal)', 'http://rbms.info/lpn/c/conimbricae/', 'pt')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(103, 'Friburgi Brisgouiae', 'Freiburg im Breisgau (Germany)', 'http://rbms.info/lpn/f/friburgi-brisgouiae/', 'de')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(104, 'Friburgo Brisgouiae', 'Freiburg im Breisgau (Germany)', 'http://rbms.info/lpn/f/friburgi-brisgouiae/', 'de')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(105, 'Hagenoae', 'Haguenau (France)', 'http://rbms.info/lpn/h/hagenoae/', 'fr')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(106, 'Haganoae', 'Haguenau (France)', 'http://rbms.info/lpn/h/hagenoae/', 'fr')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(107, 'Anconae', 'Ancona (Italy)', 'http://rbms.info/lpn/a/anconae/', 'it')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(108, 'Pisauri', 'Pesaro (Italy)', 'http://rbms.info/lpn/p/pisauri/', 'it')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(109, 'Neapoli', 'Naples (Italy)', 'http://rbms.info/lpn/n/neapoli/', 'it')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(110, 'Mantuae', 'Mantua (Italy)', 'http://rbms.info/lpn/m/mantuae/', 'it')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(111, 'Moguntiae', 'Mainz (Rhineland-Palatinate, Germany)', 'http://rbms.info/lpn/m/moguntiae/', 'de')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(112, 'Perusiae', 'Perugia (Italy)', 'http://rbms.info/lpn/p/perusiae/', 'it')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(113, 'Augustae Vindelicorum', 'Augsburg (Germany)', 'http://rbms.info/lpn/a/augustae-vindelcorum/', 'de')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(114, 'Kiloni', 'Kiel (Germany)', 'http://rbms.info/lpn/k/kiloni/', 'de')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(115, 'Papiae', 'Pavia (Italy)', 'http://rbms.info/lpn/p/papiae/', 'it')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(116, 'Vlmae', 'Ulm (Germany)', 'http://rbms.info/lpn/v/vlmae/', 'de')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(117, 'Ulmae Suevorum', 'Ulm (Germany)', 'http://rbms.info/lpn/v/vlmae/', 'de')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(118, 'Caesenae', 'Cesena (Italy)', 'http://rbms.info/lpn/c/caesenae/', 'it')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(119, 'Lausannae', 'Lausanne (Switzerland)', 'http://rbms.info/lpn/l/lausannae/', 'sz')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(120, 'Goettingae', 'Göttingen (Germany)', 'http://rbms.info/lpn/g/goettingae/', 'de')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(121, 'Gottingae', 'Göttingen (Germany)', 'http://rbms.info/lpn/g/goettingae/', 'de')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(122, 'Bernae', 'Bern (Switzerland)', 'http://rbms.info/lpn/b/bernae/', 'sz')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(123, 'Pragae', 'Prague (Czech Republic)', 'http://rbms.info/lpn/p/pragae/', 'cz')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(124, 'Campaniae', 'Campagna (Italy)', 'http://rbms.info/lpn/c/campaniae/', 'it')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(125, 'Antverpiae', 'Antwerp (Belgium)', 'http://rbms.info/lpn/a/antuerpiae/', 'be')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(126, 'Brunswici', 'Braunschweig (Germany)', 'http://rbms.info/lpn/b/brunswici/', 'de')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(127, 'Brunsvigae', 'Braunschweig (Germany)', 'http://rbms.info/lpn/b/brunswici/', 'de')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(128, 'Bracciani', 'Bracciano (Italy)', 'http://rbms.info/lpn/b/bracciani/', 'it')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(129, 'Hafniae', 'Copenhagen (Denmark)', 'http://rbms.info/lpn/h/hafniae/', 'dk')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(130, 'Francofurti ad Moenum', 'Frankfurt am Main (Germany)', 'http://rbms.info/lpn/f/francofurti-ad-moenum/', 'de')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(131, 'Francofurdi', 'Frankfurt am Main (Germany)', 'http://rbms.info/lpn/f/francofurti-ad-moenum/', 'de')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(132, 'Vlysipone', 'Lisbon (Portugal)', 'http://rbms.info/lpn/v/vlysipone/', 'pt')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(133, 'Olisipone', 'Lisbon (Portugal)', 'http://rbms.info/lpn/v/vlysipone/', 'pt')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(134, 'Olyssipponae', 'Lisbon (Portugal)', 'http://rbms.info/lpn/v/vlysipone/', 'pt')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(135, 'Monachii', 'Munich (Germany)', 'http://rbms.info/lpn/m/monachii/', 'de')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(136, 'Aureliae', 'Orléans (France)', 'http://rbms.info/lpn/a/aureliae/', 'fr')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(137, 'Aurelianae', 'Orléans (France)', 'http://rbms.info/lpn/a/aureliae/', 'fr')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(138, 'Heidelbergae', 'Heidelberg (Germany)', 'http://rbms.info/lpn/h/heidelbergae/', 'de')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(139, 'Franekerae', 'Franeker (Netherlands)', 'http://rbms.info/lpn/f/franekerae/', 'nl')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(140, 'Çesarauguste', 'Zaragoza (Spain)', 'http://rbms.info/lpn/c/caesaraugustae/', 'es')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(141, 'Vicentiae', 'Vicenza (Italy)', 'http://rbms.info/lpn/v/vicentiae/', 'it')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(142, 'Vincentie', 'Vicenza (Italy)', 'http://rbms.info/lpn/v/vicentiae/', 'it')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(143, 'Nuremberge', 'Nuremberg (Germany)', 'http://rbms.info/lpn/n/norimbergae/', 'de')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(144, 'Segoviae', 'Segovia', '', 'es')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(145, 'Taurini', 'Turin (Italy)', 'http://rbms.info/lpn/t/taurini/', 'it')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(146, 'Duaci', 'Douai (France)', 'http://rbms.info/lpn/d/duaci/', 'fr')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(147, 'Parisis', 'Paris (France)', 'http://rbms.info/lpn/l/lutetiae-parisiorum/', 'fr')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(148, 'Lugduñ', 'Lyon (France)', 'http://rbms.info/lpn/l/lugduni/', 'fr')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(149, 'Metina', 'Medina del Campo (Spain)', 'http://rbms.info/lpn/m/methymnae-a-campo/', 'es')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(150, 'Tiguri', 'Zurich (Switzerland)', 'http://rbms.info/lpn/t/tiguri/', 'sz')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(151, 'Bassani', 'Bassano del Grappa (Italy)', 'http://rbms.info/lpn/b/bassani/', 'it')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(152, 'Spirae', 'Speyer (Germany)', 'http://rbms.info/lpn/s/spirae/', 'de')");
            stmt.execute("INSERT INTO TRANSLATION(id, latin, translation, lpnUri, countrycode) VALUES(153, 'Spira', 'Speyer (Germany)', 'http://rbms.info/lpn/s/spirae/', 'de')");
            
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }
    
    protected static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER,
                    DB_PASSWORD);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }
    
    public static void createDatabaseScheme() throws SQLException{
        H2Database.createBibliographicRecordTable();
        H2Database.createFKGeonamesTable();
        H2Database.createFKTranslationTable();
        H2Database.createGeonamesTable();
        H2Database.createTranslationTable();
    }
}
