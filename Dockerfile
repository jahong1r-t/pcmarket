# 1. Используем JDK для сборки
FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

# 2. Копируем Maven wrapper и pom.xml
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# 3. Скачиваем зависимости (чтобы кэшировать)
RUN ./mvnw dependency:go-offline

# 4. Копируем исходники и собираем jar
COPY src src
RUN ./mvnw clean package -DskipTests

# 5. Второй этап — минимальный образ для запуска
FROM eclipse-temurin:17-jre
WORKDIR /app

# 6. Копируем jar из предыдущего этапа
COPY --from=build /app/target/*.jar app.jar

# 7. Запуск приложения
ENTRYPOINT ["java", "-jar", "app.jar"]
