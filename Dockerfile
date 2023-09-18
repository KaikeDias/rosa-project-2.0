FROM openjdk:17-oracle AS build
COPY . .
RUN gradle build

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /build/libs/rosa-0.0.1-SNAPSHOT.jar rosa.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","rosa.jar"]