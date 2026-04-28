package mx.edu.unpa.reporteanualbackend.services.report.s2;

import mx.edu.unpa.reporteanualbackend.dtos.report.s2.DireccionTesisRequestDTO;
import mx.edu.unpa.reporteanualbackend.dtos.report.s2.DireccionTesisResponseDTO;
import java.util.List;

public interface DireccionTesisService {
    DireccionTesisResponseDTO crear(Integer reporteId, DireccionTesisRequestDTO dto);
    List<DireccionTesisResponseDTO> obtenerPorReporte(Integer reporteId);
    DireccionTesisResponseDTO actualizar(Integer id, DireccionTesisRequestDTO dto);
    void eliminar(Integer id);
}