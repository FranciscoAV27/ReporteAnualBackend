package mx.edu.unpa.reporteanualbackend.controllers.report.s7;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mx.edu.unpa.reporteanualbackend.dtos.report.s7.DistribucionTiempoRequestDTO;
import mx.edu.unpa.reporteanualbackend.dtos.report.s7.DistribucionTiempoResponseDTO;
import mx.edu.unpa.reporteanualbackend.services.report.s7.DistribucionTiempoService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/reportes/{reporteId}/distribucion")
@RequiredArgsConstructor
public class DistribucionTiempoController {

    private final DistribucionTiempoService service;

    @PostMapping
    public ResponseEntity<DistribucionTiempoResponseDTO> crear(
            @PathVariable Integer reporteId,
            @Valid @RequestBody DistribucionTiempoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(reporteId, dto));
    }

    @GetMapping
    public ResponseEntity<List<DistribucionTiempoResponseDTO>> obtenerPorReporte(
            @PathVariable Integer reporteId) {
        return ResponseEntity.ok(service.obtenerPorReporte(reporteId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DistribucionTiempoResponseDTO> actualizar(
            @PathVariable Integer reporteId,
            @PathVariable Integer id,
            @Valid @RequestBody DistribucionTiempoRequestDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer reporteId,
                                         @PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}