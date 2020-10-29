/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packModele;
import java.sql.Connection;
/**
 *
 * @author Antonio
 */
public abstract class Modele {
   
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    //public abstract Categorie[] findCategorie()throws Exception;
    public abstract void save()throws Exception;
    public abstract Object[] findAll()throws Exception;
}
