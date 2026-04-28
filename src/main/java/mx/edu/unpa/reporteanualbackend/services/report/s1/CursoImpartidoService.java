package mx.edu.unpa.reporteanualbackend.services.report.s1;

import mx.edu.unpa.reporteanualbackend.dtos.report.s1.CursoImpartidoRequestDTO;
import mx.edu.unpa.reporteanualbackend.dtos.report.s1.CursoImpartidoResponseDTO;
import java.util.List;

public interface CursoImpartidoService {
    CursoImpartidoResponseDTO crear(Integer reporteId, CursoImpartidoRequestDTO dto);
    List<CursoImpartidoResponseDTO> obtenerPorReporte(Integer reporteId);
    CursoImpartidoResponseDTO actualizar(Integer id, CursoImpartidoRequestDTO dto);
    void eliminar(Integer id);
}