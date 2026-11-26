
# Etapa 1: Compilación de la aplicación
FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

# Copiar archivos necesarios para Maven
COPY pom.xml .
COPY src ./src

# Compilar el proyecto sin ejecutar pruebas
RUN mvn clean package -DskipTests

# Etapa 2: Imagen final para ejecutar la aplicación
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Crear usuario sin privilegios de administrador
RUN addgroup -S appgroup && adduser -S appuser -G appgroup
USER appuser

# Copiar el archivo JAR generado
COPY --from=build /app/target/*.jar app.jar

# Puerto utilizado por Render
ENV PORT=10000
EXPOSE 10000

# Ejecutar Spring Boot utilizando el puerto de Render
ENTRYPOINT ["sh", "-c", "java -Dserver.address=0.0.0.0 -Dserver.port=${PORT:-10000} -jar app.jar"]
