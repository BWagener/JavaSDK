#/bin/bash

mvn package

pushd ../
mkdir -p ./builds
popd

cd target
cp  combo-sdk-0.136.210830.jar ../../builds/combo-sdk-0.136.210830.jar
