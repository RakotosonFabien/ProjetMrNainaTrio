/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sitraka.packLivre;

import Sitraka.utils.Helper;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

/**
 *
 * @author Sitraka
 */
public class LivreImp {
     int id;
     int idEcrivain;
     int idCategorie;
     int idFournisseur;
     String titre;
     String description;
     Date date_sortie;
     Float prix_achat; 
     int nombre;

    public LivreImp() {
    }

    public LivreImp(int id,int idEcrivain, int idCategorie, int idFournisseur, String titre, String description, Date date_sortie, Float prix_achat, int nombre) {
        this.id=id;
        this.idEcrivain = idEcrivain;
        this.idCategorie = idCategorie;
        this.idFournisseur = idFournisseur;
        this.titre = titre;
        this.description = description;
        this.date_sortie = date_sortie;
        this.prix_achat = prix_achat;
        this.nombre = nombre;
    }

    public LivreImp(int idEcrivain, int idCategorie, int idFournisseur, String titre, String description, Date date_sortie, Float prix_achat, int nombre) {
        this.idEcrivain = idEcrivain;
        this.idCategorie = idCategorie;
        this.idFournisseur = idFournisseur;
        this.titre = titre;
        this.description = description;
        this.date_sortie = date_sortie;
        this.prix_achat = prix_achat;
        this.nombre = nombre;
    }
     
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEcrivain() {
        return idEcrivain;
    }

    public void setIdEcrivain(int idEcrivain) {
        this.idEcrivain = idEcrivain;
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public int getIdFournisseur() {
        return idFournisseur;
    }

    public void setIdFournisseur(int idFournisseur) {
        this.idFournisseur = idFournisseur;
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

    public Date getDate_sortie() {
        return date_sortie;
    }

    public void setDate_sortie(Date date_sortie) {
        this.date_sortie = date_sortie;
    }

    public Float getPrix_achat() {
        return prix_achat;
    }

    public void setPrix_achat(Float prix_achat) {
        this.prix_achat = prix_achat;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }
    public LivreImp[] find() throws Exception{
        Connection connex=Helper.getConn();
        LivreImp[] findd=find(connex);
        return findd;
    }
    public LivreImp[] findpag(int max,int page) throws Exception{
        Connection connex=Helper.getConn();
        LivreImp[] findd=findpag(connex,page,max);
        return findd;
    }
    public void delete(int idl) throws Exception{
        Connection connex=Helper.getConn();
        delete(connex,idl);
    }
    public void save() throws Exception{
        Connection connex=Helper.getConn();
        save(connex);
    }
    public void update(int idl) throws Exception{
        Connection connex=Helper.getConn();
        update(connex,idl);
    }
     public LivreImp[] find(Connection connexion) throws Exception{
        PreparedStatement pst=null;
       ResultSet rs = null;
        try{
            Vector vector = new Vector();
            String query = "SELECT * FROM LivreImp";
            pst = connexion.prepareStatement(query);
            rs = pst.executeQuery();
             LivreImp ecrivain = new LivreImp();
            while(rs.next()){
                ecrivain = new LivreImp(rs.getInt("id"),rs.getInt("idEcrivain"),rs.getInt("idCategorie"),rs.getInt("idFourniseur"),rs.getString("titre"),rs.getString("description"),rs.getDate("dateSortie"),rs.getFloat("prix"),rs.getInt("nombre"));
                vector.add(ecrivain);
            }
            LivreImp[] ecrivains = new LivreImp[vector.size()];
            vector.copyInto(ecrivains);
            return ecrivains;
        }
        catch(Exception e){
            throw e;
        }
        finally{
            if(rs!=null)rs.close();
        }
     }
      public LivreImp[] findpag(Connection connexion,int max,int page) throws Exception{
        PreparedStatement pst=null;
       ResultSet rs = null;
        try{
            Vector vector = new Vector();
            String query = "SELECT * FROM LivreImp ORDER BY id desc LIMIT ? OFFSET ?";
            pst = connexion.prepareStatement(query);
            pst.setInt(1,page);
            pst.setInt(2,page*max);
            rs = pst.executeQuery();
             LivreImp ecrivain = new LivreImp();
            while(rs.next()){
                ecrivain = new LivreImp(rs.getInt("id"),rs.getInt("idEcrivain"),rs.getInt("idCategorie"),rs.getInt("idFourniseur"),rs.getString("titre"),rs.getString("description"),rs.getDate("dateSortie"),rs.getFloat("prix"),rs.getInt("nombre"));
                vector.add(ecrivain);
            }
            LivreImp[] ecrivains = new LivreImp[vector.size()];
            vector.copyInto(ecrivains);
            return ecrivains;
        }
        catch(Exception e){
            throw e;
        }
        finally{
            if(rs!=null)rs.close();
        }
     }
     public void save(Connection connexion) throws Exception{
         PreparedStatement prepared = null;
        try{ 
            String query = "INSERT INTO LivreImp VALUES (nextval('idLivreImp'), ?, ?, ?, ?,?,?,?,?)";
            prepared = connexion.prepareStatement(query);
            prepared.setInt(1,getIdEcrivain());
            prepared.setInt(2,getIdCategorie());
            prepared.setInt(3,getIdFournisseur());
            prepared.setString(4,getTitre());
            prepared.setString(5,getDescription());
            prepared.setDate(6,getDate_sortie());
            prepared.setFloat(7,getPrix_achat());
            prepared.setInt(8,getNombre());
            prepared.executeUpdate();
            System.out.println(query);
        }
        catch(Exception e){
            throw e;
        }
        finally{
            prepared.close();
        }
     }
     public void update(Connection connexion,int idl) throws Exception{
       PreparedStatement prepared = null;
        try{ 
            String query = "UPDATE LivreImp SET idEcrivain=?, idCategorie=?, idFourniseur=?, titre=?, description=?, dateSortie=?, prix=?, nombre=? where id=?";
            prepared = connexion.prepareStatement(query);
            prepared.setInt(1,getIdEcrivain());
            prepared.setInt(2,getIdCategorie());
            prepared.setInt(3,getIdFournisseur());
            prepared.setString(4,getTitre());
            prepared.setString(5,getDescription());
            prepared.setDate(6,getDate_sortie());
            prepared.setFloat(7,getPrix_achat());
            prepared.setInt(8,getNombre());
            prepared.setInt(9,idl);
            prepared.executeUpdate();
            System.out.println(query);
        }
        catch(Exception e){
            throw e;
        }
        finally{
             prepared.close();
        }
     }
     public void delete(Connection connexion,int idl) throws Exception{
       PreparedStatement prepared = null;
        try{ 
            String query = "delete from LivreImp where id=?";
            prepared = connexion.prepareStatement(query);
            prepared.setInt(1,idl);
            prepared.executeUpdate();
            System.out.println(query);
        }
        catch(Exception e){
            throw e;
        }
        finally{
             prepared.close();
        }
     }
}
