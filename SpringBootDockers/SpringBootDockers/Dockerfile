#image
FROM eclipse-temurin:17

LABEL mantainer="chittirajachari.k@gmail.com"

WORKDIR /app

COPY target/SpringBootDockers-0.0.1-SNAPSHOT.jar /app/SpringBootDockers.jar

ENTRYPOINT ["java", "-jar","SpringBootDockers.jar"]