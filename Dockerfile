FROM adoptopenjdk:11-jre-hotspot

ADD target\DogusAssignment-0.0.1-SNAPSHOT.jar docker-spring-boot.jar

EXPOSE 8090

ENTRYPOINT ["java","-jar","docker-spring-boot.jar"]

