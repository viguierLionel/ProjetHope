package com.mycompany.hope;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLIntegrityConstraintViolationException;
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
 * @author pedago
 */
@WebServlet(name = "Addc", urlPatterns = {"/Addc"})
public class AjoutclController extends HttpServlet {

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
        String a = request.getParameter("Article");
	String nb = request.getParameter("NOMBRE");// pour recuperer le nombre d'objet a commander
        
		try {
                    
			DAO dao = new DAO(DataSourceFactory.getDataSource());
                        request.setAttribute("Articles", dao.selectNomProduct(a));//prend un string envoie un produit
                        switch (action) {
				case "RETOUR": // lien vers la page du visiteur
					request.getRequestDispatcher("Client/Maincl.jsp").forward(request, response);						
					break;
				case "AJOUTER": // lien vers l'ajout au panier
                                        if(dao.selectProductWithNom(a).getIndisponibilite()!=1)//methode a créer...
                                            {//dao.ajoutPanier(nb,a);
                                            request.setAttribute("Message", "l'objet a bien été ajouter au caddie.");
                                        }
                                        else{
                                            request.setAttribute("Message", "impossible d'ajouter l'article.");
                                        }
					request.getRequestDispatcher("Client/Caddie.jsp").forward(request, response);
					break;
			}
			
                        
		} catch (Exception ex) {
			Logger.getLogger("Client").log(Level.SEVERE, "Action en erreur", ex);
			request.setAttribute("message", ex.getMessage());
		} finally {

		}
		request.getRequestDispatcher("Client/Ajoutcl.jsp").forward(request, response);
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