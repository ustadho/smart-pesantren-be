FROM maven:3-jdk-8

WORKDIR /smart-pesantren-be
COPY . .
RUN mvn clean install

CMD mvn spring-boot:run
