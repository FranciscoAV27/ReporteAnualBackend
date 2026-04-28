package mx.edu.unpa.reporteanualbackend.controllers.report.s1;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mx.edu.unpa.reporteanualbackend.dtos.report.s1.ProductoApoyoDocenteRequestDTO;
import mx.edu.unpa.reporteanualbackend.dtos.report.s1.ProductoApoyoDocenteResponseDTO;
import mx.edu.unpa.reporteanualbackend.services.report.s1.ProductoApoyoDocenteService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/reportes/{reporteId}/productos")
@RequiredArgsConstructor
public class ProductoApoyoDocenteController {

    private final ProductoApoyoDocenteService service;

    @PostMapping
    public ResponseEntity<ProductoApoyoDocenteResponseDTO> crear(
            @PathVariable Integer reporteId,
            @Valid @RequestBody ProductoApoyoDocenteRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(reporteId, dto));
    }

    @GetMapping
    public ResponseEntity<List<ProductoApoyoDocenteResponseDTO>> obtenerPorReporte(
            @PathVariable Integer reporteId) {
        return ResponseEntity.ok(service.obtenerPorReporte(reporteId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoApoyoDocenteResponseDTO> actualizar(
            @PathVariable Integer reporteId,
            @PathVariable Integer id,
            @Valid @RequestBody ProductoApoyoDocenteRequestDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer reporteId,
                                         @PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}