/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fabien.servlets;

import Antonio.utils.Helper;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import fabien.packClient.Client;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import fabien.packClient.Client;
/**
 *
 * @author Fabien
 */
public class Inscription1 extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    /*
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
    */
            /* TODO output your page here. You may use following sample code. */
    /*        out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Inscription</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Inscription at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
        
    }
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
        //processRequest(request, response);
        PrintWriter out = response.getWriter(); 
        out.println("CAOIWEAORJOWJE");

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
        //processRequest(request, response);
        //RequestDispatcher dispat = null;
        Connection connexion = null;
        try{
            connexion = Helper.getConn();
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            Date naissance = new Date(new Integer(request.getParameter("annee"))-1900, new Integer(request.getParameter("mois")), new Integer(request.getParameter("jour")));
            String adresse = request.getParameter("adresse");
            String mdp = request.getParameter("mdp");
            String mail = request.getParameter("mail");
            Client nouveauMembre = new Client(nom, prenom, naissance, adresse, mdp, mail);
            nouveauMembre.save(connexion);
            PrintWriter out = response.getWriter(); 
            out.println("Reussite");
            //dispat = request.getRequestDispatcher("/gestionErreur?classe=Inscription&&message=reussite");
            //dispat.forward(request,response); 
        }
        catch(Exception e){
            //dispat = request.getRequestDispatcher("/gestionErreur?classe=Inscription&&message="+e.getMessage());
            //dispat.forward(request,response); 
            PrintWriter out = response.getWriter(); 
            out.println(e.getMessage());
        }
        finally{
            try {
                if(connexion!=null)connexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(Inscription.class.getName()).log(Level.SEVERE, null, ex);
            }
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
