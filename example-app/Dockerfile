FROM openjdk:18
#FROM ubuntu
COPY target/spring-boot-2-rest-service-basic-0.0.1-SNAPSHOT.jar spring-boot-2-rest-service-basic-0.0.1-SNAPSHOT.jar

RUN mkdir -p /bin
RUN chmod -R 777 /bin

#RUN chown root:bind /usr/local/bin/argocd ; chmod g+rwx /usr/local/bin/argocd
COPY configs/*.sh /bin

EXPOSE 8084
#ENTRYPOINT ["java",  "-jar", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005", "/spring-boot-2-rest-service-basic-0.0.1-SNAPSHOT.jar"]
ENTRYPOINT ["sh", "/bin/start.sh"]