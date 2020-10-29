<%-- 
    Document   : FormulaireEcrivain
    Created on : 24 oct. 2020, 06:58:58
    Author     : Fabien
--%>

<%@page import="java.sql.Connection"%>
<%@page import="Antonio.utils.Helper"%>
<%@page import="fabien.packEcrivain.CatEcrivain"%>
<%@page import="fabien.helpers.Helper1"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="assets/Bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <title>Inserer un ecrivain</title>
    </head>
    <div class="container">
        <!java>
            <% 
                //Connection connexion = null;
                try{
                    //connexion = Helper.getConn();
                    String[] allMonths = Helper1.getAllMonth();
                    int indiceJour = 1;
                    int indiceMois = 0;
                    int indiceAnnee = new java.util.Date().getYear() + 1900;
                    CatEcrivain[] categories = new CatEcrivain().findAll();
            %>
        <!java end>
        <body>
           <form action="InsertionEcrivain" method="post">
            <div class="row">

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
                        <div class="form-group">
                            <label for="exampleFormControlSelect1">Categorie</label>
                            <select class="form-control" id="exampleFormControlSelect1" name="idCategorie">
                                <% for(CatEcrivain categorie : categories){
                                    %>
                                }
                                <option value="<%=categorie.getId() %>"><%=categorie.getNom() %></option>
                                <%
                                    }
                                %>
                            </select>
                        </div>


                </div>
            </div>
                <button type="submit" class="btn btn-success">Inscrire l'ecrivain</button>
                <a href="index.html"><button type="button" class="btn btn-primary">Retourner a la page d'accueil</button></a>
            </form>
                            <%
                                }
    catch(Exception e){
        out.println(e.getMessage());
    }

                            %>
                            <a href="Accueil.jsp">Accueil</a>
                            <p><a href="Deconnexion">Se deconnecter</p>
    </div>
    </body>
</html>
