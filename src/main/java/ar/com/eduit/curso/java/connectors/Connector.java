package ar.com.eduit.curso.java.connectors;

import java.sql.Connection;
import java.sql.DriverManager;
public class Connector {
   private static String driver="org.mariadb.jdbc.Driver";
   private static String url="jdbc:mariadb://localhost:3306/colegio?serverTimezoneUTC";
   private static String user="root";
   private static String pass="";
   private static Connection conn=null;
   
   private Connector(){}
   
   public static synchronized Connection getConnection(){
       try {
              if(conn==null || conn.isClosed()){
                  Class.forName(driver);
              conn=DriverManager.getConnection(url,user,pass);
                }
           } 
       
       catch (Exception e) {
           
           System.out.println(e);
       }
       return conn;

   }
   

   }
   
   

