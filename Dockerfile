FROM openjdk:8

WORKDIR /home/docker/jenkins/workspace/web

CMD java -jar thyme-boot.jar
