/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author hvv
 */
public class Commande {
    
    private int numero;
    private String client;
    private String saisieLe;
    private String envoyeeLe;
    private float port;
    private String destinataire;
    private String adresseLivraison;
    private String villeLivraison;
    private String regionLivraison;
    private String codePostalLivrais;
    private String paysLivraison;
    private float remise;

    public Commande(int numero, String client, String saisieLe, String envoyeeLe, float port, String destinataire, String adresseLivraison, String villeLivraison, String regionLivraison, String codePostalLivrais, String paysLivraison, float remise) {
        this.numero = numero;
        this.client = client;
        this.saisieLe = saisieLe;
        this.envoyeeLe = envoyeeLe;
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
