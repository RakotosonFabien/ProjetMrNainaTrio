<%-- 
    Document   : Liste
    Created on : 27 oct. 2020, 13:11:30
    Author     : Sitraka
--%>

<%@page import="fabien.packClient.Client"%>
<%@page import="fabien.helpers.Helper1"%>
<%@page import="fabien.packEcrivain.Ecrivain"%>
<%@page import="Sitraka.utils.Helper"%>
<%@page import="java.sql.Connection"%>
<%@page import="packModele.Modele" %>
<%@page import="Sitraka.packLivre.LivreImp" %>
<%@page import="java.sql.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
LivreImp f=new LivreImp();
String num=request.getParameter("page");
int nump =Integer.parseInt(num);
int numPage=nump-1;
int maximum=3;
LivreImp[] allf=f.find();
LivreImp[] all=f.findpag(maximum,numPage);
int nombrePages = Helper1.nombrePages(allf.length, maximum);
boolean authentifie = false;
Client monClient = (Client)session.getAttribute("monClient");
authentifie = (monClient!=null);
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Livre</title>
        <link href="assets/Bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="style.css" rel="stylesheet">
	<script src="main.js"></script>
    </head>
    <body>
        <% if(!authentifie){ %>
            <div class="row">
                <h2 class="alert-danger">Vous n'etes pas connecte!</h2>
            </div>
            <% } %>
        <table class="table">
  <thead>
    <tr>
      <th scope="col">Exemplaire</th>
      <th scope="col">Titre</th>
      <th scope="col">Auteur</th>
      <th scope="col">Description</th>
      <th scope="col">Prix</th>
    </tr>
  </thead>
  <tbody>
        <%
        for(int i=0;i<all.length;i++)
        { 
            Ecrivain a=new Ecrivain();
            a=a.findById(all[i].getIdEcrivain()); %>
    <tr>
      <th scope="row"><% out.print(all[i].getNombre()); %></th>
      <td><% out.print(all[i].getTitre()); %></td>
      <td><% out.print(a.getNom()); %>  <% out.print(a.getPrenom()); %></td>
      <td><% out.print(all[i].getDescription());%></td>
      <td><% out.print(all[i].getPrix_achat()); %></td>
      <% if(authentifie){ %>
      <td><a href="Modifier.jsp?id=<% out.print(all[i].getId()); %>&&page=<% out.print(num); %>&&titre=<% out.print(all[i].getTitre()); %>&&date=<% out.print(all[i].getDate_sortie()); %>&&prix=<% out.print(all[i].getPrix_achat()); %>&&nombre=<% out.print(all[i].getNombre()); %>&&description=<% out.print(all[i].getDescription()); %>"><button type="button" class="btn btn-primary" data-toggle="button" aria-pressed="false" autocomplete="off">Modifier
              </button></a></td>
              <td><a href="Supprimer?id=<% out.print(all[i].getId()); %>&&page=<% out.print(num); %>"><button type="button" class="btn btn-primary" data-toggle="button" aria-pressed="false" autocomplete="off">Supprimer
                  </button></a></td>
        <% } %>
    </tr>
        <%
        }
        %>
  </tbody>
    </table>
                <center>
                <%    if(nump>1){ %>
                <a href="Liste.jsp?page=<%= nump-1 %>"><button type="button" class="btn btn-light">Precedent</button></a>
        <% }
                int numero = 1;
                while(numero<=nombrePages){
                    %>
                    <a href="Liste.jsp?page=<%= numero %>"><button type="button" class="btn btn-light"><%= numero %></button></a>
                    <%
                        numero ++;
                }
            %>
                    <%    if(nump<nombrePages){ %>
                <a href="Liste.jsp?page=<%= nump+1 %>"><button type="button" class="btn btn-light">Suivant</button></a>
                <% } %>
                    </center>
                    <center><a href="Accueil.jsp"><button type="button" class="btn btn-light">Accueil</button></a></center>
    </body>
</html>

