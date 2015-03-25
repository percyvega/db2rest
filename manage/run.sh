#!/bin/bash

cd ..

nohup /opt/bea/bea1033/jdk1.6.0_20/bin/java \
-Ddb2rest \
-jar target/db2rest-1.0-SNAPSHOT.jar &
