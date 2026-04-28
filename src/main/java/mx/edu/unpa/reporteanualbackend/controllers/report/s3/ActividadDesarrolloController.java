package mx.edu.unpa.reporteanualbackend.controllers.report.s3;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mx.edu.unpa.reporteanualbackend.dtos.report.s3.ActividadDesarrolloRequestDTO;
import mx.edu.unpa.reporteanualbackend.dtos.report.s3.ActividadDesarrolloResponseDTO;
import mx.edu.unpa.reporteanualbackend.services.report.s3.ActividadDesarrolloService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/reportes/{reporteId}/desarrollo")
@RequiredArgsConstructor
public class ActividadDesarrolloController {

    private final ActividadDesarrolloService service;

    @PostMapping
    public ResponseEntity<ActividadDesarrolloResponseDTO> crear(
            @PathVariable Integer reporteId,
            @Valid @RequestBody ActividadDesarrolloRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(reporteId, dto));
    }

    @GetMapping
    public ResponseEntity<List<ActividadDesarrolloResponseDTO>> obtenerPorReporte(
            @PathVariable Integer reporteId) {
        return ResponseEntity.ok(service.obtenerPorReporte(reporteId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActividadDesarrolloResponseDTO> actualizar(
            @PathVariable Integer reporteId,
            @PathVariable Integer id,
            @Valid @RequestBody ActividadDesarrolloRequestDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer reporteId,
                                         @PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}