@echo off
call mvn clean package
call docker build -t nu.te4/recepies .
call docker rm -f recepies
call docker run -d -p 9080:9080 -p 9443:9443 --name recepies nu.te4/recepies