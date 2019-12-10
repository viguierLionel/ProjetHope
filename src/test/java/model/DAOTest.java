/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
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
    public void setUp()throws SQLException, IOException {
        	
    }
    
    @After
    public void tearDown() throws SQLException {
        myConnection.close();		
        dao = null;
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

    /**
     * Test of allProducts method, of class DAO.
     */
    @Test
    public void testAllProducts() throws Exception {
        System.out.println("allProducts");
        String cat = "";
        DAO instance = null;
        List<Produit> expResult = null;
        List<Produit> result = instance.allProducts(cat);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
