package co.com.castor.evaluacion.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cargos")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class CargoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cargo_seq")
    @SequenceGenerator(name = "cargo_seq", sequenceName = "cargo_seq", allocationSize = 1)
    @EqualsAndHashCode.Include
    @Column(name = "id_cargo")
    private Integer idCargo;

    @Column(nullable = false, length = 20)
    private String nombre;
}
