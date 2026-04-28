package mx.edu.unpa.reporteanualbackend.controllers.report.s3;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mx.edu.unpa.reporteanualbackend.dtos.report.s3.PublicacionRequestDTO;
import mx.edu.unpa.reporteanualbackend.dtos.report.s3.PublicacionResponseDTO;
import mx.edu.unpa.reporteanualbackend.services.report.s3.PublicacionService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/reportes/{reporteId}/publicaciones")
@RequiredArgsConstructor
public class PublicacionController {

    private final PublicacionService service;

    @PostMapping
    public ResponseEntity<PublicacionResponseDTO> crear(
            @PathVariable Integer reporteId,
            @Valid @RequestBody PublicacionRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(reporteId, dto));
    }

    @GetMapping
    public ResponseEntity<List<PublicacionResponseDTO>> obtenerPorReporte(
            @PathVariable Integer reporteId) {
        return ResponseEntity.ok(service.obtenerPorReporte(reporteId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublicacionResponseDTO> actualizar(
            @PathVariable Integer reporteId,
            @PathVariable Integer id,
            @Valid @RequestBody PublicacionRequestDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer reporteId,
                                         @PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}