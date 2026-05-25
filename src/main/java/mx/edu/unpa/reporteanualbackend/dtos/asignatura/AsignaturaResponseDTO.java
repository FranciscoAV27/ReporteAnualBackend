package mx.edu.unpa.reporteanualbackend.dtos.asignatura;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AsignaturaResponseDTO {
    private Integer id;
    private Integer carreraId;
    private Integer semestre;
    private String nombre;
}