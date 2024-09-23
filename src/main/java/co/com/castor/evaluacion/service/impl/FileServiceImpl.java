package co.com.castor.evaluacion.service.impl;

import co.com.castor.evaluacion.dto.EmpleadoDTO;
import co.com.castor.evaluacion.exception.ModelNotFoundException;
import co.com.castor.evaluacion.helper.CloudinaryHelper;
import co.com.castor.evaluacion.mapper.EmpleadoMapper;
import co.com.castor.evaluacion.model.EmpleadoEntity;
import co.com.castor.evaluacion.repository.EmpleadoRepository;
import co.com.castor.evaluacion.service.IFileService;
import co.com.castor.evaluacion.util.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileServiceImpl implements IFileService {

    private final EmpleadoRepository empleadoRepository;
    private final CloudinaryHelper cloudinaryHelper;
    private final EmpleadoMapper mapper;


    /**
     * Método que se encarga de guardar la imágen en cloudinary y actualizar
     * el path en la tabla de empleados.
     *
     * @param idEmpleado    ID empleado a consultar
     * @param multipartFile Archivo a guardar
     * @throws IOException
     */
    @Override
    public EmpleadoDTO guardarFoto(Integer idEmpleado, MultipartFile multipartFile) throws IOException {

        // Se consulta el empleado a guardar ( en caso de que NO exista, lanza excepción)
        EmpleadoEntity employee = empleadoRepository.findById(idEmpleado).orElseThrow(() -> new ModelNotFoundException(Constants.ID_NOT_FOUND + idEmpleado));

        // Se guarda imágen en cloudinary y se obtiene path generado
        String imagePath = cloudinaryHelper.saveFile(multipartFile);

        // Se arma la data a guardar
        employee.setFoto(imagePath);

        // Se guarda información en la tabla
        var updatedEmployee = empleadoRepository.save(employee);

        return mapper.toDto(updatedEmployee);
    }

    /**
     * Método que se encarga de actualizar la foto en cloudinary y actualizar
     * el path en la tabla empleados.
     *
     * @param idEmpleado    ID Empleado
     * @param multipartFile Archivo a actualizar
     * @throws IOException
     */
    @Override
    public EmpleadoDTO actualizarFoto(Integer idEmpleado, MultipartFile multipartFile) throws IOException {

        // Se consulta el empleado a guardar ( en caso de que NO exista, lanza excepción)
        EmpleadoEntity employee = empleadoRepository.findById(idEmpleado).orElseThrow(() -> new ModelNotFoundException(Constants.ID_NOT_FOUND + idEmpleado));

        // Se elimina imagen anterior en cloudinary

        log.info("Se inicia borrado de foto anterior: {}", employee.getFoto());

        cloudinaryHelper.deleteFile(employee.getFoto());

        // Se guarda imágen en cloudinary y se obtiene path generado
        String imagePath = cloudinaryHelper.saveFile(multipartFile);

        // Se actualiza la foto del empleado en la base de datos
        employee.setFoto(imagePath);
        var updatedEmployee = empleadoRepository.save(employee);

        return mapper.toDto(updatedEmployee);
    }
}
