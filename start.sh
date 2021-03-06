#! /usr/bin/env bash

#fn start -d

FNSERVER_IP=$(docker inspect --type container -f '{{.NetworkSettings.IPAddress}}' fnserver)

echo -n "Starting fnproject/flow:latest > "
docker run --rm -d \
      -p 8081:8081 \
      -e API_URL="http://$FNSERVER_IP:8080/invoke" \
      -e no_proxy=$FNSERVER_IP \
      --name flowserver \
      fnproject/flow:latest

FLOWSERVER_IP=$(docker inspect --type container -f '{{.NetworkSettings.IPAddress}}' flowserver)

echo -n "Starting tteggel/bristol > "
docker run --rm \
       -p 3000:3000 \
       -p 3001:3001 \
       -d \
       --name bristol \
       tteggel/bristol

echo -n "Starting delabassee/ddflowui > "
docker run --rm \
       -p 3002:3000 \
       -d \
       --name dflowui \
       -e API_URL=http://$FNSERVER_IP:8080 \
       -e COMPLETER_BASE_URL=http://$FLOWSERVER_IP:8081 \
       delabassee/ddflowui
#       fnproject/flow:ui

#docker run --rm \
#       -p 4000:4000 \
#       -d \
#       --name fnui \
#       -e FN_API_URL=http://$FNSERVER_IP:8080 \
#       fnproject/ui
