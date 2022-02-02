
FROM openjdk:11
LABEL maitainer="SelimHorri"
RUN mkdir -p /home/app
WORKDIR /home/app
ARG APP_VERSION=0.0.1
ENV SPRING_PROFILES_ACTIVE test
ENV SPRING_DATASOURCE_URL mysql
ENV SPRING_ELASTICSEARCH_URI elasticsearch
COPY target/*.jar .
EXPOSE 8400
ADD target/cita-backend-app-v${APP_VERSION}.jar cita-backend-app.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}", "-jar", "cita-backend-app.jar"]




