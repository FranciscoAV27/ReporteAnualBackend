package mx.edu.unpa.reporteanualbackend.services.report.s1;

import mx.edu.unpa.reporteanualbackend.dtos.report.s1.AsignaturaAfinidadRequestDTO;
import mx.edu.unpa.reporteanualbackend.dtos.report.s1.AsignaturaAfinidadResponseDTO;
import java.util.List;

public interface AsignaturaAfinidadService {
    AsignaturaAfinidadResponseDTO crear(Integer reporteId, AsignaturaAfinidadRequestDTO dto);
    List<AsignaturaAfinidadResponseDTO> obtenerPorReporte(Integer reporteId);
    AsignaturaAfinidadResponseDTO actualizar(Integer id, AsignaturaAfinidadRequestDTO dto);
    void eliminar(Integer id);
}