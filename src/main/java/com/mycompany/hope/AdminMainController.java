/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hope;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.DAO;
import model.DataSourceFactory;

/**
 *
 * @author 33647
 */
@WebServlet(name = "AdminMainController", urlPatterns = {"/AdminMainController"})
public class AdminMainController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
	action = (action == null) ? "" : action; // Pour le switch qui n'aime pas les null
	String cat = request.getParameter("catego");// pour recuperer la catégorie du volet déroulant Categorie
        HttpSession session=request.getSession();
		try {
			DAO dao = new DAO(DataSourceFactory.getDataSource());
                        request.setAttribute("Categorie", dao.allCategories());
                        //on fait en sorte d'afficher les produit par catégorie, si la catégorie n'est pas selectioner on affiche tous les produits
                        if( cat == null){request.setAttribute("Produit", dao.allProducts());}
                        else{request.setAttribute("Produit", dao.allProductsFromCat(cat));}

			/*Ici on initialise les graphiques*/
                        request.setAttribute("Cat", dao.chiffreParCatego(null,null));// chiffreParCatégo renvoie quand dateDeb et DateFin sont null le chiffre d'affaire sur tous le temps
                        request.setAttribute("Pays", dao.chiffreParPays(null,null));// chiffreParPays renvoie quand dateDeb et DateFin sont null le chiffre d'affaire sur tous le temps
                        request.setAttribute("Client", dao.chiffreParClient(null,null));// chiffreParClient renvoie quand dateDeb et DateFin sont null le chiffre d'affaire sur tous le temps

                        

                        
                        switch (action) {
				case "DECONNEXION": // lien vers la page du visiteur
                                        session.setAttribute("Login", null);
					request.getRequestDispatcher("Visiteur/Main.jsp").forward(request, response);
					break;
				case "CAT": // Réactualisation du graphe par catégorie
                                        String dateDeb = request.getParameter("DebCat");
                                        String dateFin = request.getParameter("FinCat");
                                        request.setAttribute("Categorie", dao.chiffreParCatégo(dateDeb,dateFin));// chiffreParCatégo renvoie quand dateDeb et DateFin sont null le chiffre d'affaire sur tous le temps
					break;
                                
				case "PAYS": // Réactualisation du graphe par catégorie
                                        String dateDebP = request.getParameter("Debpays");
                                        String dateFinP = request.getParameter("Finpays");
                                        request.setAttribute("Pays", dao.chiffreParPays(dateDebP,dateFinP));// chiffreParPays renvoie quand dateDeb et DateFin sont null le chiffre d'affaire sur tous le temps
					break;
                                
				case "CLIENT": // Réactualisation du graphe par Client
                                        String dateDebC = request.getParameter("DebCli");
                                        String dateFinC = request.getParameter("FinCli");
                                        request.setAttribute("Client", dao.chiffreParClient(dateDebC,dateFinC));// chiffreParClient renvoie quand dateDeb et DateFin sont null le chiffre d'affaire sur tous le temps
					break;
                                case "AJOUTER": // Ajouter un Produit qui recupere nom fournisseur     private int reference;

                                        String nom  = request.getParameter("nom");
                                        String fournisseur  = request.getParameter("fournisseur");
                                        String categorie   = request.getParameter("categorie");
                                        int quantiteParUnite   = Integer.parseInt(request.getParameter("quantiteParUnite")  ) ;
                                        String prixUnitaire   = request.getParameter("prixUnitaire");
                                        int unitesEnStock   = Integer.parseInt(request.getParameter("unitesEnStock"));
                                        int unites_Commandees   = Integer.parseInt(request.getParameter("unites_Commandees"));
                                        String niveauReaprovi   = request.getParameter("niveauReaprovi");
                                        int indisponible = Integer.parseInt(request.getParameter("indisponible"));


                                        request.setAttribute("Message Ajout", dao.ajoutProduit(nom,fournisseur,categorie,quantiteParUnite,prixUnitaire,unitesEnStock,unites_Commandees,niveauReaprovi,indisponible));
    
                                        break;
                                case "SUPPRIMER": // Supprimer produit
                                        String Supp = request.getParameter("CodeSupp");
					request.setAttribute("Message Suppression", dao.delProduct(Integer.parseInt(Supp)));
                                        break;
                                case "MODIFIER": // lien vers le caddie
                                        String codemodif = request.getParameter("codeModif");
                                        request.setAttribute("Modif", dao.afficheProduit(codemodif));
                                        request.getRequestDispatcher("ModifAd.jsp").forward(request, response);
                                        break;
                                case "VALIDMODIF":
                                        String nomM  = request.getParameter("nom");
                                        String fournisseurM  = request.getParameter("fournisseur");
                                        String categorieM   = request.getParameter("categorie");
                                        int quantiteParUniteM  = Integer.parseInt(request.getParameter("quantiteParUnite")  ) ;
                                        String prixUnitaireM   = request.getParameter("prixUnitaire");
                                        int unitesEnStockM   = Integer.parseInt(request.getParameter("unitesEnStock"));
                                        int unites_CommandeesM   = Integer.parseInt(request.getParameter("unites_Commandees"));
                                        String niveauReaproviM   = request.getParameter("niveauReaprovi");
                                        int indisponibleM = Integer.parseInt(request.getParameter("indisponible"));
                                        
                                        request.setAttribute("Message Ajout", dao.modifClient(nomM,fournisseurM,categorieM,quantiteParUniteM,prixUnitaireM,unitesEnStockM,unites_CommandeesM,niveauReaproviM,indisponibleM));
                                        request.getRequestDispatcher("Admin/Mainadmin.jsp").forward(request, response);
                                        
			}
		} catch (Exception ex) {
			Logger.getLogger("Client").log(Level.SEVERE, "Action en erreur", ex);
			request.setAttribute("message", ex.getMessage());
		} finally {

		}
		// On continue vers la page JSP suivante
		request.getRequestDispatcher("Admin/Mainadmin.jsp").forward(request, response);
	}
        

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
