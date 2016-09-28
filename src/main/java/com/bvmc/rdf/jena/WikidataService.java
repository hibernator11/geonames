package com.bvmc.rdf.jena;

import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Resource;

public class WikidataService {

    public static String getWikidataUri(String idgeoname){
		String resultado = "";
    	
		String queryString = "PREFIX wdt: <http://www.wikidata.org/prop/direct/>"
				+ " SELECT ?wikidataUri where { "
				+ " ?wikidataUri wdt:P1566 \""+ idgeoname + "\" "
				+ " } limit 5";
		
		org.apache.jena.query.QueryExecution qexec = org.apache.jena.query.QueryExecutionFactory.sparqlService("https://query.wikidata.org/sparql", queryString);
        try {
            ResultSet results = qexec.execSelect();
            //ResultSetFormatter.out(System.out, results);
            
            for ( ; results.hasNext() ; )
            {
              QuerySolution soln = results.nextSolution() ;
              Resource r = soln.getResource("wikidataUri") ; // Get a result variable - must be a resource
              System.out.println(r.getURI());
              resultado = r.getURI();
              break;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            qexec.close();
        }
        
        return resultado;
	}
}
