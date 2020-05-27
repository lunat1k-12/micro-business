FROM openjdk:11-jdk-slim
RUN mkdir -p /data/heap-dumps
COPY ./build/libs/*.jar client.jar
COPY ./repo/build/resources/main /resources
#CMD "/bin/bash"
CMD ["java", \
    "-XX:+HeapDumpOnOutOfMemoryError", \
    "-jar", \
    "/client.jar"]
EXPOSE 8081
