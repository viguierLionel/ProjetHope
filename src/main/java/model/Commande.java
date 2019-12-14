/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Objects;

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
    
    @Override
    public String toString() {
        return "Commande{" + " numero= " + numero + ",\n client= " + client + ",\n saisieLe= " 
                + saisieLe + ",\n envoyeLe= " + envoyeeLe + ",\n port= " + port + ",\n destinataire= " 
                + destinataire + ",\n adresseLivraison= " + adresseLivraison + ",\n villeLivraison= " 
                + villeLivraison + ",\n regionLivraison= " + regionLivraison + ",\n codePostalLivraison= " 
                + codePostalLivrais + ",\n paysLivraison= " + paysLivraison + ",\n remise= " + remise + '}';
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Commande other = (Commande) obj;
        if (this.numero != other.numero) {
            return false;
        }
        if (Double.doubleToLongBits(this.port) != Double.doubleToLongBits(other.port)) {
            return false;
        }
        if (Double.doubleToLongBits(this.remise) != Double.doubleToLongBits(other.remise)) {
            return false;
        }
        if (!Objects.equals(this.client, other.client)) {
            return false;
        }
        if (!Objects.equals(this.destinataire, other.destinataire)) {
            return false;
        }
        if (!Objects.equals(this.adresseLivraison, other.adresseLivraison)) {
            return false;
        }
        if (!Objects.equals(this.villeLivraison, other.villeLivraison)) {
            return false;
        }
        if (!Objects.equals(this.regionLivraison, other.regionLivraison)) {
            return false;
        }
        if (!Objects.equals(this.codePostalLivrais, other.codePostalLivrais)) {
            return false;
        }
        if (!Objects.equals(this.paysLivraison, other.paysLivraison)) {
            return false;
        }
        if (!Objects.equals(this.saisieLe, other.saisieLe)) {
            return false;
        }
        if (!Objects.equals(this.envoyeeLe, other.envoyeeLe)) {
            return false;
        }
        if (!Objects.equals(this.formatDate, other.formatDate)) {
            return false;
        }
        return true;
    }

    public int getNumero() {
        return numero;
    }

    public String getClient() {
        return client;
    }

    public Date getSaisieLe() {
        return saisieLe;
    }
    
    public String getStringSaisieLe() {
        return formatDate.format(saisieLe);
    }

    public Date getEnvoyeeLe() {
        return envoyeeLe;
    }

    public String getStringEnvoyeeLe() {
        return formatDate.format(envoyeeLe);
    }
    
    public double getPort() {
        return port;
    }

    public String getDestinataire() {
        return destinataire;
    }

    public String getAdresseLivraison() {
        return adresseLivraison;
    }

    public String getVilleLivraison() {
        return villeLivraison;
    }

    public String getRegionLivraison() {
        return regionLivraison;
    }

    public String getCodePostalLivrais() {
        return codePostalLivrais;
    }

    public String getPaysLivraison() {
        return paysLivraison;
    }

    public double getRemise() {
        return remise;
    }

    public SimpleDateFormat getFormatDate() {
        return formatDate;
    }

    
    
}