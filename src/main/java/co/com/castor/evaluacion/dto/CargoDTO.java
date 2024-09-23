package co.com.castor.evaluacion.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CargoDTO {

    @EqualsAndHashCode.Include
    private Integer idCargo;
    @NotNull
    private String nombre;
}
