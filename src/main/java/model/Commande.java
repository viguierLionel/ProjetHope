/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author hvv
 */
public class Commande {
    
    private int numero;
    private String client;
    private Date saisieLe;//can be NULL
    private Date envoyeeLe;//can be NULL
    private double port;//can be NULL
    private String destinataire;//can be NULL
    private String adresseLivraison;//can be NULL
    private String villeLivraison;//can be NULL
    private String regionLivraison;//can be NULL
    private String codePostalLivrais;//can be NULL
    private String paysLivraison;//can be NULL
    private double remise;
    SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");

    public Commande(int numero, String client, String saisieLe, String envoyeeLe, double port, String destinataire, String adresseLivraison, String villeLivraison, String regionLivraison, String codePostalLivrais, String paysLivraison, double remise) {
        this.numero = numero;
        this.client = client;
        try{ 
            this.saisieLe = formatDate.parse(saisieLe);
        }catch(Exception e){
            this.saisieLe = null;
        }
        try{
             this.envoyeeLe = formatDate.parse(envoyeeLe);
        }catch(Exception e){
            this.envoyeeLe = null;
        }
        this.port = port;
        this.destinataire = destinataire;
        this.adresseLivraison = adresseLivraison;
        this.villeLivraison = villeLivraison;
        this.regionLivraison = regionLivraison;
        this.codePostalLivrais = codePostalLivrais;
        this.paysLivraison = paysLivraison;
        this.remise = remise;
    }
    
    public boolean dteVerif(String dte){
        boolean dteConforme=false;

        if (dte.length()==10 && dte.charAt(4) =='-' && dte.charAt(7)=='-'){
            dteConforme=true;
        } else {
            dteConforme=false;
        }
        return dteConforme;
    }
    
}
