/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Antonio.packLivre;

/**
 *
 * @author Antonio
 */

import Antonio.packCategorie.Categorie;
import Antonio.utils.Helper;
import fabien.packEcrivain.CatEcrivain;
import fabien.packEcrivain.Ecrivain;
import java.sql.Connection;
import java.sql.Date;
//import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
public class Livre extends packModele.Modele{
Ecrivain ecrivain;
Categorie categorielivre;
String titre;
String description;
Date datesortie;
double prix;
int nombre;
private int idecrivain;
private int idcategorie;

public Livre(){}
public Livre(Ecrivain e,Categorie cat,String titres,String desc,Date dat,double prixx,int nbr){
ecrivain=e;
categorielivre=cat;
titre=titres;
description=desc;
datesortie=dat;
prix=prixx;
nombre=nbr;
}
public Livre(int ide,int idc,String ttl,String desc,Date dt,double price,int nbr){
this.setIdecrivain(ide);
this.setIdcategorie(idc);
this.setTitre(ttl);
this.setDescription(desc);
this.setDatesortie(dt);
this.setPrix(price);
this.setNombre(nbr);
}

    public Categorie getCategorielivre() {
        return categorielivre;
    }

    public void setCategorielivre(Categorie categorielivre) {
        this.categorielivre = categorielivre;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDatesortie() {
        return datesortie;
    }

    public void setDatesortie(Date datesortie) {
        this.datesortie = datesortie;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    public Ecrivain getEcrivain() {
        return ecrivain;
    }

    public void setEcrivain(Ecrivain ecrivain) {
        this.ecrivain = ecrivain;
    }

    @Override
    public void save() throws Exception {
        Connection conn=Helper.getConn();
        this.save(conn);
    }
    
    public void save(Connection conn) throws Exception {
        PreparedStatement pst=null;
        try{
            conn.setAutoCommit(false);
            pst = conn.prepareStatement("INSERT INTO livre(id,idecrivain,idcategorie,titre,description,datesortie,prix,nombre) VALUES (nextval('idlivre'),?,?,?,?,?,?,?) "); 
            pst.setInt(1,this.getIdecrivain());
            pst.setInt(2,this.getIdcategorie());
            pst.setString(3,this.getTitre());
            pst.setString(4,this.getDescription());
            pst.setDate(5,this.getDatesortie());
            pst.setDouble(6,this.getPrix());
            pst.setInt(7,this.getNombre());
            pst.executeUpdate();
            conn.commit();
            
        }catch(Exception ex){   
            conn.rollback();
            throw ex;
            
        }finally{
            if(pst!=null)pst.close();
            if(conn!=null)conn.close();            
        }
    }

    @Override
    public Livre[] findAll() throws Exception {
    Connection conn=Helper.getConn();
    Livre[] all=this.findAll(conn);
    return all;
    }
    
    public Livre[] findAll(Connection conn) throws Exception {
     PreparedStatement pst=null;
        try{
            Vector v=new Vector();
            //conn.setAutoCommit(false);
            pst = conn.prepareStatement("select * from livrecomplet");
            ResultSet rs=pst.executeQuery();
            Livre a=new Livre();
            while(rs.next()){
            a=new Livre();
            Ecrivain ecrivain = new Ecrivain();
            CatEcrivain categorieE = new CatEcrivain(rs.getInt("idcatEcrivain"), rs.getString("nomcatecrivain"));
            ecrivain = new Ecrivain(rs.getInt("idecrivain"),rs.getString("nomecrivain"),rs.getString("prenomecrivain"),rs.getDate("datenaissance"), categorieE);
            a = new Livre(ecrivain, new Categorie(rs.getInt("idcategorie"), rs.getString("nomcategorie")), rs.getString("titre"), rs.getString("description"), rs.getDate("datesortie"), rs.getDouble("prix"), rs.getInt("nombre"));
            a.setId(rs.getInt("id"));
            v.add(a);
            }
         Livre[] all=new Livre[v.size()];
         v.copyInto(all);
         return all;  
        }catch(Exception ex){   
            
            throw ex;
        }finally{
            if(pst!=null)pst.close();
            if(conn!=null)conn.close();            
        }     
  
    }

    public int getIdecrivain() {
        return idecrivain;
    }

    public void setIdecrivain(int idecrivain) {
        this.idecrivain = idecrivain;
    }

    public int getIdcategorie() {
        return idcategorie;
    }

    public void setIdcategorie(int idcategorie) {
        this.idcategorie = idcategorie;
    }

    
    public Livre[] findbyId(int idl) throws Exception {
    Connection conn=Helper.getConn();
    Livre[] allbyid=this.findbyId(conn, idl);
    return allbyid;
    }
    
    
    public Livre[] findbyId(Connection conn,int idl) throws Exception {
     PreparedStatement pst=null;
        try{
            Vector v=new Vector();
            conn  = Helper.getConn();
            conn.setAutoCommit(false);
            pst = conn.prepareStatement("select * from livrecomplet where id="+idl);
            ResultSet rs=pst.executeQuery();
            Livre a=new Livre();
            while(rs.next()){
            a=new Livre();
            Ecrivain ecrivain = new Ecrivain();
            CatEcrivain categorieE = new CatEcrivain(rs.getInt("idcatEcrivain"), rs.getString("nomcatecrivain"));
            ecrivain = new Ecrivain(rs.getInt("idecrivain"),rs.getString("nomecrivain"),rs.getString("prenomecrivain"),rs.getDate("datenaissance"), categorieE);
            a = new Livre(ecrivain, new Categorie(rs.getInt("idcategorie"), rs.getString("nomcategorie")), rs.getString("titre"), rs.getString("description"), rs.getDate("datesortie"), rs.getDouble("prix"), rs.getInt("nombre"));
            a.setId(rs.getInt("id"));
            v.add(a);
            }
         Livre[] all=new Livre[v.size()];
         v.copyInto(all);
         return all;  
        }catch(Exception ex){   
            
            throw ex;
        }finally{
            if(pst!=null)pst.close();
            if(conn!=null)conn.close();            
        }  
    }
    
    
    
    public Livre[] findAll(Connection conn,int page,int max) throws Exception {
     PreparedStatement pst=null;
        try{
            Vector v=new Vector();
            //conn.setAutoCommit(false);
            pst = conn.prepareStatement("select * from livrecomplet limit ? offset ?");
            pst.setInt(1, max);
            pst.setInt(2, page*max);
            ResultSet rs=pst.executeQuery();
            Livre a=new Livre();
            while(rs.next()){
            a=new Livre();
            Ecrivain ecrivain = new Ecrivain();
            CatEcrivain categorieE = new CatEcrivain(rs.getInt("idcatEcrivain"), rs.getString("nomcatecrivain"));
            ecrivain = new Ecrivain(rs.getInt("idecrivain"),rs.getString("nomecrivain"),rs.getString("prenomecrivain"),rs.getDate("datenaissance"), categorieE);
            a = new Livre(ecrivain, new Categorie(rs.getInt("idcategorie"), rs.getString("nomcategorie")), rs.getString("titre"), rs.getString("description"), rs.getDate("datesortie"), rs.getDouble("prix"), rs.getInt("nombre"));
            a.setId(rs.getInt("id"));
            v.add(a);
            }
         Livre[] all=new Livre[v.size()];
         v.copyInto(all);
         return all;  
        }catch(Exception ex){   
            
            throw ex;
        }finally{
            if(pst!=null)pst.close();
            if(conn!=null)conn.close();            
        }     
  
    }
  
    public void delete(Connection conn,int idl) throws Exception {
     PreparedStatement pst=null;
        try{
            Vector v=new Vector();
            conn  = Helper.getConn();
            conn.setAutoCommit(false);
            pst = conn.prepareStatement("delete  from livre where id="+idl);
            pst.executeUpdate();
            conn.commit();
            }  
        catch(Exception ex){   
            conn.rollback();
            throw ex;
        }finally{
            if(pst!=null)pst.close();
            if(conn!=null)conn.close();            
        }  
    
    }
    public void delete(int idl) throws Exception {
     Connection conn=Helper.getConn();
     this.delete(conn, idl);
    }
    
     public  void modify(int id, Livre nouveau)throws Exception{
     Connection conn=Helper.getConn();
     modify(conn,id,nouveau);
     }
    
    public void modify(Connection connexion, int id, Livre nouveau)throws Exception{
        PreparedStatement preparedStatement = null;
        try{
            connexion.setAutoCommit(false);
            String query = "UPDATE livre SET idecrivain=?,idcategorie=?, titre=?, description=?,datesortie=?,prix=?,nombre=? WHERE id=?";
            preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setInt(1, nouveau.getIdecrivain());
            preparedStatement.setInt(2, nouveau.getIdcategorie());
            preparedStatement.setString(3, nouveau.getTitre());
            preparedStatement.setString(4, nouveau.getDescription());
            preparedStatement.setDate(5, nouveau.getDatesortie());
            preparedStatement.setDouble(6, nouveau.getPrix());
            preparedStatement.setInt(7, nouveau.getNombre());
            preparedStatement.setInt(8, id);
            preparedStatement.executeUpdate();
            connexion.commit();
            
        }
        catch(Exception e){
            connexion.rollback();
            throw e;
        }
        finally{
            if(preparedStatement!=null)preparedStatement.close();
        }
    }
    
    
    
    
    
}





