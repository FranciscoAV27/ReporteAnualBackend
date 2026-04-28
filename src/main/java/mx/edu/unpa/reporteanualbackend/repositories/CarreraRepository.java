package mx.edu.unpa.reporteanualbackend.repositories;

import mx.edu.unpa.reporteanualbackend.entities.Carrera;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CarreraRepository extends JpaRepository<Carrera, Integer> {
    Optional<Carrera> findByClave(String clave);
    boolean existsByClave(String clave);
}