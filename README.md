# Grupo-C1A-022019

* ***Master*** branch: [![Build Status](https://travis-ci.com/dapp-unq/Grupo-C1A-022019.svg?branch=master)](https://travis-ci.com/dapp-unq/Grupo-C1A-022019)

* ***Develop*** branch: [![Build Status](https://travis-ci.com/dapp-unq/Grupo-C1A-022019.svg?branch=develop)](https://travis-ci.com/dapp-unq/Grupo-C1A-022019)

* ***Develop*** branch: [![Codacy Badge](https://api.codacy.com/project/badge/Grade/fb761d40fa5648da821e3df58da760b6)](https://www.codacy.com/manual/LizaChambi/Grupo-C1A-022019?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=dapp-unq/Grupo-C1A-022019&amp;utm_campaign=Badge_Grade)

## HOW TO RUN CONNECTING TO MARIADB

1.  Build the application with mariadb profile `mvn clean install -Pmariadb`

2.  Run the application giving the following JVM arguments: `-Dspring.profiles.active=mariadb`

The application also can be started via console connecting to MariaDB database with the following command:

`mvn spring-boot:run -Dspring.profiles.active=mariadb`