/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Objects;

/**
 *
 * @author hvv
 */
public class Produit {
    
    private int reference;
    private String nProduit;
    private int fournisseur;
    private int categorie;
    private String quantiteParUnite; //can be NULL
    private double prixUnitaire;
    private int unitesEnStock;
    private int unites_Commandees;
    private int niveauReaprovi;
    private boolean indisponible;

    public Produit(int reference,String nProduit, int fournisseur, int categorie, String quantiteParUnite, double prixUnitaire, int unitesEnStock, int unites_Commandees, int niveauReaprovi, int indisponible) {
        this.reference=reference;
        this.nProduit = nProduit;
        this.fournisseur = fournisseur;
        this.categorie = categorie;
        this.quantiteParUnite = quantiteParUnite;
        this.prixUnitaire = prixUnitaire;
        this.unitesEnStock = unitesEnStock;
        this.unites_Commandees = unites_Commandees;
        this.niveauReaprovi = niveauReaprovi;
        if (indisponible ==1){
            this.indisponible = true;
        }
        else{
            this.indisponible = false;
        }
    }

    public int getReference() {
        return reference;
    }

    public String getnProduit() {
        return nProduit;
    }

    public int getFournisseur() {
        return fournisseur;
    }

    public int getCategorie() {
        return categorie;
    }

    public String getQuantiteParUnite() {
        return quantiteParUnite;
    }

    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public int getUnitesEnStock() {
        return unitesEnStock;
    }
    
    public int getUnitesCommandees() {
        return unites_Commandees;
    }

    public int getNiveauReaprovi() {
        return niveauReaprovi;
    }
    
    public int getIndisponibilite() {
        return (indisponible ? 1 : 0);
    }
    
    public boolean isIndisponible() {
        return indisponible;
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
        final Produit other = (Produit) obj;
        if (this.reference != other.reference) {
            return false;
        }
        if (this.fournisseur != other.fournisseur) {
            return false;
        }
        if (this.categorie != other.categorie) {
            return false;
        }
        if (Double.doubleToLongBits(this.prixUnitaire) != Double.doubleToLongBits(other.prixUnitaire)) {
            return false;
        }
        if (this.unitesEnStock != other.unitesEnStock) {
            return false;
        }
        if (this.unites_Commandees != other.unites_Commandees) {
            return false;
        }
        if (this.niveauReaprovi != other.niveauReaprovi) {
            return false;
        }
        if (this.indisponible != other.indisponible) {
            return false;
        }
        if (!Objects.equals(this.nProduit, other.nProduit)) {
            return false;
        }
        if (!Objects.equals(this.quantiteParUnite, other.quantiteParUnite)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "Produit{" + "reference= " + reference + ",\n nom= " + nProduit 
                + ",\n fournisseur= " + fournisseur + ",\n categorie= " + categorie 
                + ",\n quantite_par_unite= " + quantiteParUnite + ",\n prix_unitaire= " 
                + prixUnitaire + ",\n unites_en_stock= " + unitesEnStock + ",\n unites_commandes= " 
                + unites_Commandees + ",\n niveau_reaprovis= " + niveauReaprovi 
                + ",\n indisponible= " + indisponible + '}';
    }
    
}
