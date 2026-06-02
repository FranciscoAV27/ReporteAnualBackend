package mx.edu.unpa.reporteanualbackend.dtos.periodo;

import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PeriodoRequestDTO {

    @NotNull
    private LocalDate fechaApertura;

    @NotNull
    private LocalDate fechaLimite;

    private String instrucciones;
}