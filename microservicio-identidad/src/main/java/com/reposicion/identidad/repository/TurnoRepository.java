package com.reposicion.identidad.repository;

import com.reposicion.identidad.model.TurnoTrabajador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnoRepository extends JpaRepository<TurnoTrabajador, Long> {

}
