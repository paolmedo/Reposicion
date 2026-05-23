package PJfullstack.Reposicion.service;


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
    public TurnoTrabajador crearTurno(TurnoTrabajador nuevoTurno){
        log.info("Iniciando creacion de turno... Horario solicitado: {} - {}", nuevoTurno.getHoraInicio(), nuevoTurno.getHoraTermino());
        if (nuevoTurno.getHoraTermino().isBefore(nuevoTurno.getHoraInicio()) ||
            nuevoTurno.getHoraTermino().equals(nuevoTurno.getHoraInicio())){
            log.warn("Se rechazo la creacion del turno: La hora de término ({}) es anterior o igual a la de inicio ({}) ", nuevoTurno.getHoraTermino(), nuevoTurno.getHoraInicio());
            throw  new RuntimeException("La hora de término no puede ser anterior o igual a la hora de inicio.");
        }
        TurnoTrabajador guardado = turnoRepository.save(nuevoTurno);
        log.info("Turno creado exitosamente en la base de datos con ID: {}", guardado.getId());
    return guardado;
    }
    // Listar turnos
    public List<TurnoTrabajador> listarTodosTurnos(){
        return turnoRepository.findAll();
    }
    // Listar un turno en especifico
    public TurnoTrabajador listarUnSoloTurno(Long id){
        return turnoRepository.findById(id).orElseThrow(() -> new RuntimeException("Turno no encontrado" + id));
    }
    // Actualizar turno
    public TurnoTrabajador actualizarTurno(Long id, TurnoTrabajador turnoTrabajadorActualizado){
        TurnoTrabajador turnoExistente = turnoRepository.findById(id).orElseThrow(() -> new RuntimeException("Turno no encontrado" + id));
        turnoExistente.setTipoTurno(turnoTrabajadorActualizado.getTipoTurno());
        turnoExistente.setHoraInicio(turnoTrabajadorActualizado.getHoraInicio());
        turnoExistente.setHoraTermino(turnoTrabajadorActualizado.getHoraTermino());
        return turnoRepository.save(turnoExistente);
    }
    // Eliminar turno
    public void eliminarTurno(Long id){
        turnoRepository.deleteById(id);
    }
}
