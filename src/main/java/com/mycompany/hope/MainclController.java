/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hope;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAO;
import model.DataSourceFactory;
        

/**
 *
 * @author 33647
 */
@WebServlet(name = "Maincl", urlPatterns = {"/Maincl"})
public class MainclController extends HttpServlet {

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
		try {
			DAO dao = new DAO(DataSourceFactory.getDataSource());
                        request.setAttribute("Categorie", dao.allCategories());
			request.setAttribute("Produit", dao.allProducts(cat)); // allProduct renvoi une liste de produit d'une catégorie, si cat = null => revoie tous les produits
			
                        switch (action) {
				case "DECONNEXION": // lien vers la page du visiteur
					request.getRequestDispatcher("Visiteur/Main.jsp").forward(request, response);						
					break;
				case "AJOUT": // lien vers l'ajout au panier
					request.getRequestDispatcher("Client/Ajoutcl.jsp").forward(request, response);
					break;
                                case "CADDIE": // lien vers le caddie
					request.getRequestDispatcher("Client/Caddie.jsp").forward(request, response);
					break;
			}
		} catch (Exception ex) {
			Logger.getLogger("Client").log(Level.SEVERE, "Action en erreur", ex);
			request.setAttribute("message", ex.getMessage());
		} finally {

		}
		// On continue vers la page JSP sélectionnée
		request.getRequestDispatcher("Main.jsp").forward(request, response);
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
