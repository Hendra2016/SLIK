FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY target/*.war app.war

EXPOSE 8083

ENTRYPOINT ["java","-jar","app.war"]