/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Antonio.packModele;

import Antonio.packLivre.Livre;

/**
 *
 * @author Antonio
 */
public abstract class Modele {
   
    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
   
    public abstract Livre[] find()throws Exception;
    public abstract Livre[] findbyId(String id)throws Exception;
    public abstract void save()throws Exception;
    public abstract Livre[] search(String tofind,String colonne)throws Exception;
    public abstract void udpdate(Object toupdate,String colonne,String id)throws Exception;;
}
