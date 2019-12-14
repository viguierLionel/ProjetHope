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
    private Connection myConnection ;
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
    
        
    //CLIENT ****************************************************************************************************************************
        
    @Test
    public void allClientsTest() throws SQLException {
        assertEquals(91,dao.allClients().size());
    }
    
    @Test
    public void selectClientTest() throws SQLException {
        Client client = new Client("ALFKI", "Alfreds Futterkiste", "Maria Anders", "Représentant(e)", "Obere Str. 57", "Berlin", null, "12209     ", "Allemagne", "030-0074321", "030-0076545");
        assertEquals(client,dao.selectClient("ALFKI"));
    }
    
    @Test
    public void pwdExisteTest() throws SQLException {
        assertEquals(0,dao.pwdExiste("bruh"));
        
        assertEquals(1,dao.pwdExiste("ALFKI"));
    }
    
    @Test
    public void compteExisteTest() throws SQLException {
        assertEquals(0,dao.compteExiste("big","bruh"));
        
        assertEquals(1,dao.compteExiste("Maria Anders","ALFKI"));

        assertEquals(2,dao.compteExiste("admin","admin"));
    }
    
    @Test
    public void majModClientTest() throws SQLException{
        //nouvelles informations à maj du client
        Client clientModif = new Client("ALFKI","Alfreds Futterkiste","Maria Anders","Représentant(e)","Obere Str. 57",
                "France", null,"81000","France","033-0074321","033-0076545");
        //nouvelles informations à maj du client avec le codePostal comme un Double
        Client clientModifDouble = new Client("ALFKI","Alfreds Futterkiste","Maria Anders","Représentant(e)","Obere Str. 57",
                "France", null,"81000     ","France","033-0074321","033-0076545");
        dao.majModClient(clientModif);
        assertEquals(dao.selectClient("ALFKI"),clientModifDouble);
    }
     
    
        
    //PRODUIT ****************************************************************************************************************************
    
    @Test
    public void selectProductTest() throws SQLException{
        Produit produit = new Produit( 18, "Carnarvon Tigers", 7, 8, "1 carton (16 kg)", 312.00, 42, 0, 0, 0);
        assertEquals(produit,dao.selectProduct(18));
    }
        
    @Test
    public void selectNomProductTest() throws SQLException{
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
    
    @Test
     public void addProductTest() throws SQLException{
        Produit produit = new Produit(100,"Pickle juice", 1, 1, "3 bottles ", 4.20, 69, 0, 10, 0);
        dao.addProduct(produit);
        assertEquals(dao.selectProduct(100),produit);
    }
    
     @Test
     public void majModProductTest() throws SQLException{
        Produit produit = new Produit(100,"Pickle juice", 1, 1, "3 bottles ", 4.20, 69, 0, 10, 0);
        Produit produitModif = new Produit(100,"Pickle juice", 1, 1, "4 bottles ", 4.20, 69, 0, 10, 0);
        dao.addProduct(produit);
        assertEquals(dao.selectProduct(100),produit);
        dao.majModProduct(produitModif);
        assertEquals(dao.selectProduct(100),produitModif);
    }
     
    @Test
    public void delProductAuxTest() throws SQLException {
        assertEquals(1,dao.delProduct(1));
    }
    
    @Test
    public void delProductTest() throws SQLException {
        assertEquals(1,dao.delProduct(1));
    }
    
    //COMMANDE ****************************************************************************************************************************   
 
    @Test
    public void allCommandesTest() throws SQLException {
        List<Commande> listeCommande = dao.allCommandes();
        assertEquals(830,listeCommande.size());
    }
    
    @Test
    public void selectCommandeTest() throws SQLException{
        Commande commande = new Commande(10248, "VINET", "1994-08-04", "1994-08-16", 161.00, "Vins et alcools Chevalier",
                "59 rue de l'Abbaye", "Reims", null, "51100", "France", 0.00);
        assertEquals(commande,dao.selectCommande(10248));
    }
    
    @Test
    public void addCommandeTest() throws SQLException{
        Commande commande = new Commande(11400, "VINET", "2020-04-20","2020-09-30",420.00,"La chancla",
                "22 avenue j'ai plus d'idée","Albi", "TR","81000","France",0.00);
        dao.addCommande(commande);
        assertEquals(commande,dao.selectCommande(11400));
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
