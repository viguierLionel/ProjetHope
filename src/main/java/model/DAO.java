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
        
        //CLIENT ****************************************************************************************************************************
        
        /**
         * Permet d'avoir une liste de tout les clients
         * @return liste de tout les clients
         * @throws SQLException 
         */
        public List<Client> allClients() throws SQLException {
        List<Client> result = new ArrayList<>();
        String sql = "SELECT * FROM CLIENT";
        
        try (Connection connection = myDataSource.getConnection(); 
		     PreparedStatement stmt = connection.prepareStatement(sql)) {
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
                            String code = rs.getString("CODE");
                            String societe = rs.getString("SOCIETE");
                            String contact = rs.getString("CONTACT");
                            String fonction = rs.getString("FONCTION");
                            String adresse = rs.getString("ADRESSE");
                            String ville = rs.getString("VILLE");
                            String region = rs.getString("REGION");
                            String codePostal = rs.getString("CODE_POSTAL");
                            String pays = rs.getString("PAYS");
                            String telephone = rs.getString("TELEPHONE");
                            String fax = rs.getString("FAX");
                            Client c = new Client(code, societe, contact, fonction, adresse, ville, region, codePostal, pays, telephone, fax);
                            result.add(c);
            }
        }
        return result;
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
        
        public Produit selectProduct(int reference) throws SQLException {
            Produit resultat = null;
            String sql = "SELECT * FROM PRODUIT WHERE REFERENCE = ?";
            
            try (Connection connection = myDataSource.getConnection(); 
		     PreparedStatement stmt = connection.prepareStatement(sql)) {
                        stmt.setInt(1, reference);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
                            String nProduit=rs.getString("NOM");
                            int fournisseur=rs.getInt("FOURNISSEUR");
                            int categorie=rs.getInt("CATEGORIE");
                            String quantiteParUnite=rs.getString("QUANTITE_PAR_UNITE");
                            double prixUnitaire=rs.getDouble("PRIX_UNITAIRE");
                            int unitesEnStock=rs.getInt("UNITES_EN_STOCK");
                            int unites_Commandees=rs.getInt("UNITES_COMMANDEES");
                            int niveauReaprovi=rs.getInt("NIVEAU_DE_REAPPRO");
                            int indispo=rs.getInt("INDISPONIBLE");
                            Produit p = new Produit(reference,nProduit,fournisseur,categorie,quantiteParUnite,prixUnitaire,
                                                        unitesEnStock,unites_Commandees,niveauReaprovi,indispo);
                            resultat = p;
                        }
            }
            return resultat;
        }
        
        /**
         * permet d'avoir une liste de produits dont le nom possède le mot clé entré en argument
         * @return ou liste de produits
         * @throws java.sql.SQLException renvoyées par JDBC
         */
        public List<Produit> selectNomProduct(String nom) throws SQLException {
            List<Produit> result = new LinkedList<>();
            
            String sql = "SELECT * FROM PRODUIT WHERE Nom LIKE ? ";
            try (Connection connection = myDataSource.getConnection(); 
		     PreparedStatement stmt = connection.prepareStatement(sql)) {
                        stmt.setString(1, "%" + nom + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
                            int reference=rs.getInt("REFERENCE");
                            String nProduit=rs.getString("NOM");
                            int fournisseur=rs.getInt("FOURNISSEUR");
                            int categorie=rs.getInt("CATEGORIE");
                            String quantiteParUnite=rs.getString("QUANTITE_PAR_UNITE");
                            double prixUnitaire=rs.getDouble("PRIX_UNITAIRE");
                            int unitesEnStock=rs.getInt("UNITES_EN_STOCK");
                            int unites_Commandees=rs.getInt("UNITES_COMMANDEES");
                            int niveauReaprovi=rs.getInt("NIVEAU_DE_REAPPRO");
                            int indispo=rs.getInt("INDISPONIBLE");
                            Produit p = new Produit(reference,nProduit,fournisseur,categorie,quantiteParUnite,prixUnitaire,
                                                        unitesEnStock,unites_Commandees,niveauReaprovi,indispo);
			result.add(p);
                        }
                }
            return result;
        }
            
        /**
         * Permet d'avoir une tout les produits
         * @return liste de tout les produits
         * @throws SQLException
         */
        public List<Produit> allProducts() throws SQLException {
            
            List<Produit> result = new LinkedList<>();
            
            String sql = "SELECT * FROM PRODUIT";;

            try (Connection connection = myDataSource.getConnection(); 
		     PreparedStatement stmt = connection.prepareStatement(sql)) {
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
                            int reference=rs.getInt("REFERENCE");
                            String nProduit=rs.getString("NOM");
                            int fournisseur=rs.getInt("FOURNISSEUR");
                            int categorie=rs.getInt("CATEGORIE");
                            String quantiteParUnite=rs.getString("QUANTITE_PAR_UNITE");
                            double prixUnitaire=rs.getDouble("PRIX_UNITAIRE");
                            int unitesEnStock=rs.getInt("UNITES_EN_STOCK");
                            int unites_Commandees=rs.getInt("UNITES_COMMANDEES");
                            int niveauReaprovi=rs.getInt("NIVEAU_DE_REAPPRO");
                            int indispo=rs.getInt("INDISPONIBLE");
                            Produit p = new Produit(reference,nProduit,fournisseur,categorie,quantiteParUnite,prixUnitaire,
                                                        unitesEnStock,unites_Commandees,niveauReaprovi,indispo);
                            result.add(p);
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
        public List<Produit> allProductsFromCat(String cat) throws SQLException {
            
            List<Produit> result = new LinkedList<>();
            String sql = "";
            
            if (cat==null) {
                sql = "SELECT * FROM PRODUIT";;
            } else {
                sql = "SELECT * FROM PRODUIT INNER JOIN CATEGORIE ON PRODUIT.CATEGORIE = CATEGORIE.CODE WHERE LIBELLE LIKE ?";;
            }
            
            try (Connection connection = myDataSource.getConnection(); 
		     PreparedStatement stmt = connection.prepareStatement(sql)) {
                        if (cat!=null) {
                            stmt.setString(1, "%" + cat + "%");
                        } 
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
                            int reference=rs.getInt("REFERENCE");
                            String nProduit=rs.getString("NOM");
                            int fournisseur=rs.getInt("FOURNISSEUR");
                            int categorie=rs.getInt("CATEGORIE");
                            String quantiteParUnite=rs.getString("QUANTITE_PAR_UNITE");
                            double prixUnitaire=rs.getDouble("PRIX_UNITAIRE");
                            int unitesEnStock=rs.getInt("UNITES_EN_STOCK");
                            int unites_Commandees=rs.getInt("UNITES_COMMANDEES");
                            int niveauReaprovi=rs.getInt("NIVEAU_DE_REAPPRO");
                            int indispo=rs.getInt("INDISPONIBLE");
                            Produit p = new Produit(reference,nProduit,fournisseur,categorie,quantiteParUnite,prixUnitaire,
                                                        unitesEnStock,unites_Commandees,niveauReaprovi,indispo);
                            result.add(p);
                        }
            }
            return result;
        }
        
        /**
         * 
         * @param produit
         * @return le nombre d'enregistrements réalisé (1 ou 0 si non realisé)
         * @throws SQLException 
         */
        public int addProduct(Produit produit) throws SQLException {
            String sql = "INSERT INTO PRODUIT(REFERENCE,NOM,FOURNISSEUR,CATEGORIE,QUANTITE_PAR_UNITE,PRIX_UNITAIRE,UNITES_EN_STOCK,UNITES_COMMANDEES,NIVEAU_DE_REAPPRO,INDISPONIBLE)"
                    + " VALUES (?,?,?,?,?,?,?,?,?,?)";
            
            try (   Connection connection = myDataSource.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql)
                ) {
                        // Définir les valeurs du paramètre
			stmt.setInt(1, produit.getReference());
                        stmt.setString(2, produit.getnProduit());
                        stmt.setInt(3, produit.getFournisseur());
                        stmt.setInt(4, produit.getCategorie());
                        stmt.setString(5, produit.getQuantiteParUnite());
                        stmt.setDouble(6, produit.getPrixUnitaire());
                        stmt.setInt(7, produit.getUnitesEnStock());
                        stmt.setInt(8, produit.getUnitesCommandees());
                        stmt.setInt(9, produit.getNiveauReaprovi());
                        stmt.setInt(10, produit.getIndisponibilite());
			
			return stmt.executeUpdate();
		}
        }
        
        /**
         * Permet d'enregistrer les modifications d'un produit
         * @param produit
         * @return si la modification a été réalisé ou non ( 0 si ce n'est pas le cas)
         * @throws SQLException 
         */
        public int majModProduct(Produit produit) throws SQLException {
            String sql = " UPDATE PRODUIT SET NOM= ? ,FOURNISSEUR= ? ,CATEGORIE= ? ,QUANTITE_PAR_UNITE = ? ,PRIX_UNITAIRE = ? ,UNITES_EN_STOCK = ?,UNITES_COMMANDEES = ?,"
                    + "NIVEAU_DE_REAPPRO= ? ,INDISPONIBLE = ? WHERE REFERENCE= ? ";
            
            try (   Connection connection = myDataSource.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql)
                ) {
                        // Définir les valeurs du paramètre
                        stmt.setString(1, produit.getnProduit());
                        stmt.setInt(2, produit.getFournisseur());
                        stmt.setInt(3, produit.getCategorie());
                        stmt.setString(4, produit.getQuantiteParUnite());
                        stmt.setDouble(5, produit.getPrixUnitaire());
                        stmt.setInt(6, produit.getUnitesEnStock());
                        stmt.setInt(7, produit.getUnitesCommandees());
                        stmt.setInt(8, produit.getNiveauReaprovi());
                        stmt.setInt(9, produit.getIndisponibilite());
                        stmt.setInt(10,produit.getReference());
			
			return stmt.executeUpdate();
		}
            
        }
        
        
        /**
         * Permet de supprimer un produit après avoir supprimé les lignes où se produit etait
         * utilisé (problème de fk) 
         * @param nProduit 
         * @return le nombre d'enregistrements détruits (1 ou 0 si pas trouvé)
         * @throws SQLException
         */
        public int delProduct(int reference) throws SQLException {
            String sql = "DELETE FROM PRODUIT WHERE reference = ?";
            delProductAux(reference);
		try (   Connection connection = myDataSource.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql)
                ) {
                        // Définir la valeur du paramètre
			stmt.setInt(1, reference);
			
			return stmt.executeUpdate();

		}

        }
        
        /**
         * Permet de supprimer des lignes
         * @param reference
         * @return le nombre de lignes détruites (0 si non trouvé)
         * @throws SQLException 
         */
        public int delProductAux(int reference) throws SQLException {
             String sql = "DELETE FROM LIGNE WHERE PRODUIT = ?";
		try (   Connection connection = myDataSource.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql)
                ) {
                        // Définir la valeur du paramètre
			stmt.setInt(1, reference);
			
			return stmt.executeUpdate();
		}
        }

        //CATEGORIE ****************************************************************************************************************************
        
        /**
         * permet de retourner toutes les categories de produits
         * @return liste de categories
         * @throws SQLException 
         */
        public List<Categorie> allCategories() throws SQLException {
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


