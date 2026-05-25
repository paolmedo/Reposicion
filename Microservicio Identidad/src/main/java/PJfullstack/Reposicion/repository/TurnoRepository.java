package PJfullstack.Reposicion.repository;

import PJfullstack.Reposicion.model.TurnoTrabajador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnoRepository extends JpaRepository<TurnoTrabajador, Long> {

}
