#!/bin/sh
mvn clean package && docker build -t nu.te4/recepies .
docker rm -f recepies || true && docker run -d -p 9080:9080 -p 9443:9443 --name recepies nu.te4/recepies