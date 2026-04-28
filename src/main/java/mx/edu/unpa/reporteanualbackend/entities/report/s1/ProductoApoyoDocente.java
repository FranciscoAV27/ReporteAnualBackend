package mx.edu.unpa.reporteanualbackend.entities.report.s1;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import mx.edu.unpa.reporteanualbackend.entities.report.Reporte;

@Entity
@Table(name = "productos_apoyo_docente")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ProductoApoyoDocente {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reporte_id", nullable = false)
    private Reporte reporte;

    @NotNull
    @Column(name = "numero_curso", nullable = false)
    private Integer numeroCurso;

    @NotBlank
    @Column(nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    @Size(max = 500)
    @Column(length = 500)
    private String enlace;
}