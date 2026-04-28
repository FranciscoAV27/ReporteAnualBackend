package mx.edu.unpa.reporteanualbackend.controllers.report.s3;

// Nota: anidado bajo /proyectos/{proyectoId}/indicadores (no bajo reporteId)
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mx.edu.unpa.reporteanualbackend.dtos.report.s3.IndicadorProyectoRequestDTO;
import mx.edu.unpa.reporteanualbackend.dtos.report.s3.IndicadorProyectoResponseDTO;
import mx.edu.unpa.reporteanualbackend.services.report.s3.IndicadorProyectoService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/proyectos/{proyectoId}/indicadores")
@RequiredArgsConstructor
public class IndicadorProyectoController {

    private final IndicadorProyectoService service;

    @PostMapping
    public ResponseEntity<IndicadorProyectoResponseDTO> crear(
            @PathVariable Integer proyectoId,
            @Valid @RequestBody IndicadorProyectoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(proyectoId, dto));
    }

    @GetMapping
    public ResponseEntity<List<IndicadorProyectoResponseDTO>> obtenerPorProyecto(
            @PathVariable Integer proyectoId) {
        return ResponseEntity.ok(service.obtenerPorProyecto(proyectoId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<IndicadorProyectoResponseDTO> actualizar(
            @PathVariable Integer proyectoId,
            @PathVariable Integer id,
            @Valid @RequestBody IndicadorProyectoRequestDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer proyectoId,
                                         @PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}