/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.eduit.curso.java.repositories.jdbc;

import ar.com.eduit.curso.java.entities.Curso;
import ar.com.eduit.curso.java.enums.Dia;
import ar.com.eduit.curso.java.enums.Turno;
import ar.com.eduit.curso.java.repositories.interfaces.I_CursoRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;

/**
 *
 * @author pablo
 */
public class CursoRepository  implements I_CursoRepository{
    
    Connection conn;

    public CursoRepository(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void save(Curso curso) {
        if (curso==null) return;
        String query="insert into cursos (titulo,profesor,dia,turno)  values(?,?,?,?)";
        try (PreparedStatement ps=conn.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);){
            ps.setString(1, curso.getTitulo());
            ps.setString(2, curso.getProfesor());
            ps.setString(3, curso.getDia().toString());
            ps.setString(4, curso.getTurno().toString());
            ps.execute();
            ResultSet rs= ps.getGeneratedKeys();
            if(rs.next())curso.setId(rs.getInt(1));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void remove(Curso curso) {
      if (curso==null) return;
      String query = "delete from cursos where id = " +curso.getId();
        try (Statement st = conn.createStatement()){
            st.executeQuery(query);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void update(Curso curso) {
        if (curso==null) return;
        String query = "update cursos set titulo=?, profesor=?, dia=?, turno=? where id=?";
        try (PreparedStatement ps=conn.prepareStatement(query)) 
        {
            ps.setString(1, curso.getTitulo());
            ps.setString(2, curso.getProfesor());
            ps.setString(3, curso.getDia().toString());
            ps.setString(4, curso.getTurno().toString());
            ps.setInt(5, curso.getId());
            ps.execute();
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public List<Curso> getAll() {
        List<Curso> list = new ArrayList();
        try (ResultSet rs = conn.createStatement().executeQuery("select * from cursos")){
            while (rs.next()) {  
                list.add(
                        new Curso(rs.getInt("id"),
                                rs.getString("titulo"),
                                rs.getString("profesor"),
                                Dia.valueOf(rs.getString("dia")),
                                Turno.valueOf(rs.getString("turno"))));             
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
   
    }

 
    

