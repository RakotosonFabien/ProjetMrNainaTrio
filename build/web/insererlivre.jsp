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
            <center><h3>Insertion Livre</h3></center>
        </div>
                
     
          
           
           <div class="col-md-12">
                 
       
                </div>
        <div class="col-md-2"></div>
        <div class="col-md-8" style="border: 1px solid gray;border-radius: 15px;">
            <form action="Insererlivre" method="post">
<div class="form-row">
    <div class="form-group col-md-6">
       <label for="inputState">Ecrivain</label>
        <select name="idc" id="inputState" class="form-control">
	<OPTION>Ecrivain</OPTION>
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
    <div class="form-group col-md-4">
      <label for="inputState">Categorie</label>
      <select name="idcat" id="inputState" class="form-control">
        <OPTION>Categorie</OPTION>
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
    
  </div>
            <div class="form-row">
                
    <div class="form-group col-md-6">
      <label for="inputEmail4">Titre</label>
      <input type="text" name="titre" class="form-control" id="inputEmail4" placeholder="Titre">
    </div>
    <div class="form-group">
    <label for="desc">Description</label>
    <textarea class="form-control" name="description" id="desc" rows="3"></textarea>
  </div>
  </div>
  <div class="form-group">
    <label for="dates">Date</label>
    <input type="date" name="dates" class="form-control" id="dates"/>
  </div>
                        <div class="form-row">
  <div class="form-group col-md-6">
    <label for="prixx">Prix(Ariary)</label>
    <input type="text" name="prix" class="form-control" id="prixx" placeholder="000">
  </div>
  <div class="form-group col-md-6">
      <label for="nombres">Nombre</label>
    <input type="text" name="nombre" class="form-control" id="nombres" placeholder="Nombre">
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
