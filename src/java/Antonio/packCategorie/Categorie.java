/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Antonio.packCategorie;
import Antonio.utils.Helper;
import java.sql.Connection;
//import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
/**
 *
 * @author Antonio
 */
public class Categorie extends packModele.Modele{
    String categorie;
    public Categorie(){}
    public Categorie(int id, String cate){
        setId(id);
        setCategorie(cate);
    }
    public Categorie(String cat){
    categorie=cat;
    }
    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
    public Categorie[] findCategorie()throws Exception
    {
        Connection conn=null;
        PreparedStatement pst=null;
        try{
            Vector v=new Vector();
            conn  = Helper.getConn();
            conn.setAutoCommit(false);
            pst = conn.prepareStatement("select * from catLivre");
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
            Categorie a=new Categorie(rs.getString("nom"));
            a.setId(rs.getInt("id"));
            v.add(a);
            }
         Categorie[] all=new Categorie[v.size()];
         v.copyInto(all);
         return all;  
        }catch(Exception ex){   
            
            throw ex;
        }finally{
            if(pst!=null)pst.close();
            if(conn!=null)conn.close();            
        }     
        
    }

    @Override
    public void save() throws Exception {
    
    
    
    }

  
    @Override
    public Categorie[] findAll() throws Exception {
    Connection conn=Helper.getConn();
    Categorie[] all=this.findAll(conn);
    return all;
    }
    public Categorie[] findAll(Connection conn) throws Exception {
    PreparedStatement pst=null;
        try{
            Vector v=new Vector();
            conn.setAutoCommit(false);
            pst = conn.prepareStatement("select * from catLivre");
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
            Categorie a=new Categorie(rs.getString("nom"));
            a.setId(rs.getInt("id"));
            v.add(a);
            }
         Categorie[] all=new Categorie[v.size()];
         v.copyInto(all);
         return all;  
        }catch(Exception ex){   
            
            throw ex;
        }finally{
            if(pst!=null)pst.close();
            if(conn!=null)conn.close();            
        }     
    
    
    
    
    }
}
