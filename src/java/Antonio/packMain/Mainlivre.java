/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Antonio.packMain;

import Antonio.packCategorie.Categorie;
import Antonio.packLivre.Livre;
import Antonio.utils.Helper;
import static java.lang.Math.round;
import java.sql.Connection;
import java.sql.Date;

/**
 *
 * @author Antonio
 */
public class Mainlivre {
    public static void main(String[]lol) throws Exception{
   
    Date d=new Date(2017-1900,8,25);
    
    Connection conn=new Helper().getConn();
        
  
   Livre a=new Livre(2,3,"Java Doc","Apprendre a programmer en java",d,50,3);
 //a.save();
 //a.delete(conn,10);
  //a.modify(conn,2,a);
    Livre[] livres = new Livre().findAll(conn,2,3);
            for (int i=0; i<livres.length; i++){
                System.out.println(livres[i].getTitre());
            }
           // System.out.println("Vita");
}
}
