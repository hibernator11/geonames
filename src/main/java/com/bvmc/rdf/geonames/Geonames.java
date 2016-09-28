package com.bvmc.rdf.geonames;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.geonames.FeatureClass;
import org.geonames.Toponym;
import org.geonames.ToponymSearchCriteria;
import org.geonames.ToponymSearchResult;
import org.geonames.WebService;

import com.bvmc.rdf.dto.TranslationDTO;
import com.bvmc.rdf.h2.H2Database;

/**
 * class for matching labels against the Geonames repository 
 * @author gcandela
 *
 */
public class Geonames {
	
	public static void launchMatchingProcess(){
		Reader in = null;
	    try {
	    	
		    in = new FileReader("src/main/resources/rdf_place_date_20160921.csv");
	        //in = new FileReader("src/main/resources/rdf_place_test.csv");
			Iterable<CSVRecord> records = CSVFormat.EXCEL.withDelimiter('\t').withFirstRecordAsHeader().parse(in);
	    	
	    	for (CSVRecord record : records) {
	    		
				if(record.size() > 1){
		    	    String idManifestation = record.get(0);
		    	    String place = record.get(1);
		    	    String entity = "";
		    	    String date = "";
		    	    String lugarPublicacionOriginal = "";
		    	    
		    	    if(record.size() > 2)
		    	        entity = record.get(2);
		    	    if(record.size() > 3)
		    	        date = record.get(3);
		    	    if(record.size() > 4)
		    	        lugarPublicacionOriginal = record.get(4);
		    	    
		    	    if(place!=null && !place.trim().isEmpty()){
			    	    
			    	    H2Database.insertBibliographicRecord(idManifestation, place, entity, date, lugarPublicacionOriginal);
			    	    
			    	    //System.out.println("line:" + idManifestation + " -- " + place + " -- " + entity + " -- " + date + " -- " + lugarPublicacionOriginal);
			    	    
			    	    try {
			    	    	if(place.contains(";")){
			    	    		String [] tokens =  place.split(";");
			    	    		if(tokens != null){
				    	    		for (String s: tokens){
				    	    			loadDatabase(idManifestation, s);
				    	    		}
			    	    		}
			    	    	} else {
			    	    		loadDatabase(idManifestation, place);
			    	    	}
						} catch (Exception e) {
							System.out.println("Problem Geonames Search Service, place:" + place + ". Message:" + e.getMessage());
						}
		    	    }
	    		}
	    	} // loop for
	    	
		} catch (FileNotFoundException e) {
			System.out.println("Problem reading CSV file");
		} catch (IOException e) {
			System.out.println("Problem reading CSV file");
		} catch (SQLException e1) {
			System.out.println("Error creating H2 database");
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				System.out.println("Error closing H2 database");
			}
		}
	}
	
    /**
     * Check the string against Geonames repository and insert the results
     * @param idManifestation
     * @param place
     * @throws Exception 
     */
    public static void loadDatabase(String idManifestation, String place) throws Exception{
    	System.out.println("matchGeonames place:" + place);
    	place = Exceptions.apply(place);
		
		if(!place.isEmpty()){
			
			String idGeoname = H2Database.checkGeoname(place);
	    	if(!idGeoname.isEmpty()){
	    		
	    		H2Database.insertBibliographicGeoname(idManifestation, idGeoname);
	    	
	    	}else{
	    		
	    		// translations
    			TranslationDTO translation = H2Database.checkTranslation(place);
    			if(translation != null && translation.getId() > 0){
    				// check translation in geoname
    				String idGeonameTranslation = H2Database.checkGeoname(translation.getText());
    				
    				if(!idGeonameTranslation.isEmpty()){
    		    		H2Database.insertBibliographicGeoname(idManifestation, idGeonameTranslation);
    		    		H2Database.insertBibliographicTranslation(idManifestation, translation.getId() +"");
    		    	}else {
    		    		Toponym t = Geonames.matchingService(translation.getText(), "");
    	    			if(t != null){
    	    			    H2Database.insertGeoname(t.getGeoNameId() +"", t.getName(), t.getCountryCode(), translation.getText());
    	    			    H2Database.insertBibliographicGeoname(idManifestation, t.getGeoNameId() +"");
    	    			    H2Database.insertBibliographicTranslation(idManifestation, translation.getId() +"");
    	    			}
    		    	}
    			
    			} else {
	    			
    				Toponym t = Geonames.matchingService(place, "");
	    			if(t != null){
	    			    H2Database.insertGeoname(t.getGeoNameId() +"", t.getName(), t.getCountryCode(), place);
	    			    H2Database.insertBibliographicGeoname(idManifestation, t.getGeoNameId() +"");
	    			}
    			}
	    	}
		}
    }
	
    public static Toponym matchingService(String query, String countryCode) throws Exception{
		
    	Toponym result = null;
    	//WebService.setUserName("demo"); // add your username here
		
    	WebService.setUserName("gcandela");
		
		if(!query.isEmpty()){
		
			ToponymSearchCriteria searchCriteria = new ToponymSearchCriteria();
		    searchCriteria.setQ(query);
		    searchCriteria.setFeatureClass(FeatureClass.P);
		    if(countryCode != null && !countryCode.isEmpty()){
		    	searchCriteria.setCountryBias(countryCode);
		    }else{
		    	// si cumple algun pais, eliminar texto y marcar codigo
		    	// set country code
		    	String countryCodeFound = Country.getCode(query);
		    	if(!countryCodeFound.isEmpty())
		    	    searchCriteria.setCountryBias(countryCodeFound);
		    }
		    searchCriteria.setMaxRows(1);
		    
		    ToponymSearchResult searchResult = WebService.search(searchCriteria);
		    if(searchResult.getTotalResultsCount() > 0)
		    	result = searchResult.getToponyms().get(0);
		}
		return result;
	}
}
