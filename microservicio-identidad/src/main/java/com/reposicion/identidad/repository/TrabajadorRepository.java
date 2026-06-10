package com.reposicion.identidad.repository;
import com.reposicion.identidad.model.Trabajador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrabajadorRepository extends JpaRepository<Trabajador, Long>{

    boolean existsByRut(String rut);

    boolean existsByCorreo(String correo);
}