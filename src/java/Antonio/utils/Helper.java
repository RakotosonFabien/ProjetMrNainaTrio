/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Antonio.utils;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Antonio
 */
public class Helper {
     public static Connection getConn()throws Exception {
        Connection conn = null;
        try{
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost/projets2021?user=bibliotheque&password=123456";
            conn = DriverManager.getConnection(url);
        }catch(Exception ex){
            throw ex;
        }
        return conn;
    }
    public static int nombrePages(int total, int max){//total commence par 1
        int nombre = 0;
            nombre= total/max;
            if(nombre*max<total)
		{
			nombre++;
                }	
        return nombre;
    }
    public static int retourner0(int a)
    {
        int val=a;
        if(a<0)
        {
           val=a+1;
        }
        return val;
    }
    
}
