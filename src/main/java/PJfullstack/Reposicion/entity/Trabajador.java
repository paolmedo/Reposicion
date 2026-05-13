package PJfullstack.Reposicion.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class Trabajador {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trabajador_seq")
    @SequenceGenerator(name = "trabajador_seq", sequenceName = "TRABAJADOR_SEQ", allocationSize = 1)
    private Long id;
    @Column(unique = true, nullable = false)
    private String rut;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String correo;
    @Column(nullable = false)
    private String rol;
    @Column(nullable = false)
    private int edad;


}
