#!/bin/bash
script_path=$(cd `dirname $0`;pwd)
echo "script_path=$script_path"
cd ${script_path}
cd ..
## proto file path
protoDir=$(pwd)/proto
## go pb path
goProtoDir=${script_path}/org_feuyeux_given_proto
echo "protoDir=${protoDir}"
echo "goProtoDir=${goProtoDir}"

cd ${script_path}
if [ ! -d "${goProtoDir}" ]; then
  mkdir -p ${goProtoDir}
else
  cd ${goProtoDir}
  rm -f *
  cd ${baseDir}
fi

protoc \
--proto_path=${protoDir} \
--go_out=plugins=grpc:${goProtoDir} \
${protoDir}/landing.proto