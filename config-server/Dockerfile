FROM openjdk:11-jdk-slim
RUN mkdir -p /data/heap-dumps
COPY ./build/libs/*.jar config-server.jar
COPY ./build/resources/main /resources
#CMD "/bin/bash"
CMD ["java", \
    "-XX:+HeapDumpOnOutOfMemoryError", \
    "-jar", \
    "/config-server.jar"]
EXPOSE 8888
