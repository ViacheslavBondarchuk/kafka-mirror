FROM    gradle:jdk17-alpine AS builder
WORKDIR /app
COPY    build.gradle.kts build.gradle.kts
COPY    settings.gradle.kts settings.gradle.kts
COPY    .git .git
COPY     src/ src/
RUN gradle --no-daemon build --stacktrace

FROM openjdk:17-alpine
WORKDIR /app
RUN apk --no-cache add curl
COPY --from=builder /app/build/libs/*.jar /kafka-mirror.jar
CMD ["java", "-jar", "-Dspring.profile.active=default", "kafka-mirror.jar"]
