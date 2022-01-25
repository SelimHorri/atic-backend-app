
FROM openjdk:11
RUN mkdir -p /home/app
WORKDIR /home/app
ARG APP_VERSION=0.0.1
ENV SPRING_PROFILES_ACTIVE dev
COPY . .
EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}", "-jar", "target/cita-backend-app-v${APP_VERSION}.jar"]
