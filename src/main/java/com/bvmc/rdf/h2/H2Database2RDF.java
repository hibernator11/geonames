package com.bvmc.rdf.h2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.io.FileUtils;

import com.bvmc.rdf.jena.BVMCPB;
import com.bvmc.rdf.jena.PublicationStatement;
import com.bvmc.rdf.jena.Translation;
import com.bvmc.rdf.jena.WikidataService;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.OWL;
import com.hp.hpl.jena.vocabulary.RDF;

public class H2Database2RDF extends H2Database{
	
	public static void generateRDF() throws SQLException{
		downloadGeonamesRDFFiles();
		loopBibliographicRecords();
		loopTranslations();
		loopBibliographicGeoname();
		loopBibliographicTranslation();
	}

    private static void downloadGeonamesRDFFiles() throws SQLException {
    	Connection connection = getDBConnection();
    	PreparedStatement selectPreparedStatement = null;
    	String SelectQuery = "select distinct idGeoname from geoname";
    	try {
    		
    		selectPreparedStatement = connection.prepareStatement(SelectQuery);
	        ResultSet rs = selectPreparedStatement.executeQuery();
	        
	        while (rs.next()) {
		        File f = new File(System.getProperty("user.home") + "/geonamesrdf/geonamesfiles/" + rs.getString("idGeoname") + ".rdf");
				System.out.println("Downloading " + f.getAbsolutePath());
				
				FileUtils.copyURLToFile(new URL("http://sws.geonames.org/" + rs.getString("idGeoname") + "/about.rdf"), f);
		    }
	        selectPreparedStatement.close();
	    } catch (SQLException e) {
	        System.out.println("Exception Message " + e.getLocalizedMessage());
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        connection.close();
	    }
    }
    
    private static void loopBibliographicRecords() throws SQLException {
    	Connection connection = getDBConnection();
    	PreparedStatement selectPreparedStatement = null;

    	String SelectQuery = "select * from BibliographicRecord";
    	
    	try {
    		selectPreparedStatement = connection.prepareStatement(SelectQuery);
	        ResultSet rs = selectPreparedStatement.executeQuery();
	        while (rs.next()) {
	        	String idManifestation = rs.getString("idmanifestation");
	        	String date = rs.getString("date");
	        	String description = rs.getString("publication");
	        	
	        	PublicationStatement pb = new PublicationStatement(idManifestation, date, description);
	        	pb.generateRDF();
		    }
	        selectPreparedStatement.close();
	       
	    } catch (SQLException e) {
	        System.out.println("Exception Message " + e.getLocalizedMessage());
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        connection.close();
	    }
    }
    
    private static void loopTranslations() throws SQLException {
    	Connection connection = getDBConnection();
    	PreparedStatement selectPreparedStatement = null;

    	String SelectQuery = "select * from translation";
    	
    	try {
    		selectPreparedStatement = connection.prepareStatement(SelectQuery);
	        ResultSet rs = selectPreparedStatement.executeQuery();
	        while (rs.next()) {
	        	String id = rs.getString("id");
	        	String latin = rs.getString("latin");
	        	String englishText = rs.getString("translation");
	        	String lpnuri = rs.getString("lpnuri");
	        	String countrycode = rs.getString("countrycode");
	        	
	        	Translation translation = new Translation(id,englishText,latin, countrycode, lpnuri);
	        	translation.generateRDF();
		    }
	        selectPreparedStatement.close();
	       
	    } catch (SQLException e) {
	        System.out.println("Exception Message " + e.getLocalizedMessage());
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        connection.close();
	    }
    }
    
    private static void loopBibliographicGeoname() throws SQLException {
    	Connection connection = getDBConnection();
    	PreparedStatement selectPreparedStatement = null;
     	String SelectQuery = "select * from BibliographicGeoname";
    	
    	try {
    		
            Model model = ModelFactory.createDefaultModel();
            
            // set a namespace prefix
            model.setNsPrefix("rdf", RDF.getURI());
            model.setNsPrefix("rdac", "http://rdaregistry.info/Elements/c/#");
            model.setNsPrefix("rdam", "http://rdaregistry.info/Elements/m/#");
            model.setNsPrefix("bvmc-ps", BVMCPB.getURI());
    		
    		selectPreparedStatement = connection.prepareStatement(SelectQuery);
	        ResultSet rs = selectPreparedStatement.executeQuery();
	        while (rs.next()) {
	        	System.out.println("found idmanifestation: "+rs.getString("idmanifestation") );
	        	System.out.println("found idgeoname: "+ rs.getString("idgeoname"));

	        	String idManifestation = rs.getString("idmanifestation");
	        	String idgeoname = rs.getString("idgeoname");
	        	
                Resource publicationStatement = model.createResource("http://data.cervantesvirtual.com/publicationStatement/" + idManifestation);
                
                Resource geoname = model.createResource("http://sws.geonames.org/" + idgeoname + "/");
                model.add(publicationStatement, BVMCPB.hasPlaceOfPublication, geoname);
                
                String wikidataUri = WikidataService.getWikidataUri(idgeoname);
                if(wikidataUri!=null && !wikidataUri.isEmpty()){
                	 Resource rWikidata = model.createResource(wikidataUri);
                     model.add(geoname, OWL.sameAs, rWikidata);
                }
            }
	        
	        selectPreparedStatement.close();
	        
            StringWriter out = new StringWriter();
            model.write(out, "RDF/XML-ABBREV");
            
            model.write(System.out, "RDF/XML-ABBREV");
            try {
                PrintWriter printFile = new PrintWriter(new BufferedWriter(new FileWriter(System.getProperty("user.home") + "/geonamesrdf/bibliographicGeonames.rdf", true)));
                printFile.println(out.toString());
                printFile.close();
            } catch (IOException e) {
            	System.out.println("Exception Message " + e.getLocalizedMessage());
            }
	       
	    } catch (SQLException e) {
	        System.out.println("Exception Message " + e.getLocalizedMessage());
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        connection.close();
	    }
    }
    
    private static void loopBibliographicTranslation() throws SQLException {
    	Connection connection = getDBConnection();
    	PreparedStatement selectPreparedStatement = null;
     	String SelectQuery = "select * from BibliographicTranslation";
    	
    	try {
    		
            Model model = ModelFactory.createDefaultModel();
            
            // set a namespace prefix
            model.setNsPrefix("rdf", RDF.getURI());
            model.setNsPrefix("rdac", "http://rdaregistry.info/Elements/c/#");
            model.setNsPrefix("rdam", "http://rdaregistry.info/Elements/m/#");
            model.setNsPrefix("bvmc-ps", BVMCPB.getURI());
    		
    		System.out.println("query: " + SelectQuery);
    		selectPreparedStatement = connection.prepareStatement(SelectQuery);
	        ResultSet rs = selectPreparedStatement.executeQuery();
	        while (rs.next()) {
	        	System.out.println("found idmanifestation: "+rs.getString("idmanifestation") );
	        	System.out.println("found idtranslation: "+ rs.getString("idtranslation"));

	        	String idManifestation = rs.getString("idmanifestation");
	        	String idtranslation = rs.getString("idtranslation");
	        	
                Resource publicationStatement = model.createResource("http://data.cervantesvirtual.com/publicationStatement/" + idManifestation);
                
                Resource translation = model.createResource("http://data.cervantesvirtual.com/translation/" + idtranslation);
                model.add(publicationStatement, BVMCPB.hasTranslation, translation);
            }
	        
	        selectPreparedStatement.close();
	        
	        StringWriter out = new StringWriter();
            model.write(out, "RDF/XML-ABBREV");
            model.write(System.out, "RDF/XML-ABBREV");
            try {
                PrintWriter printFile = new PrintWriter(new BufferedWriter(new FileWriter(System.getProperty("user.home") + "/geonamesrdf/bibliographictranslation.rdf", true)));
                printFile.println(out.toString());
                printFile.close();
            } catch (IOException e) {
            	System.out.println("Exception Message " + e.getLocalizedMessage());
            }
	       
	    } catch (SQLException e) {
	        System.out.println("Exception Message " + e.getLocalizedMessage());
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        connection.close();
	    }
    }

}
