<%-- 
    Document   : ModificationEcrivain
    Created on : 26 oct. 2020, 18:36:17
    Author     : Fabien
--%>

<%@page import="java.sql.Date"%>
<%@page import="fabien.helpers.Helper1"%>
<%@page import="fabien.packEcrivain.CatEcrivain"%>
<%@page import="java.sql.Connection"%>
<%@page import="fabien.packEcrivain.Ecrivain"%>
<%@page import="fabien.packClient.Client"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="assets/Bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            //Connection connexion = null;
            
            try
            {
                //connexion = Antonio.utils.Helper.getConn();
                String[] allMonths = Helper1.getAllMonth();
                int indiceJour = 1;
                int indiceMois = 0;
                int indiceAnnee = new java.util.Date().getYear() + 1900;
                CatEcrivain[] categories = new CatEcrivain().findAll();
                Client monClient = (Client)session.getAttribute("monClient");
                if(monClient!=null){
                    String id = request.getParameter("id");
                    if(id.compareTo("")==0||id==null){
                        out.println("La reference du client est nulle");
                    }
                    else{
                        int idEcrivain = Integer.parseInt(id);
                        Ecrivain aModifie = Ecrivain.findById(idEcrivain);
                        Date dateNaissance = aModifie.getDateNaissance();
        %>
        <form action="ModificationEcrivain" method="post">
            <input type="hidden" name="id" value="<%= aModifie.getId() %>">
            <div class="row">
        
            <div class="col-md-2">
                
            </div>
            <div class=""col-md-8">
                
                    <!Nom>
                    <div class="form-group">
                        <label for="exampleInputEmail1">Nom</label>
                        <input type="text" class="form-control"  value="<%=aModifie.getNom()%>" placeholder="Nom" name="nom">
                    </div>
                    <!Prenom>
                    <div class="form-group">
                        <label for="exampleInputEmail1">Prenom</label>
                        <input type="text" class="form-control"  value="<%= aModifie.getPrenom()  %>" placeholder="Prenom" name="prenom">
                    </div>
                    <!Date de naissance>
                    <div class="form-group">
                        <label for="exampleInputEmail1">Date de naissance</label><br>
                        <select class="custom-select col-md-4" name="jour">
                            <option>Jour</option>
                            <% while(indiceJour<=31){ %>
                            <option value="<%= indiceJour %>" <%if(indiceJour==dateNaissance.getDate()){ out.println("selected"); }%>><%= indiceJour%></option>
                            <% indiceJour++; } %>
                        </select>
             
                        <select class="custom-select col-md-3" name="mois">
                            <option>Mois</option>
                            <% while(indiceMois<12){ %>
                            <option value="<%= indiceMois%>" <% if(indiceMois==dateNaissance.getMonth()){ out.println("selected"); } %>><%= allMonths[indiceMois]%></option>
                            <% indiceMois ++; } %>
                           
                        </select>
                        <select class="custom-select col-md-4" name="annee">
                            <option>Annee</option>
                            <% while(indiceAnnee>1920){ %>
                            <option value="<%= indiceAnnee %>" <% if(indiceAnnee==1900 + dateNaissance.getYear()){ out.println("selected"); } %>><%= indiceAnnee%></option>
                            <% indiceAnnee--; } %>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="exampleFormControlSelect1">Categorie</label>
                        <select class="form-control" id="exampleFormControlSelect1" name="idCategorie">
                            <% for(CatEcrivain categorie : categories){
                                %>
                            }
                            <option value="<%=categorie.getId() %>" <% if(categorie.getId()==aModifie.getCatEcrivain().getId()){ out.println("selected"); } %>><%=categorie.getNom() %></option>
                            <%
                                }
                            %>
                        </select>
                    </div>
                
                <button type="submit" class="btn btn-success">Valider les modifications</button>
            </div>
        </div>
        </form>
        <%
                    }
                }
                else{
                    out.println("Impossible d'acceder au contenu sans etre authentifie!");
                }
            }
            catch(Exception e){
                out.println("Erreur==>"+e.getMessage());
            }

        %>
        <a href="Accueil.jsp">Accueil</a>
        <p><a href="Deconnexion">Se deconnecter</p>
    </body>
</html>
