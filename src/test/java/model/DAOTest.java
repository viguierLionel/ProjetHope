/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.hsqldb.cmdline.SqlFile;
import org.hsqldb.cmdline.SqlToolError;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 *
 * @author Hugo
 */
public class DAOTest {
    
    private DataSource myDataSource;
    private static Connection myConnection ;
    private DAO dao; 
    
    	@Before
	public  void setUp() throws IOException, SqlToolError, SQLException {
		// On crée la connection vers la base de test "in memory"
		myDataSource = getDataSource();
		myConnection = myDataSource.getConnection();
		// On crée le schema de la base de test
		executeSQLScript(myConnection, "ressources.testSQL.sql");
		// On y met des données
		executeSQLScript(myConnection, "smalltestdata.sql");		

            	dao = new DAO(myDataSource);
	}
	
	private void executeSQLScript(Connection connexion, String filename)  throws IOException, SqlToolError, SQLException {
		// On initialise la base avec le contenu d'un fichier de test
		String sqlFilePath = DAOTest.class.getResource(filename).getFile();
		SqlFile sqlFile = new SqlFile(new File(sqlFilePath));

		sqlFile.setConnection(connexion);
		sqlFile.execute();
		sqlFile.closeReader();		
	}
		
	@After
	public void tearDown() throws IOException, SqlToolError, SQLException {
		myConnection.close(); // La base de données de test est détruite ici
             	dao = null; // Pas vraiment utile

	}
    /**
     * Test of codeClient method, of class DAO.
     */
    @Test
    public void testCodeClient() throws Exception {
        System.out.println("codeClient");
        String contact = "";
        DAO instance = null;
        String expResult = "";
        String result = instance.codeClient(contact);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of selectProduct method, of class DAO.
     */
    @Test
    public void testSelectProduct() throws Exception {
        System.out.println("selectProduct");
        String nom = "";
        DAO instance = null;
        List<Produit> expResult = null;
        List<Produit> result = instance.selectProduct(nom);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    
    public static DataSource getDataSource() {
		org.hsqldb.jdbc.JDBCDataSource ds = new org.hsqldb.jdbc.JDBCDataSource();
		ds.setDatabase("jdbc:hsqldb:mem:testcase;shutdown=true");
		ds.setUser("sa");
		ds.setPassword("sa");
		return ds;
	}	
    
}
