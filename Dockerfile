FROM azul/zulu-openjdk-alpine:17-latest

EXPOSE 5500

COPY target/BankService-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]