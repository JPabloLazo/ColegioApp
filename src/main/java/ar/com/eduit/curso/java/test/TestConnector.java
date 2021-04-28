package ar.com.eduit.curso.java.test;

import ar.com.eduit.curso.java.connectors.Connector;
import java.sql.ResultSet;

public class TestConnector {
    public static void main(String[] args) {
     
        try {
           ResultSet rs=
            Connector.getConnection().createStatement().executeQuery("select version()");
           
            if (rs.next()) {
                System.out.println("Se conecto a: " + rs.getString(1));            
                           }
            else { System.out.println("No se pudo conectar!");}
        } catch (Exception e) {
            System.out.println(e);
        };
    }
}
