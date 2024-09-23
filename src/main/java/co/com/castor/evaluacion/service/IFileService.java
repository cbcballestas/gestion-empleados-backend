package co.com.castor.evaluacion.service;

import co.com.castor.evaluacion.dto.EmpleadoDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IFileService {
    EmpleadoDTO guardarFoto(Integer idEmpleado, MultipartFile multipartFile) throws IOException;
    EmpleadoDTO actualizarFoto(Integer idEmpleado, MultipartFile multipartFile) throws IOException;
}
