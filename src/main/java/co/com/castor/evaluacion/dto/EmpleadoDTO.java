package co.com.castor.evaluacion.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class EmpleadoDTO {

    @EqualsAndHashCode.Include
    private Integer empleadoId;
    @NotNull
    private String cedula;
    @NotNull
    private String nombre;
    @NotNull
    private LocalDate fechaIngreso;

    private String foto;
    @NotNull
    private CargoDTO cargo;
}
