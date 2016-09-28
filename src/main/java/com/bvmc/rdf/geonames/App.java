package com.bvmc.rdf.geonames;

import java.sql.SQLException;

import com.bvmc.rdf.h2.H2Database2RDF;
import com.bvmc.rdf.h2.H2Database;

/**
 * Application to link locations to geonames 
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	long startTime = System.currentTimeMillis();
        System.out.println( "Starting matching Geonames process!!" );
        
    	try {
        	H2Database.createDatabaseScheme();
		    Geonames.launchMatchingProcess();
		    
    		//H2Database2RDF.generateRDF();
        } catch (SQLException e1) {
			System.out.println("Error creating H2 database");
		}
    	
        long runtime = System.currentTimeMillis() - startTime;
    	
    	System.out.println("Finished matching Geonames process in " + runtime + "ms");
    }
}
