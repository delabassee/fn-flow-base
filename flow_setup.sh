#!/bin/bash

echo -e "Fn Flow setup\n-------------"

countflow () {
   docker ps | grep fnproject/flow | wc -l
}

function setupFn {
   echo -e "fnproject/fnserver\n┗> $FNSERVER_IP\n"

   FLOWSERVER_CONTAINER=$(
   docker run --rm -d \
      -p 8081:8081 \
      -e API_URL="http://$FNSERVER_IP:8080/r" \
      -e no_proxy=$FNSERVER_IP \
      --name flowserver \
      fnproject/flow:latest
   )

   FLOWSERVER_IP=$(docker inspect --type container -f '{{.NetworkSettings.IPAddress}}' flowserver)

   echo -e "fnproject/flow:latest\n┣> $FLOWSERVER_CONTAINER\n┗> $FLOWSERVER_IP\n"

   FLOWUI_CONTAINER=$(
   docker run --rm -d \
      -p 3002:3000 \
      --name flowui \
      -e API_URL=http://$FNSERVER_IP:8080 \
      -e COMPLETER_BASE_URL=http://$FLOWSERVER_IP:8081 \
      fnproject/flow:ui
   )

	   echo -e "fnproject/flow:ui\n┗> $FLOWUI_CONTAINER\n"

   echo -e "Make sure to config your app to talk to the Flow server."
   echo -e "┗> fn config app myapp COMPLETER_BASE_URL "\""http://$FLOWSERVER_IP:8081"\""" 

}


if [ $(countflow) -gt 0 ]
   then
   echo "It seems some Flow related container(s) are already running. Stopping here..."
   exit 1
fi

FNSERVER_IP=$(docker inspect --type container -f '{{.NetworkSettings.IPAddress}}' fnserver)

if [ "$?" = "0" ]; then
	setupFn
else
   echo -e "Start Fn Server first!\n"
   exit 1
fi

