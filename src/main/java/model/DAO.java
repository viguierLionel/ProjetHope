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
     * Permet d'avoir le Contact d'un client en fonction du code  
     * @param code
     * @return
     * @throws SQLException 
     */
    public Client selectClient(String code) throws SQLException {
        Client client = null;
        String sql = "SELECT * FROM CLIENT WHERE CODE = ?";
        try (Connection myConnection = myDataSource.getConnection();
                PreparedStatement statement = myConnection.prepareStatement(sql)) {
                    statement.setString(1, code);
                    try (ResultSet rs = statement.executeQuery()) {
                        if (rs.next()) {
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
                            client = new Client(code, societe, contact, fonction, adresse, ville, region, codePostal, pays, telephone, fax);
                        }
                    }
        }
        return client;
    }


    /**
     * Permet de voir si un mot de passe (pwd) existe
     * @param pwd
     * @return 0 si le password n'existe pas, 1 si il existe 
     * @throws SQLException 
     */
    public int pwdExiste(String pwd) throws SQLException {
        int resultat = 0;

        String sql = "SELECT * FROM CLIENT WHERE CODE = ?";
        try (Connection myConnection = myDataSource.getConnection();
                PreparedStatement statement = myConnection.prepareStatement(sql)) {
                    statement.setString(1, pwd);
                    try (ResultSet rs = statement.executeQuery()) {
                        if (rs.next()) {
                            resultat = 1;
                        }
                    }
        }
        return resultat;
    }

    /**
     * Permet de voir si un compte existe ou non ainsi que sa nature (client ou admin)
     * @param id
     * @param pwd
     * @return 0 si le compte n'existe pas, 1 si il existe et est celui d'un client, 2 si il existe et est celui un admin
     * @throws SQLException 
     */
    public int compteExiste(String id, String pwd) throws SQLException {
        int resultat = 0;
        if (id.equals("admin") && pwd.equals("admin")) {
            resultat = 2;
        } else {
            String sql = "SELECT * FROM CLIENT WHERE CONTACT = ? AND CODE = ?";
            try (Connection myConnection = myDataSource.getConnection();
                    PreparedStatement statement = myConnection.prepareStatement(sql)) {
                        statement.setString(1, id);
                        statement.setString(2, pwd);
                        try (ResultSet rs = statement.executeQuery()) {
                            if (rs.next()) {
                                resultat = 1;
                            }
                        }
            }
        }
        return resultat;
    }
    
    /**
     * Permet d'ajouter un nouveau client
     * @param client
     * @return le nombre d'enregistrements réalisé (1 ou 0 si non realisé)
     * @throws SQLException 
     */
    public int addClient(Client client) throws SQLException {
        String sql = "INSERT INTO CLIENT(CODE,SOCIETE,CONTACT,FONCTION,ADRESSE,VILLE,REGION,CODE_POSTAL,PAYS,TELEPHONE,FAX) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        
        try (   Connection connection = myDataSource.getConnection();
                    PreparedStatement stmt = connection.prepareStatement(sql)
            ) {
                    // Définir les valeurs du paramètre
                    stmt.setString(1, client.getCode());
                    stmt.setString(2, client.getSociete());
                    stmt.setString(3, client.getContact());
                    stmt.setString(4, client.getFonction());
                    stmt.setString(5, client.getAdresse());
                    stmt.setString(6, client.getVille());
                    stmt.setString(7, client.getRegion());
                    stmt.setString(8, client.getCodePostal());
                    stmt.setString(9, client.getPays());
                    stmt.setString(10, client.getTelephone());
                    stmt.setString(11, client.getFax());

                    return stmt.executeUpdate();
            }
    }

    /**
     * Permet d'enregistrer les modifications d'un client
     * @param client
     * @return si la modification a été réalisé ou non ( 0 si ce n'est pas le cas)
     * @throws SQLException 
     */
    public int majModClient(Client client) throws SQLException {

        String sql = "UPDATE CLIENT SET SOCIETE=?,CONTACT=?,FONCTION=?,ADRESSE=?,VILLE=?,REGION=?,CODE_POSTAL=?,PAYS=?,"
                + "TELEPHONE=?,FAX=? WHERE CODE=?";

        try (Connection myConnection = myDataSource.getConnection();
                PreparedStatement statement = myConnection.prepareStatement(sql)) {
                    statement.setString(1, client.getSociete());
                    statement.setString(2, client.getContact());
                    statement.setString(3, client.getFonction());
                    statement.setString(4, client.getAdresse());
                    statement.setString(5, client.getVille());
                    statement.setString(6, client.getRegion());
                    statement.setString(7, client.getCodePostal());
                    statement.setString(8, client.getPays());
                    statement.setString(9, client.getTelephone());
                    statement.setString(10, client.getFax());
                    statement.setString(11, client.getCode());

                    return statement.executeUpdate();
        }
    }

    //PRODUIT ****************************************************************************************************************************

    /**
     * permet d'avoir un produit en fonction d'une reference
     * @return le produit
     * @throws java.sql.SQLException renvoyées par JDBC
     */
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
     * Permet d'avoir un produit en fonction de son nom
     * @param nom
     * @return
     * @throws SQLException 
     */
    public Produit selectProductWithNom(String nom) throws SQLException {
        Produit resultat = null;
         String sql = "SELECT * FROM PRODUIT WHERE NOM = ? ";
         try (Connection connection = myDataSource.getConnection(); 
                 PreparedStatement stmt = connection.prepareStatement(sql)) {
                    stmt.setString(1, nom);
                    ResultSet rs = stmt.executeQuery();
                    while (rs.next()) {
                        int reference=rs.getInt("REFERENCE");
                        int fournisseur=rs.getInt("FOURNISSEUR");
                        int categorie=rs.getInt("CATEGORIE");
                        String quantiteParUnite=rs.getString("QUANTITE_PAR_UNITE");
                        double prixUnitaire=rs.getDouble("PRIX_UNITAIRE");
                        int unitesEnStock=rs.getInt("UNITES_EN_STOCK");
                        int unites_Commandees=rs.getInt("UNITES_COMMANDEES");
                        int niveauReaprovi=rs.getInt("NIVEAU_DE_REAPPRO");
                        int indispo=rs.getInt("INDISPONIBLE");
                        Produit p = new Produit(reference,nom,fournisseur,categorie,quantiteParUnite,prixUnitaire,
                                                    unitesEnStock,unites_Commandees,niveauReaprovi,indispo);
                        resultat = p;
                    }
        }
        return resultat;
    }

    /**
     * permet d'avoir une liste de produits dont le nom possède le mot clé entré en argument
     * @return liste de produits
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
     * Permet d'avoir tout les produits
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
     * Permet d'ajouter un nouveau produit
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
     * Permet de supprimer un produit après avoir supprimé les lignes où le produit etait
     * utilisé (problème de fk) 
     * @param reference 
     * @return le nombre d'enregistrements détruits (1 ou 0 si pas trouvé)
     * @throws SQLException
     */
    public int delProduct(int reference) throws SQLException {
        String sql = "DELETE FROM PRODUIT WHERE reference = ?";
        delProductAux(reference);
            try (   Connection connection = myDataSource.getConnection();
                    PreparedStatement stmt = connection.prepareStatement(sql)) {
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

    //COMMANDE ****************************************************************************************************************************

     /**
     * Permet d'avoir toute les commandes
     * @return liste de toute les commandes
     * @throws SQLException
     */
    public List<Commande> allCommandes() throws SQLException {
        List<Commande> resultat = new ArrayList<Commande>();
        String sql = "SELECT * FROM COMMANDE";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
                    ResultSet rs = stmt.executeQuery();
                    while (rs.next()) {
                        int code = rs.getInt("NUMERO");
                        String client = rs.getString("CLIENT");
                        String saisieLe = rs.getString("SAISIE_LE");
                        String envoyeeLe = rs.getString("ENVOYEE_LE");
                        double port = rs.getDouble("PORT");
                        String destinataire = rs.getString("DESTINATAIRE");
                        String adresseLivraison = rs.getString("ADRESSE_LIVRAISON");
                        String villeLivraison = rs.getString("VILLE_LIVRAISON");
                        String regionLivraison = rs.getString("REGION_LIVRAISON");
                        String codePostalLivrais = rs.getString("CODE_POSTAL_LIVRAIS");
                        String paysLivraison = rs.getString("PAYS_LIVRAISON");
                        double remise = rs.getDouble("REMISE");

                        Commande c = new Commande(code, client, saisieLe, envoyeeLe, port,
                                destinataire, adresseLivraison, villeLivraison, regionLivraison,
                                codePostalLivrais, paysLivraison, remise);
                        resultat.add(c);
                    }
        }
        return resultat;
    }

    /**
     * permet d'avoir une commande en fonction d'un numéro
     * @return une commande
     * @throws java.sql.SQLException renvoyées par JDBC
     */
    public Commande selectCommande(int numero) throws SQLException {
        Commande resultat = null;
        String sql = "SELECT * FROM COMMANDE WHERE NUMERO = ?";

        try (Connection connection = myDataSource.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, numero);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    String client = rs.getString("CLIENT");
                    String saisieLe = rs.getString("SAISIE_LE");
                    String envoyeeLe = rs.getString("ENVOYEE_LE");
                    double port = rs.getDouble("PORT");
                    String destinataire = rs.getString("DESTINATAIRE");
                    String adresseLivraison = rs.getString("ADRESSE_LIVRAISON");
                    String villeLivraison = rs.getString("VILLE_LIVRAISON");
                    String regionLivraison = rs.getString("REGION_LIVRAISON");
                    String codePostalLivrais = rs.getString("CODE_POSTAL_LIVRAIS");
                    String paysLivraison = rs.getString("PAYS_LIVRAISON");
                    double remise = rs.getDouble("REMISE");

                    Commande c = new Commande(numero, client, saisieLe, envoyeeLe, port, destinataire, adresseLivraison,
                            villeLivraison, regionLivraison, codePostalLivrais, paysLivraison, remise);
                    resultat = c;
                }
        }
        return resultat;
    } 
    /**
     * Permet d'ajouter une commande
     * @param commande
     * @return le nombre d'enregistrements réalisé (1 ou 0 si non realisé)
     * @throws SQLException 
     */
    public int addCommande(Commande commande) throws SQLException {
        String sql = "INSERT INTO COMMANDE VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
                    stmt.setInt(1, commande.getNumero());
                    stmt.setString(2, commande.getClient());
                    stmt.setString(3, commande.getStringSaisieLe());
                    stmt.setString(4, commande.getStringEnvoyeeLe());
                    stmt.setDouble(5, commande.getPort());
                    stmt.setString(6, commande.getDestinataire());
                    stmt.setString(7, commande.getAdresseLivraison());
                    stmt.setString(8, commande.getVilleLivraison());
                    stmt.setString(9, commande.getRegionLivraison());
                    stmt.setString(10, commande.getCodePostalLivrais());
                    stmt.setString(11, commande.getPaysLivraison());
                    stmt.setDouble(12, commande.getRemise());

                    return stmt.executeUpdate();
        }
    }

    /**
     * Permet d'enregistrer les modifications d'une commande
     * @param commande
     * @return si la modification a été réalisé ou non ( 0 si ce n'est pas le cas)
     * @throws SQLException 
     */
    public int majModCommande(Commande commande) throws SQLException {
        String sql = " UPDATE COMMANDE SET CLIENT= ? ,SAISIE_LE= ? ,ENVOYEE_LE= ? ,PORT = ? ,DESTINATAIRE = ? ,ADRESSE_LIVRAISON = ?,VILLE_LIVRAISON = ?,"
                + "REGION_LIVRAISON= ? ,CODE_POSTAL_LIVRAIS = ?,PAYS_LIVRAISON=?,REMISE=? WHERE NUMERO= ? ";

        try (   Connection connection = myDataSource.getConnection();
                    PreparedStatement stmt = connection.prepareStatement(sql)) {
                        // Définir les valeurs du paramètre
                        stmt.setString(1, commande.getClient());
                        stmt.setString(2, commande.getStringSaisieLe());
                        stmt.setString(3, commande.getStringEnvoyeeLe());
                        stmt.setDouble(4, commande.getPort());
                        stmt.setString(5, commande.getDestinataire());
                        stmt.setString(6, commande.getAdresseLivraison());
                        stmt.setString(7, commande.getVilleLivraison());
                        stmt.setString(8, commande.getRegionLivraison());
                        stmt.setString(9, commande.getCodePostalLivrais());
                        stmt.setString(10, commande.getPaysLivraison());
                        stmt.setDouble(11, commande.getRemise());
                        stmt.setInt(12, commande.getNumero());

                        return stmt.executeUpdate();
            }
    }

    /**
     * Permet de supprimer une commande
     * @param numero 
     * @return le nombre d'enregistrements détruits (1 ou 0 si pas trouvé)
     * @throws SQLException
     */
    public int delCommande(int numero) throws SQLException {
        String sql = "DELETE FROM COMMANDE WHERE NUMERO = ?";

        try (Connection connection = myDataSource.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, numero);
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

    //LIGNE ****************************************************************************************************************************

    /**
     * permet de retourner toutes les lignes
     * @return liste de ligne
     * @throws SQLException 
     */
    public List<Ligne> allLignes() throws SQLException {
        List<Ligne> resultat = new ArrayList();
        String sql = "SELECT * FROM LIGNE";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
                    ResultSet rs = stmt.executeQuery();
                    while (rs.next()) {
                        int commande = rs.getInt("COMMANDE");
                        int produit = rs.getInt("PRODUIT");
                        int quantite = rs.getInt("QUANTITE");
                        Ligne l = new Ligne(commande, produit, quantite);
                        resultat.add(l);
                    }
        }
        return resultat;
    }

    /**
     * Permet d'avoir toute les lignes d'une commande
     * @param commande
     * @return liste de ligne
     * @throws SQLException 
     */
    public List<Ligne> selectLignesCommande(int commande) throws SQLException {
        List<Ligne> resultat = new ArrayList<Ligne>();
        String sql = "SELECT * FROM LIGNE WHERE COMMANDE = ?";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
                    stmt.setInt(1,commande);
                    ResultSet rs = stmt.executeQuery();
                    while (rs.next()) {
                        int produit = rs.getInt("PRODUIT");
                        int quantite = rs.getInt("QUANTITE");
                        Ligne l = new Ligne(commande, produit, quantite);
                        resultat.add(l);
                    }
        }
        return resultat;
    }
      
    /**
     * Permet d'avoir tout les produits (leur reference) d'une commande
     * @param commande
     * @return liste d'entier
     * @throws SQLException 
     */
    public List<Integer> selectProduitsCommande(int commande) throws SQLException {
        List<Integer> resultat = new ArrayList<Integer>();
        String sql = "SELECT * FROM LIGNE WHERE COMMANDE = ?";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
                    stmt.setInt(1,commande);
                    ResultSet rs = stmt.executeQuery();
                    while (rs.next()) {
                        resultat.add(rs.getInt("PRODUIT"));
                    }
        }
        return resultat;
    }
        
        
    /**
     * Permet d'ajouter une ligne
     * @param ligne
     * @return le nombre d'enregistrements réalisé (1 ou 0 si non realisé)
     * @throws SQLException 
     */
    public int addLigne(Ligne ligne) throws SQLException {
        String sql = "INSERT INTO LIGNE VALUES (?,?,?)";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
                    stmt.setInt(1, ligne.getCommande());
                    stmt.setInt(2, ligne.getProduit());
                    stmt.setInt(3, ligne.getQuantite());

                    return stmt.executeUpdate();
        }
    }
    
    /**
     * Permet d'enregistrer les modifications d'une ligne
     * @param ligne
     * @return si la modification a été réalisé ou non ( 0 si ce n'est pas le cas)
     * @throws SQLException 
     */
    public int majModLigne(Ligne ligne) throws SQLException {
        String sql = " UPDATE LIGNE SET QUANTITE= ? WHERE PRODUIT= ? AND COMMANDE= ? ";

        try (   Connection connection = myDataSource.getConnection();
                    PreparedStatement stmt = connection.prepareStatement(sql)) {
                        // Définir les valeurs du paramètre
                        stmt.setInt(1, ligne.getQuantite());
                        stmt.setInt(2, ligne.getProduit());
                        stmt.setInt(3, ligne.getCommande());

                        return stmt.executeUpdate();
            }
    }
        
    
    /**
     * Permet de supprimer une ligne
     * @param commande
     * @param produit
     * @return le nombre d'enregistrements détruits (1 ou 0 si pas trouvé)
     * @throws SQLException
     */
    public int delLigne(int commande,int produit) throws SQLException {
        String sql = "DELETE FROM LIGNE WHERE COMMANDE= ? AND PRODUIT= ?";
        
        try (   Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
                    // Définir la valeur du paramètre
                    stmt.setInt(1, commande);
                    stmt.setInt(2, produit);
                    return stmt.executeUpdate();

        }
    }
    
    
    
}


