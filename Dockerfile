FROM openjdk:8

RUN sh -c 'touch /app.jar'

CMD java -jar thyme-boot.jar
