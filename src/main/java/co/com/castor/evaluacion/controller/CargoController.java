package co.com.castor.evaluacion.controller;

import co.com.castor.evaluacion.exception.CustomErrorResponse;
import co.com.castor.evaluacion.model.CargoEntity;
import co.com.castor.evaluacion.service.ICargoService;
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

@Tag(name = "Cargo", description = "Controlador para la gestión de cargos")
@RestController
@RequestMapping("/cargos")
@RequiredArgsConstructor
public class CargoController {

    private final ICargoService cargoService;

    @Operation(
            summary = "Obtener cargos",
            description = "Obtiene todos los cargos de la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación éxitosa")
    })
    @GetMapping
    public ResponseEntity<List<CargoEntity>> findAll() {
        return ResponseEntity.ok(cargoService.findAll());
    }


    @Operation(
            summary = "Obtener cargo",
            description = "Obtiene cargo por ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación éxitosa"),
            @ApiResponse(responseCode = "404", description = "Cargo NO encontrado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class)) }),
            @ApiResponse(responseCode = "500", description = "Error",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class)) })
    })
    @GetMapping("/{id}")
    public ResponseEntity<CargoEntity> findById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(cargoService.findById(id));
    }

    @Operation(
            summary = "Guardar cargo",
            description = "Guarda cargo en la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación éxitosa"),
            @ApiResponse(responseCode = "500", description = "Error",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class)) })
    })
    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody CargoEntity dto) {
        CargoEntity obj = cargoService.save(dto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdCargo()).toUri();

        return ResponseEntity.created(location).build();
    }

    @Operation(
            summary = "Actualizar cargo",
            description = "Actualiza los datos de un cargo en la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación éxitosa"),
            @ApiResponse(responseCode = "404", description = "Cargo NO encontrado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class)) }),
            @ApiResponse(responseCode = "500", description = "Error",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class)) })
    })
    @PutMapping(value = "/{id}")
    public ResponseEntity<CargoEntity> update(@Valid @RequestBody CargoEntity dto, @PathVariable("id") Integer id) {
        CargoEntity obj = cargoService.update(id, dto);

        return ResponseEntity.ok(obj);
    }

    @Operation(
            summary = "Borrar cargo",
            description = "Realiza el borrado de un cargo en la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación éxitosa"),
            @ApiResponse(responseCode = "404", description = "Cargo NO encontrado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class)) }),
            @ApiResponse(responseCode = "500", description = "Error",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class)) })
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws IOException {
        cargoService.delete(id);
        return ResponseEntity.noContent().build(); //204 NO CONTENT
    }
}
