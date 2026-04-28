package mx.edu.unpa.reporteanualbackend.dtos.carrera;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class CarreraRequestDTO {
    @NotBlank @Size(max = 150)
    private String nombre;

    @NotBlank @Size(max = 20)
    private String clave;
}