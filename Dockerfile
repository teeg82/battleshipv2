FROM openjdk:18-slim-buster

RUN mkdir -p /usr/share/man/man1

RUN apt-get update && apt-get install -y ant

RUN mkdir -p /battleship/src && mkdir -p /battleship/bin

WORKDIR /battleship

CMD "ant"
