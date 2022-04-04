
##################### BASE IMAGE #####################
FROM openjdk:11

##################### MAITAINER #####################
LABEL maintainer="SelimHorri"

##################### CMNDLINE ##################### 
RUN mkdir -p /home/app

##################### BASE DIR #####################
WORKDIR /home/app

##################### ARGS #####################
ARG APP_VERSION "-1"
ARG SPRING_MAIL_PASSWORD

##################### ENVS #####################
ENV APP_SPRING_DATASOURCE_HOST mysql
ENV APP_SPRING_ELASTICSEARCH_HOST elasticsearch
ENV APP_SPRING_REDIS_HOST redis
ENV APP_SPRING_ZIPKIN_BASE_HOST zipkin
ENV APP_SPRING_MAIL_PASSWORD ${SPRING_MAIL_PASSWORD}

ENV APP_SPRING_PROFILES_ACTIVE staging
ENV APP_MYSQL_DATABASE cita_${APP_SPRING_PROFILES_ACTIVE}_db

##################### RUNNABLE #####################
COPY target/*.jar .

##################### EXPOSED PORT #####################
EXPOSE 8400

##################### RENAME #####################
ADD target/cita-backend-app-v${APP_VERSION}.jar cita-backend-app.jar

##################### ENTRYPOINT EXECUTION #####################
ENTRYPOINT ["java", "-DSPRING_PROFILES_ACTIVE=${APP_SPRING_PROFILES_ACTIVE}", \ 
					"-DSPRING_MAIL_PASSWORD=${APP_SPRING_MAIL_PASSWORD}", \
					"-DSPRING_DATASOURCE_HOST=${APP_SPRING_DATASOURCE_HOST}", \
					"-DMYSQL_DATABASE=${APP_MYSQL_DATABASE}", \
					"-DSPRING_ZIPKIN_BASE_HOST=${APP_SPRING_ZIPKIN_BASE_HOST}", \
					#"-Dspring.elasticsearch.uris[0]=http://${SPRING_ELASTICSEARCH_HOST}:9200/", \
					"-DSPRING_REDIS_HOST=${APP_SPRING_REDIS_HOST}", \
					"-jar", "cita-backend-app.jar"]





