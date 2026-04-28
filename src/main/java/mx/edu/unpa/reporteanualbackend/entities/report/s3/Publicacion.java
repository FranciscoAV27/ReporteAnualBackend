package mx.edu.unpa.reporteanualbackend.entities.report.s3;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import mx.edu.unpa.reporteanualbackend.entities.enums.FasePublicacion;
import mx.edu.unpa.reporteanualbackend.entities.report.Reporte;

@Entity
@Table(name = "publicaciones")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Publicacion {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reporte_id", nullable = false)
    private Reporte reporte;

    @NotNull
    @Column(name = "num_publicacion", nullable = false)
    private Integer numPublicacion;

    @NotBlank
    @Column(nullable = false, columnDefinition = "TEXT")
    private String titulo;

    @NotBlank @Size(max = 300)
    @Column(nullable = false, length = 300)
    private String revista;

    @NotNull @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 1)
    private FasePublicacion fase;

    @NotNull
    @Column(nullable = false)
    private Integer anio;
}