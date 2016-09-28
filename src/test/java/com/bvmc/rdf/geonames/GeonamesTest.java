package com.bvmc.rdf.geonames;

import org.geonames.Toponym;
import org.junit.Assert;
import org.junit.Test;

public class GeonamesTest {
	
	@Test
	public void testEasy() throws Exception{
		Toponym t = Geonames.matchingService("Sevilla : España", "");
		Assert.assertEquals("Seville", t.getName());
		t = Geonames.matchingService("En Sevilla", "");
		Assert.assertEquals("Seville", t.getName());
		t = Geonames.matchingService("Granada", "es");
		Assert.assertEquals("Granada", t.getName());
		t = Geonames.matchingService("Matanzas Cuba", "cu");
		Assert.assertEquals("Matanzas", t.getName());
	}
	
	@Test
	public void testChangeCountry() throws Exception{
		Toponym t = Geonames.matchingService("Tegucigalpa Honduras", "es");
		Assert.assertEquals("Tegucigalpa", t.getName());
	}
	
	@Test
	public void testCatalan() throws Exception{
		Toponym t = Geonames.matchingService("Baeça", "es"); // cambiar Baeça por Baeza
		Assert.assertEquals("Baeza", t.getName());
		t = Geonames.matchingService("Baeza", "es");
		Assert.assertEquals("Baeza", t.getName());
		
		t = Geonames.matchingService("Saragossa", "es");
		Assert.assertEquals("Zaragoza", t.getName());
		t = Geonames.matchingService("Zaragoça", "es");
		Assert.assertEquals("Zaragoza", t.getName());
		t = Geonames.matchingService("Çaragoça", "es");
		Assert.assertEquals("Zaragoza", t.getName());
		
		t = Geonames.matchingService("Bruxelles", "es");
		Assert.assertEquals("Brussels", t.getName());
		t = Geonames.matchingService("Milano", "");
		Assert.assertEquals("Milan", t.getName());
	}
	
	@Test
	public void testSplit() throws Exception{
		Toponym t = Geonames.matchingService("Madrid :Despacho de Marés y Compañía", "");
		Assert.assertEquals("Madrid", t.getName());
		t = Geonames.matchingService("Barcelona :", "");
		Assert.assertEquals("Barcelona", t.getName());
		t = Geonames.matchingService("Madrid :Lit. Boronat", "");
		Assert.assertEquals("Madrid", t.getName());
		t = Geonames.matchingService("Manresa :Pau Roca", "");
		Assert.assertEquals("Manresa", t.getName());
		t = Geonames.matchingService("Massachusetts", "");
		Assert.assertEquals("Massachusetts", t.getName());
		t = Geonames.matchingService("Medina Sidonia", "");
		Assert.assertEquals("Medina-Sidonia", t.getName());
	}
	
	
	@Test
	public void testExceptions() throws Exception{
		
		Toponym t = Geonames.matchingService("Ayuntamiento de Villena", "");
		Assert.assertEquals("Villena", t.getName());
		
		t = Geonames.matchingService("Barcelona :Imprenta de Llorens", "");
		Assert.assertEquals("Barcelona", t.getName());
		
		t = Geonames.matchingService("Buen Retiro", "");
		Assert.assertEquals("Parco del Buen Retiro", t.getName());
		
		t = Geonames.matchingService("caen francia", "es");
		Assert.assertEquals("Caen", t.getName());
		
		t = Geonames.matchingService("carbonera soria", "");
		Assert.assertEquals("Carbonera de Frentes", t.getName());
		
		t = Geonames.matchingService("fulgoni", "");
		Assert.assertNull(t);
		
		t = Geonames.matchingService("harsy", "");
		Assert.assertNull(t);
		
		t = Geonames.matchingService("Liédena", "");
		Assert.assertEquals("Liédena", t.getName());
		
		t = Geonames.matchingService("Mérida Venezuela", "");
		Assert.assertEquals("VE", t.getCountryCode());
		
		t = Geonames.matchingService("Ocaña Colombia", "");
		Assert.assertEquals("CO", t.getCountryCode());
		
		t = Geonames.matchingService("País de Gales", "");System.out.println(t);
		
		t = Geonames.matchingService("Rosario de Santa Fé Argentina", "");
		Assert.assertEquals("AR", t.getCountryCode());
		
		t = Geonames.matchingService("Córdoba República Argentina", "");
		Assert.assertEquals("AR", t.getCountryCode());
		
		t = Geonames.matchingService("San José de Costa Rica", "");
		Assert.assertEquals("CR", t.getCountryCode());
		
		t = Geonames.matchingService("Valencia", "");
		Assert.assertEquals("ES", t.getCountryCode());
		t = Geonames.matchingService("Valencia Venezuela", "");
		Assert.assertEquals("VE", t.getCountryCode());
		t = Geonames.matchingService("Lima", "");
		
		Assert.assertEquals("PE", t.getCountryCode());
		
		t = Geonames.matchingService("S. XVIII", "");
		Assert.assertNull(t);
		
		t = Geonames.matchingService("s. l.", "");
		Assert.assertNull(t);
		
	}
}
