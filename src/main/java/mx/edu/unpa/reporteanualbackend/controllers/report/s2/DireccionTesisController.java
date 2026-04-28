package mx.edu.unpa.reporteanualbackend.controllers.report.s2;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mx.edu.unpa.reporteanualbackend.dtos.report.s2.DireccionTesisRequestDTO;
import mx.edu.unpa.reporteanualbackend.dtos.report.s2.DireccionTesisResponseDTO;
import mx.edu.unpa.reporteanualbackend.services.report.s2.DireccionTesisService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/reportes/{reporteId}/tesis")
@RequiredArgsConstructor
public class DireccionTesisController {

    private final DireccionTesisService service;

    @PostMapping
    public ResponseEntity<DireccionTesisResponseDTO> crear(
            @PathVariable Integer reporteId,
            @Valid @RequestBody DireccionTesisRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(reporteId, dto));
    }

    @GetMapping
    public ResponseEntity<List<DireccionTesisResponseDTO>> obtenerPorReporte(
            @PathVariable Integer reporteId) {
        return ResponseEntity.ok(service.obtenerPorReporte(reporteId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DireccionTesisResponseDTO> actualizar(
            @PathVariable Integer reporteId,
            @PathVariable Integer id,
            @Valid @RequestBody DireccionTesisRequestDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer reporteId,
                                         @PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}