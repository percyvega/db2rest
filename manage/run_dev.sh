#!/bin/bash

. setVars_dev.sh
if [ -z "$APP_NAME" ]
then
    echo "$APP_NAME cannot be empty."
    exit
fi

cd ..

java \
-D$APP_NAME \
-jar target/$APP_NAME-1.0-SNAPSHOT.jar \
    $spring_datasource_url \
    $spring_datasource_username \
    $spring_datasource_password
