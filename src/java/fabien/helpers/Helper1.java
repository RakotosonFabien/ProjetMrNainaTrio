/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fabien.helpers;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

/**
 *
 * @author Fabien
 */
public class Helper1 {
    public static String[] getAllMonth(){
        String[] allMonth = {"Janvier", "Fevrier", "Mars", "Avril", "Mai", "Juin", "Juillet", "Aout", "Septembre", "Octobre", "Novembre", "Decembre"};
        return allMonth;
    }
    
    public static int getNombreJours(int mois, int annee){
        int[] jours={31,28,31,30,31,30,31,31,30,31,30,31};
		if(annee%4==0){
			jours[1]=29;
		}
        return jours[mois];
    }
    public static Date stringsToDate(String annee, String mois, String jour)throws Exception{
        try{
            if(annee.compareTo("Annee")==0||mois.compareTo("Mois")==0||jour.compareTo("Jour")==0){
                throw new Exception("Veuillez inserer votre date de naissance");
            }
            int intMois = Integer.parseInt(mois);
            int intAnnee = Integer.parseInt(annee);
            int intJour = Integer.parseInt(jour);
            int nombreJour = getNombreJours(intMois, intAnnee);
            if(nombreJour<intJour){
                throw new Exception("Date invalide eh!");
            }
            Date date = new Date(intAnnee-1900, intMois, new Integer(jour));
            return date;
        }catch(Exception e){
            throw new Exception("La date selectionne est invalide");
        }
    }
    public static void modify(Connection connexion, String table, String colonne, int id, Object nouveau)throws Exception{
        PreparedStatement prepared = null;
        try{
            String query = "UPDATE "+table+" SET "+colonne+"=? WHERE id=?";
            prepared = connexion.prepareStatement(query);
            prepared.setObject(1, nouveau);
            prepared.setInt(2, id);
            prepared.executeUpdate();
        }catch(Exception e){
            throw e;
        }
        finally{
            if(prepared!=null)prepared.close();
        }
    }
    public static int nombrePages(int total, int max){//total commence par 1
        int nombre = 0;
        while(total>0){
            total = total - max;
            nombre ++;
        }
        return nombre;
    }
}
