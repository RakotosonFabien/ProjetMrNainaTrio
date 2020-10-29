/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fabien.servlets;

import Antonio.utils.Helper;
import fabien.helpers.Helper1;
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
public class InsertionEcrivain extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet InsertionEcrivain</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet InsertionEcrivain at " + request.getContextPath() + "</h1>");
            out.println("<h1>Process</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        //Connection connexion = null;
        PrintWriter out = response.getWriter();
        RequestDispatcher dispatcher = null;
        try{
            //processRequest(request, response);

            //connexion = Helper.getConn();
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String annee = request.getParameter("annee");
            String mois = request.getParameter("mois");
            String jour = request.getParameter("jour");
            out.println(nom + "--" + prenom + "--" + annee + "--" + mois + "--" + jour);
            Date dateNaissance = Helper1.stringsToDate(annee, mois, jour);
            int idCategorie = Integer.parseInt(request.getParameter("idCategorie"));// new Integer(request.getParameter("idCategorie"));
            out.println("Fatina idCategorie" + request.getParameter("idCategorie"));
            Ecrivain ecrivain = new Ecrivain(idCategorie, nom, prenom, dateNaissance);
            
            ecrivain.save();
            
            //out.println("Insertion non reussi bitches. F*ck everything!!!!!");
            dispatcher = request.getRequestDispatcher("gestionErreur?classe=InsertionEcrivain&message=reussite");
            dispatcher.forward(request, response);
        }
        catch(Exception e){
            dispatcher = request.getRequestDispatcher("gestionErreur?classe=InsertionEcrivain&message="+e.getMessage());
            dispatcher.forward(request, response);
            
            //out.println("Insertion echouee==>"+e.getMessage());
            //out.println(e.getMessage());
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
