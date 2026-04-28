package mx.edu.unpa.reporteanualbackend.controllers.report.s4;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mx.edu.unpa.reporteanualbackend.dtos.report.s4.ActividadGestionRequestDTO;
import mx.edu.unpa.reporteanualbackend.dtos.report.s4.ActividadGestionResponseDTO;
import mx.edu.unpa.reporteanualbackend.services.report.s4.ActividadGestionService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/reportes/{reporteId}/gestion")
@RequiredArgsConstructor
public class ActividadGestionController {

    private final ActividadGestionService service;

    @PostMapping
    public ResponseEntity<ActividadGestionResponseDTO> crear(
            @PathVariable Integer reporteId,
            @Valid @RequestBody ActividadGestionRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(reporteId, dto));
    }

    @GetMapping
    public ResponseEntity<List<ActividadGestionResponseDTO>> obtenerPorReporte(
            @PathVariable Integer reporteId) {
        return ResponseEntity.ok(service.obtenerPorReporte(reporteId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActividadGestionResponseDTO> actualizar(
            @PathVariable Integer reporteId,
            @PathVariable Integer id,
            @Valid @RequestBody ActividadGestionRequestDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer reporteId,
                                         @PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}