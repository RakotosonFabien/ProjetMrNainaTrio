<%-- 
    Document   : Inscription
    Created on : 19 oct. 2020, 18:50:05
    Author     : Fabien
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="fabien.helpers.Helper1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="assets/Bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <title>JSP Page</title>
    </head>
    <!java>
        <% 
            String[] allMonths = Helper1.getAllMonth();
            int indiceJour = 1;
            int indiceMois = 0;
            int indiceAnnee = new java.util.Date().getYear() + 1900;
        %>
    <!java end>
    <body>
        <form action="inscription" method="post">
        <div class="row">
        
            <div class="col-md-2">
                
            </div>
            <div class=""col-md-8">
                
                    <!Nom>
                    <div class="form-group">
                        <label for="exampleInputEmail1">Nom</label>
                        <input type="text" class="form-control"  placeholder="Nom" name="nom">
                    </div>
                    <!Prenom>
                    <div class="form-group">
                        <label for="exampleInputEmail1">Prenom</label>
                        <input type="text" class="form-control"  placeholder="Prenom" name="prenom">
                    </div>
                    <!Date de naissance>
                    <div class="form-group">
                        <label for="exampleInputEmail1">Date de naissance</label><br>
                        <select class="custom-select col-md-4" name="jour">
                            <option selected>Jour</option>
                            <% while(indiceJour<=31){ %>
                            <option value="<%= indiceJour %>"><%= indiceJour%></option>
                            <% indiceJour++; } %>
                        </select>
                        <select class="custom-select col-md-3" name="mois">
                            <option selected>Mois</option>
                            <% while(indiceMois<12){ %>
                            <option value="<%= indiceMois%>"><%= allMonths[indiceMois]%></option>
                            <% indiceMois ++; } %>
                           
                        </select>
                        <select class="custom-select col-md-4" name="annee">
                            <option selected>Annee</option>
                            <% while(indiceAnnee>1920){ %>
                            <option value="<%= indiceAnnee %>"><%= indiceAnnee%></option>
                            <% indiceAnnee--; } %>
                        </select>
                    </div>
                    <!Adresse>
                    <div class="form-group">
                        <label for="exampleInputEmail1">Adresse</label>
                        <input type="text" class="form-control"  placeholder="Adresse" name="adresse">
                    </div>
                    <!mdp>
                    <div class="form-group">
                        <label for="exampleInputEmail1">Mot de passe</label>
                        <input type="password" class="form-control"  placeholder="password" name="mdp">
                    </div>
                    <!e-mail>
                    <div class="form-group">
                        <label for="exampleInputEmail1">Adresse e-mail</label>
                        <input type="email" class="form-control"  placeholder="e-mail" name="mail">
                    </div>

                
                
            </div>
        </div>
            <button type="submit" class="btn btn-success">S'inscrire</button>
            <a href="index.html"><button type="button" class="btn btn-primary">Retourner a la page d'accueil</button></a>
        </form>
        <a href="Accueil.jsp">Accueil</a>
        <p><a href="Deconnexion">Se deconnecter</p>
    </body>
</html>
