package mx.edu.unpa.reporteanualbackend.services.report.s5;

import mx.edu.unpa.reporteanualbackend.dtos.report.s5.ActividadDifusionRequestDTO;
import mx.edu.unpa.reporteanualbackend.dtos.report.s5.ActividadDifusionResponseDTO;
import java.util.List;

public interface ActividadDifusionService {
    ActividadDifusionResponseDTO crear(Integer reporteId, ActividadDifusionRequestDTO dto);
    List<ActividadDifusionResponseDTO> obtenerPorReporte(Integer reporteId);
    ActividadDifusionResponseDTO actualizar(Integer id, ActividadDifusionRequestDTO dto);
    void eliminar(Integer id);
}