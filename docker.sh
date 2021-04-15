#!/bin/bash

echo "Building ui docker image..." &&
cd ../ui &&
docker build . -t rb/room-ui &&
echo "Built ui docker image..." &&

echo "Building api docker image..." &&
cd ../api &&
docker build . -t rb/room-api &&
echo "Built api docker image..." &&


cd ../ &&
echo "Running docker-compose up..." &&
docker-compose up -d