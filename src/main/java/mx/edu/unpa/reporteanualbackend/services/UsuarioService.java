package mx.edu.unpa.reporteanualbackend.services;

import mx.edu.unpa.reporteanualbackend.dtos.usuario.UsuarioRequestDTO;
import mx.edu.unpa.reporteanualbackend.dtos.usuario.UsuarioResponseDTO;
import java.util.List;

public interface UsuarioService {
    UsuarioResponseDTO crear(UsuarioRequestDTO dto);
    UsuarioResponseDTO obtenerPorId(Integer id);
    List<UsuarioResponseDTO> obtenerTodos();
    UsuarioResponseDTO actualizar(Integer id, UsuarioRequestDTO dto);
    void desactivar(Integer id);
}