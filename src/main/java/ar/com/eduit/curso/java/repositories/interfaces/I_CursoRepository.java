package ar.com.eduit.curso.java.repositories.interfaces;

import ar.com.eduit.curso.java.entities.Curso;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface I_CursoRepository {
    void save(Curso curso);                      //insert (SQL LENGUAGE)
    void remove(Curso curso);                    //delete (SQL LENGUAGE)
    void update(Curso curso);                    //update (SQL LENGUAGE)
    List<Curso> getAll();                        //Select (SQL LENGUAGE)
    
    default Curso getById(int id){
    return getAll()
            .stream()
            .filter(c->c.getId() == id)         //Select where (SQL LENGUAGE)
            .findAny()
            .orElse(new Curso());     
    }                       
    default List<Curso>getLikeTitulo(String titulo){
    if (titulo == null) return new ArrayList<Curso>();
    return getAll()
            .stream()
            .filter(c-> c.getTitulo().toLowerCase().contains(titulo.toLowerCase())) //select where (SQL LENGUAGE)
            .collect(Collectors.toList());
    }
    
    default List<Curso>getLikeTituloProfesor(String titulo, String profesor){
        if(titulo==null || profesor==null) return new ArrayList<Curso>();
        return getAll()
                .stream()
                .filter(c->c.getTitulo().toLowerCase().contains(titulo.toLowerCase())
                        && c.getProfesor().toLowerCase().contains(profesor.toLowerCase()))
                .collect(Collectors.toList());
    }
}
