package mx.edu.unpa.reporteanualbackend.services.report.s3;

import mx.edu.unpa.reporteanualbackend.dtos.report.s3.ActividadDesarrolloRequestDTO;
import mx.edu.unpa.reporteanualbackend.dtos.report.s3.ActividadDesarrolloResponseDTO;
import java.util.List;

public interface ActividadDesarrolloService {
    ActividadDesarrolloResponseDTO crear(Integer reporteId, ActividadDesarrolloRequestDTO dto);
    List<ActividadDesarrolloResponseDTO> obtenerPorReporte(Integer reporteId);
    ActividadDesarrolloResponseDTO actualizar(Integer id, ActividadDesarrolloRequestDTO dto);
    void eliminar(Integer id);
}