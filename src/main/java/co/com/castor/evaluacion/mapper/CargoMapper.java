package co.com.castor.evaluacion.mapper;

import co.com.castor.evaluacion.dto.CargoDTO;
import co.com.castor.evaluacion.dto.EmpleadoDTO;
import co.com.castor.evaluacion.model.CargoEntity;
import co.com.castor.evaluacion.model.EmpleadoEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CargoMapper {

    @Mapping(source = "idCargo", target = "idCargo")
    @Mapping(source = "nombre", target = "nombre")
    CargoEntity toDbo(CargoDTO cargoDTO);

    @InheritInverseConfiguration
    CargoDTO toDto(CargoEntity entity);

}
