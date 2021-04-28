package ar.com.eduit.curso.java.test;

import ar.com.eduit.curso.java.connectors.Connector;
import ar.com.eduit.curso.java.entities.Curso;
import ar.com.eduit.curso.java.enums.Dia;
import ar.com.eduit.curso.java.enums.Turno;
import ar.com.eduit.curso.java.repositories.interfaces.I_CursoRepository;
import ar.com.eduit.curso.java.repositories.jdbc.CursoRepository;
import java.util.List;

public class TestRepository {
    public static void main(String[] args) {
        I_CursoRepository cr=new CursoRepository(Connector.getConnection());
        
        Curso curso = new Curso("JAVA", "Lopez", Dia.LUNES, Turno.TARDE);
        
        cr.save(curso);
        
        System.out.println(curso);
        
        System.out.println("*************************************");
        
        List<Curso>list=cr.getAll();
        //Recorrido por indice
        
        list.forEach(System.out::println);
        
               // System.out.println("*************************************");

                curso=cr.getById(2);
                System.out.println(curso);
                
               System.out.println("*************************************");
               
               cr.remove(cr.getById(3));

                                
    }
}
