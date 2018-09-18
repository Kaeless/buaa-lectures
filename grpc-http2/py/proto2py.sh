#!/bin/bash
script_path=$(cd `dirname $0`;pwd)
echo "script_path=$script_path"
cd ${script_path}
cd ..
## proto file path
protoDir=$(pwd)/proto
## py pb path
pyProtoDir=${script_path}/pb
echo "protoDir=${protoDir}"

cd ${script_path}
if [ ! -d "${pyProtoDir}" ]; then
mkdir -p ${pyProtoDir}
else
cd ${pyProtoDir}
rm -f *
cd ${baseDir}
fi

## https://developers.google.com/protocol-buffers/docs/reference/python-generated
## *_pb2.py which contains our generated request and response classes
## *_pb2_grpc.py which contains our generated client and server classes.
python3 -m grpc.tools.protoc \
-I ${protoDir} \
--python_out=${pyProtoDir} \
--grpc_python_out=${pyProtoDir} \
${protoDir}/landing.proto