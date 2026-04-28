package mx.edu.unpa.reporteanualbackend.services.report.s3;

import mx.edu.unpa.reporteanualbackend.dtos.report.s3.IndicadorProyectoRequestDTO;
import mx.edu.unpa.reporteanualbackend.dtos.report.s3.IndicadorProyectoResponseDTO;
import java.util.List;

public interface IndicadorProyectoService {
    IndicadorProyectoResponseDTO crear(Integer proyectoId, IndicadorProyectoRequestDTO dto);
    List<IndicadorProyectoResponseDTO> obtenerPorProyecto(Integer proyectoId);
    IndicadorProyectoResponseDTO actualizar(Integer id, IndicadorProyectoRequestDTO dto);
    void eliminar(Integer id);
}