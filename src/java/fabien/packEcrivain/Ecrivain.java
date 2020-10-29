/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fabien.packEcrivain;

import Antonio.packCategorie.Categorie;
import fabien.helpers.Helper1;
import java.awt.List;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

/**
 *
 * @author Rakotoson
 */
public class Ecrivain extends packModele.Modele{
    CatEcrivain catEcrivain;
    String nom;
    String prenom;
    Date dateNaissance;
//Constructors
    public Ecrivain(){
        
    }
    public Ecrivain(int idCategorie, String nom, String prenom, Date dateNaissance)throws Exception{
        CatEcrivain categorie = new CatEcrivain(idCategorie);
        this.setCatEcrivain(categorie);
        this.setNom(nom);
        this.setPrenom(prenom);
        this.setDateNaissance(dateNaissance);
    }
    //ecrivain = new Ecrivain(rs.getInt("idecrivain"),rs.getString("nomecrivain"),rs.getString("prenomecrivain"),rs.getDate("datenaissance"), categorieE);
    public Ecrivain(int id, String nom, String prenom, Date dateNaissance, CatEcrivain cat)throws Exception{
        setId(id);
        setNom(nom);
        setPrenom(prenom);
        setDateNaissance(dateNaissance);
        setCatEcrivain(cat);
    }
    public Ecrivain(ResultSet results)throws Exception{
        int idCategorie = results.getInt("idCategorie");
        int id = results.getInt("id");
        String nomCategorie = results.getString("nomCategorie");
        String nom = results.getString("nom");
        String prenom = results.getString("prenom");
        Date dateNaissance = results.getDate("dateNaissance");
        this.setId(id);
        this.setCatEcrivain(new CatEcrivain(idCategorie, nomCategorie));
        this.setNom(nom);
        this.setPrenom(prenom);
        this.setDateNaissance(dateNaissance);
    }
//Getters and setters

    public CatEcrivain getCatEcrivain() {
        return catEcrivain;
    }

    public void setCatEcrivain(CatEcrivain catEcrivain) {
        this.catEcrivain = catEcrivain;
    }

    public String getNom() {
        
        return nom;
    }

    public void setNom(String nom) throws Exception{
        if(nom.equals("")){
            throw new Exception ("Le nom de l'ecrivain est vide");
        }
        this.nom = nom;
    }

    public String getPrenom() {
        
        return prenom;
    }

    public void setPrenom(String prenom) throws Exception{
        if(prenom.equals("")){
            throw new Exception ("Le prenom de l'ecrivain est vide");
        }
        this.prenom = prenom;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
    public Ecrivain[] findAll(int page, int max, String colonneTri, String way)throws Exception{
        Connection connexion = null;
        try{
            connexion = Antonio.utils.Helper.getConn();
            return findAll(connexion, page, max, colonneTri, way);
        }
        catch(Exception e){
            throw e;
        }
        finally{
            if(connexion!=null)connexion.close();
        }
    }
    public Ecrivain[] findAll(Connection connexion, int page, int max, String colonneTri, String way)throws Exception{//Page debute par 0 non par 1
        if(colonneTri==null){
            colonneTri = "dateNaissance";
        }
        if(way==null){
            way="DESC";
        }
        
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try{
            String query = "SELECT * FROM ecrivainComplet ORDER BY "+colonneTri+" " + way + " LIMIT ? OFFSET ?";
            preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setInt(1, max);
            preparedStatement.setInt(2, page*max);
            resultSet = preparedStatement.executeQuery();
            Ecrivain ecrivain = null;
            Vector vector = new Vector(50);
            while(resultSet.next()){
                ecrivain=new Ecrivain(resultSet);
                vector.add(ecrivain);
            }
            Ecrivain[] ecrivains = new Ecrivain[vector.size()];
            vector.copyInto(ecrivains);
            return ecrivains;
        }
        catch(Exception e){
            throw e;
        }
        finally{
            if(resultSet!=null)resultSet.close();
            if(preparedStatement!=null)preparedStatement.close();
        }
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
            String query = "INSERT INTO ecrivain VALUES (nextval('idLivre'), ?, ?, ?, ?)";
            prepared = connexion.prepareStatement(query);
            prepared.setInt(1,this.getCatEcrivain().getId());
            prepared.setString(2,this.getNom());
            prepared.setString(3,this.getPrenom());
            prepared.setDate(4,this.getDateNaissance());
            prepared.executeUpdate();
        }
        catch(Exception e){
            throw e;
        }
    }
    public static Ecrivain[] find(String valeur, int page, int max, String colonneTri, String way)throws Exception{
        if(colonneTri==null){
            colonneTri = "nom";
        }
        if(way==null){
            way = "DESC";
        }
        Connection connexion = null;
        Ecrivain[] ecrivains = null;
        PreparedStatement prepared = null;
        String query = "SELECT * FROM ecrivaincomplet WHERE concat(lower(nom),' ',lower(prenom)) like lower(?) ORDER BY " + colonneTri + " " + way + " LIMIT ? OFFSET ?";
        ResultSet results = null;
        Vector vector = new Vector(50, 50);
        try{
            connexion = Antonio.utils.Helper.getConn();
            prepared = connexion.prepareStatement(query);
            prepared.setString(1, "%"+valeur+"%");
            prepared.setInt(2, max);
            prepared.setInt(3, max*page);
            results = prepared.executeQuery();
            while(results.next()){
                Ecrivain ecrivain = new Ecrivain(results);
                vector.add(ecrivain);
            }
            ecrivains=new Ecrivain[vector.size()];
            vector.copyInto(ecrivains);
            return ecrivains;
        }
        catch(Exception e){
            throw e;
        }
        finally{
            if(prepared!=null)prepared.close();
            if(results!=null)results.close();
            if(connexion!=null)connexion.close();
        }
    }
    public static Ecrivain[] find(String valeur)throws Exception{
        Connection connexion = null;
        Ecrivain[] ecrivains = null;
        PreparedStatement prepared = null;
        String query = "SELECT * FROM ecrivaincomplet WHERE concat(lower(nom),' ',lower(prenom)) like lower(?) ";
        ResultSet results = null;
        Vector vector = new Vector(50, 50);
        try{
            connexion = Antonio.utils.Helper.getConn();
            prepared = connexion.prepareStatement(query);
            prepared.setString(1, "%"+valeur+"%");
            results = prepared.executeQuery();
            while(results.next()){
                Ecrivain ecrivain = new Ecrivain(results);
                vector.add(ecrivain);
            }
            ecrivains=new Ecrivain[vector.size()];
            vector.copyInto(ecrivains);
            return ecrivains;
        }
        catch(Exception e){
            throw e;
        }
        finally{
            if(prepared!=null)prepared.close();
            if(results!=null)results.close();
            if(connexion!=null)connexion.close();
        }
    }
    public void modify(String colonne, Object nouveau)throws Exception{
        Connection connexion = null;
        try{
            connexion = Antonio.utils.Helper.getConn();
            this.modify(connexion, colonne, nouveau);
        }
        catch(Exception e){
            throw e;
        }
        finally{
            if(connexion!=null)connexion.close();
        }
    }
    public void modify(Connection connexion, String colonne, Object nouveau)throws Exception{
        Ecrivain.modify(connexion, colonne, this.getId(), nouveau);
    }
    public static void modify(Connection connexion, String colonne, int id, Object nouveau)throws Exception{
        Helper1.modify(connexion, "ecrivain", colonne, id, nouveau);
    }
    public static void modify(int id, Ecrivain nouveau)throws Exception{
        Connection connexion = null;
        try{
            connexion = Antonio.utils.Helper.getConn();
            Ecrivain.modify(connexion, id, nouveau);
        }
        catch(Exception e){
            throw e;
        }
        finally{
            if(connexion!=null)connexion.close();
        }
    }
    public static void modify(Connection connexion, int id, Ecrivain nouveau)throws Exception{
        PreparedStatement preparedStatement = null;
        try{
            String query = "UPDATE ecrivain SET idcategorie=?, nom=?, prenom=?, datenaissance=? WHERE id=?";
            preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setInt(1, nouveau.getCatEcrivain().getId());
            preparedStatement.setString(2, nouveau.getNom());
            preparedStatement.setString(3, nouveau.getPrenom());
            preparedStatement.setDate(4, nouveau.getDateNaissance());
            preparedStatement.setInt(5, id);
            preparedStatement.executeUpdate();
        }
        catch(Exception e){
            throw e;
        }
        finally{
            if(preparedStatement!=null)preparedStatement.close();
        }
    }
    public static void deleteById(int id)throws Exception{
        Connection connexion = null;
        try{
            connexion = Antonio.utils.Helper.getConn();
            Ecrivain.deleteById(connexion, id);
        }catch(Exception e){
            throw e;
        }
        finally{
            if(connexion!=null)connexion.close();
        }
    }
    public static void deleteById(Connection connexion, int id)throws Exception{
        PreparedStatement prepared = null;
        int resultat = -110;
        ResultSet resultSet = null;
        try{
            String query = "SELECT deleteEcrivain(?)";
            prepared = connexion.prepareStatement(query);
            prepared.setInt(1,id);
            resultSet = prepared.executeQuery();
            resultSet.next();
            resultat = resultSet.getInt("deleteecrivain");
            if(resultat<0){
                throw new Exception("Inscription non reussi, l'ecrivain selectionne n'existe plus dans la base");
            }
            if(resultat>0){
                throw new Exception("L'ecrivain selectionne ne peut etre supprime, veuillez supprimer ses livres avant");
            }
        }
        catch(Exception e){
            throw e;
        }
        finally{
            if(prepared!=null)prepared.close();
            if(resultSet!=null)resultSet.close();
        }
    }
    public void delete(Connection connexion)throws Exception{
        Ecrivain.deleteById(connexion, this.getId());
    }
    public static Ecrivain findById(int id)throws Exception{
        Connection connexion = null;
        try{
            connexion = Antonio.utils.Helper.getConn();
            return findById(connexion, id);
        }
        catch(Exception e){
            throw e;
        }
        finally{
            if(connexion!=null)connexion.close();
        }
    }
    public static Ecrivain findById(Connection connexion, int id)throws Exception{
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            Ecrivain ecrivain = null;
            String query = "SELECT * FROM ecrivainComplet WHERE id=?";
            preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            ecrivain = new Ecrivain(resultSet);
            return ecrivain;
        }
        catch(Exception e){
            throw e;
        }
        finally{
            if(preparedStatement!=null)preparedStatement.close();
            if(resultSet!=null)resultSet.close();
        }
    }
    @Override
    public Ecrivain[] findAll()throws Exception{
        Connection connexion = null;
        try{
            connexion = Antonio.utils.Helper.getConn();
            return this.findAll(connexion);
        }
        catch(Exception e){
            throw e;
        }
        finally{
            if(connexion!=null)connexion.close();
        }
        
    }
    public Ecrivain[] findAll(Connection connexion) throws Exception {
        ResultSet results = null;
        try{
            Vector vector = new Vector();
            String query = "SELECT * FROM ecrivainComplet";
            results = connexion.createStatement().executeQuery(query);
            Ecrivain ecrivain = new Ecrivain();
            while(results.next()){
                ecrivain = new Ecrivain(results);
                /*int idCategorie = results.getInt("idCategorie");
                int id = results.getInt("id");
                
                String nomCategorie = results.getString("nomCategorie");
                
                String nom = results.getString("nom");
                String prenom = results.getString("prenom");
                Date dateNaissance = results.getDate("dateNaissance");
                ecrivain.setId(id);
                ecrivain.setCatEcrivain(new CatEcrivain(idCategorie, nomCategorie));
                ecrivain.setNom(nom);
                ecrivain.setPrenom(prenom);
                ecrivain.setDateNaissance(dateNaissance);
*/
                vector.add(ecrivain);
            }
            Ecrivain[] ecrivains = new Ecrivain[vector.size()];
            vector.copyInto(ecrivains);
            return ecrivains;
        }
        catch(Exception e){
            throw e;
        }
        finally{
            if(results!=null)results.close();
        }
        
    }

}
