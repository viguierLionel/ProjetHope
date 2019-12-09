package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.sql.DataSource;



public class DAO {

	private final DataSource myDataSource;

	/**
	 * Construit le AO avec sa source de données
	 * @param dataSource la source de données à utiliser
	 */
	public DAO(DataSource dataSource) {
		this.myDataSource = dataSource;
	}
        
        
        /**
         * Permet d'avoir le code d'un client en fonction de Contact  
         * @return Code du Client
         * @throws java.sql.SQLException renvoyées par JDBC
         */
        public String codeClient(String contact) throws SQLException {
            String result = null;;
            String sql = "SELECT CODE FROM CLIENT WHERE CONTACT= '?'";
            
            try (Connection connection = myDataSource.getConnection(); 
		     PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, contact);
                        try (ResultSet resultSet = stmt.executeQuery()) {
				if (resultSet.next()) {
					result = resultSet.getString("LastName");
				}
			}
			
		}
            
            return result;
        }
        
        //PRODUIT ****************************************************************************************************************************
        
        /**
         * permet d'avoir une liste de produits dont le nom possède le mot clé entré en argument
         * @return ou liste de produits
         * @throws java.sql.SQLException renvoyées par JDBC
         */
        public List<Produit> selectProduct(String nom) throws SQLException {
            List<Produit> result = new LinkedList<>();
            
            String sql = "SELECT * FROM PRODUIT WHERE Nom LIKE '%?%'";
            try (Connection connection = myDataSource.getConnection(); 
		     PreparedStatement stmt = connection.prepareStatement(sql)) {
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
                            
                        }
            }
            return result;
        }
            
        /**
         * Permet d'avoir tout les produits en fonction d'une catégorie ou d'un mot clé de catégorie
         * (mot clé -> partie d'un no de catégorie)
         * @return Liste de produit
         * @throws SQLException renvoyées par JDBC
         */
        public List<Produit> allProducts(String cat) throws SQLException {
            
            List<Produit> result = new LinkedList<>();
            
            String sql = "SELECT * FROM PRODUIT INNER JOIN CATEGORIE ON PRODUIT.CATEGORIE = CATEGORIE.CODE WHERE LIBELLE LIKE '%?%'";;

            try (Connection connection = myDataSource.getConnection(); 
		     PreparedStatement stmt = connection.prepareStatement(sql)) {
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
                            String nProduit=rs.getString("NOM");
                            int fournisseur=rs.getInt("FOURNISSEUR");
                            int categorie=rs.getInt("CATEGORIE");
                            float prixUnitaire=rs.getFloat("PRIX_UNITAIRE");
                            float quantiteParUnite=rs.getFloat("QAUNTITE_PAR_UNITE");
                            int unitesEnStock=rs.getInt("UNITES_EN_STOCK");
                            int unites_Commandees=rs.getInt("UNITES_COMMANDEES");
                            int niveauReaprovi=rs.getInt("NIVEAU_DE_REAPPROVI");
                            boolean indispo=rs.getInt("INDISPONIBLE")==1;
                            Produit p = new Produit(nProduit, fournisseur,categorie,prixUnitaire,quantiteParUnite,
                                                        unitesEnStock,unites_Commandees,niveauReaprovi,indispo);
				result.add(p);
                        }
            }
            return result;
        }

        //CATEGORIE ****************************************************************************************************************************
        
        /**
         * permet de retourner toutes les categories de produits
         * @return liste de categories
         * @throws SQLException 
         */
        public List<Categorie> toutesCategories() throws SQLException {
        List<Categorie> resultat = new ArrayList();
        String sql = "SELECT * FROM CATEGORIE";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int code = rs.getInt("CODE");
                String libelle = rs.getString("LIBELLE");
                String description = rs.getString("DESCRIPTION");
                Categorie c = new Categorie(code, libelle, description);
                resultat.add(c);
            }
        }
        return resultat;
        }
        
        /**
         * permet d'avoir une categorie en fonction d'un code
         * @param code
         * @return une categorie
         * @throws SQLException 
         */
        public Categorie categorie(int code) throws SQLException {
            Categorie categorie = null;
            String sql = "SELECT * FROM CATEGORIE WHERE CODE = ?";
            try (Connection myConnection = myDataSource.getConnection();
                    PreparedStatement statement = myConnection.prepareStatement(sql)) {
                        statement.setInt(1, code); 
                        try (ResultSet rs = statement.executeQuery()) {
                            if (rs.next()) {
                                String libelle = rs.getString("LIBELLE");
                                String description = rs.getString("DESCRIPTION");
                                categorie = new Categorie(code, libelle, description);
                            }
                        }
            }
            return categorie;
        }

        /**
         * permet d'avoir le libelle d'une categorie en fonction d'un code
         * @param code
         * @return libelle de la categorie
         * @throws SQLException 
         */
        public String libelleCategorie(int code) throws SQLException {
            String libelle = null;
            String sql = "SELECT * FROM CATEGORIE WHERE CODE = ?";
            try (Connection myConnection = myDataSource.getConnection();
                    PreparedStatement statement = myConnection.prepareStatement(sql)) {
                        statement.setInt(1, code); 
                        try (ResultSet rs = statement.executeQuery()) {
                            if (rs.next()) {
                                libelle = rs.getString("LIBELLE");
                            }
                        }
            }
            return libelle;
        }

        /**
         * permet d'avoir la description d'une categorie en fonction d'un code
         * @param code
         * @return description de la categorie
         * @throws SQLException 
         */
        public String descriptionCategorie(int code) throws SQLException {
            String description = null;
            String sql = "SELECT * FROM CATEGORIE WHERE CODE = ?";
            try (Connection myConnection = myDataSource.getConnection();
                    PreparedStatement statement = myConnection.prepareStatement(sql)) {
                        statement.setInt(1, code); 
                        try (ResultSet rs = statement.executeQuery()) {
                            if (rs.next()) {
                                description = rs.getString("DESCRIPTION");
                            }
                        }
            }
            return description;
        }
        
}


