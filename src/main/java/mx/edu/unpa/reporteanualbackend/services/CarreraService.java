package mx.edu.unpa.reporteanualbackend.services;

import mx.edu.unpa.reporteanualbackend.dtos.carrera.CarreraRequestDTO;
import mx.edu.unpa.reporteanualbackend.dtos.carrera.CarreraResponseDTO;
import java.util.List;

public interface CarreraService {
    CarreraResponseDTO crear(CarreraRequestDTO dto);
    CarreraResponseDTO obtenerPorId(Integer id);
    List<CarreraResponseDTO> obtenerTodas();
    CarreraResponseDTO actualizar(Integer id, CarreraRequestDTO dto);
    void eliminar(Integer id);
}