package mx.edu.unpa.reporteanualbackend.dtos.report;

// Usado por el admin para aprobar o rechazar un reporte
import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ValidacionRequestDTO {
    @NotNull
    private Boolean aprobado;

    // Obligatorio si aprobado = false
    private String comentariosAdmin;
}