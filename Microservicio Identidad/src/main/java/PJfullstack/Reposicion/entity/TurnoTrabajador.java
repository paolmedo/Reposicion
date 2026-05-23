package PJfullstack.Reposicion.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalTime;

@Entity
@Table
@Data

public class TurnoTrabajador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column
    private String tipoTurno;

    @NotNull
    @Column(nullable = false)
    private LocalTime horaInicio;

    @NotNull
    @Column(nullable = false)
    private LocalTime horaTermino;

}