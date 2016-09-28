package com.bvmc.rdf.geonames;

public class Exceptions {

	static public String apply(String query){
		
		//S. l eliminarlo de place
		// si viene entre puntos y coma algun valor "Orga" es un apellido
		
		// apellido Burgos???
		
		
		if(query.startsWith("s.") || query.startsWith("S."))
			query = "";
		
		else if (query.equals("Román") || query.equals("Gast") || query.equals("Navarro")
				|| query.equals("Fernández") || query.equals("Soter") || query.equals("Thomas")
				|| query.equals("Cesano") || query.equals("Angulo") || query.equals("Zanetti")
				|| query.equals("Ribes") || query.equals("Quentel") || query.equals("Copado")
				|| query.equals("Remondini") || query.equals("Conejos") || query.equals("Marín")
				|| query.equals("Albrizzi") || query.equals("Colli") || query.equals("Rouillé")
				
				|| query.equals("Sánchez") || query.equals("Sabbio") || query.equals("Gracián")
				|| query.equals("Rodríguez Franco") || query.equals("Capó") || query.equals("Ibarra")
				|| query.equals("Bousquet") || query.equals("Escribano") || query.equals("Carreras")
				|| query.equals("Steiner") || query.equals("Pezzana") || query.equals("Occhi")
				|| query.equals("Bure") || query.equals("Monfort") || query.equals("Orga")
				|| query.equals("Rubeis") || query.equals("Mojados") || query.equals("Moreto")
				
				|| query.equals("Fritsch") || query.equals("Junchi") || query.equals("Lucas")
				|| query.equals("Rigaud") || query.equals("Puerta") || query.equals("Galli")
				|| query.equals("Moreno") || query.equals("Cano") || query.equals("Hierro")
				|| query.equals("Benedicto") || query.equals("Benedito") || query.equals("Ramírez")
				|| query.equals("Poleti") || query.equals("Bassano") || query.equals("Saint Thomas")
				|| query.equals("Palacio") || query.equals("San Lorenzo") || query.equals("Santa Catalina")
				|| query.equals("Losada") || query.equals("Santaren") || query.equals("Santarén")
				|| query.equals("Ricciardo") || query.equals("Orga") || query.equals("JD")
				|| query.equals("L.C") || query.equals("F.C.") || query.equals("F.C")
				|| query.equals("F.C.") || query.equals("F.C") || query.equals("I.M.M")
				|| query.equals("MP") || query.equals("I.M.M") || query.equals("MP")
				|| query.equals("M. Mira") || query.equals("J.M") || query.equals("L.C.")
				|| query.equals("E.B.P.") || query.equals("JDP") || query.equals("XIV")
				|| query.equals("Pedro Pascual") || query.equals("Durá") || query.equals("Lacoste")
				|| query.equals("Castañeira") || query.equals("Alpuente") || query.equals("Laurent")
				|| query.equals("J. Ortega") || query.equals("Vila")|| query.equals("Tucumán")
				|| query.equals("Cananea") || query.equals("Barchin") || query.equals("Salazar")
				|| query.equals("Vilanova") || query.equals("Callar") || query.equals("Baça")
				|| query.equals("Ramirez") || query.equals("Al fin") || query.equals("entre  i")
				|| query.equals("de mars de") || query.equals("gener") || query.equals("i")
				|| query.equals("ca") || query.equals("Año de") || query.equals("entre  y") || query.equals("Entre  y")
				|| query.equals("Argent.") || query.equals("Toro") || query.equals("h")
				|| query.equals("Helsingfors") || query.equals("de noviembre") ||query.equals("entre") || query.equals("Entre  e")
				|| query.equals("post.") || query.equals("/") || query.equals("Salamañ.") || query.equals("Bonon.")
				|| query.equals("Ayuntamiento") || query.equals("s .n.") || query.equals("S. l")
				)
			query = "";
		
		else if (query.equals("Brünn"))
			query = "Brünn germany";
		else if (query.equals("La Cometa")) // ediciones la comenta en barcelona
			query = "Barcelona";
		else if (query.equals("Alija"))
			query = "alija extremadura";
		else if (query.equals("s en Madrid")) // este en tu etl es así,en mi codigo es "n Madrid" porque elimino algunas cosas
			query = "Madrid";
		else if (query.equals("Cordoba") || query.equals("s en Cordoba")) // filtralo tu
			query = "Cordoba españa";
		else if (query.equals("s   en Barcelona"))
			query = "Barcelona";
		else if (query.equals("Gata"))
			query = "jata caceres";
		else if (query.equals("Ledesma"))
			query = "ledesma salamanca";
		else if (query.equals("Isla de León"))
			query = "isla de leon cadiz";
		else if (query.equals("Oña"))
			query = "oña burgos";
		else if (query.equals("Astorga"))
			query = "astorga leon";
		else if (query.equals("Roa"))
			query = "roa burgos";
		else if (query.equals("Cuenca"))
			query = "cuenca españa";
		else if (query.equals("Escala"))
			query = "escala gerona";
		else if (query.equals("Guernica"))
			query = "guernica vizcaya";
		else if (query.equals("Lorenzana"))
			query = "cuadros leon";
		else if (query.equals("Tacuba"))
			query = "mexico df";
		else if (query.equals("Pera"))
			query = "Beyoglu";
		else if (query.equals("Vich") || query.equals("Vic"))
			query = "Vic Barcelona";
		else if (query.equals("Cabra"))
			query = "Cabra Córdoba";
		else if (query.equals("Campos"))
			query = "Campos mallorca";
		else if (query.equals("Samos"))
			query = "Samos España";
		else if (query.equals("Bollo"))
			query = "o bolo";
		else if (query.equals("Tuy"))
			query = "tui";
		else if (query.equals("Belmonte"))
			query = "Belmonte de Gracián";
		else if (query.equals("Tábara"))
			query = "Tábara Zamora";
		else if (query.equals("Mellid"))
			query = "mellid coruña";
		else if (query.equals("Montenegro"))
			query = "Montenegro soria";
		else if (query.equals("Tebar"))
			query = "Tebar Cuenca";
		else if (query.equals("San Miguel de Culiacán"))
			query = "Culiacán";
		else if (query.equals("Almendro"))
			query = "Almendro huelva";
		else if (query.equals("Alhambra"))
			query = "Alhambra ciudad real";
		else if (query.equals("Cortes"))
			query = "cortes barriada";
		else if (query.equals("Cabos"))
			query = "los cabos asturias";
		else if (query.equals("Agoncillo"))
			query = "agoncillo logroño";
		else if (query.equals("Barja"))
			query = "barja orense";
		else if (query.equals("Carmona"))
			query = "carmona sevilla";
		else if (query.equals("Salvatierra"))
			query = "Salvatierra alava";
		else if (query.equals("Linares"))
			query = "linares jaen";
		else if (query.equals("Carolina"))
			query = "carolina jaen";
		else if (query.equals("San Lorenzo"))
			query = "san lorenzo malaga";
		else if (query.equals("Reinosa"))
			query = "Reinosa cantabria";
		else if (query.equals("Sepúlveda"))
			query = "sepulveda segovia";
		else if (query.equals("Cardó"))
			query = "benifallet";
		else if (query.equals("Morón"))
			query = "moron de la frontera";
		else if (query.equals("Saldaña"))
			query = "saldaña palencia";
		else if (query.equals("Sopetran"))
			query = "hita guadalajara";
		else if (query.equals("Nágera"))
			query = "Nájera";
		else if (query.equals("Torrijos"))
			query = "torrijos toledo";
		else if (query.equals("Almadén"))
			query = "almaden españa";
		else if (query.equals("Belalcázar"))
			query = "Belalcazar Cordoba";
		else if (query.equals("Silos"))
			query = "silos burgos";
		else if (query.equals("La Haye"))
			query = "The Hague";
		else if (query.equals("Lugano"))
			query = "lugano suiza";
		else if (query.equals("Angostura"))
			query = "ciudad bolívar";
		else if (query.equals("A Anvers"))
			query = "anvers";
		else if (query.equals("Königsberg"))
			query = "Königsberg germany";
		// Granada
		/*s en Granada 		1672	
		273360	Reimpresas en Granada 
		Por su original en Granada
		Impreso en dicha ciudad de Granada
		Reimpresa en Granada
		Reimpreso en Granada
		esta ciudad de Granada
		Impreso en Granada
		F. Granada*/
		else if (query.contains("Granada"))
			query = "Granada españa";
		else if (query.contains("Barçelona"))
			query = "Barcelona";
		else if (query.contains("Pôrto"))
			query = "Porto portugal";
		else if (query.contains("Poblet"))
			query = "Poblet vimbodi";
		else if (query.contains("Lió"))
			query = "Lyon";
		
		
		
		// problema mérida y guadalajara en emails
		
		// no tener en cuenta estos dos, son incorrectos para Trujillo
		//632013	Trujillo	Sebastián ; García de Samaniego de la Serna	1564	
		//636372	Trujillo	Sebastián	1551	
		
		// problema halle con apellidos
		//622704	Halle	Guglielmo ; Lazzari	1655	
		//624195	Halle	Guglielmo ; Falco	1664	
		
		else if (query.startsWith("por "))
			query = "";
		else if (query.startsWith("la Imprenta de "))
			query = "";
		else if (query.startsWith("la imprenta de "))
			query = "";
		
		else if (query.startsWith("In "))
			query = query.replaceFirst("In ", "");
		
		else if (query.contains("Zamora"))
			query = "Zamora españa";
		else if (query.contains("Ciudad Trujillo"))
			query = "trujillo caceres";
		else if (query.contains("Murviedro"))
			query = "Sagunto";
		else if (query.contains("Sahagún"))
			query = "Sahagún Leon";
		else if (query.contains("Baeça"))
			query = query.replace("Baeça", "Baeza");
		else if (query.contains("Zaragoça"))
			query = query.replace("Zaragoça", "Zaragoza");
		else if (query.contains("Ayuntamiento de"))
			query = query.replace("Ayuntamiento de", "");
		else if (query.contains("Ayuntamiento Constitucional de"))
			query = query.replace("Ayuntamiento Constitucional de", "");
		else if (query.contains(":")){
			String[] tokens = query.split(":");
			if (tokens[0] != null && !tokens[0].isEmpty())
			query = tokens[0];
			//Madrid :Despacho de Marés y Compañía
			// Barcelona :

		}
		else if (query.contains("MadridImprenta") || query.contains("Buen Retiro"))
			query = "Madrid";
		else if (query.contains("Se hallara en"))
			query = query.replace("Se hallara en", "");
		else if (query.contains("se hallara en"))
			query = query.replace("se hallara en", "");
		else if (query.contains("Impressum"))
			query = query.replace("Impressum", "");
		else if (query.contains("República Argentina"))
			query = query.replace("República Argentina", "Argentina");
		else if (query.contains("Mexici"))
			query = query.replace("Mexici", "México");
		else if (query.contains("Con licencia"))
			query = query.replace("Con licencia", "");
		else if (query.contains("Reimpreso en"))
			query = query.replace("Reimpreso en", "");
		else if (query.contains("reimpresa en"))
			query = query.replace("reimpresa en", "");
		else if (query.contains("Impreso en"))
			query = query.replace("Impreso en", "");
		else if (query.contains("Impressum"))
			query = query.replace("Impressum", "");
		
		query = query.trim();
	    
	    return query;
    }
}
