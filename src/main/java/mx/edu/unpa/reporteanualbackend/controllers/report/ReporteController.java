package mx.edu.unpa.reporteanualbackend.controllers.report;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mx.edu.unpa.reporteanualbackend.dtos.report.ReporteRequestDTO;
import mx.edu.unpa.reporteanualbackend.dtos.report.ValidacionRequestDTO;
import mx.edu.unpa.reporteanualbackend.dtos.report.ReporteResponseDTO;
import mx.edu.unpa.reporteanualbackend.entities.Usuario;
import mx.edu.unpa.reporteanualbackend.entities.enums.EstadoReporte;
import mx.edu.unpa.reporteanualbackend.entities.enums.Rol;
import mx.edu.unpa.reporteanualbackend.repositories.UsuarioRepository;
import mx.edu.unpa.reporteanualbackend.services.report.ReporteService;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reportes")
@RequiredArgsConstructor
public class ReporteController {

    private final ReporteService reporteService;
    private final UsuarioRepository usuarioRepository;

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
    /*@PatchMapping("/{id}/validar")
    public ResponseEntity<ReporteResponseDTO> validar(
            @PathVariable Integer id,
            @Valid @RequestBody ValidacionRequestDTO dto) {
        return ResponseEntity.ok(reporteService.validar(id, dto));
    }*/

    @PatchMapping("/{id}/validar")
    public ResponseEntity<ReporteResponseDTO> validar(
            @PathVariable Integer id,
            @Valid @RequestBody ValidacionRequestDTO dto,
            @AuthenticationPrincipal Usuario usuario) {
        return ResponseEntity.ok(reporteService.validar(id, dto, usuario));
    }

    @PatchMapping("/{id}/touch")
    @PreAuthorize("hasRole('PROFESOR')")
    public ResponseEntity<Void> touch(@PathVariable Integer id) {
        reporteService.touch(id);
        return ResponseEntity.noContent().build();
    }

    // Secretaria: lista reportes pendientes
    @GetMapping("/pendientes")
    //@PreAuthorize("hasRole('SECRETARIA')")
    public ResponseEntity<List<ReporteResponseDTO>> obtenerPendientes() {
        return ResponseEntity.ok(reporteService.obtenerPendientes());
    }

    // Profesor: historial de sus reportes
    @GetMapping("/mis-reportes")
    //@PreAuthorize("hasRole('PROFESOR')")
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

    @GetMapping
    //@PreAuthorize("hasRole('SECRETARIA') or hasRole('ADMIN')")
    public ResponseEntity<List<ReporteResponseDTO>> obtenerPorAnio(
            @RequestParam Integer anio) {
        return ResponseEntity.ok(reporteService.obtenerPorAnio(anio));
    }

    @GetMapping("/stats")
    public ResponseEntity<Map<String, Long>> obtenerStats(@RequestParam Integer anio) {
        long totalProfesores = usuarioRepository.countByRol(Rol.PROFESOR);
        List<ReporteResponseDTO> reportes = reporteService.obtenerPorAnio(anio);
        long aceptados   = reportes.stream().filter(r -> r.getEstado() == EstadoReporte.ACEPTADO).count();
        long enRevision  = reportes.stream().filter(r -> r.getEstado() == EstadoReporte.PENDIENTE_VALIDACION).count();
        long rechazados  = reportes.stream().filter(r -> r.getEstado() == EstadoReporte.RECHAZADO).count();
        long sinEntregar = totalProfesores - aceptados - enRevision - rechazados;
        return ResponseEntity.ok(Map.of(
                "totalProfesores", totalProfesores,
                "aceptados",       aceptados,
                "enRevision",      enRevision,
                "enCorreccion",    rechazados,
                "sinEntregar",     Math.max(sinEntregar, 0)
        ));
    }

    @GetMapping("/mis-reportes/historial")
    @PreAuthorize("hasRole('PROFESOR')")
    public ResponseEntity<List<ReporteResponseDTO>> obtenerHistorialAceptados(
            @AuthenticationPrincipal Usuario usuario) {
        return ResponseEntity.ok(reporteService.obtenerHistorialProfesor(usuario.getId()));
    }
}