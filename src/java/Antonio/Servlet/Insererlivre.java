/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Antonio.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Antonio.packCategorie.Categorie;
import Antonio.packLivre.Livre;
import Antonio.utils.Helper;
import static java.lang.Math.round;
import java.sql.Connection;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author Antonio
 */
public class Insererlivre extends HttpServlet {

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
        PrintWriter out=response.getWriter();
        
        out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            //out.println("<title>Servlet Insererlivre</title>");            
            out.println("</head>");
            out.println("<body>");
            //out.println("<h1>Servlet Insererlivre at " + request.getContextPath() + "</h1>");
            
        String idec=request.getParameter("idc");
            String idcat=request.getParameter("idcat");
            String title=request.getParameter("titre");
            String desc=request.getParameter("description");
            String dates=request.getParameter("dates");
            String prix=request.getParameter("prix");
            String nbr=request.getParameter("nombre");
 if(idec!=null & idec!="" || idcat!=null & idcat!="" || title!=null & title!="" || desc!=null & desc!="" || dates!=null & dates!="" || prix!=null &prix!="" || nbr!=null &prix!=""){
 
    RequestDispatcher rsd=null; 
     try{
 int idc=Integer.parseInt(idec);
 int idcateg=Integer.parseInt(idcat);
 String[] datess=dates.split("-");
 Double price=new Double(prix);
 double prices=price.doubleValue();
 int nbrs=Integer.parseInt(nbr);
 
  //Connection conn=new Helper().getConn();
 Date d=new Date(Integer.parseInt(datess[0])-1900,Integer.parseInt(datess[1])-1,Integer.parseInt(datess[2]));
  Livre vaovao=new Livre(idc,idcateg,title,desc,d,prices,nbrs);
 out.print(vaovao.getIdecrivain());
out.print(vaovao.getIdcategorie()); 
 out.print(vaovao.getDatesortie());
 
 out.print(vaovao.getTitre());
 out.print(vaovao.getPrix());
 out.print(vaovao.getNombre());
 out.print(vaovao.getDescription());
 vaovao.save();
 response.sendRedirect("ListeLivre.jsp?page=0");
 }
 catch(Exception e)
 {
    request.setAttribute("erreur","0");
    rsd = request.getRequestDispatcher("insererlivre.jsp");
    rsd.forward(request,response); 
    //response.sendRedirect("insererlivre.jsp?erreur=0");
 }
 }
       out.println("</body>");
            out.println("</html>");     
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
