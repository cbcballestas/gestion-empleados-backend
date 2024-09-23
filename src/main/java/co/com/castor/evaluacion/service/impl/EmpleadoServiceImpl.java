package co.com.castor.evaluacion.service.impl;

import co.com.castor.evaluacion.dto.EmpleadoDTO;
import co.com.castor.evaluacion.exception.ModelNotFoundException;
import co.com.castor.evaluacion.helper.CloudinaryHelper;
import co.com.castor.evaluacion.mapper.CargoMapper;
import co.com.castor.evaluacion.mapper.EmpleadoMapper;
import co.com.castor.evaluacion.model.EmpleadoEntity;
import co.com.castor.evaluacion.repository.EmpleadoRepository;
import co.com.castor.evaluacion.service.IEmpleadoService;
import co.com.castor.evaluacion.util.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmpleadoServiceImpl implements IEmpleadoService {

    private final EmpleadoRepository empleadoRepository;
    private final CloudinaryHelper cloudinaryHelper;
    private final EmpleadoMapper mapper;
    private final CargoMapper cargoMapper;

    /**
     * Método que se encarga de obtener todos los empleados registrados
     *
     * @return
     */
    public List<EmpleadoDTO> getEmployees() {
        return empleadoRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public EmpleadoDTO save(EmpleadoDTO dto) {
        // Se arma la data a guardar
        var employeeToSave = mapper.toDbo(dto);

        // Se guarda información en la tabla
        var savedEmployee = empleadoRepository.save(employeeToSave);

        return mapper.toDto(savedEmployee);
    }

    @Override
    public EmpleadoDTO update(EmpleadoDTO dto, Integer id) {
        EmpleadoEntity empleado = empleadoRepository.findById(id).orElseThrow(() -> new ModelNotFoundException(Constants.ID_NOT_FOUND + id));

        // Se actualizan los datos del empleado
        empleado.setCedula(dto.getCedula());
        empleado.setNombre(dto.getNombre());
        empleado.setFechaIngreso(dto.getFechaIngreso());
        empleado.setCargo(cargoMapper.toDbo(dto.getCargo()));

        var updatedEmployee = empleadoRepository.save(empleado);
        return mapper.toDto(updatedEmployee);
    }

    /**
     * Método que se encarga de obtener un registro por ID
     *
     * @param id Id empleado
     * @return
     */
    public EmpleadoDTO findEmployeeById(Integer id) {
        var empleado = empleadoRepository.findById(id).orElseThrow(() -> new ModelNotFoundException(Constants.ID_NOT_FOUND + id));
        return mapper.toDto(empleado);
    }

    /**
     * Método que se usa para eliminar un registro
     * @param id
     */
    public void delete(Integer id) throws IOException {
        var empleado = empleadoRepository.findById(id).orElseThrow(() -> new ModelNotFoundException(Constants.ID_NOT_FOUND + id));

        // Se elimina imagen anterior
        cloudinaryHelper.deleteFile(empleado.getFoto());

        empleadoRepository.deleteById(empleado.getEmpleadoId());
    }
}
