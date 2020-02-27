FROM openjdk:8

WORKDIR /thyme-boot

ADD thyme-boot-0.0.1-SNAPSHOT.jar thyme-boot.jar

CMD java -jar thyme-boot.jar
