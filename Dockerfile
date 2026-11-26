# Etapa 1: Compilación de la aplicación
FROM eclipse-temurin:21-jdk-alpine AS builder
WORKDIR /app

# Copiar archivos de Maven Wrapper y dependencias
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline -B

# Copiar código fuente y compilar omitiendo pruebas para velocidad
COPY src ./src
RUN ./mvnw clean package -DskipTests -B

# Etapa 2: Imagen final liviana para ejecución
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Crear usuario no root para seguridad
RUN addgroup -S appgroup && adduser -S appuser -G appgroup
USER appuser

# Copiar el jar compilado
COPY --from=builder /app/target/*.jar app.jar

# Variables de entorno por defecto
ENV PORT=8080
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
