package mx.edu.unpa.reporteanualbackend.dtos.report.s1;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class CursoImpartidoResponseDTO {
    private Integer id;
    private Integer reporteId;
    private Integer numeroCurso;
    private String carrera;
    private String asignatura;
    private Byte semestre;
    private String cicloEscolar;
    private Byte horasSemana;
    private Short numAlumnos;
}