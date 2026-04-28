package mx.edu.unpa.reporteanualbackend.services.report.s2;

import mx.edu.unpa.reporteanualbackend.dtos.report.s2.TutoriaRequestDTO;
import mx.edu.unpa.reporteanualbackend.dtos.report.s2.TutoriaResponseDTO;
import java.util.List;

public interface TutoriaService {
    TutoriaResponseDTO crear(Integer reporteId, TutoriaRequestDTO dto);
    List<TutoriaResponseDTO> obtenerPorReporte(Integer reporteId);
    TutoriaResponseDTO actualizar(Integer id, TutoriaRequestDTO dto);
    void eliminar(Integer id);
}