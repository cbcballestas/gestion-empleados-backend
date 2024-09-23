package co.com.castor.evaluacion.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "CRUD Empleados",
                version = "1.0.0",
                description = "CRUD para la gestión de empleados"
        )
)
public class OpenApiConfig {
}
