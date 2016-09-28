package com.bvmc.rdf.jena;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.OWL;
import com.hp.hpl.jena.vocabulary.RDF;

public class Translation {

	private String idTranslation;
	private String englishText;
	private String latinText;
	private String countryCode;
	private String lpnUri;
	
	public Translation(String idTranslation, String englishText, String latinText, String countryCode, String lpnUri){
		this.idTranslation = idTranslation;
		this.englishText = englishText;
		this.latinText = latinText;
		this.countryCode = countryCode;
		this.lpnUri = lpnUri;
	}
	
	public String getUri(){
		return "http://data.cervantesvirtual.com/translation/" + idTranslation;
	}
	
	public void generateRDF(){
	        
        Model model = ModelFactory.createDefaultModel();
        
        // set a namespace prefix
        model.setNsPrefix("rdf", RDF.getURI());
        model.setNsPrefix("owl", OWL.getURI());
        model.setNsPrefix("bvmc-ps", BVMCPB.getURI());
        
        Resource translation = model.createResource(getUri());
        model.add(translation, RDF.type, BVMCPB.translation);
        
        model.add(translation, BVMCPB.hasEnglishText, this.englishText);
        model.add(translation, BVMCPB.hasLatinText, this.latinText);
        model.add(translation, BVMCPB.hasCountryCode, this.countryCode);
                
        if (this.lpnUri != null && !this.lpnUri.isEmpty()){
            Resource lpn = model.createResource(this.lpnUri);
            model.add(translation, OWL.sameAs, lpn);
        }
        
        StringWriter out = new StringWriter();
        model.write(out, "RDF/XML-ABBREV");
        model.write(System.out, "RDF/XML-ABBREV");
        try {
            PrintWriter printFile = new PrintWriter(new BufferedWriter(new FileWriter(System.getProperty("user.home") + "/geonamesrdf/translations/translation-" + idTranslation +".rdf", true)));
            printFile.println(out.toString());
            printFile.close();
        } catch (IOException e) {
        	System.out.println("Exception Message " + e.getLocalizedMessage());
        }
 	}

	public String getIdTranslation() {
		return idTranslation;
	}

	public void setIdTranslation(String idTranslation) {
		this.idTranslation = idTranslation;
	}

	public String getEnglishText() {
		return englishText;
	}

	public void setEnglishText(String englishText) {
		this.englishText = englishText;
	}

	public String getLatinText() {
		return latinText;
	}

	public void setLatinText(String latinText) {
		this.latinText = latinText;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getLpnUri() {
		return lpnUri;
	}

	public void setLpnUri(String lpnUri) {
		this.lpnUri = lpnUri;
	}
}
