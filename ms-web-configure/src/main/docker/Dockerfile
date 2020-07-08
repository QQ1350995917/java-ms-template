FROM java:8
MAINTAINER pwd <www.dingpengwei@foxmail.com>
VOLUME /tmp
ADD lib /lib
ADD config /config
ADD ms-web-configure-0.0.1-SNAPSHOT.jar /app.jar
RUN bash -c 'touch /app.jar'
#设置变量
ENV JAVA_OPTS=""
ENV BOOTSTRAP=""
ENV APPLICATION=""
WORKDIR /
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar app.jar --spring.config.location=$APPLICATION --spring.cloud.bootstrap.location=$BOOTSTRAP"]
EXPOSE 11220
