package mx.edu.unpa.reporteanualbackend.controllers.report.s3;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mx.edu.unpa.reporteanualbackend.dtos.report.s3.ProyectoInvestigacionRequestDTO;
import mx.edu.unpa.reporteanualbackend.dtos.report.s3.ProyectoInvestigacionResponseDTO;
import mx.edu.unpa.reporteanualbackend.services.report.s3.ProyectoInvestigacionService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/reportes/{reporteId}/proyectos")
@RequiredArgsConstructor
public class ProyectoInvestigacionController {

    private final ProyectoInvestigacionService service;

    @PostMapping
    public ResponseEntity<ProyectoInvestigacionResponseDTO> crear(
            @PathVariable Integer reporteId,
            @Valid @RequestBody ProyectoInvestigacionRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(reporteId, dto));
    }

    @GetMapping
    public ResponseEntity<List<ProyectoInvestigacionResponseDTO>> obtenerPorReporte(
            @PathVariable Integer reporteId) {
        return ResponseEntity.ok(service.obtenerPorReporte(reporteId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProyectoInvestigacionResponseDTO> actualizar(
            @PathVariable Integer reporteId,
            @PathVariable Integer id,
            @Valid @RequestBody ProyectoInvestigacionRequestDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer reporteId,
                                         @PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}