
# ---- Etapa 1: Compilar ----
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copiar pom.xml y descargar dependencias primero (aprovecha cache de Docker)
COPY pom.xml .
RUN mvn dependency:go-offline -q

# Copiar el resto del código y compilar
COPY src ./src
RUN mvn clean package -DskipTests -q

# ---- Etapa 2: Ejecutar ----
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copiar el JAR generado
COPY --from=build /app/target/agencia-viajes-1.0.0.jar app.jar

# Puerto que expone la app
EXPOSE 8080

# Arrancar
ENTRYPOINT ["java", "-jar", "app.jar"]