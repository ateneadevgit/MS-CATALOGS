FROM openjdk:17-oracle
RUN mkdir data-shared
COPY target/ms-catalogs.jar ms-catalogs.jar
EXPOSE 8014
ENTRYPOINT ["java","-jar","/ms-catalogs.jar"]