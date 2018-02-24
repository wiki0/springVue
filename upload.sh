#!/bin/bash
mvn clean package
sshpass -p 1234qwer scp target/proxzone-service-web-0.0.1-SNAPSHOT.jar root@192.168.254.233:/tmp
sshpass -p 1234qwer ssh -o "StrictHostKeyChecking no" root@192.168.254.233 "export PATH=$PATH:/opt/jdk1.8.0_144/bin && ./start.sh"
