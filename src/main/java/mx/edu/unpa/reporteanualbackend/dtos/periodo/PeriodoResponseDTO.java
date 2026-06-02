package mx.edu.unpa.reporteanualbackend.dtos.periodo;

import lombok.*;
import mx.edu.unpa.reporteanualbackend.entities.enums.EstadoPeriodo;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PeriodoResponseDTO {

    private Integer id;
    private Integer anio;
    private String  nombre; // "Reporte Anual 2025-2026" — calculado
    private LocalDate fechaApertura;
    private LocalDate fechaLimite;
    private EstadoPeriodo estado;
    private String instrucciones;
    private String creadoPorNombre;
    private String creadoPorApellidos;
    private LocalDateTime creadoEn;
    private LocalDateTime cerradoEn;
}