#/bin/bash

mvn package

pushd ../
mkdir -p ./builds
popd

cd target
cp  server-sdk-0.127.210208.jar ../../builds/server-sdk-0.127.210208.jar
