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
import javax.servlet.http.HttpSession;
import model.DAO;
import model.Client;
import model.DataSourceFactory;

/**
 *
 * @author 33647
 */
@WebServlet(name = "EditclControlleur", urlPatterns = {"/EditclControlleur"})
public class EditclControlleur extends HttpServlet {

/**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
        HttpSession session=request.getSession();
		try {
			DAO dao = new DAO(DataSourceFactory.getDataSource());
			request.setAttribute("Donne", dao.DoneeClient(session.getAttribute("login"))); 
                        switch (action) {
                            case "MODIFIER":
                                String code  = request.getParameter("code");
                                String societe  = request.getParameter("societe");
                                String contact  = request.getParameter("contact");
                                String fonction  = request.getParameter("fonction");
                                String adresse  = request.getParameter("adresse");
                                String ville  = request.getParameter("ville");
                                String region  = request.getParameter("region");
                                String codePostal  = request.getParameter("codePostal");
                                String pays  = request.getParameter("pays");
                                String telephone  = request.getParameter("telephone");
                                String fax  = request.getParameter("fax");
                                Client c = new Client(code,societe,contact,fonction,adresse,ville,region,codePostal,pays,telephone,fax);
                                request.setAttribute("Message Ajout", dao.majModClient(c));
                                break;
                            case "RETOUR":
                                request.getRequestDispatcher("Client/Maincl.jsp").forward(request, response);
                                break;
				
			}
		} catch (Exception ex) {
			Logger.getLogger("Client").log(Level.SEVERE, "Action en erreur", ex);
			request.setAttribute("message", ex.getMessage());
		} finally {

		}
		// On continue vers la page JSP sélectionnée
		request.getRequestDispatcher("Caddie.jsp").forward(request, response);
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
