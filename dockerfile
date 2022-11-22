FROM mcr.microsoft.com/openjdk/jdk:11-mariner
EXPOSE 8080
ARG JAR=spring-petclinic-2.6.0-SNAPSHOT.jar
COPY target/$JAR /app.jar
ENTRYPOINT ["java","-XX:MaxRAMPercentage=75","-jar","/app.jar"]
## testing 10
