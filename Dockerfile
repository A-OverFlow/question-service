FROM amazoncorretto:21

ARG JAR_FILE=build/libs/question-service-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} question-service.jar

ENTRYPOINT ["java","-jar","/question-service.jar"]