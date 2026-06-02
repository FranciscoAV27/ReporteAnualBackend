package mx.edu.unpa.reporteanualbackend.entities;

import jakarta.persistence.*;
import lombok.*;
import mx.edu.unpa.reporteanualbackend.entities.enums.EstadoPeriodo;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "periodos")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Periodo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer anio; // año de cierre del ciclo (ej: 2026 = ciclo 2025-2026)

    @Column(name = "fecha_apertura", nullable = false)
    private LocalDate fechaApertura;

    @Column(name = "fecha_limite", nullable = false)
    private LocalDate fechaLimite;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoPeriodo estado;

    @Column(columnDefinition = "TEXT")
    private String instrucciones;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creado_por_id")
    private Usuario creadoPor;

    @Column(name = "creado_en", nullable = false, updatable = false)
    private LocalDateTime creadoEn;

    @Column(name = "cerrado_en")
    private LocalDateTime cerradoEn;

    @PrePersist
    void prePersist() { this.creadoEn = LocalDateTime.now(); }

}