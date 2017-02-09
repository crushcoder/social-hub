FROM maven:3.3.9-jdk-8

RUN mkdir /prj
COPY src /prj/src
COPY pom.xml /prj/

WORKDIR /prj
RUN mvn install -DskipTests=true

WORKDIR /prj/target

CMD ["java", "-jar", "social-hub-1.0.0-SNAPSHOT.jar"]