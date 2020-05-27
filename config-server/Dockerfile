FROM openjdk:11-jdk-slim as BUILD-BASE
COPY . /repo
RUN cd /repo && ./gradlew clean build && ls -la /repo/build/libs/

FROM openjdk:11-jdk-slim
RUN mkdir -p /data/heap-dumps
COPY --from=BUILD-BASE /repo/build/libs/*.jar config-server.jar
COPY --from=BUILD-BASE /repo/build/resources/main /resources
#CMD "/bin/bash"
CMD ["java", \
    "-XX:+HeapDumpOnOutOfMemoryError", \
    "-jar", \
    "/config-server.jar"]
EXPOSE 8088
