FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY target/*.war app.war

EXPOSE 8083

ENV JAVA_TOOL_OPTIONS="--add-opens=java.base/java.lang=ALL-UNNAMED"

ENTRYPOINT ["java","-jar","app.war"]