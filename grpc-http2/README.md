## grpc demo

### java

```shell
baseDir=$(cd `dirname .` && pwd -P)
cd ${baseDir}
cd java
mvn exec:java -Dexec.mainClass="org.feuyeux.given.proto.server.ProtoServer" -f pom.xml
```

```shell
baseDir=$(cd `dirname .` && pwd -P)
cd ${baseDir}
cd java
mvn exec:java -Dexec.mainClass="org.feuyeux.given.proto.client.ProtoClient" -f pom.xml
```

### go

#### setup
```shell
$ go get google.golang.org/grpc
$ go get -u github.com/golang/protobuf/{proto,protoc-gen-go}
$ export PATH=$PATH:$GOPATH/bin
```

#### generate
```shell
go/proto2go.sh
```

#### test
```shell
go/test_server.sh
```

```shell
go/test_client.sh
```

### python
#### setup
```shell
sudo python3 -m pip install grpcio --ignore-installed
sudo python3 -m pip install grpcio-tools
```

#### generate
```shell
py/proto2py.sh
```

#### test
```shell
python3 server/protoServer.py
```

```shell
python3 client/protoClient.py
```