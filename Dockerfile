#DOCKERHUB IMAGE
FROM eclipse-temurin:17-jdk

EXPOSE 8080

WORKDIR /root

COPY ./pom.xml /root
COPY ./.mvn /root/.mvn
COPY ./mvnw /root
RUN chmod +x ./mvnw

RUN ./mvnw dependency:go-offline

COPY ./src /root/src

RUN ./mvnw clean install -DskipTests

ENTRYPOINT [ "java","-jar","/root/target/eclesiasticasbackend-0.0.1-SNAPSHOT.jar" ]
