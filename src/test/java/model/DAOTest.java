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
		executeSQLScript(myConnection, "schemaBD.sql");
		// On y met des données
		executeSQLScript(myConnection, "ajoutDonnees.sql");		

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
    
        
    //PRODUIT ****************************************************************************************************************************
    
    @Test
    public void selectProductTest() throws SQLException{
        Produit produit = new Produit( 18, "Carnarvon Tigers", 7, 8, "1 carton (16 kg)", 312.00, 42, 0, 0, 0);
        assertEquals(produit,dao.selectProduct(18));
    }
        
    @Test
    public void selectNomProduct() throws SQLException{
        assertEquals(8,dao.selectNomProduct("le").size());
    }
    
    @Test
    public void allProductsTest() throws SQLException{
        assertEquals(77,dao.allProducts().size());
    }
    
    @Test
    public void allProductsFromCatTest() throws SQLException{
        assertEquals(12,dao.allProductsFromCat("Boi").size());
    }
        
 
    //CATEGORIE ****************************************************************************************************************************
    
    @Test
    public void allCategoriesTest() throws SQLException {
        List<Categorie> listeCategories = dao.allCategories();
        assertEquals(8,listeCategories.size());
    }
    
    @Test
    public void categorieTest() throws SQLException {
        Categorie categorie = new Categorie(2, "Condiments", "Sauces, assaisonnements et épices");
        assertEquals(categorie,dao.categorie(2));
    }
    
    @Test
    public void libelleCategorieTest() throws SQLException {
        assertEquals(dao.libelleCategorie(1),"Boissons");
    }

    @Test
    public void descriptionCategorieTest() throws SQLException {
        assertEquals(dao.descriptionCategorie(7),"Fruits secs, raisins, autres");
    }
    
    //********************************************************************************
    
    public static DataSource getDataSource() {
		org.hsqldb.jdbc.JDBCDataSource ds = new org.hsqldb.jdbc.JDBCDataSource();
		ds.setDatabase("jdbc:hsqldb:mem:testcase;shutdown=true");
		ds.setUser("sa");
		ds.setPassword("sa");
		return ds;
    }	
    
}
