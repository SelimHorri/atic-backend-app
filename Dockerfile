
##################### BASE IMAGE #####################
FROM openjdk:11

##################### MAITAINER #####################
LABEL maintainer="SelimHorri"

##################### CMNDLINE ##################### 
RUN mkdir -p /home/app

##################### BASE DIR #####################
WORKDIR /home/app

##################### ARGS #####################
ARG APP_VERSION=0.0.5

##################### ENVS #####################
ENV SPRING_DATASOURCE_URL mysql
ENV SPRING_ELASTICSEARCH_HOST elasticsearch
ENV SPRING_REDIS_HOST redis
ENV SPRING_ZIPKIN_BASE-URL zipkin

ENV SPRING_PROFILES_ACTIVE staging
ENV MYSQL_DATABASE cita_${SPRING_PROFILES_ACTIVE}_db

##################### RUNNABLE #####################
COPY target/*.jar .

##################### EXPOSED PORT #####################
EXPOSE 8400

##################### RENAME #####################
ADD target/cita-backend-app-v${APP_VERSION}.jar cita-backend-app.jar

##################### ENTRYPOINT EXECUTION #####################
ENTRYPOINT ["java", "-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}", \ 
					"-Dspring.datasource.url=jdbc:mysql://${SPRING_DATASOURCE_URL}:3306/${MYSQL_DATABASE}?createDatabaseIfNotExist=true", \
					"-Dspring.zipkin.base-url=http://${SPRING_ZIPKIN_BASE-URL}:9411/", \
					#"-Dspring.elasticsearch.uris[0]=http://${SPRING_ELASTICSEARCH_HOST}:9200/", \
					"-Dspring.redis.host=${SPRING_REDIS_HOST}", \
					"-jar", "cita-backend-app.jar"]




