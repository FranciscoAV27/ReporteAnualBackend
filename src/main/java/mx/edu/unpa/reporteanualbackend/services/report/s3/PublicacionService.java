package mx.edu.unpa.reporteanualbackend.services.report.s3;

import mx.edu.unpa.reporteanualbackend.dtos.report.s3.PublicacionRequestDTO;
import mx.edu.unpa.reporteanualbackend.dtos.report.s3.PublicacionResponseDTO;
import java.util.List;

public interface PublicacionService {
    PublicacionResponseDTO crear(Integer reporteId, PublicacionRequestDTO dto);
    List<PublicacionResponseDTO> obtenerPorReporte(Integer reporteId);
    PublicacionResponseDTO actualizar(Integer id, PublicacionRequestDTO dto);
    void eliminar(Integer id);
}