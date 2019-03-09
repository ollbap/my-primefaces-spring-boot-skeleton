#!/bin/bash 
mvn clean install sonar:sonar \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.login=3f9dcf7a88375977cab495b7678ac9aef04467e8
