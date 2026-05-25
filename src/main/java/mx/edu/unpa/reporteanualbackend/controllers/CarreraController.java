package mx.edu.unpa.reporteanualbackend.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mx.edu.unpa.reporteanualbackend.dtos.asignatura.AsignaturaResponseDTO;
import mx.edu.unpa.reporteanualbackend.dtos.carrera.CarreraRequestDTO;
import mx.edu.unpa.reporteanualbackend.dtos.carrera.CarreraResponseDTO;
import mx.edu.unpa.reporteanualbackend.repositories.AsignaturaRepository;
import mx.edu.unpa.reporteanualbackend.services.CarreraService;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/carreras")
@RequiredArgsConstructor
public class CarreraController {

    private final CarreraService carreraService;
    private final AsignaturaRepository asignaturaRepository; // ← nuevo

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CarreraResponseDTO> crear(@Valid @RequestBody CarreraRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(carreraService.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<CarreraResponseDTO>> obtenerTodas() {
        return ResponseEntity.ok(carreraService.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarreraResponseDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(carreraService.obtenerPorId(id));
    }

    // ── Nuevo endpoint ─────────────────────────────────────
    @GetMapping("/{id}/asignaturas")
    public ResponseEntity<List<AsignaturaResponseDTO>> obtenerAsignaturas(@PathVariable Integer id) {
        List<AsignaturaResponseDTO> result = asignaturaRepository
                .findByCarreraIdOrderBySemestreAscNombreAsc(id)
                .stream()
                .map(a -> AsignaturaResponseDTO.builder()
                        .id(a.getId())
                        .carreraId(id)
                        .semestre(a.getSemestre())
                        .nombre(a.getNombre())
                        .build())
                .toList();
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CarreraResponseDTO> actualizar(@PathVariable Integer id,
                                                         @Valid @RequestBody CarreraRequestDTO dto) {
        return ResponseEntity.ok(carreraService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        carreraService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}