package mx.edu.unpa.reporteanualbackend.dtos.report.s1;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ProductoApoyoDocenteRequestDTO {
    @NotNull
    private Integer numeroCurso;

    @NotBlank
    private String descripcion;

    @Size(max = 500)
    private String enlace;
}