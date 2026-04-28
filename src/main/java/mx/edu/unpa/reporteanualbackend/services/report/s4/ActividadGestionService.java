package mx.edu.unpa.reporteanualbackend.services.report.s4;

import mx.edu.unpa.reporteanualbackend.dtos.report.s4.ActividadGestionRequestDTO;
import mx.edu.unpa.reporteanualbackend.dtos.report.s4.ActividadGestionResponseDTO;
import java.util.List;

public interface ActividadGestionService {
    ActividadGestionResponseDTO crear(Integer reporteId, ActividadGestionRequestDTO dto);
    List<ActividadGestionResponseDTO> obtenerPorReporte(Integer reporteId);
    ActividadGestionResponseDTO actualizar(Integer id, ActividadGestionRequestDTO dto);
    void eliminar(Integer id);
}