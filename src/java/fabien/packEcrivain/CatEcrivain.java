/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fabien.packEcrivain;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

/**
 *
 * @author Rakotoson
 */
public class CatEcrivain extends packModele.Modele{
    String nom;
//Constructors
    public CatEcrivain(){}
    public CatEcrivain(int id){
        this.setId(id);
    }
    public CatEcrivain(int id, String nom){
        this.setId(id);
        this.setNom(nom);
    }
//Getters and setters

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public void save()throws Exception{
        Connection connexion = null;
        try{
            connexion = Antonio.utils.Helper.getConn();
            save(connexion);
        }
        catch(Exception e){
            throw e;
        }
        finally{
            if(connexion!=null)connexion.close();
        }
    }
    public void save(Connection connexion) throws Exception {
    PreparedStatement prepared = null;
        try{ 
            String query = "INSERT INTO catEcrivain VALUES (nextval('idCatEcrivain'), ?)";
            prepared.setString(1, this.getNom());
            prepared = connexion.prepareStatement(query);
            
            prepared.executeUpdate();
        }
        catch(Exception e){
            throw e;
        }
        finally{
            if(prepared!=null)prepared.close();
        }    
    }

    @Override
    public CatEcrivain[] findAll() throws Exception {
        Connection connexion = null;
        try{
            connexion = Antonio.utils.Helper.getConn();
            return findAll(connexion);
        }
        catch(Exception e){
            throw e;
        }
        finally{
            if(connexion!=null)connexion.close();
        }
    }
    public CatEcrivain[] findAll(Connection connexion) throws Exception {
         ResultSet results = null;
        try{
            Vector vector = new Vector();
            String query = "SELECT * FROM catecrivain";
            results = connexion.createStatement().executeQuery(query);
            CatEcrivain categorie = new CatEcrivain();
            while(results.next()){
                categorie = new CatEcrivain(results.getInt("id"), results.getString("nom"));
                vector.add(categorie);
            }
            CatEcrivain[] categories = new CatEcrivain[vector.size()];
            vector.copyInto(categories);
            return categories;
        }
        catch(Exception e){
            throw e;
        }
        finally{
            if(results!=null)results.close();
        }
    }
    
   
}
