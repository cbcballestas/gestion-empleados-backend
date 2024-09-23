package co.com.castor.evaluacion.controller;

import co.com.castor.evaluacion.dto.EmpleadoDTO;
import co.com.castor.evaluacion.exception.CustomErrorResponse;
import co.com.castor.evaluacion.service.IEmpleadoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@Tag(name = "Empleado", description = "Controlador de empleados")
@RestController
@RequestMapping("/empleados")
@RequiredArgsConstructor
public class EmpleadoController {

    private final IEmpleadoService empleadoService;

    @Operation(
            summary = "Obtener empleados",
            description = "Obtiene todos los empleados de la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación éxitosa")
    })
    @GetMapping
    public ResponseEntity<List<EmpleadoDTO>> findAll() {
        return ResponseEntity.ok(empleadoService.getEmployees());
    }

    @Operation(
            summary = "Obtener empleado",
            description = "Obtiene empleado por ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación éxitosa"),
            @ApiResponse(responseCode = "404", description = "Empleado NO encontrado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class)) }),
            @ApiResponse(responseCode = "500", description = "Error",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class)) })
    })
    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoDTO> findById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(empleadoService.findEmployeeById(id));
    }

    @Operation(
            summary = "Guardar empleado",
            description = "Guarda los datos de un empleado en la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación éxitosa")
    })
    @PostMapping
    public ResponseEntity<EmpleadoDTO> save(@Valid @RequestBody EmpleadoDTO dto) {
        EmpleadoDTO obj = empleadoService.save(dto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getEmpleadoId()).toUri();

        return ResponseEntity.created(location).body(obj);
    }

    @Operation(
            summary = "Actualizar empleado",
            description = "Actualiza los datos de un empleado en la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación éxitosa"),
            @ApiResponse(responseCode = "404", description = "Empleado NO encontrado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class)) }),
            @ApiResponse(responseCode = "500", description = "Error",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class)) })
    })
    @PutMapping(value = "/{id}")
    public ResponseEntity<EmpleadoDTO> update(@Valid @RequestBody EmpleadoDTO dto, @PathVariable("id") Integer id) {
        EmpleadoDTO obj = empleadoService.update(dto,id);

        return ResponseEntity.ok(obj);
    }

    @Operation(
            summary = "Borrar empleado",
            description = "Realiza el borrado de un empleado en la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación éxitosa"),
            @ApiResponse(responseCode = "404", description = "Empleado NO encontrado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class)) }),
            @ApiResponse(responseCode = "500", description = "Error",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class)) })
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws IOException {
        empleadoService.delete(id);
        return ResponseEntity.noContent().build(); //204 NO CONTENT
    }
}
