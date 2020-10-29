/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sitraka.utils;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Sitraka
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
}
