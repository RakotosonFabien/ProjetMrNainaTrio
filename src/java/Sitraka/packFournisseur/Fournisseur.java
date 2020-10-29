/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sitraka.packFournisseur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

/**
 *
 * @author Sitraka
 */
public class Fournisseur {
    int id;
    String nom;

    public Fournisseur() {
    }

    public Fournisseur(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Fournisseur(String nom) {
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    public static Fournisseur[] find(Connection connexion)throws Exception{
        Fournisseur[] ecrivains = null;
        PreparedStatement prepared = null;
        String query = "SELECT * FROM Fournisseur";
        ResultSet results = null;
        Vector vector = new Vector(50, 50);
        try{
            prepared = connexion.prepareStatement(query);
            results = prepared.executeQuery();
            while(results.next()){
                Fournisseur ecrivain = new Fournisseur(results.getString("nom"));
                ecrivain.setId(results.getInt("id"));
                vector.add(ecrivain);
            }
            ecrivains=new Fournisseur[vector.size()];
            vector.copyInto(ecrivains);
            return ecrivains;
        }
        catch(Exception e){
            throw e;
        }
        finally{
            if(prepared!=null)prepared.close();
            if(results!=null)results.close();
        }
    }
}
