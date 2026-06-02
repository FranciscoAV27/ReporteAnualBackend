package mx.edu.unpa.reporteanualbackend.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mx.edu.unpa.reporteanualbackend.dtos.periodo.PeriodoRequestDTO;
import mx.edu.unpa.reporteanualbackend.dtos.periodo.PeriodoResponseDTO;
import mx.edu.unpa.reporteanualbackend.entities.Usuario;
import mx.edu.unpa.reporteanualbackend.services.PeriodoService;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/periodos")
@RequiredArgsConstructor
public class PeriodoController {

    private final PeriodoService periodoService;

    // Solo secretaria puede iniciar/editar/cerrar
    /*@PostMapping
    @PreAuthorize("hasRole('SECRETARIA')")
    public ResponseEntity<PeriodoResponseDTO> iniciar(@Valid @RequestBody PeriodoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(periodoService.iniciar(dto));
    }*/
    @PostMapping
    @PreAuthorize("hasRole('SECRETARIA')")
    public ResponseEntity<PeriodoResponseDTO> iniciar(
            @Valid @RequestBody PeriodoRequestDTO dto,
            @AuthenticationPrincipal Usuario usuario) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(periodoService.iniciar(dto, usuario));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('SECRETARIA')")
    public ResponseEntity<PeriodoResponseDTO> editar(
            @PathVariable Integer id,
            @Valid @RequestBody PeriodoRequestDTO dto) {
        return ResponseEntity.ok(periodoService.editar(id, dto));
    }

    @PatchMapping("/{id}/cerrar")
    @PreAuthorize("hasRole('SECRETARIA')")
    public ResponseEntity<PeriodoResponseDTO> cerrar(@PathVariable Integer id) {
        return ResponseEntity.ok(periodoService.cerrar(id));
    }

    // Cualquier usuario autenticado puede consultar el periodo activo
    @GetMapping("/activo")
    public ResponseEntity<PeriodoResponseDTO> obtenerActivo() {
        Optional<PeriodoResponseDTO> activo = periodoService.obtenerActivo();
        return activo.map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }
}