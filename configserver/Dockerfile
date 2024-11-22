FROM amazoncorretto:21 AS build
LABEL maintainer="truonglq"
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf /app.jar)

FROM amazoncorretto:21

RUN yum install -y openssh-clients
RUN mkdir -p /root/.ssh && chmod 700 /root/.ssh
RUN echo "$SSH_KEY" > /root/.ssh/config_key && chmod 600 /root/.ssh/config_key
RUN ssh-keyscan github.com >> /root/.ssh/known_hosts && chmod 644 /root/.ssh/known_hosts

VOLUME /tmp
ARG DEPENDENCY=/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

ENTRYPOINT ["java","-cp","app:app/lib/*","com.truonglq.configserver.ConfigserverApplication"]