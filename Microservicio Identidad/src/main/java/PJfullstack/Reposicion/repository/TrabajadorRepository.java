package PJfullstack.Reposicion.repository;
import PJfullstack.Reposicion.model.Trabajador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrabajadorRepository extends JpaRepository<Trabajador, Long>{

}
