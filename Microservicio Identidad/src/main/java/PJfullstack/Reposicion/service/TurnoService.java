package PJfullstack.Reposicion.service;


import PJfullstack.Reposicion.entity.TurnoTrabajador;
import PJfullstack.Reposicion.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TurnoService {

    @Autowired
    private TurnoRepository turnoRepository;

    // Crear turno
    public TurnoTrabajador crearTurno(TurnoTrabajador nuevoTurno){
        return turnoRepository.save(nuevoTurno);
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
