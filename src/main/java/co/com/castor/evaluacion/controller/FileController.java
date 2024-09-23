package co.com.castor.evaluacion.controller;

import co.com.castor.evaluacion.dto.EmpleadoDTO;
import co.com.castor.evaluacion.enums.FileEnum;
import co.com.castor.evaluacion.exception.CustomErrorResponse;
import co.com.castor.evaluacion.service.IFileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Tag(name = "Archivos", description = "Controlador para la gestión de archivos")
@RestController
@RequestMapping("/upload")
@RequiredArgsConstructor
public class FileController {

    private final IFileService fileService;

    @Parameter(
            name = "operation",
            description = "Operación a realizar, save( guardar imágen) ó update (actualizar imágen)",
            examples = {
                    @ExampleObject(
                            name = "save",
                            summary = "save",
                            description = "Guarda imágen en cloudinary y actualiza el path en la base de datos"
                    ),
                    @ExampleObject(
                            name = "update",
                            summary = "update",
                            description = "Actualiza imágen en cloudinary y actualiza el path en la base de datos"
                    )
            }
    )
    @Parameter(name = "id", description = "ID de empleado")
    @Operation(
            summary = "Subir/Actualizar imágenes",
            description = "Realiza la gestión de imágenes a cloudinary y actualiza el path en la base de datos."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación éxitosa"),
            @ApiResponse(responseCode = "404", description = "Empleado NO encontrado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class)) }),
            @ApiResponse(responseCode = "500", description = "Error",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class)) })
    })
    @PostMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<EmpleadoDTO> handlePhoto(@Valid @PathVariable("id") Integer id, @RequestParam String operation,
                                                   @RequestPart("file") MultipartFile file) throws IOException {

        EmpleadoDTO empleadoDTO = null;

        if (operation.toLowerCase().equals(FileEnum.SAVE.getOperation())) {
            empleadoDTO = fileService.guardarFoto(id, file);
        }
        if (operation.toLowerCase().equals(FileEnum.UPDATE.getOperation())) {
            empleadoDTO = fileService.actualizarFoto(id, file);
        }

        return ResponseEntity.ok().body(empleadoDTO);
    }
}
