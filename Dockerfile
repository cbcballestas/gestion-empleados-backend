#### CREACION DEL JAR ####
FROM maven:3-openjdk-17-slim AS builder

WORKDIR /evaluacion
COPY ./src ./src
COPY ./pom.xml .
RUN mvn -e -B -f /evaluacion/pom.xml -DskipTests clean package

#### FASE FINAL DE LA IMAGEN ####
FROM openjdk:17-slim

WORKDIR /workspace

COPY --from=builder /evaluacion/target/evaluacion-*.jar evaluacion.jar

ENTRYPOINT ["java","-jar","/workspace/evaluacion.jar"]