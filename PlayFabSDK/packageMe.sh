#/bin/bash

mvn package

pushd ../
mkdir -p ./builds
popd

cd target
cp  combo-sdk-0.171.220907.jar ../../builds/combo-sdk-0.171.220907.jar
