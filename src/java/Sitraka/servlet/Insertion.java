/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sitraka.servlet;

import Sitraka.packLivre.LivreImp;
import Sitraka.utils.Helper;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sitraka
 */
public class Insertion extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param req servlet req
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
     public void doGet(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException {
        res.setContentType("text/plain");
        PrintWriter out = res.getWriter();
          String idec=req.getParameter("idc");
            String idcat=req.getParameter("idcat");
            String idfour=req.getParameter("idfour");
            String title=req.getParameter("titre");
            String desc=req.getParameter("description");
            String dates=req.getParameter("dates");
            String prix=req.getParameter("prix");
            String nbr=req.getParameter("nombre");
 if(idec!=null & idec!="" & idcat!=null & idcat!=""& idfour!=null & idfour!="" & title!=null & title!="" & desc!=null & desc!="" & dates!=null & dates!="" & prix!=null &prix!="" & nbr!=null &prix!=""){

                try{
            int idc=Integer.parseInt(idec);
            int idcateg=Integer.parseInt(idcat);
            int idfournisseur=Integer.parseInt(idfour);
            String[] datess=dates.split("-");
            Float price=new Float(prix);
            int nbrs=Integer.parseInt(nbr);

             Connection conn=new Helper().getConn();
            Date d=new Date(Integer.parseInt(datess[0])-1900,Integer.parseInt(datess[1]),Integer.parseInt(datess[2]));
             LivreImp livre=new LivreImp(idc,idcateg,idfournisseur,title,desc,d,price,nbrs);

            out.print(livre.getDescription());
            livre.save(conn);
            }
            catch(Exception e)
            {
               out.println("Donnees invalides"+e.getMessage());
            }
            res.sendRedirect("Accueil.jsp");
        }
        else{
            out.println("Veuillez remplir toutes les cases");
        }
     }
}
