package mx.edu.unpa.reporteanualbackend.controllers.report.s1;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mx.edu.unpa.reporteanualbackend.dtos.report.s1.AsignaturaAfinidadRequestDTO;
import mx.edu.unpa.reporteanualbackend.dtos.report.s1.AsignaturaAfinidadResponseDTO;
import mx.edu.unpa.reporteanualbackend.services.report.s1.AsignaturaAfinidadService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/reportes/{reporteId}/asignaturas")
@RequiredArgsConstructor
public class AsignaturaAfinidadController {

    private final AsignaturaAfinidadService service;

    @PostMapping
    public ResponseEntity<AsignaturaAfinidadResponseDTO> crear(
            @PathVariable Integer reporteId,
            @Valid @RequestBody AsignaturaAfinidadRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(reporteId, dto));
    }

    @GetMapping
    public ResponseEntity<List<AsignaturaAfinidadResponseDTO>> obtenerPorReporte(
            @PathVariable Integer reporteId) {
        return ResponseEntity.ok(service.obtenerPorReporte(reporteId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AsignaturaAfinidadResponseDTO> actualizar(
            @PathVariable Integer reporteId,
            @PathVariable Integer id,
            @Valid @RequestBody AsignaturaAfinidadRequestDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer reporteId,
                                         @PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}