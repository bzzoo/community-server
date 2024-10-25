FROM openjdk:17-jdk-slim
COPY ./core/core-api/build/libs/*.jar app.jar

ENV LOCAL_DB_NAME=community
ENV LOCAL_DB_USERNAME=root
ENV LOCAL_DB_PASSWORD=root

ENV KAKAO_OAUTH_CLIENT_ID=0bb8b2467b95230fe1f9b959758428b1
ENV KAKAO_OAUTH_CLIENT_SECRET=BihBWJYa4dMxrrVNeXaLKrcnsjWtmrN7

ENTRYPOINT ["java", "-Dspring.profiles.active=local-dev", "-jar", "/app.jar"]

# 기본 포트 설정
EXPOSE 8080
