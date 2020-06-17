FROM java:8
MAINTAINER pwd <www.dingpengwei@foxmail.com>
VOLUME /tmp
ADD ms-web-registry-0.0.1-SNAPSHOT.jar app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-jar","/app.jar"]