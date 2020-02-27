FROM openjdk:8

RUN sh -c 'touch /thyme-boot.jar'

CMD java -jar thyme-boot.jar
