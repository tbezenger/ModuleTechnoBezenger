placé dans psqlDockerfile
	sudo docker build -t animaux-db . &&\
	sudo docker run --name db -it --rm animaux-db

placé dans ModuleTechno
	sudo docker build -t animaux-serveur . &&\
	sudo docker run -it --rm --name AnimauxServeur -p 9998:9998 --network=bridge --link db:db animaux-serveur

Installer le projet Android "MesAnimaux" sur un smartphone à l'aide d'android-studio par exemple et lancer l'application.

