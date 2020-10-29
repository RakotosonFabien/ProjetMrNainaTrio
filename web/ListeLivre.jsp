<%-- 
    Document   : ListeLivre
    Created on : 27 oct. 2020, 09:20:42
    Author     : Antonio
--%>
<%@page import="fabien.packClient.Client"%>
<%@page import="java.sql.Connection"%>
<%@page import="Antonio.packCategorie.Categorie" %>
<%@page import="fabien.packEcrivain.Ecrivain" %>
<%@page import="Antonio.utils.Helper" %>
<%@page import="packModele.Modele" %>
<%@page import="Antonio.packLivre.Livre" %>
<%@page import="java.sql.*" %>

<%
Connection conn=Helper.getConn();
Livre[] alls=new Livre().findAll();
int isanaboky=alls.length;
int max=3;  
int pagess=Helper.nombrePages(isanaboky, max);
String pazy=request.getParameter("page");
Livre[] all=new Livre().findAll(conn,Integer.parseInt(pazy),max);
boolean authentifie = false;
Client monClient = (Client)session.getAttribute("monClient");
authentifie = (monClient!=null);
%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Liste des livres</title>
        <link href="assets/Bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="assets/Bootstrap/css/mdb.min.css" rel="stylesheet">
	<link href="assets/Bootstrap/css/style.css" rel="stylesheet">
	<link href="style.css" rel="stylesheet">
	<script src="main.js"></script>
    </head>
    <body>
        <div class="container">
            <% if(!authentifie){ %>
            <div class="row">
                <h2 class="alert-danger">Vous n'etes pas connecte!</h2>
            </div>
            <% } %>
            <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-4">
                    <center><h3 style="text-decoration: underline;font-family: verdana;"><b>Liste des Livres</b></h3></center>    
                </div>
                <div class="col-md-4"></div>
                
                
                <div class="col-md-12">
                    
                              <table class="table">
  <thead class="thead-dark">
    <tr>
      <th scope="col">Titre</th>
      <th scope="col">Ecrivain</th>
      <th scope="col">Categorie</th>
      <th scope="col">Description</th>
      <th scope="col">Date sortie</th>
      <th scope="col">Prix(Ariary)</th>
      <% if(authentifie){%>
      <th scope="col"></th>
      <th scope="col"></th>
      <% } %>
    </tr>
  </thead>
  <tbody>
      <%
          for(int i=0;i<all.length;i++)
          {
      %>
    <tr>
      
      <td><%=all[i].getTitre() %></td>
      <td><%=all[i].getEcrivain().getNom()+" "+all[i].getEcrivain().getPrenom() %></td>
      <td><%=all[i].getCategorielivre().getCategorie() %></td>
      <td><%=all[i].getDescription() %></td>
      <td><%=all[i].getDatesortie() %></td>
      <td><%=all[i].getPrix() %></td>
      <td>
    <% if(authentifie){ %>      
    <a href="modifierlivre.jsp?idl=<%=all[i].getId() %>" class="btn btn-outline-success btn-sm">Modifier</a>
    </td>
    <td>
         <a href="DeleteLivre?idl=<%=all[i].getId() %>" class="btn btn-outline-danger btn-sm">Supprimer</a>
    </td>
    <% } %>
    </tr>
     
    <%
    }
    %>
  
  </tbody>
</table>  
                    
                    
                </div>
                
                
     <div class="col-md-4"></div>
     <div class="col-md-4">
         
    <nav aria-label="Page navigation example">
  <ul class="pagination">
      
      <li class="page-item"><a class="page-link" href="ListeLivre.jsp?page=<%=Helper.retourner0(Integer.parseInt(pazy)-1) %>">Precedent</a></li>
    <%
    for(int i=0;i<pagess;i++)
    {
        %>
    <li class="page-item"><a class="page-link" href="ListeLivre.jsp?page=<%=i%>"><%=i %></a></li>
    
    <%
    }
    
    %>
    
    <li class="page-item">
        <% if(Integer.parseInt(pazy)<pagess-1){ %>
        <a class="page-link" href="ListeLivre.jsp?page=<%=Integer.parseInt(pazy)+1%>">Suivant</a>
        <%}
        %>
        
    </li>
  </ul>
</nav>    
         
     </div>
     <div class="col-md-4"></div>
           
    
           
                

                
            </div>
        </div>
         <script type="text/javascript" src="assets/Boostrap/js/jquery-3.4.1.min.js"></script>
		<script type="text/javascript" src="assets/Boostrap/js/popper.min.js"></script>
		<script type="text/javascript" src="assets/Boostrap/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="assets/Boostrap/js/mdb.min.js"></script>
	
    </body>
</html>
