package mx.edu.unpa.reporteanualbackend.controllers.report.s2;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mx.edu.unpa.reporteanualbackend.dtos.report.s2.TutoriaRequestDTO;
import mx.edu.unpa.reporteanualbackend.dtos.report.s2.TutoriaResponseDTO;
import mx.edu.unpa.reporteanualbackend.services.report.s2.TutoriaService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/reportes/{reporteId}/tutorias")
@RequiredArgsConstructor
public class TutoriaController {

    private final TutoriaService service;

    @PostMapping
    public ResponseEntity<TutoriaResponseDTO> crear(
            @PathVariable Integer reporteId,
            @Valid @RequestBody TutoriaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(reporteId, dto));
    }

    @GetMapping
    public ResponseEntity<List<TutoriaResponseDTO>> obtenerPorReporte(@PathVariable Integer reporteId) {
        return ResponseEntity.ok(service.obtenerPorReporte(reporteId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TutoriaResponseDTO> actualizar(
            @PathVariable Integer reporteId,
            @PathVariable Integer id,
            @Valid @RequestBody TutoriaRequestDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer reporteId,
                                         @PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}