# ==========================================
# STAGE 1: Builder (Extracting Layers)
# ==========================================
FROM openjdk:17-jdk-slim as builder
WORKDIR /app

# Jenkins S3 నుండి డౌన్‌లోడ్ చేసిన app.jar ని కాపీ చేస్తుంది
COPY app.jar app.jar

# Spring Boot లోని లేయర్స్ ని (dependencies, application code) విడదీస్తుంది
RUN java -Djarmode=layertools -jar app.jar extract


# ==========================================
# STAGE 2: Final Image
# ==========================================
FROM openjdk:17-jdk-slim
WORKDIR /app

# Stage 1 లో విడదీసిన లేయర్స్ ని ఒక పద్ధతి ప్రకారం ఇక్కడ కాపీ చేస్తున్నాం
# 1. డిపెండెన్సీలు చాలా తక్కువగా మారుతాయి, కాబట్టి ఇవి క్యాష్ (Cache) అవుతాయి.
COPY --from=builder /app/dependencies/ ./
COPY --from=builder /app/spring-boot-loader/ ./
COPY --from=builder /app/snapshot-dependencies/ ./

# 2. అప్లికేషన్ కోడ్ తరచుగా మారుతుంది, కాబట్టి ఇది మాత్రమే రీ-బిల్డ్ అవుతుంది (ఫాస్ట్ బిల్డ్).
COPY --from=builder /app/application/ ./

EXPOSE 8080

# Layered Jar ని రన్ చేయడానికి Spring Boot ఇచ్చే స్పెషల్ లాంచర్ కమాండ్
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]