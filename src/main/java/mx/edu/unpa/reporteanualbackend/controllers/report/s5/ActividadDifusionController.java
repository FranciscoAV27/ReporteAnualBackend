package mx.edu.unpa.reporteanualbackend.controllers.report.s5;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mx.edu.unpa.reporteanualbackend.dtos.report.s5.ActividadDifusionRequestDTO;
import mx.edu.unpa.reporteanualbackend.dtos.report.s5.ActividadDifusionResponseDTO;
import mx.edu.unpa.reporteanualbackend.services.report.s5.ActividadDifusionService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/reportes/{reporteId}/difusion")
@RequiredArgsConstructor
public class ActividadDifusionController {

    private final ActividadDifusionService service;

    @PostMapping
    public ResponseEntity<ActividadDifusionResponseDTO> crear(
            @PathVariable Integer reporteId,
            @Valid @RequestBody ActividadDifusionRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(reporteId, dto));
    }

    @GetMapping
    public ResponseEntity<List<ActividadDifusionResponseDTO>> obtenerPorReporte(
            @PathVariable Integer reporteId) {
        return ResponseEntity.ok(service.obtenerPorReporte(reporteId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActividadDifusionResponseDTO> actualizar(
            @PathVariable Integer reporteId,
            @PathVariable Integer id,
            @Valid @RequestBody ActividadDifusionRequestDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer reporteId,
                                         @PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}