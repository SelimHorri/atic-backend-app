
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
ARG SPRING_MAIL_USERNAME cita.team.mail@gmail.com
ARG SPRING_MAIL_PASSWORD
ARG APP_SECURITY_JWT_SECRET_KEY

##################### ENVS #####################
ENV APP_SPRING_DATASOURCE_HOST mysql
ENV APP_SPRING_ELASTICSEARCH_HOST elasticsearch
ENV APP_SPRING_REDIS_HOST redis
ENV APP_SPRING_ZIPKIN_BASE_HOST zipkin
ENV APP_SPRING_MAIL_USERNAME ${SPRING_MAIL_USERNAME}
ENV APP_SPRING_MAIL_PASSWORD ${SPRING_MAIL_PASSWORD}
ENV APP_APP_SECURITY_JWT_SECRET_KEY ${APP_SECURITY_JWT_SECRET_KEY}

ENV APP_SPRING_PROFILES_ACTIVE staging
ENV APP_DB_NAME cita_${APP_SPRING_PROFILES_ACTIVE}_db

##################### RUNNABLE #####################
COPY target/*.jar .

##################### EXPOSED PORT #####################
EXPOSE 8400

##################### RENAME #####################
ADD target/cita-backend-app-v${APP_VERSION}.jar cita-backend-app.jar

##################### REMOVE #####################
RUN rm cita-backend-app-v${APP_VERSION}.jar

##################### ENTRYPOINT EXECUTION #####################
ENTRYPOINT ["java", "-DSPRING_PROFILES_ACTIVE=${APP_SPRING_PROFILES_ACTIVE}", \ 
					"-DAPP_SECURITY_JWT_SECRET_KEY=${APP_APP_SECURITY_JWT_SECRET_KEY}", \
					"-DSPRING_MAIL_USERNAME=${APP_SPRING_MAIL_USERNAME}", \ 
					"-DSPRING_MAIL_PASSWORD=${APP_SPRING_MAIL_PASSWORD}", \ 
					"-DSPRING_DATASOURCE_HOST=${APP_SPRING_DATASOURCE_HOST}", \
					"-DDB_NAME=${APP_DB_NAME}", \
					"-DSPRING_ZIPKIN_BASE_HOST=${APP_SPRING_ZIPKIN_BASE_HOST}", \
					#"-Dspring.elasticsearch.uris[0]=http://${SPRING_ELASTICSEARCH_HOST}:9200/", \
					"-DSPRING_REDIS_HOST=${APP_SPRING_REDIS_HOST}", \
					"-jar", "cita-backend-app.jar"]





