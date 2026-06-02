package mx.edu.unpa.reporteanualbackend.services;

import mx.edu.unpa.reporteanualbackend.dtos.periodo.PeriodoRequestDTO;
import mx.edu.unpa.reporteanualbackend.dtos.periodo.PeriodoResponseDTO;
import mx.edu.unpa.reporteanualbackend.entities.Usuario;

import java.util.Optional;

public interface PeriodoService {
    PeriodoResponseDTO iniciar(PeriodoRequestDTO dto, Usuario creadoPor);
    PeriodoResponseDTO editar(Integer id, PeriodoRequestDTO dto);
    PeriodoResponseDTO cerrar(Integer id);
    Optional<PeriodoResponseDTO> obtenerActivo();
}