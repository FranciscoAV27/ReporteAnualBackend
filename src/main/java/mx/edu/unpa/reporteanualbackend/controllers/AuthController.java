package mx.edu.unpa.reporteanualbackend.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mx.edu.unpa.reporteanualbackend.configs.JwtConfig;
import mx.edu.unpa.reporteanualbackend.entities.Usuario;
import mx.edu.unpa.reporteanualbackend.exceptions.ResourceNotFoundException;
import mx.edu.unpa.reporteanualbackend.repositories.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtConfig jwtConfig;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody @Valid LoginRequest request) {
        Usuario usuario = usuarioRepository.findByNumeroTrabajo(request.numeroTrabajo())
                .orElseThrow(() -> new ResourceNotFoundException("Credenciales inválidas"));
        if (!passwordEncoder.matches(request.contrasena(), usuario.getContrasenaHash()))
            return ResponseEntity.status(401).body(Map.of("error", "Credenciales inválidas"));
        String token = jwtConfig.generarToken(
                usuario.getNumeroTrabajo(), usuario.getRol().name());
        return ResponseEntity.ok(Map.of("token", token, "rol", usuario.getRol().name()));
    }

    public record LoginRequest(String numeroTrabajo, String contrasena) {}
}