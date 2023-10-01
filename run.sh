#!/bin/bash


if [ -d "jdk-21" ]
then
    echo "JDK 20 detected :D"
else
    echo "Well, we need to download latest java. Sorry"
    wget https://download.java.net/java/GA/jdk21/fd2272bbf8e04c3dbaee13770090416c/35/GPL/openjdk-21_linux-x64_bin.tar.gz

    tar xf openjdk*.tar.gz
    rm -rf openjdk*.tar.gz
fi

export JAVA_HOME=jdk-21
./mvnw test
