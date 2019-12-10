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
public class Produit {
    
    private int reference;
    private String nProduit;
    private int fournisseur;
    private int categorie;
    private String quantiteParUnite;
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
    
    
    
    
    
    
}
