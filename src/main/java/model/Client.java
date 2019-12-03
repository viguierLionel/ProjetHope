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
public class Client {

            private String code;
            private String societe;
            private String contact;
            private String fonction;
            private String adresse;
            private String ville;
            private String region;
            private String codePostal;
            private String pays;
            private String telephone;
            private String fax;

    public Client(String code, String societe, String contact, String fonction, String adresse, String ville, String region, String codePostal, String pays, String telephone, String fax) {
        this.code = code;
        this.societe = societe;
        this.contact = contact;
        this.fonction = fonction;
        this.adresse = adresse;
        this.ville = ville;
        this.region = region;
        this.codePostal = codePostal;
        this.pays = pays;
        this.telephone = telephone;
        this.fax = fax;
    }
            
            
}
