package ar.com.eduit.curso.java.repositories.interfaces;

import ar.com.eduit.curso.java.entities.Alumno;
import ar.com.eduit.curso.java.entities.Curso;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface I_AlumnoRepository {
    void save(Alumno alumno);                      //insert (SQL LENGUAGE)
    void remove(Alumno alumno);                    //delete (SQL LENGUAGE)
    void update(Alumno alumno);                    //update (SQL LENGUAGE)
    List<Alumno> getAll();                         //Select (SQL LENGUAGE)
    default Alumno getById(int id){
        return getAll()
                .stream()
                .filter(a->a.getId()==id)                                                          //Select where (SQL LENGUAGE)
                .findAny()
                .orElse(new Alumno());
    }                                             
   default List<Alumno>getLikeApellido(String apellido){
            if(apellido == null) return new ArrayList<Alumno>();
            return getAll()
                    .stream()
                    .filter (a -> a.getApellido().toLowerCase().contains(apellido.toLowerCase()))  //select where (SQL LENGUAGE)
                    .collect(Collectors.toList());
}
   
    default List<Alumno>getLikeCurso(int idCurso){
        return getAll()
                .stream()
                .filter(a->a.getIdCurso()==idCurso)
                .collect(Collectors.toList());
    }
   
   default List<Alumno>getLikeCurso(Curso curso){
       if (curso==null) return new ArrayList<Alumno>();
       return getLikeCurso(curso.getId());
   
   }          //select where (SQL LENGUAGE)
}
