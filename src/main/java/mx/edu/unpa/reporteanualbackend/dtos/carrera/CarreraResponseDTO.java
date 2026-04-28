package mx.edu.unpa.reporteanualbackend.dtos.carrera;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class CarreraResponseDTO {
    private Integer id;
    private String nombre;
    private String clave;
}