package com.bvmc.rdf.geonames;

/**
 * class for applying a country when matching a label
 * @author gcandela
 *
 */
public class Country {

	public static String getCode(String query){
		
		String countryCode = "";
		if (query.contains("England") || query.contains("Inglaterra") || query.contains("Gran Bretaña")){
    		countryCode = "gb";
    	} else if (query.contains("Bélgica") || query.contains("Bèlgica")){
    		countryCode = "be";
    	} else if (query.contains("Cuba") || query.equals("Cárdenas")){
    		countryCode = "cu";
    	} else if (query.contains("España") || query.contains("Espanya") || query.equals("León")/*|| query.contains("Buen Retiro") || query.contains("Roa")*/){
    		countryCode = "es";
    	} else if (query.contains("Portugal")){
    		countryCode = "pt";
    	} else if (query.contains("Suiza") || query.contains("Suïssa")){
    		countryCode = "ch";
    	} else if (query.contains("Italia") || query.contains("Itàlia")){
    		countryCode = "it";
    	} else if (query.contains("Francia") || query.contains("França")){
    		countryCode = "fr";
    	} else if (query.contains("Holanda") || query.contains("Netherlands") || query.contains("Países Bajos") || query.contains("Països Baixos")){
    		countryCode = "nl";
    	} else if (query.contains("Alemania") || query.contains("Alemanya")){
    		countryCode = "de";
    	} else if (query.contains("Honduras")){
    		countryCode = "hn";
    	} else if (query.contains("Paraguay")){
    		countryCode = "py";
    	} else if (query.contains("Venezuela")){
    		countryCode = "ve";
    	} else if (query.contains("Colombia")){
    		countryCode = "co";
    	} else if (query.contains("Argentina")){
    		countryCode = "ar";
    	} else if (query.contains("México") || query.equals("Alburquerque")){
    		countryCode = "mx";
    	} else if (query.contains("Ecuador")){
    		countryCode = "ec";
    	} else if (query.contains("Bolivia")){
    		countryCode = "bo";
    	} else if (query.contains("Polonia")){
    		countryCode = "pl";
    	} else if (query.contains("Perú")){
    		countryCode = "pe";
    	} else if (query.contains("Filipinas") || query.contains("Filipines")){
    		countryCode = "ph";
    	} else if (query.contains("China")){
    		countryCode = "cn";
    	}
		
		return countryCode;
	}
}
