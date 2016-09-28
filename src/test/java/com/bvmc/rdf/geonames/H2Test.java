package com.bvmc.rdf.geonames;

import java.io.FileReader;
import java.io.Reader;
import java.sql.SQLException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.junit.Test;

import com.bvmc.rdf.h2.H2Database;

public class H2Test {

    @Test
    public void createSynonymsTest() throws SQLException{
    	System.out.println("Started creation Synonyms table");
    	H2Database.createTranslationTable();
    	H2Database.showTable("synonyms", "countrycode");
    	System.out.println("Finished creation Synonyms table");
    }
    
    @Test
    public void readBibliographicRecords() throws Exception{
    	int count = 0;
    	Reader in = new FileReader("src/test/resources/rdf_place_20160912.csv");
    	Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader("idManifestacion", "place", "entity", "date", "lugarPublicacionOriginal").parse(in);
    	
    	System.out.println("Started reading CSV file");
    	
    	for (CSVRecord record : records) {
    		
    	    String idManifestacion = record.get("idManifestacion");
    	    String place = "";
    	    String entity = "";
    	    String date = "";
    	    String lugarPublicacionOriginal = "";
    	    
    	    if (record.isSet("place"))
    	        place = record.get("place");
    	    
    	    if (record.isSet("entity"))
    	        entity = record.get("entity");
    	    
    	    if (record.isSet("date"))
    	        date = record.get("date");
    	    
    	    if (record.isSet("lugarPublicacionOriginal"))
    	    	lugarPublicacionOriginal = record.get("lugarPublicacionOriginal");
    	    
   	    
    	    System.out.println(idManifestacion + " " + place + " " + entity + " " + date + " " + lugarPublicacionOriginal);
    	    
    	    Geonames.matchingService("Bélgica.. Amberes", "");
    	    
    	    
    	    
    	    count++;
    	    //if (count >= 10)
    	    	break;
    	}
    	
    	System.out.println("Finished reading CSV file");
    	  
    }
}
