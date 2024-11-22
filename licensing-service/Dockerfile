# Multistage build
# Stage 1
#FROM amazoncorretto:21 AS build
#WORKDIR application
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} application.jar
#RUN java -Djarmode=layertools -jar application.jar extract

# Stage 2
#FROM amazoncorretto:21
#WORKDIR application
#COPY --from=build application/dependencies/ ./
#COPY --from=build application/spring-boot-loader/ ./
#COPY --from=build application/snapshot-dependencies/ ./
#COPY --from=build application/application/ ./
#ENTRYPOINT ["java","org.springframework.boot.loader.launch.JarLauncher"]
# JarLauncher is located in com/springframework/boot/loader/launch, used to execute the application!

# Multistage build
# Stage 1
FROM amazoncorretto:21 as build
LABEL maintainer="truonglq"
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf /app.jar)

# Stage 2
FROM amazoncorretto:21
VOLUME /tmp
ARG DEPENDENCY=/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","/app:app/lib/*","com.truonglq.licensingservice.LicensingServiceApplication"]

#FROM amazoncorretto:21
#LABEL maintainer="truonglq"
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} licensing-service-0.0.1-SNAPSHOT.jar
#EXPOSE 8080
#ENTRYPOINT ["java","-jar","licensing-service-0.0.1-SNAPSHOT.jar"]
