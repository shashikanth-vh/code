FROM openjdk:18
#FROM ubuntu
COPY target/spring-boot-2-rest-service-basic-0.0.1-SNAPSHOT.jar spring-boot-2-rest-service-basic-0.0.1-SNAPSHOT.jar

#RUN apt update && \
#      apt install -y curl && \
#      curl -LO https://storage.googleapis.com/kubernetes-release/release/`curl -s https://storage.googleapis.com/kubernetes-release/release/stable.txt`/bin/linux/amd64/kubectl && \
#      chmod +x ./kubectl && \
#      mv ./kubectl /usr/local/bin/kubectl
#CMD kubectl get po

#RUN apt install -y openjdk-18-jdk
#RUN apt install -y curl
RUN curl -sSL -o /usr/local/bin/argocd https://github.com/argoproj/argo-cd/releases/latest/download/argocd-linux-amd64
#RUN chmod +x /usr/local/bin/argocd
RUN mkdir -p /bin
RUN chmod -R 777 /bin

#RUN chown root:bind /usr/local/bin/argocd ; chmod g+rwx /usr/local/bin/argocd
COPY configs/*.sh /bin

EXPOSE 8082
CMD chmod +x /usr/local/bin/argocd
#ENTRYPOINT ["java",  "-jar", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005", "/spring-boot-2-rest-service-basic-0.0.1-SNAPSHOT.jar"]
ENTRYPOINT ["sh", "/bin/start.sh"]