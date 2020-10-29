<%-- 
    Document   : InsertionLivre
    Created on : 27 oct. 2020, 09:04:29
    Author     : Sitraka
--%>

<%@page import="Sitraka.packFournisseur.Fournisseur"%>
<%@page import="java.sql.Connection"%>
<%@page import="Antonio.packCategorie.Categorie" %>
<%@page import="fabien.packEcrivain.Ecrivain" %>
<%@page import="Sitraka.utils.Helper" %>
<%@page import="packModele.Modele" %>
<%@page import="java.sql.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
Connection conn=new Helper().getConn();
Ecrivain a=new Ecrivain();
Ecrivain[] allec=a.findAll(conn);
Categorie c=new Categorie();
Categorie[] allcat=c.findAll();
Fournisseur f=new Fournisseur();
Fournisseur[] allfour=f.find(conn);
String idb=request.getParameter("id");
String titre=request.getParameter("titre");
String date=request.getParameter("date");
String prix=request.getParameter("prix");
String nombre=request.getParameter("nombre");
String pageb=request.getParameter("page");
String description=request.getParameter("description");
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modification</title>
        <link href="bootstrap/Bootstrap/bootstrap-4.3-2.1-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="style.css" rel="stylesheet">
	<script src="main.js"></script>
    </head>
    <body>
    <center><h1 style="color: darkblue">Modification</h1></center>
    <form action="Modifier" method="GET">
        <input type="hidden" name="page" value="<% out.print(pageb); %>">
        <input type="hidden" name="id" value="<% out.print(idb); %>">
        <div class="form-row">

          <div class="form-group col-md-2">
            <label for="inputState">Auteur</label>
            <select name="idc" id="inputState" class="form-control">
              <%
              for(int i=0;i<allec.length;i++)
              { %>
                      <OPTION value="<% out.print(allec[i].getId()); %>" >
              <%
                      out.print(allec[i].getNom()+" "+allec[i].getPrenom());
              %>
              </OPTION>
              <%
                  }
              %>
            </select>
          </div>
            <div class="form-group col-md-2">
            <label for="inputState">Categorie</label>
            <select name="idcat" id="inputState" class="form-control">
              <%
              for(int i=0;i<allcat.length;i++)
              { %>
                      <OPTION value="<% out.print(allcat[i].getId()); %>" >
              <%
                      out.print(allcat[i].getCategorie());
              %>
              </OPTION>
              <%

              }
              %>
            </select>
          </div>
            <div class="form-group col-md-2">
            <label for="inputState">Fournisseur</label>
            <select name="idfour" id="inputState" class="form-control">
              <%
              for(int i=0;i<allfour.length;i++)
              { %>
                      <OPTION value="<% out.print(allfour[i].getId()); %>" >
              <%
                      out.print(allfour[i].getNom());
              %>
              </OPTION>
              <%
              }
              %>
            </select>
          </div>
          <div class="form-group col-md-2">
            <label for="inputZip">Titre</label>
            <input type="text" name="titre" class="form-control" id="inputZip" value="<% out.print(titre); %>">
          </div>
            <div class="form-group col-md-2">
            <label for="inputZip">Date</label>
            <input type="date" name="dates" class="form-control" id="inputZip" value="<% out.print(date); %>">
          </div>
            <div class="form-group col-md-1">
            <label for="inputZip">prix</label>
            <input type="text" name="prix" class="form-control" id="inputZip" value="<% out.print(prix); %>">
          </div>
            <div class="form-group col-md-1">
            <label for="inputZip">nombre</label>
            <input type="text" name="nombre" class="form-control" id="inputZip" value="<% out.print(nombre); %>">
          </div>
        </div>
            <div class="form-group">
                <center><label for="exampleFormControlTextarea1">description</label></center>
            <textarea name="description" class="form-control" id="exampleFormControlTextarea1" rows="3"><% out.print(description); %></textarea>
          </div>
            <center><button type="submit" class="btn btn-secondary btn-lg">Valider</button></center>
    </form>
    <a href="Liste.jsp">Accueil</a>
    </body>
    
    
   
	
    
    
</html>
