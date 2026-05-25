package mx.edu.unpa.reporteanualbackend.controllers.report;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mx.edu.unpa.reporteanualbackend.dtos.report.ReporteRequestDTO;
import mx.edu.unpa.reporteanualbackend.dtos.report.ValidacionRequestDTO;
import mx.edu.unpa.reporteanualbackend.dtos.report.ReporteResponseDTO;
import mx.edu.unpa.reporteanualbackend.entities.Usuario;
import mx.edu.unpa.reporteanualbackend.services.report.ReporteService;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/reportes")
@RequiredArgsConstructor
public class ReporteController {

    private final ReporteService reporteService;

    // Profesor crea su reporte del año
    @PostMapping
    @PreAuthorize("hasRole('PROFESOR')")
    public ResponseEntity<ReporteResponseDTO> crear(
            @AuthenticationPrincipal Usuario usuario,
            @Valid @RequestBody ReporteRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(reporteService.crear(usuario.getId(), dto));
    }

    // Profesor guarda borrador
    @PutMapping("/{id}/borrador")
    @PreAuthorize("hasRole('PROFESOR')")
    public ResponseEntity<ReporteResponseDTO> guardarBorrador(
            @PathVariable Integer id,
            @Valid @RequestBody ReporteRequestDTO dto) {
        return ResponseEntity.ok(reporteService.guardarBorrador(id, dto));
    }

    // Profesor envía a revisión
    @PatchMapping("/{id}/enviar")
    @PreAuthorize("hasRole('PROFESOR')")
    public ResponseEntity<ReporteResponseDTO> enviarARevision(@PathVariable Integer id) {
        return ResponseEntity.ok(reporteService.enviarARevision(id));
    }

    // Secretaria valida (aprueba o rechaza)
    @PatchMapping("/{id}/validar")
    @PreAuthorize("hasRole('SECRETARIA')")
    public ResponseEntity<ReporteResponseDTO> validar(
            @PathVariable Integer id,
            @Valid @RequestBody ValidacionRequestDTO dto) {
        return ResponseEntity.ok(reporteService.validar(id, dto));
    }

    // Secretaria: lista reportes pendientes
    @GetMapping("/pendientes")
    @PreAuthorize("hasRole('SECRETARIA')")
    public ResponseEntity<List<ReporteResponseDTO>> obtenerPendientes() {
        return ResponseEntity.ok(reporteService.obtenerPendientes());
    }

    // Profesor: historial de sus reportes
    @GetMapping("/mis-reportes")
    @PreAuthorize("hasRole('PROFESOR')")
    public ResponseEntity<List<ReporteResponseDTO>> miHistorial(
            @AuthenticationPrincipal Usuario usuario) {
        return ResponseEntity.ok(reporteService.obtenerPorProfesor(usuario.getId()));
    }

    // Jefe de carrera: estado de entrega de su carrera
    @GetMapping("/carrera/{carreraId}/anio/{anio}")
    @PreAuthorize("hasRole('JEFE_CARRERA')")
    public ResponseEntity<List<ReporteResponseDTO>> porCarreraYAnio(
            @PathVariable Integer carreraId,
            @PathVariable Integer anio) {
        return ResponseEntity.ok(reporteService.obtenerPorCarreraYAnio(carreraId, anio));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReporteResponseDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(reporteService.obtenerPorId(id));
    }

    // Profesor alterna el estado de conclusión de una sección
    @PatchMapping("/{id}/seccion/{numSeccion}/concluir")
    @PreAuthorize("hasRole('PROFESOR')")
    public ResponseEntity<ReporteResponseDTO> toggleSeccion(
            @PathVariable Integer id,
            @PathVariable Integer numSeccion) {
        return ResponseEntity.ok(reporteService.toggleSeccion(id, numSeccion));
    }
}