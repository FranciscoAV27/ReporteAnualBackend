package mx.edu.unpa.reporteanualbackend.services.report.s7;

import mx.edu.unpa.reporteanualbackend.dtos.report.s7.DistribucionTiempoRequestDTO;
import mx.edu.unpa.reporteanualbackend.dtos.report.s7.DistribucionTiempoResponseDTO;
import java.util.List;

public interface DistribucionTiempoService {
    DistribucionTiempoResponseDTO crear(Integer reporteId, DistribucionTiempoRequestDTO dto);
    List<DistribucionTiempoResponseDTO> obtenerPorReporte(Integer reporteId);
    DistribucionTiempoResponseDTO actualizar(Integer id, DistribucionTiempoRequestDTO dto);
    void eliminar(Integer id);
}
