package mx.edu.unpa.reporteanualbackend.controllers.report.s1;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mx.edu.unpa.reporteanualbackend.dtos.report.s1.CursoImpartidoRequestDTO;
import mx.edu.unpa.reporteanualbackend.dtos.report.s1.CursoImpartidoResponseDTO;
import mx.edu.unpa.reporteanualbackend.services.report.s1.CursoImpartidoService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/reportes/{reporteId}/cursos")
@RequiredArgsConstructor
public class CursoImpartidoController {

    private final CursoImpartidoService cursoService;

    @PostMapping
    public ResponseEntity<CursoImpartidoResponseDTO> crear(
            @PathVariable Integer reporteId,
            @Valid @RequestBody CursoImpartidoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.crear(reporteId, dto));
    }

    @GetMapping
    public ResponseEntity<List<CursoImpartidoResponseDTO>> obtenerPorReporte(
            @PathVariable Integer reporteId) {
        return ResponseEntity.ok(cursoService.obtenerPorReporte(reporteId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CursoImpartidoResponseDTO> actualizar(
            @PathVariable Integer reporteId,
            @PathVariable Integer id,
            @Valid @RequestBody CursoImpartidoRequestDTO dto) {
        return ResponseEntity.ok(cursoService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer reporteId,
                                         @PathVariable Integer id) {
        cursoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}