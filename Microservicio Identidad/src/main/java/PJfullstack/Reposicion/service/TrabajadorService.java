package PJfullstack.Reposicion.service;

import PJfullstack.Reposicion.dto.TrabajadorDTO;
import PJfullstack.Reposicion.model.Trabajador;
import PJfullstack.Reposicion.model.TurnoTrabajador;
import PJfullstack.Reposicion.repository.TrabajadorRepository;
import PJfullstack.Reposicion.repository.TurnoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TrabajadorService {

    @Autowired
    private TrabajadorRepository trabajadorRepository;

    @Autowired
    private TurnoRepository turnoRepository;

    // Crear un trabajador
    public Trabajador guardarTrabajador(TrabajadorDTO trabajadorDTO){
        log.info("Iniciando creación de nuevo trabajador con RUT {}", trabajadorDTO.getRut());
        Trabajador nuevoTrabajador = new Trabajador();
        nuevoTrabajador.setRut(trabajadorDTO.getRut());
        nuevoTrabajador.setNombre(trabajadorDTO.getNombre());
        nuevoTrabajador.setCorreo(trabajadorDTO.getCorreo());
        nuevoTrabajador.setRol(trabajadorDTO.getRol());
        nuevoTrabajador.setEdad(trabajadorDTO.getEdad());
        if (trabajadorDTO.getTurnoId() != null){
            TurnoTrabajador turno = turnoRepository.findById(trabajadorDTO.getTurnoId()).orElseThrow(() -> new RuntimeException("Turno no encontrado con ID" + trabajadorDTO.getTurnoId()));
            nuevoTrabajador.setTurno(turno);
        }
        return trabajadorRepository.save(nuevoTrabajador);
    }
    // Listar TODOS los trabajadores registrados
    public  List<Trabajador> listarTodosTrabajadores() {
        log.debug("Iniciando busqueda de todos los trabajadores");
        return trabajadorRepository.findAll();
    }
    // Listar un trabajador en específico
    public Trabajador listarUnSoloTrabajador(Long id){
        log.debug("Inicando busqueda de trabajador con ID {}", id);
        return trabajadorRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado" + id));
    }
    // Actualizar trabajador
    public Trabajador actualizarTrabajador(Long id, Trabajador trabajadorActualizado){
        log.info("Iniciando actualizacion de trabajador con ID {}", id);
        Trabajador trabajadorExistente = trabajadorRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado" + id));
        trabajadorExistente.setRut(trabajadorActualizado.getRut());
        trabajadorExistente.setNombre(trabajadorActualizado.getNombre());
        trabajadorExistente.setCorreo(trabajadorActualizado.getCorreo());
        trabajadorExistente.setRol(trabajadorActualizado.getRol());
        trabajadorExistente.setEdad(trabajadorActualizado.getEdad());
        return trabajadorRepository.save(trabajadorExistente);
    }
    // Eliminar trabajador
    public void eliminarTrabajador(Long id){
        log.info("Iniciando eliminacion de trabajador con ID {}", id);
        trabajadorRepository.deleteById(id);
    }
    // Asignar turno a trabajador
    public Trabajador asignarTurno(Long trabajadorId, Long turnoId){
        log.info("Iniciando asignacion vinculo de  turno ID {} al trabajador con ID {}", turnoId, trabajadorId);
        Trabajador trabajador = trabajadorRepository.findById(trabajadorId).orElseThrow(() -> new RuntimeException("Trabajador no encontrado con ID: " + trabajadorId));
        TurnoTrabajador turno = turnoRepository.findById(turnoId).orElseThrow(() -> new RuntimeException("Turno no encontrado con ID: " + turnoId));
        trabajador.setTurno(turno);
        return trabajadorRepository.save(trabajador);
    }
}