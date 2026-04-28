package mx.edu.unpa.reporteanualbackend.services.report.s1;

import mx.edu.unpa.reporteanualbackend.dtos.report.s1.ProductoApoyoDocenteRequestDTO;
import mx.edu.unpa.reporteanualbackend.dtos.report.s1.ProductoApoyoDocenteResponseDTO;
import java.util.List;

public interface ProductoApoyoDocenteService {
    ProductoApoyoDocenteResponseDTO crear(Integer reporteId, ProductoApoyoDocenteRequestDTO dto);
    List<ProductoApoyoDocenteResponseDTO> obtenerPorReporte(Integer reporteId);
    ProductoApoyoDocenteResponseDTO actualizar(Integer id, ProductoApoyoDocenteRequestDTO dto);
    void eliminar(Integer id);
}