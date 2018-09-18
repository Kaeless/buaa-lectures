#!/bin/bash
script_path=$(cd `dirname $0`;pwd)
echo "script_path=$script_path"
cd ${script_path}

export GOPATH=$GOPATH:${PWD}
go run src/client/proto_client.go