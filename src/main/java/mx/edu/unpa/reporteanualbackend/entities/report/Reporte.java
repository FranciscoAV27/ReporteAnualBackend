package mx.edu.unpa.reporteanualbackend.entities.report;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import mx.edu.unpa.reporteanualbackend.entities.Usuario;
import mx.edu.unpa.reporteanualbackend.entities.enums.EstadoReporte;

import java.time.LocalDateTime;

@Entity
@Table(name = "reportes",
        uniqueConstraints = @UniqueConstraint(columnNames = {"profesor_id", "anio"}))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Reporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profesor_id", nullable = false)
    private Usuario profesor;

    @NotNull
    @Column(nullable = false)
    private Integer anio;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private EstadoReporte estado = EstadoReporte.BORRADOR;

    @Column(name = "comentarios_admin", columnDefinition = "TEXT")
    private String comentariosAdmin;

    @Column(name = "problemas_docencia", columnDefinition = "TEXT")
    private String problemasDocencia;

    @Column(name = "oportunidades_docencia", columnDefinition = "TEXT")
    private String oportunidadesDocencia;

    @Column(name = "problemas_investigacion", columnDefinition = "TEXT")
    private String problemasInvestigacion;

    @Column(name = "oportunidades_investigacion", columnDefinition = "TEXT")
    private String oportunidadesInvestigacion;

    @Column(name = "comentarios_generales", columnDefinition = "TEXT")
    private String comentariosGenerales;

    @Column(name = "creado_en", updatable = false)
    private LocalDateTime creadoEn;

    @Column(name = "actualizado_en")
    private LocalDateTime actualizadoEn;

    @Column(name = "enviado_en")
    private LocalDateTime enviadoEn;

    @Column(name = "aprobado_en")
    private LocalDateTime aprobadoEn;

    @Column(name = "seccion1_concluida", nullable = false)
    private Boolean seccion1Concluida = false;

    @Column(name = "seccion2_concluida", nullable = false)
    private Boolean seccion2Concluida = false;

    @Column(name = "seccion3_concluida", nullable = false)
    private Boolean seccion3Concluida = false;

    @Column(name = "seccion4_concluida", nullable = false)
    private Boolean seccion4Concluida = false;

    @Column(name = "seccion5_concluida", nullable = false)
    private Boolean seccion5Concluida = false;

    @Column(name = "seccion6_concluida", nullable = false)
    private Boolean seccion6Concluida = false;

    @Column(name = "seccion7_concluida", nullable = false)
    private Boolean seccion7Concluida = false;

    @PrePersist
    protected void onCreate() {
        this.creadoEn = LocalDateTime.now();
        this.actualizadoEn = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.actualizadoEn = LocalDateTime.now();
    }
}