package mx.edu.unpa.reporteanualbackend.repositories;

import mx.edu.unpa.reporteanualbackend.entities.Usuario;
import mx.edu.unpa.reporteanualbackend.entities.enums.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByNumeroTrabajo(String numeroTrabajo);
    Optional<Usuario> findByCorreo(String correo);
    boolean existsByNumeroTrabajo(String numeroTrabajo);
    boolean existsByCorreo(String correo);
    List<Usuario> findByRolAndCarreraId(Rol rol, Integer carreraId);
}