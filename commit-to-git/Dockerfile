#FROM openjdk:18
FROM ubuntu
#FROM centos
COPY target/spring-boot-2-rest-service-basic-0.0.1-SNAPSHOT.jar spring-boot-2-rest-service-basic-0.0.1-SNAPSHOT.jar

#RUN apt update && \
#      apt -y install git  && \
#      apt -y install openjdk-18-jdk

RUN  apt update &&  \
     apt -y install git  && \
      apt -y install openjdk-18-jdk

RUN mkdir -p /bin
RUN chmod -R 777 /bin

COPY configs/ /bin

EXPOSE 8085

#ENTRYPOINT ["java",  "-jar", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005", "/spring-boot-2-rest-service-basic-0.0.1-SNAPSHOT.jar"]
ENTRYPOINT ["sh", "/bin/start.sh"]
