# Use a imagem oficial do Amazon Corretto 21 para compilar o projeto
FROM amazoncorretto:21 AS build

# Instalar tar e gzip
RUN yum install -y tar gzip

WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests

# Use a imagem oficial do Amazon Corretto 21 para rodar o projeto
FROM amazoncorretto:21
WORKDIR /app
COPY --from=build /app/target/marketplace-0.0.1-SNAPSHOT.jar marketplace.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "marketplace.jar"]
