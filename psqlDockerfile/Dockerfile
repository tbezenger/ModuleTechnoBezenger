FROM ubuntu

EXPOSE 5432

RUN apt-get -y update && apt-get install -y postgresql

USER postgres

#VOLUME /var/lib/postgresql/9.5/data

RUN \
	/etc/init.d/postgresql start &&\
	psql -c "create user Animaux;" &&\
	psql -c "alter user Animaux with encrypted password 'password';" &&\
	psql -c "alter role Animaux with createdb;" &&\
	psql -c "create database Animaux owner Animaux;"

COPY pg_hba.conf /etc/postgresql/9.5/main/
COPY postgresql.conf /etc/postgresql/9.5/main/


CMD	/etc/init.d/postgresql start && bash
