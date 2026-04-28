package mx.edu.unpa.reporteanualbackend.dtos.report.s3;

import jakarta.validation.constraints.*;
import lombok.*;
import mx.edu.unpa.reporteanualbackend.entities.enums.FasePublicacion;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PublicacionRequestDTO {
    @NotNull
    private Integer numPublicacion;

    @NotBlank
    private String titulo;

    @NotBlank @Size(max = 300)
    private String revista;

    @NotNull
    private FasePublicacion fase;

    @NotNull @Min(2000) @Max(2100)
    private Integer anio;
}