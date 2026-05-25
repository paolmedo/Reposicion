package PJfullstack.Reposicion.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table
@Data
public class Trabajador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true, nullable = false)
    private String rut;

    @NotBlank
    @Column(nullable = false)
    private String nombre;

    @NotBlank
    @Email
    @Column(nullable = false)
    private String correo;

    @NotBlank
    @Column(nullable = false)
    private String rol;

    @Min(value = 18, message = "La edad debe ser mayor a 18 años. ")
    @Max(value = 120, message = "La edad debe ser menor a 120 años. ")
    @Column(nullable = false)
    private int edad;

    @ManyToOne
    @JoinColumn(name = "turno_id")
    private TurnoTrabajador turno;
}