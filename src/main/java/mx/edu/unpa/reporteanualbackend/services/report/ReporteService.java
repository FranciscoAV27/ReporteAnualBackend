package mx.edu.unpa.reporteanualbackend.services.report;

import mx.edu.unpa.reporteanualbackend.dtos.report.ReporteRequestDTO;
import mx.edu.unpa.reporteanualbackend.dtos.report.ValidacionRequestDTO;
import mx.edu.unpa.reporteanualbackend.dtos.report.ReporteResponseDTO;
import mx.edu.unpa.reporteanualbackend.entities.Usuario;

import java.util.List;

public interface ReporteService {
    ReporteResponseDTO crear(Integer profesorId, ReporteRequestDTO dto);
    ReporteResponseDTO obtenerPorId(Integer id);
    ReporteResponseDTO guardarBorrador(Integer id, ReporteRequestDTO dto);
    ReporteResponseDTO enviarARevision(Integer id);
    //ReporteResponseDTO validar(Integer id, ValidacionRequestDTO dto);
    ReporteResponseDTO validar(Integer id, ValidacionRequestDTO dto, Usuario secretaria);
    void touch(Integer id);
    List<ReporteResponseDTO> obtenerPorProfesor(Integer profesorId);
    List<ReporteResponseDTO> obtenerPorCarreraYAnio(Integer carreraId, Integer anio);
    List<ReporteResponseDTO> obtenerPendientes();
    ReporteResponseDTO toggleSeccion(Integer id, Integer numSeccion); // ← nuevo
    List<ReporteResponseDTO> obtenerPorAnio(Integer anio);
    List<ReporteResponseDTO> obtenerHistorialProfesor(Integer profesorId);
}