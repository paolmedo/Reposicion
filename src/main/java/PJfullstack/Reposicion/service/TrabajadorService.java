package PJfullstack.Reposicion.service;

import PJfullstack.Reposicion.entity.Trabajador;
import PJfullstack.Reposicion.repository.TrabajadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrabajadorService {

    @Autowired
    private TrabajadorRepository trabajadorRepository;

    // Crear un trabajador
    public Trabajador guardarTrabajador(Trabajador nuevoTrabajador){
        return trabajadorRepository.save(nuevoTrabajador);
    }
    // Listar TODOS los trabajadores registrados
    public  List<Trabajador> listarTodosTrabajadores(){
        return trabajadorRepository.findAll();
    }
    // Listar un trabajador en especifico
    public Trabajador listarUnSoloTrabajador(Long id){
        Trabajador trabajadorExistente = trabajadorRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado" + id));
        return trabajadorExistente;
    }
    // Actualizar trabajador
    public Trabajador actualizarTrabajador(Long id, Trabajador trabajadorActualizado){
        Trabajador trabajadorExistente = trabajadorRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado" + id));
        trabajadorExistente.setRut(trabajadorActualizado.getRut());
        trabajadorExistente.setNombre(trabajadorActualizado.getNombre());
        trabajadorExistente.setCorreo(trabajadorActualizado.getCorreo());
        trabajadorExistente.setRol(trabajadorActualizado.getRol());
        trabajadorExistente.setEdad(trabajadorActualizado.getEdad());
        return trabajadorRepository.save(trabajadorExistente);
    }
    //Eliminar trabajador
    public void eliminarTrabajador(Long id){
        Trabajador trabajadorExistente = trabajadorRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado" + id));
        trabajadorRepository.deleteById(id);
    }
}