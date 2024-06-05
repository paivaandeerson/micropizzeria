# Use a imagem oficial do Amazon Corretto 17 para compilar o projeto
FROM amazoncorretto:21 AS build

# Instalar tar e gzip
RUN yum install -y tar gzip

WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests

# Use a imagem oficial do Amazon Corretto 17 para rodar o projeto
FROM amazoncorretto:17
WORKDIR /app
COPY --from=build /app/target/kitchen-worker-0.0.1-SNAPSHOT.jar kitchen-worker.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "kitchen-worker.jar"]
