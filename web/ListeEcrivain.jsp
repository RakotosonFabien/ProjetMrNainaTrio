<%-- 
    Document   : ListeEcrivain
    Created on : 24 oct. 2020, 06:24:04
    Author     : Fabien
--%>

<%@page import="fabien.helpers.Helper1"%>
<%@page import="fabien.packClient.Client"%>
<%@page import="Antonio.utils.Helper"%>
<%@page import="fabien.packEcrivain.Ecrivain"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    boolean authentifie = false;
    String monCookie = null;
/*    Cookie[] cookies = request.getCookies();
            if(cookies!=null){
                for(Cookie cookie : cookies){
                    if(cookie.getName().equals("cookieClient")){
                        out.println("Identifie");
                        monCookie = cookie.getValue();
                    }
                }
            }
            if(monCookie == null){
                out.println("Pas de cookie");
            }
            else{
                out.println("Cookie presente ==> " + monCookie);
                authentifie = true;
            }
*/
    int maximum = 4;
    String stringPage = request.getParameter("page");
    String colonneTri = request.getParameter("colonneTri");
    String way = request.getParameter("way");
    Client monClient = (Client)session.getAttribute("monClient");
    authentifie = (monClient!=null);
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="assets/Bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <% if(!authentifie){ %>
            <div class="row">
                <h2 class="alert-danger">Vous n'etes pas connecte!</h2>
            </div>
            <% } %>
            <h1><% //if(monClient!=null) {out.println("Mr" +  monClient.getNom());  }%></h1>
            <form action="ListeEcrivain.jsp" method="get">
                <input type="text" name="recherche" placeholder="Recherchez un ecrivain ici">
                <input type="submit" value="Rechercher">
            </form>
            <h1> Liste de nos ecrivains</h1>
            <table class="table">
            <thead class="thead-dark">
                    <tr>
                        <th scope="col">Nom<a href="ListeEcrivain.jsp?colonneTri=NOM&way=DESC">-V-</a><a href="ListeEcrivain.jsp?colonneTri=NOM&way=ASC">-A-</a></th>
                        <th scope="col">Prenom<a href="ListeEcrivain.jsp?colonneTri=PRENOM&way=DESC">-V-</a><a href="ListeEcrivain.jsp?colonneTri=PRENOM&way=ASC">-A-</a></th>
                        <th scope="col">Date de naissance<a href="ListeEcrivain.jsp?colonneTri=dateNaissance&way=DESC">-V-</a><a href="ListeEcrivain.jsp?colonneTri=dateNaissance&way=ASC">-A-</a></th>
                        <th scope="col">Categorie<a href="ListeEcrivain.jsp?colonneTri=idCategorie&way=DESC">-V-</a><a href="ListeEcrivain.jsp?colonneTri=idCategorie&way=ASC">-A-</a></th>
                        <%
                            if(authentifie){
                        %>
                        <th>Supprimer</td>
                        <th>Modifier</td>
                        <%
                            }
                        %>
                    </tr> 
            </thead>
      <tbody>
            <%
                //monClient = (Client)request.getSession().getAttribute("monClient");
                //if(monClient!=null){
            %>
      <% //<h1>Vous etes <% out.println(monClient.getNom()+ "" + monClient.getPrenom()); } </h1> %>
            <%
                //Connection connexion = null;
                try{

                    String recherche = request.getParameter("recherche");
                    Ecrivain[] ecrivains = null;
                    Ecrivain[] allEcrivains = null;
                    if(stringPage==null){
                        stringPage = "1";
                    }
                    int numPage = Integer.parseInt(stringPage)-1;
                    //connexion = Helper.getConn();
                    if(recherche==null){
                        out.println("Pas de recherche");
                        ecrivains = new Ecrivain().findAll(numPage, maximum, colonneTri, way);
                        allEcrivains = new Ecrivain().findAll();
                    }
                    else{
                        out.println("Recherche ==> " + recherche);
                        ecrivains = Ecrivain.find(recherche, numPage, maximum, colonneTri, way);
                        allEcrivains = Ecrivain.find(recherche);
                    }
                    int nombrePages = Helper1.nombrePages(allEcrivains.length, maximum);

                    for(Ecrivain ecrivain : ecrivains){
            %>

                    <tr>
                        <th><% out.println(ecrivain.getNom()); %></th>
                        <td><% out.println(ecrivain.getPrenom()); %></td>
                        <td><% out.println(ecrivain.getDateNaissance()); %></td>
                        <td><% out.println(ecrivain.getCatEcrivain().getNom()); %></td>
                        <%if(authentifie){%>
                        <td><a href="SuppressionEcrivain?id=<%=ecrivain.getId()%>" class="btn btn-outline-danger btn-sm">Supprimer</a></td>
                        <td><a href="ModificationEcrivain.jsp?id=<%=ecrivain.getId()%>" class="btn btn-outline-success btn-sm">Modifier</a></td>
                        <%}%>
                    </tr>

            <%
                    }
            %>
              </tbody>
    </table>
              <!Pagination>
              <ul class="pagination">  
                  <% if(numPage>=1){ %><li class="page-item"><a class="page-link" href="ListeEcrivain.jsp?page=<%= numPage%>">Precedent</a></li><% } %>

                <%
                    int numero = 1;
                    while(numero<=nombrePages){
                        %>
                        <li class="page-item"><a class="page-link" href="ListeEcrivain.jsp?page=<%= numero %>"><%= numero %></a></li>
                        <%
                            numero ++;
                    }
                %>
                        <% if(numPage<nombrePages-1){ %><li class="page-item"><a class="page-link" href="ListeEcrivain.jsp?page=<%= numPage+2%>">Suivant</a></li><% } %>
              </ul>
            <%
                if(authentifie){
                    %>
                    <a href ="FormulaireEcrivain.jsp">Ajouter un ecrivain</a>
                    <%
                }
            %>

    <%           }
                catch(Exception e){
                    out.println(e.getMessage());
                }
            %>
            <a href="Accueil.jsp">Accueil</a>
            <p><a href="Deconnexion">Se deconnecter</p>
        </div>
    </body>
</html>
