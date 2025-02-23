FROM amazoncorretto:17

WORKDIR /app

RUN yum install -y tar gzip

COPY . .

RUN ./mvnw clean package -DskipTests

CMD ["java", "-jar", "target/swift-api-0.0.1-SNAPSHOT.jar"]
