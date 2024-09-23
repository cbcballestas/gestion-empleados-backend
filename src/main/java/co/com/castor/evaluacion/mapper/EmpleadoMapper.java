package co.com.castor.evaluacion.mapper;

import co.com.castor.evaluacion.dto.EmpleadoDTO;
import co.com.castor.evaluacion.model.EmpleadoEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmpleadoMapper {

    @Mapping(source = "empleadoId", target = "empleadoId")
    @Mapping(source = "cedula", target = "cedula")
    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "fechaIngreso", target = "fechaIngreso")
    @Mapping(source = "foto", target = "foto")
    EmpleadoEntity toDbo(EmpleadoDTO empleadoDTO);

    @InheritInverseConfiguration
    EmpleadoDTO toDto(EmpleadoEntity entity);

}
