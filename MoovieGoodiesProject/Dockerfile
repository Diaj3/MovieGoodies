FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/movie_goodies-0.0.1.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]

# sudo docker build -t movie_goodies .
# docker run -d -p 8000:8000 movie_goodies