package com.reposicion.identidad.repository;

import com.reposicion.identidad.model.TurnoTrabajador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;

@Repository
public interface TurnoRepository extends JpaRepository<TurnoTrabajador, Long> {
    boolean existsByTipoTurnoAndHoraInicioAndHoraTermino(String tipoTurno, LocalTime horaInicio, LocalTime horaTermino);
    TurnoTrabajador findByTipoTurnoAndHoraInicioAndHoraTermino(String tipoTurno, LocalTime horaInicio, LocalTime horaTermino);
}
