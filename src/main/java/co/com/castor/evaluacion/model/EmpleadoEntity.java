package co.com.castor.evaluacion.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "empleados")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class EmpleadoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "empleado_seq")
    @SequenceGenerator(name = "empleado_seq", sequenceName = "empleado_seq", allocationSize = 1)
    @EqualsAndHashCode.Include
    @Column(name = "empleado_id")
    private Integer empleadoId;

    @Column(nullable = false, length = 20)
    private String cedula;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, name = "fecha_ingreso")
    private LocalDate fechaIngreso;

    @Column(length = 255)
    private String foto;

    @ManyToOne
    @JoinColumn(name = "id_cargo", nullable = false, foreignKey = @ForeignKey(name = "FK_EMPLEADO_CARGO"))
    private CargoEntity cargo;
}
