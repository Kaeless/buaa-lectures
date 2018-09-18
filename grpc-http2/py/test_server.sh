#!/bin/bash
script_path=$(cd `dirname $0`;pwd)
echo "script_path=$script_path"
cd ${script_path}
python3 server/protoServer.py