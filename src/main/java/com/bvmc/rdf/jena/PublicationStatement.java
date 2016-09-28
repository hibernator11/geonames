package com.bvmc.rdf.jena;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;

public class PublicationStatement {

	private String idManifestation;
	private String date;
	private String description;
	private String location;
	
	public PublicationStatement(String idManifestation, String date, String description, String location){
		this.setIdManifestation(idManifestation);
		this.date = date;
		this.description = description;
		this.location = location;
	}
	
	public PublicationStatement(String idManifestation, String date, String description){
		this.setIdManifestation(idManifestation);
		this.date = date;
		this.description = description;
	}
	
	public String getUri(){
		return "http://data.cervantesvirtual.com/publicationStatement/" + idManifestation;
	}
	
	public void generateRDF(){
	        
        Model model = ModelFactory.createDefaultModel();
        
        // set a namespace prefix
        model.setNsPrefix("rdf", RDF.getURI());
        model.setNsPrefix("rdac", "http://rdaregistry.info/Elements/c/#");
        model.setNsPrefix("rdam", "http://rdaregistry.info/Elements/m/#");
        model.setNsPrefix("bvmc-ps", BVMCPB.getURI());
        
        Resource manifestation = model.createResource("http://data.cervantesvirtual.com/manifestation/" + idManifestation);
        model.add(manifestation, RDF.type, BVMCPB.manifestation);
        
        Resource publicationStatement = model.createResource(getUri());
        model.add(publicationStatement, RDF.type, BVMCPB.publicationStatement);
        model.add(manifestation, BVMCPB.hasPublicationStatement, publicationStatement);
        model.add(publicationStatement, BVMCPB.hasDescription, this.description);
        model.add(publicationStatement, BVMCPB.hasDateOfPublication, this.date);
        
        StringWriter out = new StringWriter();
        model.write(System.out, "RDF/XML-ABBREV");
        try {
            PrintWriter printFile = new PrintWriter(new BufferedWriter(new FileWriter(System.getProperty("user.home") + "/geonamesrdf/pb/pb-" + idManifestation +".rdf", true)));
            printFile.println(out.toString());
            printFile.close();
        } catch (IOException e) {
        	System.out.println("Exception Message " + e.getLocalizedMessage());
        }
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getIdManifestation() {
		return idManifestation;
	}

	public void setIdManifestation(String idManifestation) {
		this.idManifestation = idManifestation;
	}
}
