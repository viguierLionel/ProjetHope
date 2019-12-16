package model;

import javax.sql.DataSource;

/**
 *
 * @author hvv
 */
public class DataSourceFactory {

	public enum DriverType {
		embedded, server
	};
	
	// Choic du type de driver : embedded ou serveur
	private static final DriverType TYPE = DriverType.server;
	/**
	 * Renvoie la source de données (server ou embbeded)
	 * @return  la source de données
	 */
	public static DataSource getDataSource() {
		DataSource result;

		switch (TYPE) {
			case server: // Derby mode serveur, doit être démarré indépendamment
				org.apache.derby.jdbc.ClientDataSource ds = new org.apache.derby.jdbc.ClientDataSource();
			
                                ds.setDatabaseName("d70scnsddh3lpr");
				ds.setUser("fqpzathbetfjqd");
				ds.setPassword("728472ce6300d118951ab2ea36ea9a0815e3f993ce4b2dd59152f92a2b207509");
				// The host on which Network Server is running
				ds.setServerName("ec2-54-217-221-21.eu-west-1.compute.amazonaws.com");
				// port on which Network Server is listening
				ds.setPortNumber(5432);
                                
				result = ds;
				break;
			default: // Derby mode embedded, démarré automatiquement avec l'application
				org.apache.derby.jdbc.EmbeddedDataSource es = new org.apache.derby.jdbc.EmbeddedDataSource();
				es.setCreateDatabase("create");
				es.setDatabaseName("embedded_sample");
				result = es;
		}

		return result;
	}

}
