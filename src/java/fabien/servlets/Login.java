/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fabien.servlets;

import fabien.packClient.Client;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.Cookie;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Fabien
 */
public class Login extends HttpServlet {

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
            out.println("<title>Servlet Login</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Login at " + request.getContextPath() + "</h1>");
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
        RequestDispatcher dispat = null;
        try{
            
            //processRequest(request, response);
            String mail = request.getParameter("mail");
            String mdp = request.getParameter("mdp");
            HttpSession session = request.getSession();
            //session.invalidate();
            String sessionId = null;
            Client monClient = null;
            monClient = Client.find(Antonio.utils.Helper.getConn(), mail, mdp);
            Cookie[] cookies = request.getCookies();
            if(cookies!=null){
                for(Cookie cookie : cookies){
                    if(cookie.getName().equals("cookieClient")){
                        cookie.setValue(Integer.toString(monClient.getId()));
                        sessionId = "misy";
                    }
                }
            }
            if(sessionId == null){
                Cookie c = new Cookie("cookieClient", Integer.toString(monClient.getId()));
            }
            session.setAttribute("monClient", monClient);
            Cookie c = new Cookie("cookieClient", "cookieXXX000" + monClient.getId());
            response.addCookie(c);
            dispat = request.getRequestDispatcher("/gestionErreur?classe=Login&&message=reussite");
        }
        catch(Exception e){
            dispat = request.getRequestDispatcher("/gestionErreur?classe=Login&&message="+e.getMessage());
        }
        finally{
            if(dispat!=null)dispat.forward(request,response);
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
