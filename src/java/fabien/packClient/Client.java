/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fabien.packClient;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

/**
 *
 * @author Fabien
 */
public class Client extends packModele.Modele{
    private String nom;
    private String prenom;
    private Date dateNaissance;
    private String adresse;
    private String mdp;
    private String mail;
//Constructors
    public Client(){}
    public Client(int id, String n, String p, Date dn, String a, String mdp, String m)throws Exception{
        setId(id);
        setNom(n);
        setPrenom(p);
        setDateNaissance(dn);
        setAdresse(a);
        setMdp(mdp);
        setMail(m);
    }
    public Client(String n, String p, Date dn, String a, String mdp, String m)throws Exception{
        setNom(n);
        setPrenom(p);
        setDateNaissance(dn);
        setAdresse(a);
        setMdp(mdp);
        setMail(m);
    }
//Getters and setters

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) throws Exception{
        if(nom.compareTo("")==0||nom==null){
            throw new Exception("Veuillez entrer votre nom");
        }
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) throws Exception{
        if(prenom.compareTo("")==0||prenom==null){
            throw new Exception("Veuillez entrer votre prenom");
        }
        this.prenom = prenom;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) throws Exception{
        this.dateNaissance = dateNaissance;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) throws Exception{
        if(adresse.compareTo("")==0||adresse==null){
            throw new Exception("Veuillez entrer votre adresse");
        }
        this.adresse = adresse;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) throws Exception{
        if(mdp.compareTo("")==0||mdp==null){
            throw new Exception("Veuillez entrer un mot de passe");
        }
        this.mdp = mdp;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) throws Exception{
        if(mail.compareTo("")==0||mail==null){
            throw new Exception("Veuillez entrer votre adresse e-mail");
        }
        this.mail = mail;
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
    public void save(Connection connexion) throws Exception{
        PreparedStatement prepared = null;
        try{
        String query = "INSERT INTO client VALUES (nextval('idClient'), ?, ?, ?, ?, ?, ?)";
        prepared = connexion.prepareStatement(query);
        prepared.setString(1,this.nom);
        prepared.setString(2,this.prenom);
        prepared.setDate(3,this.dateNaissance);
        prepared.setString(4,this.adresse);
        prepared.setString(5,this.mdp);
        prepared.setString(6,this.mail);
        prepared.executeUpdate();
        //g.commit();
        }
        catch(Exception e){
            //g.rollback();
            throw e;
        }
        finally{
            if(prepared!=null)prepared.close();
        }
    }
    public static Client find(String findMail, String findMdp)throws Exception{
        Connection connexion = null;
        try{
            connexion = Antonio.utils.Helper.getConn();
            return find(connexion, findMail, findMdp);
        }
        catch(Exception e){
            throw e;
        }
        finally{
            if(connexion!=null)connexion.close();
        }
    }
    public static Client find(Connection  connexion, String findMail, String findMdp)throws Exception{
        Client monClient = null;
        String query = "SELECT * FROM client WHERE mail=? AND mdp=?";
        PreparedStatement prepared = connexion.prepareStatement(query);
        prepared.setString(1, findMail);
        prepared.setString(2,findMdp);
        ResultSet result = prepared.executeQuery();
        result.next();
        int id = result.getInt("id");
        String nom = result.getString("nom");
        String prenom = result.getString("prenom");
        Date dateNaissance = result.getDate("datenaissance");
        String adresse = result.getString("adresse");
        String mdp = result.getString("mdp");
        String mail = result.getString("mail");
        monClient = new Client(id, nom, prenom, dateNaissance, adresse, mdp, mail);
        return monClient;
    }

    @Override
    public Client[] findAll()throws Exception{
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
    public Client[] findAll(Connection connexion) throws Exception {
        Vector vector = new Vector();
        ResultSet results = null;
        Client[] clients = null;
        Client client = new Client();
        try{
            results = connexion.createStatement().executeQuery("SELECT * FROM Client");
            while(results.next()){
                int id = results.getInt("id");
                String nom = results.getString("nom");
                String prenom = results.getString("prenom");
                Date dateNaissance = results.getDate("dateNaissance");
                String adresse = results.getString("adresse");
                String mdp = results.getString("mdp");
                String mail = results.getString("mail");
                client= new Client(id, nom, prenom, dateNaissance, adresse, mdp, mail);
                vector.add(client);
            }
            clients = new Client[vector.size()];
            vector.copyInto(clients);
            return clients;
        }
        catch(Exception e){
            throw e;
        }
        finally{
            if(results!=null)results.close();
        }
        
    }
}
