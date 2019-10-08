FROM java:8-jdk-alpine
COPY target/photoproject-0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app
ENTRYPOINT ["java","-jar","photoproject-0.0.1-SNAPSHOT.jar"]