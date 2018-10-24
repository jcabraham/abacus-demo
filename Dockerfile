
FROM openjdk:8-jdk-alpine


LABEL maintainer="jcabraham@gmail.com"


VOLUME /tmp


EXPOSE 8080


ARG JAR_FILE=build/libs/jca-abacus-demo-0.1.0.jar

# Add the application's jar to the container
ADD ${JAR_FILE} jca-abacus-demo-0.1.0.jar

# Run the jar file 
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/jca-abacus-demo-0.1.0.jar"]
