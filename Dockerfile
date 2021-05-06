FROM maven:3.8.1-openjdk-16
WORKDIR /tp
COPY drivers /tp/drivers
COPY src /tp/src
COPY pom.xml /tp
CMD ["mvn"]
