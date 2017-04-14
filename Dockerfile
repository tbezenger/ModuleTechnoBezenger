FROM maven:3.2-jdk-8

EXPOSE 9998

COPY . /usr/src/proj/
	
RUN \
	cd /usr/src/proj/AnimalsClasses &&\
	mvn install &&\
	cd /usr/src/proj/AnimauxServeur &&\
	mvn package -Dmaven.test.skip=true

WORKDIR /usr/src/proj/AnimauxServeur

CMD mvn exec:java
