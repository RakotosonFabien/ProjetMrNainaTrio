/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fabien.servlets;

import fabien.packEcrivain.Ecrivain;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Fabien
 */
public class ModificationEcrivain extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
 
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
        //Connection connexion = null;
        PrintWriter out = response.getWriter();
        try{
            //connexion = Antonio.utils.Helper.getConn();
            String id = request.getParameter("id");
            String idCategorie = request.getParameter("idCategorie");
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String annee = request.getParameter("annee");
            String mois = request.getParameter("mois");
            String jour = request.getParameter("jour");
            Date dateNaissance = fabien.helpers.Helper1.stringsToDate(annee, mois, jour);
            Ecrivain ecrivain = new Ecrivain(Integer.parseInt(idCategorie), nom, prenom, dateNaissance);
            Ecrivain.modify(Integer.parseInt(id), ecrivain);
            response.sendRedirect("ListeEcrivain.jsp");
        }
        catch(Exception e){
            out.println("Erreur ==> " + e.getMessage());
        }
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
