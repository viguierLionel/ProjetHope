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
public class Ligne {
    
    private int commande;
    private int produit;
    private int quantite;

    public Ligne(int commande, int produit, int quantite) {
        this.commande = commande;
        this.produit = produit;
        this.quantite = quantite;
    }

    public int getCommande() {
        return commande;
    }

    public int getProduit() {
        return produit;
    }

    public int getQuantite() {
        return quantite;
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
        final Ligne other = (Ligne) obj;
        if (this.commande != other.commande) {
            return false;
        }
        if (this.produit != other.produit) {
            return false;
        }
        if (this.quantite != other.quantite) {
            return false;
        }
        return true;
    }
    
}
