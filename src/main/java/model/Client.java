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
public class Client {

            private String code;
            private String societe;
            private String contact;
            private String fonction;//can be NULL
            private String adresse;//can be NULL
            private String ville;//can be NULL
            private String region;//can be NULL
            private String codePostal;//can be NULL
            private String pays;//can be NULL
            private String telephone;//can be NULL
            private String fax;//can be NULL

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

    public String getCode() {
        return code;
    }

    public String getSociete() {
        return societe;
    }

    public String getContact() {
        return contact;
    }

    public String getFonction() {
        return fonction;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getVille() {
        return ville;
    }

    public String getRegion() {
        return region;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public String getPays() {
        return pays;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getFax() {
        return fax;
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
        final Client other = (Client) obj;
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        if (!Objects.equals(this.societe, other.societe)) {
            return false;
        }
        if (!Objects.equals(this.contact, other.contact)) {
            return false;
        }
        if (!Objects.equals(this.fonction, other.fonction)) {
            return false;
        }
        if (!Objects.equals(this.adresse, other.adresse)) {
            return false;
        }
        if (!Objects.equals(this.ville, other.ville)) {
            return false;
        }
        if (!Objects.equals(this.region, other.region)) {
            return false;
        }
        if (!Objects.equals(this.codePostal, other.codePostal)) {
            return false;
        }
        if (!Objects.equals(this.pays, other.pays)) {
            return false;
        }
        if (!Objects.equals(this.telephone, other.telephone)) {
            return false;
        }
        if (!Objects.equals(this.fax, other.fax)) {
            return false;
        }
        return true;
    }
            
    @Override
    public String toString() {
        return "Client{" + "code= " + code + ",\n societe= " + societe + ",\n contact= " 
                + contact + ",\n fonction= " + fonction + ",\n adresse= " + adresse 
                + ",\n ville= " + ville + ",\n region= " + region + ",\n codePostal= " + codePostal 
                + ",\n pays= " + pays + ",\n telephone= " + telephone + ",\n fax= " + fax + '}';
    }
    
}
