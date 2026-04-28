package mx.edu.unpa.reporteanualbackend.services.servicesImpl;

import lombok.RequiredArgsConstructor;
import mx.edu.unpa.reporteanualbackend.dtos.usuario.UsuarioRequestDTO;
import mx.edu.unpa.reporteanualbackend.dtos.usuario.UsuarioResponseDTO;
import mx.edu.unpa.reporteanualbackend.entities.Carrera;
import mx.edu.unpa.reporteanualbackend.entities.Usuario;
import mx.edu.unpa.reporteanualbackend.exceptions.ConflictException;
import mx.edu.unpa.reporteanualbackend.exceptions.ResourceNotFoundException;
import mx.edu.unpa.reporteanualbackend.mappers.UsuarioMapper;
import mx.edu.unpa.reporteanualbackend.repositories.CarreraRepository;
import mx.edu.unpa.reporteanualbackend.repositories.UsuarioRepository;
import mx.edu.unpa.reporteanualbackend.services.UsuarioService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final CarreraRepository carreraRepository;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UsuarioResponseDTO crear(UsuarioRequestDTO dto) {
        if (usuarioRepository.existsByNumeroTrabajo(dto.getNumeroTrabajo()))
            throw new ConflictException("Ya existe un usuario con número de trabajo: " + dto.getNumeroTrabajo());
        if (usuarioRepository.existsByCorreo(dto.getCorreo()))
            throw new ConflictException("Ya existe un usuario con el correo: " + dto.getCorreo());

        Usuario usuario = usuarioMapper.toEntity(dto);
        usuario.setContrasenaHash(passwordEncoder.encode(dto.getContrasena()));

        if (dto.getCarreraId() != null) {
            Carrera carrera = carreraRepository.findById(dto.getCarreraId())
                    .orElseThrow(() -> new ResourceNotFoundException("Carrera no encontrada: " + dto.getCarreraId()));
            usuario.setCarrera(carrera);
        }

        return usuarioMapper.toResponse(usuarioRepository.save(usuario));
    }

    @Override
    @Transactional(readOnly = true)
    public UsuarioResponseDTO obtenerPorId(Integer id) {
        return usuarioMapper.toResponse(findOrThrow(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioResponseDTO> obtenerTodos() {
        return usuarioRepository.findAll().stream()
                .map(usuarioMapper::toResponse).toList();
    }

    @Override
    @Transactional
    public UsuarioResponseDTO actualizar(Integer id, UsuarioRequestDTO dto) {
        Usuario usuario = findOrThrow(id);

        // Verificar unicidad solo si cambió el valor
        if (!usuario.getNumeroTrabajo().equals(dto.getNumeroTrabajo())
                && usuarioRepository.existsByNumeroTrabajo(dto.getNumeroTrabajo()))
            throw new ConflictException("Número de trabajo ya en uso: " + dto.getNumeroTrabajo());

        if (!usuario.getCorreo().equals(dto.getCorreo())
                && usuarioRepository.existsByCorreo(dto.getCorreo()))
            throw new ConflictException("Correo ya en uso: " + dto.getCorreo());

        usuario.setNumeroTrabajo(dto.getNumeroTrabajo());
        usuario.setNombre(dto.getNombre());
        usuario.setApellidos(dto.getApellidos());
        usuario.setCorreo(dto.getCorreo());
        usuario.setRol(dto.getRol());

        if (dto.getContrasena() != null && !dto.getContrasena().isBlank())
            usuario.setContrasenaHash(passwordEncoder.encode(dto.getContrasena()));

        if (dto.getCarreraId() != null) {
            Carrera carrera = carreraRepository.findById(dto.getCarreraId())
                    .orElseThrow(() -> new ResourceNotFoundException("Carrera no encontrada: " + dto.getCarreraId()));
            usuario.setCarrera(carrera);
        } else {
            usuario.setCarrera(null);
        }

        return usuarioMapper.toResponse(usuarioRepository.save(usuario));
    }

    @Override
    @Transactional
    public void desactivar(Integer id) {
        Usuario usuario = findOrThrow(id);
        usuario.setActivo(false);
        usuarioRepository.save(usuario);
    }

    private Usuario findOrThrow(Integer id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + id));
    }
}