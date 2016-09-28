package com.bvmc.rdf.h2;

import java.sql.SQLException;

public class ShowTables {
	public static void main( String[] args )
    {
		try {
			H2Database.showTable("geoname", "country");
			//H2Database.showTable("BibliographicGeoname");
			//H2Database.showTable("translation");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
