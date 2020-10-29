<%-- 
    Document   : insererlivre
    Created on : 26 oct. 2020, 13:59:16
    Author     : Antonio
--%>


<%@page import="java.sql.Connection"%>
<%@page import="Antonio.packCategorie.Categorie" %>
<%@page import="fabien.packEcrivain.Ecrivain" %>
<%@page import="Antonio.utils.Helper" %>
<%@page import="packModele.Modele" %>
<%@page import="Antonio.packLivre.Livre" %>
<%@page import="java.sql.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
Connection conn=new Helper().getConn();
String idlivre=request.getParameter("idl");
int idbook=Integer.parseInt(idlivre);
Livre[] tomodify=new Livre().findbyId(idbook);
Ecrivain a=new Ecrivain();
Ecrivain[] allec=a.findAll();
Categorie c=new Categorie();
Categorie[] allcat=c.findAll();
String errors=(String)request.getAttribute("erreur");
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inserer Livre</title>
        <link href="assets/Bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="assets/Bootstrap/css/mdb.min.css" rel="stylesheet">
	<link href="assets/Bootstrap/css/style.css" rel="stylesheet">
	<link href="style.css" rel="stylesheet">
	<script src="main.js"></script>
    </head>
    <body>
        <div class="container">
            <div class="row">
        <div class="col-md-12">
            <center><h3>Modification</h3></center>
        </div>
                
      
          
           
           
        <div class="col-md-2"></div>
        <div class="col-md-8" style="border: 1px solid gray;border-radius: 15px;">
            <form action="ModifierLivre" method="get">
<div class="form-row">
    <div class="form-group col-md-6">
       <label for="inputState">Ecrivain</label>
       <input type="hidden" name="idlivres" value="<%=idlivre %>">
        <select name="idc" id="inputState" class="form-control">
	
	<%
	for(int i=0;i<allec.length;i++)
	{ %>
		<OPTION value="<% out.print(allec[i].getId()); %>"<% if(allec[i].getId()==tomodify[0].getEcrivain().getId()){ out.println("selected");}%> >
	<%
		out.print(allec[i].getNom()+" "+allec[i].getPrenom());
	%>
	</OPTION>
	<%
	
	}
%>
	</select>
    </div>
    <div class="form-group col-md-4">
      <label for="inputState">Categorie</label>
      <select name="idcat" id="inputState" class="form-control">
       <%
	for(int i=0;i<allcat.length;i++)
	{ %>
            <OPTION value="<% out.print(allcat[i].getId()); %>" <% if(allcat[i].getId()==tomodify[0].getCategorielivre().getId()){ out.println("selected");}%>>
	<%
		out.print(allcat[i].getCategorie());
	%>
	</OPTION>
	<%
	
	}
%>

	</select>
    </div>
    
  </div>
            <div class="form-row">
                
    <div class="form-group col-md-6">
      <label for="inputEmail4">Titre</label>
      <input type="text" name="titre" class="form-control" id="inputEmail4" value="<%=tomodify[0].getTitre() %>" placeholder="Titre">
    </div>
    <div class="form-group">
    <label for="desc">Description</label>
    <textarea class="form-control" name="description" id="desc" rows="3"><%=tomodify[0].getDescription() %></textarea>
  </div>
  </div>
  <div class="form-group">
    <label for="dates">Date</label>
    <input type="date" name="dates" value="<%=tomodify[0].getDatesortie() %>" class="form-control" id="dates"/>
  </div>
                        <div class="form-row">
  <div class="form-group col-md-6">
    <label for="prixx">Prix</label>
    <input type="text" name="prix" value="<%=tomodify[0].getPrix() %>" class="form-control" id="prixx" placeholder="Prix">
  </div>
  <div class="form-group col-md-6">
      <label for="nombres">Nombre</label>
    <input type="text" name="nombre" value="<%=tomodify[0].getNombre() %>" class="form-control" id="nombres" placeholder="Nombre">
  </div>
                        </div>
  
        <center><button type="submit" class="btn btn-primary">Valider</button></center>
            </form>            
        <div class="col-md-2"></div>
  
        </div>
        <div class="col-md-4"></div>
        <div class="col-md-4">
            <%
            if(errors!=null & errors!="")
            {
                if(errors.compareTo("0")==0)
                {
                    %>
                    <div class="alert alert-danger" role="alert">
                        Donn&eacute;e(s) invalide(s)
                    </div>
                    
                <%}
            }
            
            %>
            
            
        </div>
        <div class="col-md-4"></div>
        
 
        
        </div>
        </div>
        
        
       
        
        <h1></h1>
         <script type="text/javascript" src="assets/Boostrap/js/jquery-3.4.1.min.js"></script>
		<script type="text/javascript" src="assets/Boostrap/js/popper.min.js"></script>
		<script type="text/javascript" src="assets/Boostrap/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="assets/Boostrap/js/mdb.min.js"></script>
	
    </body>
    
    
   
	
    
    
</html>

