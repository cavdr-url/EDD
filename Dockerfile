# Compilar la aplicación
FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

# Crear la imagen final
FROM amazoncorretto:17-alpine-jdk

#COPY target/edd-0.0.1-SNAPSHOT.jar app.jar
COPY --from=build /target/edd-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT [ "java", "-jar", "/app.jar" ]