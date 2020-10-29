<%-- 
    Document   : Accueil
    Created on : 24 oct. 2020, 06:45:21
    Author     : Fabien
--%>

<%@page import="fabien.packClient.Client"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Page d'accueil</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <div>
            <%
                boolean authentifie = false;
                Client monClient = (Client)session.getAttribute("monClient");
                authentifie = (monClient!=null);
                if(authentifie){
                    out.println("Bienvenue Mr " + monClient.getNom());
                }
                else{
                    out.println("Vous n'etes pas connecte. Les modifications ne seront pas disponibles");
                }
                
            %>
            <center><a href="ListeEcrivain.jsp?page=1">
                    <button type="button" class="btn btn-dark"><h1>La liste de nos ecrivains</h1></button>
                </a></center><br>
            <% if(authentifie){ %><center><a href ="FormulaireEcrivain.jsp"><button type="button" class="btn btn-dark"><h1>Ajouter un ecrivain</h1></button></a></center><% } %>
            
            <% if(authentifie) { %><center><h1><a href="insererlivre.jsp"><button type="button" class="btn btn-dark"><h1>Inserer une Livre</h1></button></a></h1></center><% } %>
            <center><h1><a href="ListeLivre.jsp?page=0"><button type="button" class="btn btn-dark"><h1>Liste des Livres</h1></button></a></h1></center>
            <% if (authentifie){ %><center><a href="InsertionLivre.jsp"><button type="button" class="btn btn-dark"><h1>Importer livre</h1></button></a></center><% } %>
            <br>
            <br>
            <center><a href="Liste.jsp?page=1"><button type="button" class="btn btn-dark"><h1>Listes des livres importes</h1></button></a></center>
        <p><a href="Deconnexion">Se deconnecter</p>
        
    </body>
    
</html>
