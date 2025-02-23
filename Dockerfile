FROM eclipse-temurin:23-jdk

WORKDIR /app

COPY . .

RUN ./mvnw clean package -DskipTests

CMD ["java", "-jar", "target/swift-api-0.0.1-SNAPSHOT.jar"]
