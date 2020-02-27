FROM openjdk:8

WORKDIR /var/jenkins_home/workspace/web

CMD java -jar thyme-boot.jar
