FROM openjdk:11-jdk-slim
RUN mkdir -p /data/heap-dumps
COPY ./build/libs/*.jar business.jar
COPY ./build/resources/main /resources
#CMD "/bin/bash"
CMD ["java", \
    "-XX:+HeapDumpOnOutOfMemoryError", \
    "-jar", \
    "/business.jar"]
EXPOSE 8088
