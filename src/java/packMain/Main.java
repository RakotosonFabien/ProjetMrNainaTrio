/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packMain;
import fabien.packClient.Client;
import Antonio.utils.Helper;
import fabien.packEcrivain.Ecrivain;
import java.sql.Connection;
import java.sql.Date;

/**
 *
 * @author Rakotoson
 */
public class Main {
    public static void main(String[] lol)throws Exception{
        Connection connexion = null;
        try{
            connexion = Helper.getConn();
            //Ecrivain ecrivain = new Ecrivain();
            //ecrivain.setId(21);
            //ecrivain.delete(connexion);
            //Ecrivain.modify(connexion, "datenaissance", 6, new Date(2000-1900, 11, 30));
            Ecrivain[] ecrivains = new Ecrivain().find("ab",0, 5, "dateNaissance", "asc");
            for(Ecrivain ecrivain : ecrivains){
                System.out.println(ecrivain.getDateNaissance());
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally{
        if(connexion!=null)connexion.close();
        }
        
    }
}
