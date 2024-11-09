# Gestión empleados BACKEND
Backend para gestión de empleados usando: Spring Boot 3, Cloudinary ( para gestión de imágenes) y PostgreSQL como motor
de base de datos.

## Requirements
- [JDK 17](https://adoptium.net/es/?variant=openjdk17)
- [Maven 3](https://maven.apache.org/)
- PostgreSQL

## Swagger
Para acceder a la documentación realizada con Swagger, se debe entrar al siguiente endpoint:
``` http request
http://localhost:8081/api/v1/swagger-ui
```
## Aspectos a tener en cuenta ⚠

- En la carpeta `db` se encuentra un archivo llamado `data.sql` donde se encuentra un `INSERT` para agregar los cargos
  en la tabla, se recomienda ejecutar éste script cuando se creen las tablas en la base de datos. Los cargos también se
  pueden agregar por su enpoint correspondiente.
- En caso de probar con una nueva API_KEY ( generada desde cloudinary), se debe crear una carpeta llamada `uploads` y
  cambiar los siguientes valores en el archivo `application.yml`

``` yaml
cloudinary:
  cloud-name: [NUEVO VALOR]
  api_key: [NUEVO VALOR]
  api_secret: [NUEVO VALOR]
  upload-folder: uploads/
```

You will need to set up your database (or create your own) with the following configuration
``` yaml
server:
  servlet:
    context-path: /api/v1
  port: 8081
spring:
  datasource:
    url: jdbc:postgresql://${POSTGRESQL_HOST:localhost}:${POSTGRESQL_PORT:5433}/${POSTGRESQL_DATABASE:evaluacion_db}
    username: ${POSTGRESQL_USERNAME:postgres}
    password: ${POSTGRESQL_PASSWORD:123456}
    hikari:
      connection-timeout: 20000
      maximum-pool-size: 5
    driver-class-name: org.postgresql.Driver
  jpa:
    properties:
      hibernate:
        format_sql: true
    hibernate:
      ddl-auto: update
    database-platform: org.hibernate.dialect.PostgreSQLDialect


cloudinary:
  cloud-name:
  api_key:
  api_secret:
  upload-folder: uploads/

logging:
  level:
    org:
      hibernate:
        SQL: DEBUG
        type:
          descriptor:
            sql:
              BasicBinder: TRACE
springdoc:
  swagger-ui:
    path: /swagger-ui
  api-docs:
    path: /api-docs

```

## Run application

There are several ways to run a Spring Boot application in your local machine. The most common way is executing `main` method in `co.com.castor.evaluacion.EvaluacionApplication` class from your IDE

Alternative you can use [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/maven-plugin/reference/htmlsingle), just running:

```shell
mvn spring-boot:run
```
