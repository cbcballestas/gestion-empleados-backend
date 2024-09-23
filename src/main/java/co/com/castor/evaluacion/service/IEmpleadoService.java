package co.com.castor.evaluacion.service;

import co.com.castor.evaluacion.dto.EmpleadoDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IEmpleadoService {
    EmpleadoDTO findEmployeeById(Integer id);

    List<EmpleadoDTO> getEmployees();

    EmpleadoDTO save(EmpleadoDTO dto);

    EmpleadoDTO update(EmpleadoDTO dto, Integer id);

    void delete(Integer id) throws IOException;

}
