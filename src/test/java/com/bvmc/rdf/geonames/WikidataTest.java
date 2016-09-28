package com.bvmc.rdf.geonames;

import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Resource;
import org.junit.Test;

public class WikidataTest {

	@Test
	public void wikidataQuery(){
		
		String queryString = "PREFIX wdt: <http://www.wikidata.org/prop/direct/>"
				+ " SELECT ?wikidataUri where { "
				+ " ?wikidataUri wdt:P1566 \"3117735\" "
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
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            qexec.close();
        }
	}
}
