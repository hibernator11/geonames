package com.bvmc.rdf.jena;

import com.hp.hpl.jena.rdf.model.*;
 
/**
 * Vocabulary definitions from http://data.cervantesvirtual.com/publicationStatement/
 * Publication Statement properties
 * @author gcandela 
 */
public class BVMCPB 
{
    /** <p>The RDF model that holds the vocabulary terms</p> */
    private static Model m_model = ModelFactory.createDefaultModel();
    
    /** <p>The namespace of the vocabulary as a string</p> */
    public static final String NS = "http://data.cervantesvirtual.com/publicationStatement/#";
    
    /** Recommended prefix for the RDF namespace: "bvmc-pe" */
	public static final String PREFIX = "bvmc-pe";
    
    /** <p>The namespace of the vocabulary as a string</p>
     *  @see #NS */
    public static String getURI() {return NS;}
    
    /** <p>The namespace of the vocabulary as a resource</p> */
    public static final Resource NAMESPACE = m_model.createResource( NS );

    public static final Resource manifestation = m_model.createProperty( "http://rdaregistry.info/Elements/c/#C10007" );
    
    public static final Resource publicationStatement = m_model.createProperty( NS + "publicationStatement" );
    public static final Property hasPublicationStatement  = m_model.createProperty( "http://rdaregistry.info/Elements/m/#P30111" );
    public static final Property hasDateOfPublication = m_model.createProperty( "http://rdaregistry.info/Elements/m/#P30011" );
    public static final Property hasPlaceOfPublication = m_model.createProperty( "http://rdaregistry.info/Elements/m/#P30088" );
    public static final Property hasDescription = m_model.createProperty( NS + "hasDescription" );
    
    public static final Resource translation = m_model.createProperty( NS + "Translation" );
    public static final Property hasTranslation = m_model.createProperty( NS + "hasTranslation" );
    public static final Property hasEnglishText = m_model.createProperty( NS + "hasEnglishText" );
    public static final Property hasLatinText = m_model.createProperty( NS + "hasLatinText" );
    public static final Property hasCountryCode = m_model.createProperty( NS + "hasCountryCode" );
    
    
    
    
}
