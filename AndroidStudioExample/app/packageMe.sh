#/bin/bash

mvn package

pushd ../
mkdir -p ./builds
popd

cd target
cp  client-sdk-0.198.230901.jar ../../builds/client-sdk-0.198.230901.jar
