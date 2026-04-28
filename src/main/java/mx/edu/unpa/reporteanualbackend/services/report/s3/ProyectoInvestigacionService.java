package mx.edu.unpa.reporteanualbackend.services.report.s3;

import mx.edu.unpa.reporteanualbackend.dtos.report.s3.ProyectoInvestigacionRequestDTO;
import mx.edu.unpa.reporteanualbackend.dtos.report.s3.ProyectoInvestigacionResponseDTO;
import java.util.List;

public interface ProyectoInvestigacionService {
    ProyectoInvestigacionResponseDTO crear(Integer reporteId, ProyectoInvestigacionRequestDTO dto);
    List<ProyectoInvestigacionResponseDTO> obtenerPorReporte(Integer reporteId);
    ProyectoInvestigacionResponseDTO actualizar(Integer id, ProyectoInvestigacionRequestDTO dto);
    void eliminar(Integer id);
}