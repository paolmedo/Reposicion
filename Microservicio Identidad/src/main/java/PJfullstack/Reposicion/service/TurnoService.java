package PJfullstack.Reposicion.service;


import PJfullstack.Reposicion.dto.TurnoDTO;
import PJfullstack.Reposicion.entity.TurnoTrabajador;
import PJfullstack.Reposicion.repository.TurnoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
public class TurnoService {

    @Autowired
    private TurnoRepository turnoRepository;

    // Crear turno con validaciones
    public TurnoTrabajador crearTurno(TurnoDTO turnoDTO){
        log.info("Iniciando creacion de turno... Horario solicitado: {} - {}", turnoDTO.getHoraInicio(), turnoDTO.getHoraTermino());
        if (turnoDTO.getHoraTermino().isBefore(turnoDTO.getHoraInicio()) ||
            turnoDTO.getHoraTermino().equals(turnoDTO.getHoraInicio())){
            log.warn("Se rechazo la creacion del turno: La hora de término ({}) es anterior o igual a la de inicio ({}) ", turnoDTO.getHoraTermino(), turnoDTO.getHoraInicio());
            throw  new RuntimeException("La hora de término no puede ser anterior o igual a la hora de inicio.");
        }
        TurnoTrabajador nuevoTurno = new TurnoTrabajador();
        nuevoTurno.setTipoTurno(turnoDTO.getTipoTurno());
        nuevoTurno.setHoraInicio(turnoDTO.getHoraInicio());
        nuevoTurno.setHoraTermino(turnoDTO.getHoraTermino());

        TurnoTrabajador guardado = turnoRepository.save(nuevoTurno);
        log.info("Turno creado exitosamente en la base de datos con ID: {}", guardado.getTipoTurno());
    return guardado;
    }
    // Listar turnos
    public List<TurnoTrabajador> listarTodosTurnos(){
        log.debug("Iniciando busqueda de todos los turnos");
        return turnoRepository.findAll();
    }
    // Listar un turno en especifico
    public TurnoTrabajador listarUnSoloTurno(Long id){
        log.debug("Iniciando busqueda de turno con ID {}", id);
        return turnoRepository.findById(id).orElseThrow(() -> new RuntimeException("Turno no encontrado" + id));
    }
    // Actualizar turno
    public TurnoTrabajador actualizarTurno(Long id, TurnoTrabajador turnoTrabajadorActualizado){
        log.info("Iniciando actualizacion actualizacion de turno con ID {}", id);
        TurnoTrabajador turnoExistente = turnoRepository.findById(id).orElseThrow(() -> new RuntimeException("Turno no encontrado" + id));
        turnoExistente.setTipoTurno(turnoTrabajadorActualizado.getTipoTurno());
        turnoExistente.setHoraInicio(turnoTrabajadorActualizado.getHoraInicio());
        turnoExistente.setHoraTermino(turnoTrabajadorActualizado.getHoraTermino());
        return turnoRepository.save(turnoExistente);
    }
    // Eliminar turno
    public void eliminarTurno(Long id){
        log.info("Iniciando eliminacion de turno con ID {}", id);
        turnoRepository.deleteById(id);
    }
}
